package com.cse.haste.service.impl;

import com.cse.haste.model.pojo.LeaderCadreScoreForm;
import com.cse.haste.repository.LeaderCadreScoreFormRepository;
import com.cse.haste.service.LeaderCadreScoreFormService;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "leaderCadreScoreFormService")
public class LeaderCadreScoreFormServiceImpl implements LeaderCadreScoreFormService {

    private final LeaderCadreScoreFormRepository leaderCadreScoreFormRepository;

    @Autowired
    public LeaderCadreScoreFormServiceImpl(LeaderCadreScoreFormRepository leaderCadreScoreFormRepository) {
        this.leaderCadreScoreFormRepository = leaderCadreScoreFormRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLeaderCadreScoreForm(LeaderCadreScoreForm leaderCadreScoreForm) {
        leaderCadreScoreForm.setObjectId(GeneratorUtil.getObjectId());
        leaderCadreScoreForm.setStatus(Constant.Status.ENABLED);
        leaderCadreScoreForm.setComplete(false);
        leaderCadreScoreFormRepository.save(leaderCadreScoreForm);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteLeaderCadreScoreFormsByEvaluationGroup(Integer evaluationGroupId) {
        List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormRepository.findAllByEvaluationGroupId(evaluationGroupId);
        for (LeaderCadreScoreForm leaderCadreScoreForm : leaderCadreScoreForms) {
            leaderCadreScoreFormRepository.delete(leaderCadreScoreForm.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitLeaderCadreScoreForm(LeaderCadreScoreForm leaderCadreScoreForm) {
        LeaderCadreScoreForm target = leaderCadreScoreFormRepository.findOneById(leaderCadreScoreForm.getId());
        if (!target.getComplete()) {
            leaderCadreScoreForm.setComplete(true);
            leaderCadreScoreFormRepository.update(leaderCadreScoreForm);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<LeaderCadreScoreForm> findLeaderCadreScoreFormsByEvaluationGroup(Integer evaluationGroupId) {
        return leaderCadreScoreFormRepository.findAllByEvaluationGroupId(evaluationGroupId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<LeaderCadreScoreForm> findLeaderCadreScoreFormsByEvaluationGroupAndEvaluator(Integer evaluationGroupId, Integer evaluatorId) {
        return leaderCadreScoreFormRepository.findAllByEvaluationGroupIdAndEvaluatorId(evaluationGroupId, evaluatorId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public LeaderCadreScoreForm findLeaderCadreScoreFormById(Integer id) {
        return leaderCadreScoreFormRepository.findOneById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<LeaderCadreScoreForm> findLeaderCadreScoreFormsByEvaluator(Integer evaluatorId) {
        return leaderCadreScoreFormRepository.findAllByEvaluatorId(evaluatorId);
    }
}