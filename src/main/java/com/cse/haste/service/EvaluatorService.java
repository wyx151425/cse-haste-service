package com.cse.haste.service;

import com.cse.haste.model.pojo.Evaluator;

import java.util.List;

/**
 * @author WangZhenqi
 */
public interface EvaluatorService {
    /**
     * 根据考核评价计划组删除评价者
     *
     * @param evaluationGroupId 考核评价工作组ID
     */
    void deleteEvaluatorsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 根据考核评价工作组查询评价者
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 评价者数据集合
     */
    List<Evaluator> findEvaluatorsByEvaluationGroup(Integer evaluationGroupId);
}
