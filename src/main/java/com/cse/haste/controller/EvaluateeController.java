package com.cse.haste.controller;

import com.cse.haste.context.HasteException;
import com.cse.haste.model.dto.Response;
import com.cse.haste.model.pojo.Evaluatee;
import com.cse.haste.model.pojo.User;
import com.cse.haste.service.EvaluateeService;
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
public class EvaluateeController extends HasteFacade {

    private final EvaluateeService evaluateeService;

    @Autowired
    public EvaluateeController(EvaluateeService evaluateeService) {
        this.evaluateeService = evaluateeService;
    }

    @PostMapping(value = "evaluatees")
    public Response<Evaluatee> actionAddEvaluatee(@RequestBody Evaluatee evaluatee) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        Evaluatee newEvaluatee = evaluateeService.saveEvaluatee(evaluatee);
        return new Response<>(newEvaluatee);
    }

    @DeleteMapping(value = "evaluatees/{id}")
    public Response<Evaluatee> actionDeleteEvaluatee(@PathVariable(value = "id") Integer id) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        evaluateeService.deleteEvaluatee(id);
        return new Response<>();
    }

    @GetMapping(value = "evaluationGroups/{evaluationGroupId}/evaluatees")
    public Response<List<Evaluatee>> actionQueryEvaluateesByEvaluationGroup(@PathVariable(value = "evaluationGroupId") Integer evaluationGroupId) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        List<Evaluatee> evaluatees = evaluateeService.findEvaluateesByEvaluationGroup(evaluationGroupId);
        return new Response<>(evaluatees);
    }
}
