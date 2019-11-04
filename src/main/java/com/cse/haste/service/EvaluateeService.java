package com.cse.haste.service;

import com.cse.haste.model.pojo.Evaluatee;

import java.util.List;

/**
 * @author WangZhenqi
 */
public interface EvaluateeService {
    /**
     * 保存受评者
     *
     * @param evaluatee 受评者
     * @return 保存后的受评
     */
    Evaluatee saveEvaluatee(Evaluatee evaluatee);

    /**
     * 删除受评者
     *
     * @param id 受评者ID
     */
    void deleteEvaluatee(Integer id);

    /**
     * 根据考核评价计划组删除受评者
     *
     * @param evaluationGroupId 考核评价工作组ID
     */
    void deleteEvaluateesByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 根据考核评价工作组查询受评者
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 受评者数据集合
     */
    List<Evaluatee> findEvaluateesByEvaluationGroup(Integer evaluationGroupId);
}
