package com.cse.haste.service.impl;

import com.cse.haste.model.pojo.Evaluator;
import com.cse.haste.repository.EvaluatorRepository;
import com.cse.haste.service.EvaluationPlanService;
import com.cse.haste.service.EvaluatorService;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "evaluatorService")
public class EvaluatorServiceImpl implements EvaluatorService {

    private final EvaluatorRepository evaluatorRepository;

    @Autowired
    public EvaluatorServiceImpl(EvaluatorRepository evaluatorRepository) {
        this.evaluatorRepository = evaluatorRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Evaluator saveEvaluator(Evaluator evaluator) {
        evaluator.setObjectId(GeneratorUtil.getObjectId());
        evaluator.setStatus(Constant.Status.ENABLED);
        evaluatorRepository.save(evaluator);
        return evaluatorRepository.findOneById(evaluator.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEvaluator(Integer id) {
        evaluatorRepository.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEvaluatorsByEvaluationGroup(Integer evaluationGroupId) {
        List<Evaluator> evaluators = evaluatorRepository.findAllByEvaluationGroupId(evaluationGroupId);
        for (Evaluator evaluator : evaluators) {
            evaluatorRepository.delete(evaluator.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Evaluator> findEvaluatorsByEvaluationGroup(Integer evaluationGroupId) {
        return evaluatorRepository.findAllByEvaluationGroupId(evaluationGroupId);
    }
}
