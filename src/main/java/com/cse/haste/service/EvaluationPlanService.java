package com.cse.haste.service;

import com.cse.haste.model.pojo.EvaluationPlan;

import java.util.List;

/**
 * @author WangZhenqi
 */
public interface EvaluationPlanService {
    /**
     * 保存考核评价计划
     *
     * @param evaluationPlan 考核评价计划
     */
    void saveEvaluationPlan(EvaluationPlan evaluationPlan);

    /**
     * 删除考核评价计划
     *
     * @param id 考核评价计划ID
     */
    void deleteEvaluationPlan(Integer id);

    /**
     * 启动考核评价计划
     *
     * @param id 考核评价计划ID
     */
    void startEvaluationPlan(Integer id);

    /**
     * 完成考核评价计划
     *
     * @param id 考核评价计划ID
     */
    void submitEvaluationPlan(Integer id);

    /**
     * 查询所有的考核评价计划
     *
     * @return 考核评价计划
     */
    List<EvaluationPlan> findEvaluationPlans();
}
