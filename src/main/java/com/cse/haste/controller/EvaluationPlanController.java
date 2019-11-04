package com.cse.haste.controller;

import com.cse.haste.context.HasteException;
import com.cse.haste.model.dto.Response;
import com.cse.haste.model.pojo.EvaluationPlan;
import com.cse.haste.model.pojo.User;
import com.cse.haste.service.EvaluationPlanService;
import com.cse.haste.util.Constant;
import com.cse.haste.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WangZhenqi
 */
@RestController
@RequestMapping(value = "api")
public class EvaluationPlanController extends HasteFacade {

    private final EvaluationPlanService evaluationPlanService;

    @Autowired
    public EvaluationPlanController(EvaluationPlanService evaluationPlanService) {
        this.evaluationPlanService = evaluationPlanService;
    }

    @PostMapping(value = "evaluationPlans")
    public Response<EvaluationPlan> actionSaveEvaluationPlan(@RequestBody EvaluationPlan evaluationPlan) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        evaluationPlanService.saveEvaluationPlan(evaluationPlan);
        return new Response<>(evaluationPlan);
    }

    @DeleteMapping(value = "evaluationPlans/{id}")
    public Response<EvaluationPlan> actionDeleteEvaluationPlan(@PathVariable(value = "id") Integer id) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        evaluationPlanService.deleteEvaluationPlan(id);
        return new Response<>();
    }

    @PutMapping(value = "evaluationPlans/{id}/start")
    public Response<EvaluationPlan> actionStartEvaluationPlan(@PathVariable(value = "id") Integer id) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        evaluationPlanService.startEvaluationPlan(id);
        return new Response<>();
    }

    @PutMapping(value = "evaluationPlans/{id}/submit")
    public Response<EvaluationPlan> actionSubmitEvaluationPlan(@PathVariable(value = "id") Integer id) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        evaluationPlanService.submitEvaluationPlan(id);
        return new Response<>();
    }

    @GetMapping(value = "evaluationPlans")
    public Response<List<EvaluationPlan>> actionQueryPlanList() {
        List<EvaluationPlan> evaluationPlanList = evaluationPlanService.findEvaluationPlans();
        return new Response<>(evaluationPlanList);
    }
}
