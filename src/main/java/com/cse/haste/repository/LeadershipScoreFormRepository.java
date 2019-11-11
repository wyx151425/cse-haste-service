package com.cse.haste.repository;

import com.cse.haste.model.pojo.LeadershipScoreForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WangZhenqi
 */
@Repository(value = "leadershipScoreFormRepository")
public interface LeadershipScoreFormRepository extends HasteRepository<LeadershipScoreForm, Integer> {
    /**
     * 根据考核评价工作组ID查询领导班子评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 领导班子评价评分表数据集合
     */
    List<LeadershipScoreForm> findAllByEvaluationGroupId(Integer evaluationGroupId);

    /**
     * 根据评价者ID查询领导班子评价评分表
     *
     * @param evaluatorId 评价者ID
     * @return 领导班子评价评分表数据集合
     */
    List<LeadershipScoreForm> findAllByEvaluatorId(Integer evaluatorId);

    /**
     * 根据考核评价工作组ID和评分完成情况查询领导班子评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param complete          评分完成情况
     * @return 领导班子评价评分表数据集合
     */
    List<LeadershipScoreForm> findAllByEvaluationGroupIdAndComplete(
            @Param(value = "evaluationGroupId") Integer evaluationGroupId, @Param(value = "complete") Boolean complete);

    /**
     * 根据考核评价工作组ID和评价者ID查询领导班子评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param evaluatorId       评价者ID
     * @return 领导班子评价评分表数据集合
     */
    List<LeadershipScoreForm> findAllByEvaluationGroupIdAndEvaluatorId(
            @Param(value = "evaluationGroupId") Integer evaluationGroupId, @Param(value = "evaluatorId") Integer evaluatorId);

    /**
     * 根据考核评价工作组和受评者查询领导班子考核评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param evaluateeId       受评者ID
     * @return 领导班子考核评价表
     */
    List<LeadershipScoreForm> findAllByEvaluationGroupIdAndEvaluateeId(
            @Param(value = "evaluationGroupId") Integer evaluationGroupId, @Param(value = "evaluateeId") Integer evaluateeId);
}
