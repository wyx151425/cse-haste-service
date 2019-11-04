package com.cse.haste.repository;

import com.cse.haste.model.pojo.EvaluationPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WangZhenqi
 */
@Repository(value = "evaluationPlanRepository")
public interface EvaluationPlanRepository extends HasteRepository<EvaluationPlan, Integer> {
}
