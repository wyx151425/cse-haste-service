package com.cse.haste.model.pojo;

import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;

import java.time.LocalDateTime;

/**
 * 领导班子考核评价评分表
 *
 * @author WangZhenqi
 */
public class LeadershipScoreForm extends EvaluationScoreForm {
    /**
     * 政治方向
     */
    private Integer politicsQuality1;
    /**
     * 党建工作
     */
    private Integer politicsQuality2;
    /**
     * 社会责任
     */
    private Integer politicsQuality3;
    /**
     * 经济效益
     */
    private Integer operatePerformance1;
    /**
     * 可持续发展
     */
    private Integer operatePerformance2;
    /**
     * 创新成效
     */
    private Integer operatePerformance3;
    /**
     * 科学管理
     */
    private Integer operatePerformance4;
    /**
     * 发扬民主
     */
    private Integer teamwork1;
    /**
     * 整体合力
     */
    private Integer teamwork2;
    /**
     * 运行机制
     */
    private Integer teamwork3;
    /**
     * 联系群众
     */
    private Integer styleAndImage1;
    /**
     * 选人用人
     */
    private Integer styleAndImage2;
    /**
     * 廉洁自律
     */
    private Integer styleAndImage3;

    public LeadershipScoreForm() {
    }

    public static LeadershipScoreForm newInstance() {
        LeadershipScoreForm leadershipScoreForm = new LeadershipScoreForm();
        leadershipScoreForm.setObjectId(GeneratorUtil.getObjectId());
        leadershipScoreForm.setStatus(Constant.Status.ENABLED);
        LocalDateTime dateTime = LocalDateTime.now();
        leadershipScoreForm.setCreateAt(dateTime);
        leadershipScoreForm.setUpdateAt(dateTime);
        leadershipScoreForm.setComplete(false);
        leadershipScoreForm.setType(Constant.EvaluationScoreForm.Types.LEADERSHIP_EVALUATION_SCORE_FORM);
        return leadershipScoreForm;
    }

    public Integer getPoliticsQuality1() {
        return politicsQuality1;
    }

    public void setPoliticsQuality1(Integer politicsQuality1) {
        this.politicsQuality1 = politicsQuality1;
    }

    public Integer getPoliticsQuality2() {
        return politicsQuality2;
    }

    public void setPoliticsQuality2(Integer politicsQuality2) {
        this.politicsQuality2 = politicsQuality2;
    }

    public Integer getPoliticsQuality3() {
        return politicsQuality3;
    }

    public void setPoliticsQuality3(Integer politicsQuality3) {
        this.politicsQuality3 = politicsQuality3;
    }

    public Integer getOperatePerformance1() {
        return operatePerformance1;
    }

    public void setOperatePerformance1(Integer operatePerformance1) {
        this.operatePerformance1 = operatePerformance1;
    }

    public Integer getOperatePerformance2() {
        return operatePerformance2;
    }

    public void setOperatePerformance2(Integer operatePerformance2) {
        this.operatePerformance2 = operatePerformance2;
    }

    public Integer getOperatePerformance3() {
        return operatePerformance3;
    }

    public void setOperatePerformance3(Integer operatePerformance3) {
        this.operatePerformance3 = operatePerformance3;
    }

    public Integer getOperatePerformance4() {
        return operatePerformance4;
    }

    public void setOperatePerformance4(Integer operatePerformance4) {
        this.operatePerformance4 = operatePerformance4;
    }

    public Integer getTeamwork1() {
        return teamwork1;
    }

    public void setTeamwork1(Integer teamwork1) {
        this.teamwork1 = teamwork1;
    }

    public Integer getTeamwork2() {
        return teamwork2;
    }

    public void setTeamwork2(Integer teamwork2) {
        this.teamwork2 = teamwork2;
    }

    public Integer getTeamwork3() {
        return teamwork3;
    }

    public void setTeamwork3(Integer teamwork3) {
        this.teamwork3 = teamwork3;
    }

    public Integer getStyleAndImage1() {
        return styleAndImage1;
    }

    public void setStyleAndImage1(Integer styleAndImage1) {
        this.styleAndImage1 = styleAndImage1;
    }

    public Integer getStyleAndImage2() {
        return styleAndImage2;
    }

    public void setStyleAndImage2(Integer styleAndImage2) {
        this.styleAndImage2 = styleAndImage2;
    }

    public Integer getStyleAndImage3() {
        return styleAndImage3;
    }

    public void setStyleAndImage3(Integer styleAndImage3) {
        this.styleAndImage3 = styleAndImage3;
    }
}
