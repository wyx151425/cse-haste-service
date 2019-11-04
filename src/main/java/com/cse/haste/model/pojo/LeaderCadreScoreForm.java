package com.cse.haste.model.pojo;

import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;

import java.time.LocalDateTime;

/**
 * 领导干部考核评价评分表
 *
 * @author WangZhenqi
 */
public class LeaderCadreScoreForm extends EvaluationScoreForm {
    /**
     * 政治忠诚
     */
    private Integer politicsPerformance1;
    /**
     * 政治定力
     */
    private Integer politicsPerformance2;
    /**
     * 政治担当
     */
    private Integer politicsPerformance3;
    /**
     * 政治能力
     */
    private Integer politicsPerformance4;
    /**
     * 政治自律
     */
    private Integer politicsPerformance5;
    /**
     * 推动执行能力
     */
    private Integer abilityAndQuality1;
    /**
     * 学习创新能力
     */
    private Integer abilityAndQuality2;
    /**
     * 团队建设能力
     */
    private Integer abilityAndQuality3;
    /**
     * 职业操守
     */
    private Integer abilityAndQuality4;
    /**
     * 履职绩效
     */
    private Integer workPerformance1;
    /**
     * 协同成效
     */
    private Integer workPerformance2;
    /**
     * 作风建设
     */
    private Integer integrity1;
    /**
     * 廉洁自律
     */
    private Integer integrity2;
    /**
     * 一岗双责
     */
    private Integer integrity3;

    public LeaderCadreScoreForm() {
    }

    public static LeaderCadreScoreForm newInstance() {
        LeaderCadreScoreForm leaderCadreScoreForm = new LeaderCadreScoreForm();
        leaderCadreScoreForm.setObjectId(GeneratorUtil.getObjectId());
        leaderCadreScoreForm.setStatus(Constant.Status.ENABLED);
        LocalDateTime dateTime = LocalDateTime.now();
        leaderCadreScoreForm.setCreateAt(dateTime);
        leaderCadreScoreForm.setUpdateAt(dateTime);
        leaderCadreScoreForm.setComplete(true);
        return leaderCadreScoreForm;
    }

    public Integer getPoliticsPerformance1() {
        return politicsPerformance1;
    }

    public void setPoliticsPerformance1(Integer politicsPerformance1) {
        this.politicsPerformance1 = politicsPerformance1;
    }

    public Integer getPoliticsPerformance2() {
        return politicsPerformance2;
    }

    public void setPoliticsPerformance2(Integer politicsPerformance2) {
        this.politicsPerformance2 = politicsPerformance2;
    }

    public Integer getPoliticsPerformance3() {
        return politicsPerformance3;
    }

    public void setPoliticsPerformance3(Integer politicsPerformance3) {
        this.politicsPerformance3 = politicsPerformance3;
    }

    public Integer getPoliticsPerformance4() {
        return politicsPerformance4;
    }

    public void setPoliticsPerformance4(Integer politicsPerformance4) {
        this.politicsPerformance4 = politicsPerformance4;
    }

    public Integer getPoliticsPerformance5() {
        return politicsPerformance5;
    }

    public void setPoliticsPerformance5(Integer politicsPerformance5) {
        this.politicsPerformance5 = politicsPerformance5;
    }

    public Integer getAbilityAndQuality1() {
        return abilityAndQuality1;
    }

    public void setAbilityAndQuality1(Integer abilityAndQuality1) {
        this.abilityAndQuality1 = abilityAndQuality1;
    }

    public Integer getAbilityAndQuality2() {
        return abilityAndQuality2;
    }

    public void setAbilityAndQuality2(Integer abilityAndQuality2) {
        this.abilityAndQuality2 = abilityAndQuality2;
    }

    public Integer getAbilityAndQuality3() {
        return abilityAndQuality3;
    }

    public void setAbilityAndQuality3(Integer abilityAndQuality3) {
        this.abilityAndQuality3 = abilityAndQuality3;
    }

    public Integer getAbilityAndQuality4() {
        return abilityAndQuality4;
    }

    public void setAbilityAndQuality4(Integer abilityAndQuality4) {
        this.abilityAndQuality4 = abilityAndQuality4;
    }

    public Integer getWorkPerformance1() {
        return workPerformance1;
    }

    public void setWorkPerformance1(Integer workPerformance1) {
        this.workPerformance1 = workPerformance1;
    }

    public Integer getWorkPerformance2() {
        return workPerformance2;
    }

    public void setWorkPerformance2(Integer workPerformance2) {
        this.workPerformance2 = workPerformance2;
    }

    public Integer getIntegrity1() {
        return integrity1;
    }

    public void setIntegrity1(Integer integrity1) {
        this.integrity1 = integrity1;
    }

    public Integer getIntegrity2() {
        return integrity2;
    }

    public void setIntegrity2(Integer integrity2) {
        this.integrity2 = integrity2;
    }

    public Integer getIntegrity3() {
        return integrity3;
    }

    public void setIntegrity3(Integer integrity3) {
        this.integrity3 = integrity3;
    }
}
