package com.cse.haste.service;

import com.cse.haste.model.pojo.EvaluationGroup;
import com.cse.haste.model.pojo.EvaluationPlan;

import java.util.List;

/**
 * @author WangZhenqi
 */
public interface EvaluationGroupService {
    /**
     * 保存考核评价工作组
     *
     * @param evaluationGroup 考核评价工作组
     */
    void saveEvaluationGroup(EvaluationGroup evaluationGroup);

    /**
     * 初始化考核评价工作组
     *
     * @param evaluationPlan 考核评价计划
     */
    void initializeEvaluationGroupsByEvaluationPlan(EvaluationPlan evaluationPlan);

    /**
     * 删除考核评价工作组
     *
     * @param id 考核评价工作组ID
     */
    void deleteEvaluationGroup(Integer id);

    /**
     * 根据考核评价计划删除考核评价工作组
     *
     * @param evaluationPlanId 考核评价计划ID
     */
    void deleteEvaluationGroupsByEvaluationPlan(Integer evaluationPlanId);

    /**
     * 根据ID查询考核评价工作组
     *
     * @param id 考核评价工作组ID
     * @return 考核评价工作组
     */
    EvaluationGroup findEvaluationGroupById(Integer id);

    /**
     * 根据考核评价计划查询考核评价工作组
     *
     * @param planId 考核评价计划ID
     * @return 考核评价工作组
     */
    List<EvaluationGroup> findEvaluationGroupsByEvaluationPlan(Integer planId);
}
