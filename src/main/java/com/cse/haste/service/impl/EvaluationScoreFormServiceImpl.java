package com.cse.haste.service.impl;

import com.cse.haste.model.dto.Excel;
import com.cse.haste.model.pojo.*;
import com.cse.haste.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "evaluationScoreFormService")
public class EvaluationScoreFormServiceImpl implements EvaluationScoreFormService {

    private final EvaluatorService evaluatorService;
    private final LeadershipScoreFormService leadershipScoreFormService;
    private final LeaderCadreScoreFormService leaderCadreScoreFormService;
    private final DepartmentCadreScoreFormService departmentCadreScoreFormService;
    private final ProfessionalScoreFormService professionalScoreFormService;

    @Autowired
    public EvaluationScoreFormServiceImpl(EvaluatorService evaluatorService, LeadershipScoreFormService leadershipScoreFormService, LeaderCadreScoreFormService leaderCadreScoreFormService, DepartmentCadreScoreFormService departmentCadreScoreFormService, ProfessionalScoreFormService professionalScoreFormService) {
        this.evaluatorService = evaluatorService;
        this.leadershipScoreFormService = leadershipScoreFormService;
        this.leaderCadreScoreFormService = leaderCadreScoreFormService;
        this.departmentCadreScoreFormService = departmentCadreScoreFormService;
        this.professionalScoreFormService = professionalScoreFormService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<EvaluationScoreForm> findEvaluationScoreFormsByEvaluationGroup(Integer evaluationGroupId) {
        List<EvaluationScoreForm> evaluationScoreForms = new ArrayList<>();
        List<LeadershipScoreForm> leadershipScoreForms = leadershipScoreFormService.findLeadershipScoreFormsByEvaluationGroup(evaluationGroupId);
        List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormService.findLeaderCadreScoreFormsByEvaluationGroup(evaluationGroupId);
        List<DepartmentCadreScoreForm> departmentCadreScoreForms = departmentCadreScoreFormService.findDepartmentCadreScoreFormsByEvaluationGroup(evaluationGroupId);
        List<ProfessionalScoreForm> professionalScoreForms = professionalScoreFormService.findProfessionalScoreFormsByEvaluationGroup(evaluationGroupId);
        evaluationScoreForms.addAll(leadershipScoreForms);
        evaluationScoreForms.addAll(leaderCadreScoreForms);
        evaluationScoreForms.addAll(departmentCadreScoreForms);
        evaluationScoreForms.addAll(professionalScoreForms);
        return evaluationScoreForms;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<EvaluationScoreForm> findEvaluationScoreFormsByUser(Integer userId) {
        List<Evaluator> evaluators = evaluatorService.findEvaluatorsByUser(userId);
        List<EvaluationScoreForm> evaluationScoreForms = new ArrayList<>();
        for (Evaluator evaluator : evaluators) {
            List<LeadershipScoreForm> leadershipScoreForms = leadershipScoreFormService.findLeadershipScoreFormsByEvaluator(evaluator.getId());
            List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormService.findLeaderCadreScoreFormsByEvaluator(evaluator.getId());
            List<DepartmentCadreScoreForm> departmentCadreScoreForms = departmentCadreScoreFormService.findDepartmentCadreScoreFormsByEvaluator(evaluator.getId());
            List<ProfessionalScoreForm> professionalScoreForms = professionalScoreFormService.findProfessionalScoreFormsByEvaluator(evaluator.getId());
            evaluationScoreForms.addAll(leadershipScoreForms);
            evaluationScoreForms.addAll(leaderCadreScoreForms);
            evaluationScoreForms.addAll(departmentCadreScoreForms);
            evaluationScoreForms.addAll(professionalScoreForms);
        }
        return evaluationScoreForms;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Excel exportEvaluationScoreFormByEvaluatee(Evaluatee evaluatee) {

        return null;
    }
}
