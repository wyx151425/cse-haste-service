package com.cse.haste.service.impl;

import com.cse.haste.model.pojo.EvaluationGroup;
import com.cse.haste.model.pojo.LeaderCadreScoreForm;
import com.cse.haste.repository.LeaderCadreScoreFormRepository;
import com.cse.haste.service.EvaluationGroupService;
import com.cse.haste.service.LeaderCadreScoreFormService;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "leaderCadreScoreFormService")
public class LeaderCadreScoreFormServiceImpl implements LeaderCadreScoreFormService {

    private final LeaderCadreScoreFormRepository leaderCadreScoreFormRepository;
    private EvaluationGroupService evaluationGroupService;

    @Autowired
    public LeaderCadreScoreFormServiceImpl(LeaderCadreScoreFormRepository leaderCadreScoreFormRepository) {
        this.leaderCadreScoreFormRepository = leaderCadreScoreFormRepository;
    }

    @Autowired
    @Lazy
    public void setEvaluationGroupService(EvaluationGroupService evaluationGroupService) {
        this.evaluationGroupService = evaluationGroupService;
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
    public void deleteLeaderCadreScoreFormsByEvaluator(Integer evaluatorId) {
        List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormRepository.findAllByEvaluatorId(evaluatorId);
        for (LeaderCadreScoreForm leaderCadreScoreForm : leaderCadreScoreForms) {
            leaderCadreScoreFormRepository.delete(leaderCadreScoreForm.getId());
        }
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
    public void updateLeaderCadreScoreForm(LeaderCadreScoreForm leaderCadreScoreForm) {
        leaderCadreScoreForm.setComplete(false);
        leaderCadreScoreFormRepository.update(leaderCadreScoreForm);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEvaluationGroupName(EvaluationGroup evaluationGroup) {
        leaderCadreScoreFormRepository.updateEvaluationGroupNameByEvaluationGroupId(evaluationGroup);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LeaderCadreScoreForm submitLeaderCadreScoreForm(LeaderCadreScoreForm leaderCadreScoreForm) {
        LeaderCadreScoreForm target = leaderCadreScoreFormRepository.findOneById(leaderCadreScoreForm.getId());
        if (!target.getComplete()) {
            leaderCadreScoreForm.setComplete(true);
            leaderCadreScoreForm.setCompleteAt(LocalDateTime.now().withNano(0));
            leaderCadreScoreFormRepository.update(leaderCadreScoreForm);
        }
        List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormRepository.findAllByEvaluationGroupIdAndComplete(leaderCadreScoreForm.getEvaluationGroupId(), false);
        if (leaderCadreScoreForms.size() == 0) {
            evaluationGroupService.submitEvaluationGroup(leaderCadreScoreForm.getEvaluationGroupId());
        }
        return leaderCadreScoreFormRepository.findOneById(leaderCadreScoreForm.getId());
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

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<LeaderCadreScoreForm> findLeaderCadreScoreFormsByEvaluationGroupAndEvaluatee(Integer evaluationGroupId, Integer evaluateeId) {
        return leaderCadreScoreFormRepository.findAllByEvaluationGroupIdAndEvaluateeId(evaluationGroupId, evaluateeId);
    }
}
