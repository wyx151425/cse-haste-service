package com.cse.haste.service;

import com.cse.haste.model.dto.Excel;
import com.cse.haste.model.pojo.Evaluatee;
import com.cse.haste.model.pojo.EvaluationScoreForm;

import java.util.List;

/**
 * 考核评价评分表
 *
 * @author WangZhenqi
 */
public interface EvaluationScoreFormService {
    /**
     * 根据考核评价工作组ID查询考核评价评分表
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 查询到的考核评价评分表
     */
    List<EvaluationScoreForm> findEvaluationScoreFormsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 根据用户ID查询考核评价评分表
     *
     * @param userId 用户ID
     * @return 查询到的考核评价评分表
     */
    List<EvaluationScoreForm> findEvaluationScoreFormsByUser(Integer userId);

    /**
     * 根据受评者导出评分表
     *
     * @param evaluatee 受评者
     * @return 编制完成的EXCEL表格
     */
    Excel exportEvaluationScoreFormByEvaluatee(Evaluatee evaluatee);
}
