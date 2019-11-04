package com.cse.haste.controller;

import com.cse.haste.model.dto.Response;
import com.cse.haste.model.pojo.*;
import com.cse.haste.service.LeaderCadreScoreFormService;
import com.cse.haste.service.LeadershipScoreFormService;
import com.cse.haste.service.ProfessionalScoreFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangZhenqi
 */
@RestController
@RequestMapping(value = "api")
public class EvaluationScoreFormController extends HasteFacade {

    private final LeadershipScoreFormService leadershipScoreFormService;
    private final LeaderCadreScoreFormService leaderCadreScoreFormService;
    private final ProfessionalScoreFormService professionalScoreFormService;

    @Autowired
    public EvaluationScoreFormController(LeadershipScoreFormService leadershipScoreFormService, LeaderCadreScoreFormService leaderCadreScoreFormService, ProfessionalScoreFormService professionalScoreFormService) {
        this.leadershipScoreFormService = leadershipScoreFormService;
        this.leaderCadreScoreFormService = leaderCadreScoreFormService;
        this.professionalScoreFormService = professionalScoreFormService;
    }

    @PutMapping(value = "leadershipScoreForms/submit")
    public Response<LeadershipScoreForm> actionSubmitLeadershipScoreForm(@RequestBody LeadershipScoreForm leadershipScoreForm) {
        leadershipScoreFormService.submitLeadershipScoreForm(leadershipScoreForm);
        return new Response<>();
    }

    @PutMapping(value = "leaderCadreScoreForms/submit")
    public Response<LeaderCadreScoreForm> actionSubmitLeaderCadreScoreForm(@RequestBody LeaderCadreScoreForm leaderCadreScoreForm) {
        leaderCadreScoreFormService.submitLeaderCadreScoreForm(leaderCadreScoreForm);
        return new Response<>();
    }

    @PutMapping(value = "professionalScoreForms/submit")
    public Response<ProfessionalScoreForm> actionSubmitProfessionalScoreForm(@RequestBody ProfessionalScoreForm professionalScoreForm) {
        professionalScoreFormService.submitProfessionalScoreForm(professionalScoreForm);
        return new Response<>();
    }

    @GetMapping(value = "leadershipScoreForms/{id}")
    public Response<LeadershipScoreForm> actionQueryLeadershipScoreFormsById(@PathVariable(value = "id") Integer id) {
        LeadershipScoreForm leadershipScoreForm = leadershipScoreFormService.findLeadershipScoreFormById(id);
        return new Response<>(leadershipScoreForm);
    }

    @GetMapping(value = "leaderCadreScoreForms/{id}")
    public Response<LeaderCadreScoreForm> actionQueryLeaderCadreScoreFormsById(@PathVariable(value = "id") Integer id) {
        LeaderCadreScoreForm leaderCadreScoreForm = leaderCadreScoreFormService.findLeaderCadreScoreFormById(id);
        return new Response<>(leaderCadreScoreForm);
    }

    @GetMapping(value = "professionalScoreForms/{id}")
    public Response<ProfessionalScoreForm> actionQueryProfessionalScoreFormsById(@PathVariable(value = "id") Integer id) {
        ProfessionalScoreForm professionalScoreForm = professionalScoreFormService.findProfessionalScoreFormById(id);
        return new Response<>(professionalScoreForm);
    }

    @GetMapping(value = "evaluators/{evaluatorId}/leadershipScoreForms")
    public Response<List<LeadershipScoreForm>> actionQueryLeadershipScoreFormsByEvaluator(@PathVariable(value = "evaluatorId") Integer evaluatorId) {
        List<LeadershipScoreForm> leadershipScoreForms = leadershipScoreFormService.findLeadershipScoreFormsByEvaluator(evaluatorId);
        return new Response<>(leadershipScoreForms);
    }

    @GetMapping(value = "evaluators/{evaluatorId}/leaderCadreScoreForms")
    public Response<List<LeaderCadreScoreForm>> actionQueryLeaderCadreScoreFormsByEvaluator(@PathVariable(value = "evaluatorId") Integer evaluatorId) {
        List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormService.findLeaderCadreScoreFormsByEvaluator(evaluatorId);
        return new Response<>(leaderCadreScoreForms);
    }

    @GetMapping(value = "evaluators/{evaluatorId}/professionalScoreForms")
    public Response<List<ProfessionalScoreForm>> actionQueryProfessionalScoreFormByEvaluator(@PathVariable(value = "evaluatorId") Integer evaluatorId) {
        List<ProfessionalScoreForm> professionalScoreForms = professionalScoreFormService.findProfessionalScoreFormsByEvaluator(evaluatorId);
        return new Response<>(professionalScoreForms);
    }

    @GetMapping(value = "evaluators/{evaluatorId}/evaluationScoreForms")
    public Response<List<EvaluationScoreForm>> actionQueryEvaluationScoreFormsByEvaluator(@PathVariable(value = "evaluatorId") Integer evaluatorId) {
        List<LeadershipScoreForm> leadershipScoreForms = leadershipScoreFormService.findLeadershipScoreFormsByEvaluator(evaluatorId);
        List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormService.findLeaderCadreScoreFormsByEvaluator(evaluatorId);
        List<ProfessionalScoreForm> professionalScoreForms = professionalScoreFormService.findProfessionalScoreFormsByEvaluator(evaluatorId);
        List<EvaluationScoreForm> evaluationScoreForms = new ArrayList<>();
        evaluationScoreForms.addAll(leadershipScoreForms);
        evaluationScoreForms.addAll(leaderCadreScoreForms);
        evaluationScoreForms.addAll(professionalScoreForms);
        return new Response<>(evaluationScoreForms);
    }
}
