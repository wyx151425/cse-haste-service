package com.cse.haste.model.pojo;

/**
 * 评价者
 *
 * @author WangZhenqi
 */
public class Evaluator extends HasteEntity {
    /**
     * 关联用户
     */
    private Integer userId;
    private String userName;
    private User user;
    /**
     * 考核评价计划
     */
    private Integer evaluationPlanId;
    private String evaluationPlanName;
    private EvaluationPlan evaluationPlan;
    /**
     * 考核评价工作组
     */
    private Integer evaluationGroupId;
    private String evaluationGroupName;
    private EvaluationGroup evaluationGroup;

    public Evaluator() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getEvaluationPlanId() {
        return evaluationPlanId;
    }

    public void setEvaluationPlanId(Integer evaluationPlanId) {
        this.evaluationPlanId = evaluationPlanId;
    }

    public String getEvaluationPlanName() {
        return evaluationPlanName;
    }

    public void setEvaluationPlanName(String evaluationPlanName) {
        this.evaluationPlanName = evaluationPlanName;
    }

    public EvaluationPlan getEvaluationPlan() {
        return evaluationPlan;
    }

    public void setEvaluationPlan(EvaluationPlan evaluationPlan) {
        this.evaluationPlan = evaluationPlan;
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
