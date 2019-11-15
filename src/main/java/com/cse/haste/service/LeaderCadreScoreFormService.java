package com.cse.haste.service;

import com.cse.haste.model.pojo.EvaluationGroup;
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
     * 删除评价者的评分表
     *
     * @param evaluatorId 评价者ID
     */
    void deleteLeaderCadreScoreFormsByEvaluator(Integer evaluatorId);

    /**
     * 根据考核评价计划组删除领导干部评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     */
    void deleteLeaderCadreScoreFormsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 领导干部评价评分表
     *
     * @param leaderCadreScoreForm 领导干部评价评分表
     */
    void updateLeaderCadreScoreForm(LeaderCadreScoreForm leaderCadreScoreForm);

    /**
     * 通过evaluationGroupId字段更新EvaluationGroupName
     *
     * @param evaluationGroup 考核评价工作组
     */
    void updateEvaluationGroupName(EvaluationGroup evaluationGroup);

    /**
     * 提交领导干部评价评分表
     *
     * @param leaderCadreScoreForm 领导干部评价评分表
     * @return 更新后的领导干部评价评分表
     */
    LeaderCadreScoreForm submitLeaderCadreScoreForm(LeaderCadreScoreForm leaderCadreScoreForm);

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

    /**
     * 根据考核评价工作组和受评者查询领导干部考核评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param evaluateeId       受评者ID
     * @return 领导干部考核评价表
     */
    List<LeaderCadreScoreForm> findLeaderCadreScoreFormsByEvaluationGroupAndEvaluatee(Integer evaluationGroupId, Integer evaluateeId);
}
