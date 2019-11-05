package com.cse.haste.service.impl;

import com.cse.haste.model.pojo.Evaluatee;
import com.cse.haste.model.pojo.User;
import com.cse.haste.repository.EvaluateeRepository;
import com.cse.haste.repository.UserRepository;
import com.cse.haste.service.EvaluateeService;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public EvaluateeServiceImpl(UserRepository userRepository, EvaluateeRepository evaluateeRepository) {
        this.userRepository = userRepository;
        this.evaluateeRepository = evaluateeRepository;
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
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Evaluatee> findEvaluateesByEvaluationGroup(Integer evaluationGroupId) {
        return evaluateeRepository.findAllByEvaluationGroupId(evaluationGroupId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<User> findNotSelectEvaluateesByEvaluationGroup(Integer evaluationGroupId) {
        List<Evaluatee> evaluatees = evaluateeRepository.findAllByEvaluationGroupId(evaluationGroupId);
        if (evaluatees.size() > 0) {
            List<Integer> ids = new ArrayList<>();
            for (Evaluatee evaluatee : evaluatees) {
                ids.add(evaluatee.getUserId());
            }
            return userRepository.findAllByIdNotIn(ids);
        }
        return userRepository.findAllByRole(Constant.Roles.USER);
    }
}
