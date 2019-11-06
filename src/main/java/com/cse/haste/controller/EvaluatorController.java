package com.cse.haste.controller;

import com.cse.haste.context.HasteException;
import com.cse.haste.model.dto.Response;
import com.cse.haste.model.pojo.Evaluator;
import com.cse.haste.model.pojo.User;
import com.cse.haste.service.EvaluatorService;
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
public class EvaluatorController extends HasteFacade {

    private final EvaluatorService evaluatorService;

    @Autowired
    public EvaluatorController(EvaluatorService evaluatorService) {
        this.evaluatorService = evaluatorService;
    }

    @PostMapping(value = "evaluators")
    public Response<Evaluator> actionAddEvaluator(@RequestBody Evaluator evaluator) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        Evaluator newEvaluator = evaluatorService.saveEvaluator(evaluator);
        return new Response<>(newEvaluator);
    }

    @DeleteMapping(value = "evaluators/{id}")
    public Response<Evaluator> actionDeleteEvaluator(@PathVariable(value = "id") Integer id) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        evaluatorService.deleteEvaluator(id);
        return new Response<>();
    }

    @GetMapping(value = "evaluators/{id}")
    public Response<Evaluator> actionQueryEvaluatorById(@PathVariable(value = "id") Integer id) {
        Evaluator evaluator = evaluatorService.findEvaluatorById(id);
        return new Response<>(evaluator);
    }

    @GetMapping(value = "evaluationGroups/{evaluationGroupId}/evaluators")
    public Response<List<Evaluator>> actionQueryEvaluatorsByEvaluationGroup(@PathVariable(value = "evaluationGroupId") Integer evaluationGroupId) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        List<Evaluator> evaluators = evaluatorService.findEvaluatorsByEvaluationGroup(evaluationGroupId);
        return new Response<>(evaluators);
    }

    @GetMapping(value = "evaluationGroups/{evaluationGroupId}/notSelectEvaluators")
    public Response<List<User>> actionQueryNotSelectEvaluatorsByEvaluationGroup(@PathVariable(value = "evaluationGroupId") Integer evaluationGroupId) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        List<User> users = evaluatorService.findNotSelectEvaluatorsByEvaluationGroup(evaluationGroupId);
        return new Response<>(users);
    }
}
