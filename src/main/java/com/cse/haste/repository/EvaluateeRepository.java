package com.cse.haste.repository;

import com.cse.haste.model.pojo.Evaluatee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WangZhenqi
 */
@Repository(value = "evaluateeRepository")
public interface EvaluateeRepository extends HasteRepository<Evaluatee, Integer> {
    /**
     * 根据考核评价计划ID查询受评者
     *
     * @param evaluationPlanId 考核评价工作组ID
     * @return 受评者数据集合
     */
    List<Evaluatee> findAllByEvaluationPlanId(Integer evaluationPlanId);

    /**
     * 根据考核评价工作组ID查询受评者
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 受评者数据集合
     */
    List<Evaluatee> findAllByEvaluationGroupId(Integer evaluationGroupId);

    /**
     * 根据考核评价工作组ID和关联用户ID查询受评者
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param userId            关联用户ID
     * @return 受评者
     */
    Evaluatee findAllByEvaluationGroupIdAndUserId(
            @Param(value = "evaluationGroupId") Integer evaluationGroupId, @Param(value = "userId") Integer userId);

    /**
     * 根据考核评价计划ID查询未分配到工作组的受评者
     *
     * @param evaluationPlanId 考核评价计划ID
     * @param ids              受评者ID
     * @return 受评者
     */
    List<Evaluatee> findAllByEvaluationPlanIdAndUserIdNotIn(
            @Param(value = "evaluationPlanId") Integer evaluationPlanId, @Param(value = "ids") List<Integer> ids);
}
