package com.cse.haste.service;

import com.cse.haste.model.pojo.LeaderCadreScoreForm;

import java.util.List;

/**
 * @author WangZhenqi
 */
public interface LeaderCadreScoreFormService {
    /**
     * 保存领导干部评价评分表
     *
     * @param leaderCadreScoreForm 领导干部评价评分表
     */
    void saveLeaderCadreScoreForm(LeaderCadreScoreForm leaderCadreScoreForm);

    /**
     * 根据考核评价计划组删除领导干部评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     */
    void deleteLeaderCadreScoreFormsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 提交领导干部评价评分表
     *
     * @param leaderCadreScoreForm 领导干部评价评分表
     */
    void submitLeaderCadreScoreForm(LeaderCadreScoreForm leaderCadreScoreForm);

    /**
     * 根据考核评价工作组ID查询领导干部评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 领导干部评价评分表数据集合
     */
    List<LeaderCadreScoreForm> findLeaderCadreScoreFormsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 根据考核评价工作组ID和评价者ID查询领导干部评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param evaluatorId       评价者ID
     * @return 领导干部评价评分表数据集合
     */
    List<LeaderCadreScoreForm> findLeaderCadreScoreFormsByEvaluationGroupAndEvaluator(Integer evaluationGroupId, Integer evaluatorId);

    /**
     * 根据ID查询领导干部评价评分表
     *
     * @param id 领导干部评价评分表ID
     * @return 领导干部评价评分表
     */
    LeaderCadreScoreForm findLeaderCadreScoreFormById(Integer id);

    /**
     * 根据受评者和评价者查询领导干部评价评分表
     *
     * @param evaluatorId 评价者ID
     * @return 领导干部评价评分表
     */
    List<LeaderCadreScoreForm> findLeaderCadreScoreFormsByEvaluator(Integer evaluatorId);
}
