package com.cse.haste.repository;

import com.cse.haste.model.pojo.Evaluatee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WangZhenqi
 */
@Repository(value = "evaluateeRepository")
public interface EvaluateeRepository extends HasteRepository<Evaluatee, Integer> {
    /**
     * 根据考核评价工作组ID查询受评者
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 受评者数据集合
     */
    List<Evaluatee> findAllByEvaluationGroupId(Integer evaluationGroupId);
}
