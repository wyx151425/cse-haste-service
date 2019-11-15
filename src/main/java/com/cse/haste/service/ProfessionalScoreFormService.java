package com.cse.haste.service;

import com.cse.haste.model.pojo.EvaluationGroup;
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
     * 删除评价者的评分表
     *
     * @param evaluatorId 评价者ID
     */
    void deleteProfessionalScoreFormsByEvaluator(Integer evaluatorId);

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
     * 通过evaluationGroupId字段更新EvaluationGroupName
     *
     * @param evaluationGroup 考核评价工作组
     */
    void updateEvaluationGroupName(EvaluationGroup evaluationGroup);

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

    /**
     * 根据考核评价工作组和受评者查询专业人才考核评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param evaluateeId       受评者ID
     * @return 专业人才考核评价表
     */
    List<ProfessionalScoreForm> findProfessionalScoreFormsByEvaluationGroupAndEvaluatee(Integer evaluationGroupId, Integer evaluateeId);
}
