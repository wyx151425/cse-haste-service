package com.cse.haste.service.impl;

import com.cse.haste.model.pojo.LeadershipScoreForm;
import com.cse.haste.repository.LeadershipScoreFormRepository;
import com.cse.haste.service.LeadershipScoreFormService;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "leadershipScoreFormService")
public class LeadershipScoreFormServiceImpl implements LeadershipScoreFormService {

    private final LeadershipScoreFormRepository leadershipScoreFormRepository;

    @Autowired
    public LeadershipScoreFormServiceImpl(LeadershipScoreFormRepository leadershipScoreFormRepository) {
        this.leadershipScoreFormRepository = leadershipScoreFormRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLeadershipScoreForm(LeadershipScoreForm leadershipScoreForm) {
        leadershipScoreForm.setObjectId(GeneratorUtil.getObjectId());
        leadershipScoreForm.setStatus(Constant.Status.ENABLED);
        leadershipScoreForm.setComplete(false);
        leadershipScoreFormRepository.save(leadershipScoreForm);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteLeadershipScoreFormsByEvaluationGroup(Integer evaluationGroupId) {
        List<LeadershipScoreForm> leadershipScoreForms = leadershipScoreFormRepository.findAllByEvaluationGroupId(evaluationGroupId);
        for (LeadershipScoreForm leadershipScoreForm : leadershipScoreForms) {
            leadershipScoreFormRepository.delete(leadershipScoreForm.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLeadershipScoreForm(LeadershipScoreForm leadershipScoreForm) {
        leadershipScoreForm.setComplete(false);
        leadershipScoreFormRepository.update(leadershipScoreForm);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LeadershipScoreForm submitLeadershipScoreForm(LeadershipScoreForm leadershipScoreForm) {
        LeadershipScoreForm target = leadershipScoreFormRepository.findOneById(leadershipScoreForm.getId());
        if (!target.getComplete()) {
            leadershipScoreForm.setComplete(true);
            leadershipScoreForm.setCompleteAt(LocalDateTime.now().withNano(0));
            leadershipScoreFormRepository.update(leadershipScoreForm);
        }
        return leadershipScoreFormRepository.findOneById(leadershipScoreForm.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<LeadershipScoreForm> findLeadershipScoreFormsByEvaluationGroup(Integer evaluationGroupId) {
        return leadershipScoreFormRepository.findAllByEvaluationGroupId(evaluationGroupId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<LeadershipScoreForm> findLeadershipScoreFormsByEvaluationGroupAndEvaluator(Integer evaluationGroupId, Integer evaluatorId) {
        return leadershipScoreFormRepository.findAllByEvaluationGroupIdAndEvaluatorId(evaluationGroupId, evaluatorId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public LeadershipScoreForm findLeadershipScoreFormById(Integer id) {
        return leadershipScoreFormRepository.findOneById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<LeadershipScoreForm> findLeadershipScoreFormsByEvaluator(Integer evaluatorId) {
        return leadershipScoreFormRepository.findAllByEvaluatorId(evaluatorId);
    }
}
