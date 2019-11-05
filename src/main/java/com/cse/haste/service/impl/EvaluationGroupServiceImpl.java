package com.cse.haste.service.impl;

import com.cse.haste.model.pojo.*;
import com.cse.haste.repository.EvaluationGroupRepository;
import com.cse.haste.service.*;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "evaluationGroupService")
public class EvaluationGroupServiceImpl implements EvaluationGroupService {

    private final EvaluationGroupRepository evaluationGroupRepository;
    private final EvaluateeService evaluateeService;
    private final EvaluatorService evaluatorService;
    private final LeadershipScoreFormService leadershipScoreFormService;
    private final LeaderCadreScoreFormService leaderCadreScoreFormService;
    private final ProfessionalScoreFormService professionalScoreFormService;

    @Autowired
    public EvaluationGroupServiceImpl(EvaluationGroupRepository evaluationGroupRepository, EvaluateeService evaluateeService, EvaluatorService evaluatorService, LeadershipScoreFormService leadershipScoreFormService, LeaderCadreScoreFormService leaderCadreScoreFormService, ProfessionalScoreFormService professionalScoreFormService) {
        this.evaluationGroupRepository = evaluationGroupRepository;
        this.evaluateeService = evaluateeService;
        this.evaluatorService = evaluatorService;
        this.leadershipScoreFormService = leadershipScoreFormService;
        this.leaderCadreScoreFormService = leaderCadreScoreFormService;
        this.professionalScoreFormService = professionalScoreFormService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EvaluationGroup saveEvaluationGroup(EvaluationGroup evaluationGroup) {
        evaluationGroup.setObjectId(GeneratorUtil.getObjectId());
        evaluationGroup.setStatus(Constant.Status.ENABLED);
        evaluationGroup.setComplete(false);
        evaluationGroupRepository.save(evaluationGroup);
        return evaluationGroupRepository.findOneById(evaluationGroup.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void initializeEvaluationGroupsByEvaluationPlan(EvaluationPlan evaluationPlan) {
        List<EvaluationGroup> evaluationGroups = evaluationGroupRepository.findAllByEvaluationPlanId(evaluationPlan.getId());
        for (EvaluationGroup evaluationGroup : evaluationGroups) {
            List<Evaluatee> evaluatees = evaluateeService.findEvaluateesByEvaluationGroup(evaluationGroup.getId());
            List<Evaluator> evaluators = evaluatorService.findEvaluatorsByEvaluationGroup(evaluationGroup.getId());
            if (Constant.EvaluationPlan.Types.LEADERSHIP_EVALUATION_PLAN == evaluationPlan.getType()) {
                for (Evaluator evaluator : evaluators) {
                    LeadershipScoreForm leadershipScoreForm = LeadershipScoreForm.newInstance();
                    leadershipScoreForm.setType(Constant.EvaluationPlan.Types.LEADERSHIP_EVALUATION_PLAN);
                    leadershipScoreForm.setEvaluationGroup(evaluationGroup);
                    leadershipScoreForm.setEvaluator(evaluator);
                    leadershipScoreFormService.saveLeadershipScoreForm(leadershipScoreForm);
                }
            } else {
                for (Evaluatee evaluatee : evaluatees) {
                    for (Evaluator evaluator : evaluators) {
                        if (Constant.EvaluationPlan.Types.LEADER_CADRE_EVALUATION_PLAN == evaluationPlan.getType()) {
                            LeaderCadreScoreForm leaderCadreScoreForm = LeaderCadreScoreForm.newInstance();
                            leaderCadreScoreForm.setType(Constant.EvaluationPlan.Types.LEADER_CADRE_EVALUATION_PLAN);
                            leaderCadreScoreForm.setEvaluationGroup(evaluationGroup);
                            leaderCadreScoreForm.setEvaluatee(evaluatee);
                            leaderCadreScoreForm.setEvaluator(evaluator);
                            leaderCadreScoreFormService.saveLeaderCadreScoreForm(leaderCadreScoreForm);
                        }
                        if (Constant.EvaluationPlan.Types.PROFESSIONAL_EVALUATION_PLAN == evaluationPlan.getType()) {
                            ProfessionalScoreForm professionalScoreForm = ProfessionalScoreForm.newInstance();
                            professionalScoreForm.setType(Constant.EvaluationPlan.Types.PROFESSIONAL_EVALUATION_PLAN);
                            professionalScoreForm.setEvaluationGroup(evaluationGroup);
                            professionalScoreForm.setEvaluatee(evaluatee);
                            professionalScoreForm.setEvaluator(evaluator);
                            professionalScoreFormService.saveProfessionalScoreForm(professionalScoreForm);
                        }
                    }
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEvaluationGroup(Integer id) {
        evaluatorService.deleteEvaluatorsByEvaluationGroup(id);
        evaluateeService.deleteEvaluateesByEvaluationGroup(id);
        leadershipScoreFormService.deleteLeadershipScoreFormsByEvaluationGroup(id);
        leaderCadreScoreFormService.deleteLeaderCadreScoreFormsByEvaluationGroup(id);
        professionalScoreFormService.deleteProfessionalScoreFormsByEvaluationGroup(id);
        evaluationGroupRepository.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEvaluationGroupsByEvaluationPlan(Integer evaluationPlanId) {
        List<EvaluationGroup> evaluationGroups = evaluationGroupRepository.findAllByEvaluationPlanId(evaluationPlanId);
        for (EvaluationGroup evaluationGroup : evaluationGroups) {
            this.deleteEvaluationGroup(evaluationGroup.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public EvaluationGroup findEvaluationGroupById(Integer id) {
        return evaluationGroupRepository.findOneById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<EvaluationGroup> findEvaluationGroupsByEvaluationPlan(Integer evaluationPlanId) {
        return evaluationGroupRepository.findAllByEvaluationPlanId(evaluationPlanId);
    }
}
