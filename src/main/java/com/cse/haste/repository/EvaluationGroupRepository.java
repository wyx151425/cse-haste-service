package com.cse.haste.repository;

import com.cse.haste.model.pojo.EvaluationGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WangZhenqi
 */
@Repository(value = "evaluationGroupRepository")
public interface EvaluationGroupRepository extends HasteRepository<EvaluationGroup, Integer> {
    /**
     * 根据考核评价计划查询所有的考核评价工作组
     *
     * @param evaluationPlanId 考核评价计划ID
     * @return 考核评价工作组数据集合
     */
    List<EvaluationGroup> findAllByEvaluationPlanId(Integer evaluationPlanId);

    /**
     * 根据考核评价计划ID和完成情况查询所有的考核评价工作组
     *
     * @param evaluationPlanId 考核评价计划ID
     * @param complete         完成情况
     * @return 完成情况
     */
    List<EvaluationGroup> findAllByEvaluationPlanIdAndComplete(
            @Param(value = "evaluationPlanId") Integer evaluationPlanId, @Param(value = "complete") Boolean complete);
}
