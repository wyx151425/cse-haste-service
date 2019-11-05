package com.cse.haste.service;

import com.cse.haste.model.pojo.EvaluationScoreForm;

import java.util.List;

/**
 * 考核评价评分表
 *
 * @author WangZhenqi
 */
public interface EvaluationScoreFormService {
    /**
     * 根据用户ID查询考核评价评分表
     *
     * @param userId 用户ID
     * @return 查询到的考核评价评分表
     */
    List<EvaluationScoreForm> findEvaluationScoreFormsByUser(Integer userId);
}
