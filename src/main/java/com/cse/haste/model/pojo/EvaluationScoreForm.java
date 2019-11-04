package com.cse.haste.model.pojo;

/**
 * 考核评价评分表
 *
 * @author WangZhenqi
 */
public class EvaluationScoreForm extends HasteEntity {
    /**
     * 完成标志
     */
    private Boolean complete;
    /**
     * 受评者
     */
    private Integer evaluateeId;
    private String evaluateeName;
    private Evaluatee evaluatee;
    /**
     * 评价者
     */
    private Integer evaluatorId;
    private String evaluatorName;
    private Evaluator evaluator;
    /**
     * 考核评价工作组
     */
    private Integer evaluationGroupId;
    private String evaluationGroupName;
    private EvaluationGroup evaluationGroup;

    public EvaluationScoreForm() {
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Integer getEvaluateeId() {
        return evaluateeId;
    }

    public void setEvaluateeId(Integer evaluateeId) {
        this.evaluateeId = evaluateeId;
    }

    public String getEvaluateeName() {
        return evaluateeName;
    }

    public void setEvaluateeName(String evaluateeName) {
        this.evaluateeName = evaluateeName;
    }

    public Evaluatee getEvaluatee() {
        return evaluatee;
    }

    public void setEvaluatee(Evaluatee evaluatee) {
        this.evaluatee = evaluatee;
    }

    public Integer getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(Integer evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public String getEvaluatorName() {
        return evaluatorName;
    }

    public void setEvaluatorName(String evaluatorName) {
        this.evaluatorName = evaluatorName;
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public Integer getEvaluationGroupId() {
        return evaluationGroupId;
    }

    public void setEvaluationGroupId(Integer evaluationGroupId) {
        this.evaluationGroupId = evaluationGroupId;
    }

    public String getEvaluationGroupName() {
        return evaluationGroupName;
    }

    public void setEvaluationGroupName(String evaluationGroupName) {
        this.evaluationGroupName = evaluationGroupName;
    }

    public EvaluationGroup getEvaluationGroup() {
        return evaluationGroup;
    }

    public void setEvaluationGroup(EvaluationGroup evaluationGroup) {
        this.evaluationGroup = evaluationGroup;
    }
}
