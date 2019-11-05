package com.cse.haste.repository;

import com.cse.haste.model.pojo.Evaluator;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WangZhenqi
 */
@Repository(value = "evaluatorRepository")
public interface EvaluatorRepository extends HasteRepository<Evaluator, Integer> {
    /**
     * 根据考核评价工作组查询评价者
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 评价者数据集合
     */
    List<Evaluator> findAllByEvaluationGroupId(Integer evaluationGroupId);

    /**
     * 根据用户ID查询评价者
     *
     * @param userId 用户ID
     * @return 评价者数据集合
     */
    List<Evaluator> findAllByUserId(Integer userId);
}
