package com.cse.haste.repository;

import com.cse.haste.model.pojo.EvaluationGroup;
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
}
