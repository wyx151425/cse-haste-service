package com.cse.haste.model.pojo;

import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WangZhenqi
 */
public class EvaluationPlan extends HasteEntity {
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 启动时间
     */
    private LocalDateTime startAt;
    /**
     * 完成时间
     */
    private LocalDateTime completeAt;
    /**
     * 进展情况
     */
    private Integer stage;
    /**
     * 考核评价工作组
     */
    private List<EvaluationGroup> evaluationGroups;

    public EvaluationPlan() {
    }

    public static EvaluationPlan newInstance() {
        EvaluationPlan evaluationPlan = new EvaluationPlan();
        evaluationPlan.setObjectId(GeneratorUtil.getObjectId());
        evaluationPlan.setStatus(Constant.Status.ENABLED);
        LocalDateTime dateTime = LocalDateTime.now();
        evaluationPlan.setCreateAt(dateTime);
        evaluationPlan.setUpdateAt(dateTime);
        return evaluationPlan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getCompleteAt() {
        return completeAt;
    }

    public void setCompleteAt(LocalDateTime completeAt) {
        this.completeAt = completeAt;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public List<EvaluationGroup> getEvaluationGroups() {
        return evaluationGroups;
    }

    public void setEvaluationGroups(List<EvaluationGroup> evaluationGroups) {
        this.evaluationGroups = evaluationGroups;
    }
}
