package com.cse.haste.service.impl;

import com.cse.haste.model.pojo.Evaluatee;
import com.cse.haste.model.pojo.EvaluationGroup;
import com.cse.haste.model.pojo.User;
import com.cse.haste.repository.EvaluateeRepository;
import com.cse.haste.repository.UserRepository;
import com.cse.haste.service.EvaluateeService;
import com.cse.haste.service.EvaluationGroupService;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "evaluateeService")
public class EvaluateeServiceImpl implements EvaluateeService {

    private final UserRepository userRepository;
    private final EvaluateeRepository evaluateeRepository;
    private EvaluationGroupService evaluationGroupService;

    @Autowired
    public EvaluateeServiceImpl(UserRepository userRepository, EvaluateeRepository evaluateeRepository) {
        this.userRepository = userRepository;
        this.evaluateeRepository = evaluateeRepository;
    }

    @Autowired
    @Lazy
    public void setEvaluationGroupService(EvaluationGroupService evaluationGroupService) {
        this.evaluationGroupService = evaluationGroupService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Evaluatee saveEvaluatee(Evaluatee evaluatee) {
        evaluatee.setObjectId(GeneratorUtil.getObjectId());
        evaluatee.setStatus(Constant.Status.ENABLED);
        evaluateeRepository.save(evaluatee);
        return evaluateeRepository.findOneById(evaluatee.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEvaluatee(Integer id) {
        evaluateeRepository.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEvaluateesByEvaluationGroup(Integer evaluationGroupId) {
        List<Evaluatee> evaluatees = evaluateeRepository.findAllByEvaluationGroupId(evaluationGroupId);
        for (Evaluatee evaluatee : evaluatees) {
            evaluateeRepository.delete(evaluatee.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEvaluationGroupName(EvaluationGroup evaluationGroup) {
        evaluateeRepository.updateEvaluationGroupNameByEvaluationGroupId(evaluationGroup);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Evaluatee findEvaluateeById(Integer id) {
        return evaluateeRepository.findOneById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Evaluatee> findEvaluateesByEvaluationPlan(Integer evaluationPlanId) {
        return evaluateeRepository.findAllByEvaluationPlanId(evaluationPlanId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<User> findNotSelectEvaluateesByEvaluationPlanAndNameLike(Integer evaluationPlanId, String name) {
        List<Evaluatee> evaluatees = evaluateeRepository.findAllByEvaluationPlanId(evaluationPlanId);
        if (evaluatees.size() > 0) {
            List<Integer> ids = new ArrayList<>();
            for (Evaluatee evaluatee : evaluatees) {
                ids.add(evaluatee.getUserId());
            }
            if (null != name) {
                return userRepository.findAllByIdNotInAndNameLike(ids, name);
            } else {
                return userRepository.findAllByIdNotIn(ids);
            }
        }
        if (null != name) {
            return userRepository.findAllByRoleAndNameLike(Constant.Roles.USER, name);
        } else {
            return userRepository.findAllByRole(Constant.Roles.USER);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Evaluatee> findEvaluateesByEvaluationGroup(Integer evaluationGroupId) {
        return evaluateeRepository.findAllByEvaluationGroupId(evaluationGroupId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<User> findNotSelectEvaluateesByEvaluationGroupAndNameLike(Integer evaluationGroupId, String name) {
        List<Evaluatee> notSelectEvaluatees;
        List<Evaluatee> evaluatees = evaluateeRepository.findAllByEvaluationGroupId(evaluationGroupId);
        EvaluationGroup evaluationGroup = evaluationGroupService.findEvaluationGroupById(evaluationGroupId);
        if (evaluatees.size() > 0) {
            List<Integer> ids = new ArrayList<>();
            for (Evaluatee evaluatee : evaluatees) {
                ids.add(evaluatee.getUserId());
            }
            if (null != name) {
                notSelectEvaluatees = evaluateeRepository.findAllByEvaluationPlanIdAndUserIdNotInAndNameLike(evaluationGroup.getEvaluationPlanId(), ids, name);
            } else {
                notSelectEvaluatees = evaluateeRepository.findAllByEvaluationPlanIdAndUserIdNotIn(evaluationGroup.getEvaluationPlanId(), ids);
            }

        } else {
            if (null != name) {
                notSelectEvaluatees = evaluateeRepository.findAllByEvaluationPlanIdAndNameLike(evaluationGroup.getEvaluationPlanId(), name);
            } else {
                notSelectEvaluatees = evaluateeRepository.findAllByEvaluationPlanId(evaluationGroup.getEvaluationPlanId());
            }
        }
        List<User> users = new ArrayList<>();
        for (Evaluatee evaluatee : notSelectEvaluatees) {
            users.add(evaluatee.getUser());
        }
        return users;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Evaluatee findEvaluateeByEvaluationGroupAndUser(Integer evaluationGroupId, Integer userId) {
        return evaluateeRepository.findAllByEvaluationGroupIdAndUserId(evaluationGroupId, userId);
    }
}
