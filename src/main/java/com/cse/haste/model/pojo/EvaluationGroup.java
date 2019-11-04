package com.cse.haste.model.pojo;

import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 考核评价工作组
 *
 * @author WangZhenqi
 */
public class EvaluationGroup extends HasteEntity {
    /**
     * 名称
     */
    private String name;
    /**
     * 完成标志
     */
    private Boolean complete;
    /**
     * 考核评价计划
     */
    private Integer evaluationPlanId;
    private String evaluationPlanName;
    private EvaluationPlan evaluationPlan;
    /**
     * 评价者
     */
    private List<Evaluator> evaluators;
    /**
     * 受评者
     */
    private List<Evaluatee> evaluatees;

    public EvaluationGroup() {
    }

    public static EvaluationGroup newInstance() {
        EvaluationGroup evaluationGroup = new EvaluationGroup();
        evaluationGroup.setObjectId(GeneratorUtil.getObjectId());
        evaluationGroup.setStatus(Constant.Status.ENABLED);
        LocalDateTime dateTime = LocalDateTime.now();
        evaluationGroup.setCreateAt(dateTime);
        evaluationGroup.setUpdateAt(dateTime);
        return evaluationGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
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

    public List<Evaluator> getEvaluators() {
        return evaluators;
    }

    public void setEvaluators(List<Evaluator> evaluators) {
        this.evaluators = evaluators;
    }

    public List<Evaluatee> getEvaluatees() {
        return evaluatees;
    }

    public void setEvaluatees(List<Evaluatee> evaluatees) {
        this.evaluatees = evaluatees;
    }
}
