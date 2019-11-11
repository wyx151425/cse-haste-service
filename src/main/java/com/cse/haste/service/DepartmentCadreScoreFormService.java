package com.cse.haste.service;

import com.cse.haste.model.pojo.DepartmentCadreScoreForm;

import java.util.List;

/**
 * @author WangZhenqi
 */
public interface DepartmentCadreScoreFormService {
    /**
     * 保存中层干部评价评分表
     *
     * @param departmentCadreScoreForm 中层干部评价评分表
     */
    void saveDepartmentCadreScoreForm(DepartmentCadreScoreForm departmentCadreScoreForm);

    /**
     * 根据考核评价计划组删除中层干部评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     */
    void deleteDepartmentCadreScoreFormsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 中层干部评价评分表
     *
     * @param departmentCadreScoreForm 中层干部评价评分表
     */
    void updateDepartmentCadreScoreForm(DepartmentCadreScoreForm departmentCadreScoreForm);

    /**
     * 提交中层干部评价评分表
     *
     * @param departmentCadreScoreForm 中层干部评价评分表
     * @return 更新后的领导干部评价评分表
     */
    DepartmentCadreScoreForm submitDepartmentCadreScoreForm(DepartmentCadreScoreForm departmentCadreScoreForm);

    /**
     * 根据考核评价工作组ID查询中层干部评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 中层干部评价评分表数据集合
     */
    List<DepartmentCadreScoreForm> findDepartmentCadreScoreFormsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 根据考核评价工作组ID和评价者ID查询中层干部评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param evaluatorId       评价者ID
     * @return 中层干部评价评分表数据集合
     */
    List<DepartmentCadreScoreForm> findDepartmentCadreScoreFormsByEvaluationGroupAndEvaluator(Integer evaluationGroupId, Integer evaluatorId);

    /**
     * 根据ID查询中层干部评价评分表
     *
     * @param id 中层干部评价评分表ID
     * @return 中层干部评价评分表
     */
    DepartmentCadreScoreForm findDepartmentCadreScoreFormById(Integer id);

    /**
     * 根据受评者和评价者查询中层干部评价评分表
     *
     * @param evaluatorId 评价者ID
     * @return 中层干部评价评分表
     */
    List<DepartmentCadreScoreForm> findDepartmentCadreScoreFormsByEvaluator(Integer evaluatorId);

    /**
     * 根据考核评价工作组和受评者查询部门干部考核评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param evaluateeId       受评者ID
     * @return 部门干部考核评价表
     */
    List<DepartmentCadreScoreForm> findDepartmentCadreScoreFormsByEvaluationGroupAndEvaluatee(Integer evaluationGroupId, Integer evaluateeId);
}
