package com.cse.haste.model.pojo;

import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;

import java.time.LocalDateTime;

/**
 * 专业人才考核评价评分表
 *
 * @author WangZhenqi
 */
public class ProfessionalScoreForm extends EvaluationScoreForm {
    /**
     * 价值观
     */
    private Integer moral1;
    /**
     * 认同感
     */
    private Integer moral2;
    /**
     * 诚信正直
     */
    private Integer quality1;
    /**
     * 责任心
     */
    private Integer quality2;
    /**
     * 进取心
     */
    private Integer quality3;
    /**
     * 团队建设
     */
    private Integer ability1;
    /**
     * 高效执行
     */
    private Integer ability2;
    /**
     * 学习创新
     */
    private Integer ability3;
    /**
     * 履职绩效
     */
    private Integer performance1;
    /**
     * 问题解决
     */
    private Integer performance2;
    /**
     * 协调成效
     */
    private Integer performance3;

    public ProfessionalScoreForm() {
    }

    public static ProfessionalScoreForm newInstance() {
        ProfessionalScoreForm professionalScoreForm = new ProfessionalScoreForm();
        professionalScoreForm.setObjectId(GeneratorUtil.getObjectId());
        professionalScoreForm.setStatus(Constant.Status.ENABLED);
        LocalDateTime dateTime = LocalDateTime.now().withNano(0);
        professionalScoreForm.setCreateAt(dateTime);
        professionalScoreForm.setUpdateAt(dateTime);
        professionalScoreForm.setComplete(false);
        professionalScoreForm.setType(Constant.EvaluationScoreForm.Types.PROFESSIONAL_EVALUATION_SCORE_FORM);
        return professionalScoreForm;
    }

    public Integer getMoral1() {
        return moral1;
    }

    public void setMoral1(Integer moral1) {
        this.moral1 = moral1;
    }

    public Integer getMoral2() {
        return moral2;
    }

    public void setMoral2(Integer moral2) {
        this.moral2 = moral2;
    }

    public Integer getQuality1() {
        return quality1;
    }

    public void setQuality1(Integer quality1) {
        this.quality1 = quality1;
    }

    public Integer getQuality2() {
        return quality2;
    }

    public void setQuality2(Integer quality2) {
        this.quality2 = quality2;
    }

    public Integer getQuality3() {
        return quality3;
    }

    public void setQuality3(Integer quality3) {
        this.quality3 = quality3;
    }

    public Integer getAbility1() {
        return ability1;
    }

    public void setAbility1(Integer ability1) {
        this.ability1 = ability1;
    }

    public Integer getAbility2() {
        return ability2;
    }

    public void setAbility2(Integer ability2) {
        this.ability2 = ability2;
    }

    public Integer getAbility3() {
        return ability3;
    }

    public void setAbility3(Integer ability3) {
        this.ability3 = ability3;
    }

    public Integer getPerformance1() {
        return performance1;
    }

    public void setPerformance1(Integer performance1) {
        this.performance1 = performance1;
    }

    public Integer getPerformance2() {
        return performance2;
    }

    public void setPerformance2(Integer performance2) {
        this.performance2 = performance2;
    }

    public Integer getPerformance3() {
        return performance3;
    }

    public void setPerformance3(Integer performance3) {
        this.performance3 = performance3;
    }
}
