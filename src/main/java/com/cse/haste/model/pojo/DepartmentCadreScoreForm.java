package com.cse.haste.model.pojo;

import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;

import java.time.LocalDateTime;

/**
 * @author WangZhenqi
 */
public class DepartmentCadreScoreForm extends EvaluationScoreForm {
    /**
     * 政治表现
     */
    private Integer politicsPerformance;
    /**
     * 能力素质
     */
    private Integer abilityAndQuality;
    /**
     * 工作业绩
     */
    private Integer workPerformance;
    /**
     * 廉洁从业和“一岗双责”
     */
    private Integer integrity;

    public DepartmentCadreScoreForm() {
    }

    public static DepartmentCadreScoreForm newInstance() {
        DepartmentCadreScoreForm departmentCadreScoreForm = new DepartmentCadreScoreForm();
        departmentCadreScoreForm.setObjectId(GeneratorUtil.getObjectId());
        departmentCadreScoreForm.setStatus(Constant.Status.ENABLED);
        LocalDateTime dateTime = LocalDateTime.now();
        departmentCadreScoreForm.setCreateAt(dateTime);
        departmentCadreScoreForm.setUpdateAt(dateTime);
        departmentCadreScoreForm.setComplete(false);
        departmentCadreScoreForm.setType(Constant.EvaluationScoreForm.Types.DEPARTMENT_CADRE_EVALUATION_SCORE_FORM);
        return departmentCadreScoreForm;
    }

    public Integer getPoliticsPerformance() {
        return politicsPerformance;
    }

    public void setPoliticsPerformance(Integer politicsPerformance) {
        this.politicsPerformance = politicsPerformance;
    }

    public Integer getAbilityAndQuality() {
        return abilityAndQuality;
    }

    public void setAbilityAndQuality(Integer abilityAndQuality) {
        this.abilityAndQuality = abilityAndQuality;
    }

    public Integer getWorkPerformance() {
        return workPerformance;
    }

    public void setWorkPerformance(Integer workPerformance) {
        this.workPerformance = workPerformance;
    }

    public Integer getIntegrity() {
        return integrity;
    }

    public void setIntegrity(Integer integrity) {
        this.integrity = integrity;
    }
}
