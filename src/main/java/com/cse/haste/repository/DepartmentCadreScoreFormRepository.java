package com.cse.haste.repository;

import com.cse.haste.model.pojo.DepartmentCadreScoreForm;
import com.cse.haste.model.pojo.EvaluationGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WangZhenqi
 */
@Repository(value = "departmentCadreScoreFormRepository")
public interface DepartmentCadreScoreFormRepository extends HasteRepository<DepartmentCadreScoreForm, Integer> {
    /**
     * 通过evaluationGroupId字段更新EvaluationGroupName
     *
     * @param evaluationGroup 考核评价工作组
     */
    void updateEvaluationGroupNameByEvaluationGroupId(EvaluationGroup evaluationGroup);

    /**
     * 根据考核评价工作组ID查询中层干部评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 中层干部评价评分表数据集合
     */
    List<DepartmentCadreScoreForm> findAllByEvaluationGroupId(Integer evaluationGroupId);

    /**
     * 根据评价者ID查询中层干部评价评分表
     *
     * @param evaluatorId 评价者ID
     * @return 中层干部评价评分表数据集合
     */
    List<DepartmentCadreScoreForm> findAllByEvaluatorId(Integer evaluatorId);

    /**
     * 根据考核评价工作组ID和评分完成情况查询中层干部评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param complete          评分完成情况
     * @return 中层干部评价评分表数据集合
     */
    List<DepartmentCadreScoreForm> findAllByEvaluationGroupIdAndComplete(
            @Param(value = "evaluationGroupId") Integer evaluationGroupId, @Param(value = "complete") Boolean complete);

    /**
     * 根据考核评价工作组ID和评价者ID查询中层干部评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param evaluatorId       评价者ID
     * @return 中层干部评价评分表数据集合
     */
    List<DepartmentCadreScoreForm> findAllByEvaluationGroupIdAndEvaluatorId(
            @Param(value = "evaluationGroupId") Integer evaluationGroupId, @Param(value = "evaluatorId") Integer evaluatorId);

    /**
     * 根据考核评价工作组和受评者查询部门干部考核评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param evaluateeId       受评者ID
     * @return 部门干部考核评价表
     */
    List<DepartmentCadreScoreForm> findAllByEvaluationGroupIdAndEvaluateeId(
            @Param(value = "evaluationGroupId") Integer evaluationGroupId, @Param(value = "evaluateeId") Integer evaluateeId);
}
