package com.cse.haste.service;

import com.cse.haste.model.pojo.ProfessionalScoreForm;

import java.util.List;

/**
 * @author WangZhenqi
 */
public interface ProfessionalScoreFormService {
    /**
     * 保存专业人才评价评分表
     *
     * @param professionalScoreForm 专业人才评价评分表
     */
    void saveProfessionalScoreForm(ProfessionalScoreForm professionalScoreForm);

    /**
     * 根据考核评价计划组删除专业人才评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     */
    void deleteProfessionalScoreFormsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 更新专业人才评价评分表
     *
     * @param professionalScoreForm 专业人才评价评分表
     */
    void updateProfessionalScoreForm(ProfessionalScoreForm professionalScoreForm);

    /**
     * 提交专业人才评价评分表
     *
     * @param professionalScoreForm 专业人才评价评分表
     * @return 更新后的专业人才评价评分表
     */
    ProfessionalScoreForm submitProfessionalScoreForm(ProfessionalScoreForm professionalScoreForm);

    /**
     * 根据考核评价工作组ID查询专业人才评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 专业人才评价评分表数据集合
     */
    List<ProfessionalScoreForm> findProfessionalScoreFormsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 根据考核评价工作组ID和评价者ID查询专业人才评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param evaluatorId       评价者ID
     * @return 专业人才评价评分表数据集合
     */
    List<ProfessionalScoreForm> findProfessionalScoreFormsByEvaluationGroupAndEvaluator(Integer evaluationGroupId, Integer evaluatorId);

    /**
     * 根据ID查询专业人才评价评分表
     *
     * @param id 专业人才评价评分表ID
     * @return 专业人才评价评分表
     */
    ProfessionalScoreForm findProfessionalScoreFormById(Integer id);

    /**
     * 根据受评者和评价者查询专业人才评价评分表
     *
     * @param evaluatorId 评价者ID
     * @return 专业人才评价评分表
     */
    List<ProfessionalScoreForm> findProfessionalScoreFormsByEvaluator(Integer evaluatorId);
}
