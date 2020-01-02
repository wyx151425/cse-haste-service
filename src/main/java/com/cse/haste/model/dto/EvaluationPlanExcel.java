package com.cse.haste.model.dto;

import java.util.List;

/**
 * 考核评价计划评分表
 *
 * @author WangZhenqi
 */
public class EvaluationPlanExcel {
    /**
     * 考核评价计划名称
     */
    private String evaluationPlanName;
    /**
     * 评分统计表
     */
    private List<Excel> excels;

    public EvaluationPlanExcel() {
    }

    public EvaluationPlanExcel(String evaluationPlanName, List<Excel> excels) {
        this.evaluationPlanName = evaluationPlanName;
        this.excels = excels;
    }

    public String getEvaluationPlanName() {
        return evaluationPlanName;
    }

    public void setEvaluationPlanName(String evaluationPlanName) {
        this.evaluationPlanName = evaluationPlanName;
    }

    public List<Excel> getExcels() {
        return excels;
    }

    public void setExcels(List<Excel> excels) {
        this.excels = excels;
    }
}
