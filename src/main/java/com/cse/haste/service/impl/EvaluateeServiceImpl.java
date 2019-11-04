package com.cse.haste.service.impl;

import com.cse.haste.model.pojo.Evaluatee;
import com.cse.haste.repository.EvaluateeRepository;
import com.cse.haste.service.EvaluateeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "evaluateeService")
public class EvaluateeServiceImpl implements EvaluateeService {

    private final EvaluateeRepository evaluateeRepository;

    @Autowired
    public EvaluateeServiceImpl(EvaluateeRepository evaluateeRepository) {
        this.evaluateeRepository = evaluateeRepository;
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
}
