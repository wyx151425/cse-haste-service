package com.cse.haste.service.impl;

import com.cse.haste.context.HasteException;
import com.cse.haste.model.pojo.*;
import com.cse.haste.repository.*;
import com.cse.haste.service.EvaluateeService;
import com.cse.haste.service.EvaluationGroupService;
import com.cse.haste.service.EvaluationPlanService;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;
import com.cse.haste.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "planService")
public class EvaluationPlanServiceImpl implements EvaluationPlanService {

    private final EvaluationPlanRepository evaluationPlanRepository;
    private final EvaluationGroupService evaluationGroupService;
    private final EvaluateeService evaluateeService;

    @Autowired
    public EvaluationPlanServiceImpl(EvaluationPlanRepository evaluationPlanRepository, EvaluationGroupService evaluationGroupService, EvaluateeService evaluateeService) {
        this.evaluationPlanRepository = evaluationPlanRepository;
        this.evaluationGroupService = evaluationGroupService;
        this.evaluateeService = evaluateeService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EvaluationPlan saveEvaluationPlan(EvaluationPlan evaluationPlan) {
        evaluationPlan.setObjectId(GeneratorUtil.getObjectId());
        evaluationPlan.setStatus(Constant.Status.ENABLED);
        evaluationPlan.setStage(Constant.EvaluationPlan.Stages.INITIALIZE);
        evaluationPlanRepository.save(evaluationPlan);

        if (Constant.EvaluationPlan.Types.LEADERSHIP_EVALUATION_PLAN == evaluationPlan.getType()) {
            User user = new User();
            user.setName("领导班子");
            Evaluatee evaluatee = Evaluatee.newInstance();
            evaluatee.setUser(user);
            evaluatee.setEvaluationPlan(evaluationPlan);
            evaluateeService.saveEvaluatee(evaluatee);
        }

        return evaluationPlanRepository.findOneById(evaluationPlan.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEvaluationPlan(Integer id) {
        evaluationGroupService.deleteEvaluationGroupsByEvaluationPlan(id);
        evaluationPlanRepository.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EvaluationPlan startEvaluationPlan(Integer id) {
        EvaluationPlan evaluationPlan = evaluationPlanRepository.findOneById(id);
        evaluationPlan.setStage(Constant.EvaluationPlan.Stages.STARTED);
        evaluationPlan.setStartAt(LocalDateTime.now().withNano(0));
        evaluationPlanRepository.update(evaluationPlan);
        evaluationGroupService.initializeEvaluationGroupsByEvaluationPlan(evaluationPlan);
        return evaluationPlanRepository.findOneById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EvaluationPlan submitEvaluationPlan(Integer id) {
        List<EvaluationGroup> evaluationGroups = evaluationGroupService.findIncompleteEvaluationGroupsByEvaluationPlan(id);
        if (evaluationGroups.size() > 0) {
            throw new HasteException(StatusCode.OPERATION_FAILED);
        }
        EvaluationPlan evaluationPlan = evaluationPlanRepository.findOneById(id);
        evaluationPlan.setStage(Constant.EvaluationPlan.Stages.COMPLETED);
        evaluationPlan.setCompleteAt(LocalDateTime.now().withNano(0));
        evaluationPlanRepository.update(evaluationPlan);
        return evaluationPlanRepository.findOneById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<EvaluationPlan> findEvaluationPlans() {
        return evaluationPlanRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public EvaluationPlan findEvaluationPlanById(Integer id) {
        return evaluationPlanRepository.findOneById(id);
    }
}
