package com.cse.haste.service;

import com.cse.haste.model.pojo.EvaluationGroup;
import com.cse.haste.model.pojo.Evaluator;
import com.cse.haste.model.pojo.LeadershipScoreForm;

import java.util.List;

/**
 * @author WangZhenqi
 */
public interface LeadershipScoreFormService {
    /**
     * 保存领导班子评价评分表
     *
     * @param leadershipScoreForm 领导班子评价评分表
     */
    void saveLeadershipScoreForm(LeadershipScoreForm leadershipScoreForm);

    /**
     * 删除评价者的评分表
     *
     * @param evaluatorId 评价者ID
     */
    void deleteLeadershipScoreFormsByEvaluator(Integer evaluatorId);

    /**
     * 根据考核评价计划组删除领导班子评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     */
    void deleteLeadershipScoreFormsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 领导班子评价评分表
     *
     * @param leadershipScoreForm 领导班子评价评分表
     */
    void updateLeadershipScoreForm(LeadershipScoreForm leadershipScoreForm);

    /**
     * 通过evaluationGroupId字段更新EvaluationGroupName
     *
     * @param evaluationGroup 考核评价工作组
     */
    void updateEvaluationGroupName(EvaluationGroup evaluationGroup);

    /**
     * 提交领导班子评价评分表
     *
     * @param leadershipScoreForm 领导班子评价评分表
     * @return 更新后的领导班子评价评分表
     */
    LeadershipScoreForm submitLeadershipScoreForm(LeadershipScoreForm leadershipScoreForm);

    /**
     * 根据考核评价工作组ID查询领导班子评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 领导班子评价评分表数据集合
     */
    List<LeadershipScoreForm> findLeadershipScoreFormsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 根据考核评价工作组ID和评价者ID查询领导班子评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param evaluatorId       评价者ID
     * @return 领导班子评价评分表数据集合
     */
    List<LeadershipScoreForm> findLeadershipScoreFormsByEvaluationGroupAndEvaluator(Integer evaluationGroupId, Integer evaluatorId);

    /**
     * 根据ID查询领导班子评价评分表
     *
     * @param id 领导班子评价评分表ID
     * @return 领导班子评价评分表
     */
    LeadershipScoreForm findLeadershipScoreFormById(Integer id);

    /**
     * 根据受评者和评价者查询领导班子评价评分表
     *
     * @param evaluatorId 评价者ID
     * @return 领导班子评价评分表
     */
    List<LeadershipScoreForm> findLeadershipScoreFormsByEvaluator(Integer evaluatorId);

    /**
     * 根据考核评价工作组和受评者查询领导班子考核评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param evaluateeId       受评者ID
     * @return 领导班子考核评价表
     */
    List<LeadershipScoreForm> findLeadershipScoreFormsByEvaluationGroupAndEvaluatee(Integer evaluationGroupId, Integer evaluateeId);
}
