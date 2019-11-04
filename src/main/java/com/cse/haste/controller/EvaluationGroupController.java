package com.cse.haste.controller;

import com.cse.haste.context.HasteException;
import com.cse.haste.model.dto.Response;
import com.cse.haste.model.pojo.EvaluationGroup;
import com.cse.haste.model.pojo.EvaluationPlan;
import com.cse.haste.model.pojo.User;
import com.cse.haste.service.EvaluationGroupService;
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
public class EvaluationGroupController extends HasteFacade {

    private final EvaluationGroupService evaluationGroupService;

    @Autowired
    public EvaluationGroupController(EvaluationGroupService evaluationGroupService) {
        this.evaluationGroupService = evaluationGroupService;
    }

    @PostMapping(value = "evaluationGroups")
    public Response<EvaluationGroup> actionSaveEvaluationGroup(@RequestBody EvaluationGroup evaluationGroup) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        evaluationGroupService.saveEvaluationGroup(evaluationGroup);
        return new Response<>(evaluationGroup);
    }

    @DeleteMapping(value = "evaluationGroups/{id}")
    public Response<EvaluationGroup> actionDeleteEvaluationGroup(@PathVariable(value = "id") Integer id) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        evaluationGroupService.deleteEvaluationGroup(id);
        return new Response<>();
    }

    @GetMapping(value = "evaluationGroups/{id}")
    public Response<EvaluationGroup> actionQueryEvaluationGroupById(@PathVariable(value = "id") Integer id) {
        EvaluationGroup evaluationGroup = evaluationGroupService.findEvaluationGroupById(id);
        return new Response<>(evaluationGroup);
    }

    @GetMapping(value = "evaluationPlans/{evaluationPlanId}/evaluationGroups")
    public Response<List<EvaluationGroup>> actionQueryEvaluationGroupsByEvaluationPlan(@PathVariable(value = "evaluationPlanId") Integer evaluationPlanId) {
        List<EvaluationGroup> evaluationGroups = evaluationGroupService.findEvaluationGroupsByEvaluationPlan(evaluationPlanId);
        return new Response<>(evaluationGroups);
    }
}
