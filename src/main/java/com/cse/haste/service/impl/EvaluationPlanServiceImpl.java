package com.cse.haste.service.impl;

import com.cse.haste.model.pojo.*;
import com.cse.haste.repository.*;
import com.cse.haste.service.EvaluationGroupService;
import com.cse.haste.service.EvaluationPlanService;
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
@Service(value = "planService")
public class EvaluationPlanServiceImpl implements EvaluationPlanService {

    private final EvaluationPlanRepository evaluationPlanRepository;
    private final EvaluationGroupService evaluationGroupService;

    @Autowired
    public EvaluationPlanServiceImpl(EvaluationPlanRepository evaluationPlanRepository, EvaluationGroupService evaluationGroupService) {
        this.evaluationPlanRepository = evaluationPlanRepository;
        this.evaluationGroupService = evaluationGroupService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EvaluationPlan saveEvaluationPlan(EvaluationPlan evaluationPlan) {
        evaluationPlan.setObjectId(GeneratorUtil.getObjectId());
        evaluationPlan.setStatus(Constant.Status.ENABLED);
        evaluationPlan.setStage(Constant.EvaluationPlan.Stages.INITIALIZE);
        evaluationPlanRepository.save(evaluationPlan);
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
