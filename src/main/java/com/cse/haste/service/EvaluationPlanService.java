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
     * @return 保存后的考核评价计划
     */
    EvaluationPlan saveEvaluationPlan(EvaluationPlan evaluationPlan);

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
     * @return 启动后的考核评价计划
     */
    EvaluationPlan startEvaluationPlan(Integer id);

    /**
     * 完成考核评价计划
     *
     * @param id 考核评价计划ID
     * @return 提交后的考核评价计划
     */
    EvaluationPlan submitEvaluationPlan(Integer id);

    /**
     * 查询所有的考核评价计划
     *
     * @return 考核评价计划
     */
    List<EvaluationPlan> findEvaluationPlans();
}
