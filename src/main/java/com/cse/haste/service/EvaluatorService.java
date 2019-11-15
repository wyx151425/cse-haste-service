package com.cse.haste.service;

import com.cse.haste.model.pojo.EvaluationGroup;
import com.cse.haste.model.pojo.Evaluator;
import com.cse.haste.model.pojo.User;

import java.util.List;

/**
 * @author WangZhenqi
 */
public interface EvaluatorService {
    /**
     * 保存评价者
     *
     * @param evaluator 评价者
     * @return 保存后的受评
     */
    Evaluator saveEvaluator(Evaluator evaluator);

    /**
     * 删除评价者
     *
     * @param id 评价者ID
     */
    void deleteEvaluator(Integer id);

    /**
     * 根据考核评价计划组删除评价者
     *
     * @param evaluationGroupId 考核评价工作组ID
     */
    void deleteEvaluatorsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 通过evaluationGroupId字段更新EvaluationGroupName
     *
     * @param evaluationGroup 考核评价工作组
     */
    void updateEvaluationGroupName(EvaluationGroup evaluationGroup);

    /**
     * 根据ID查询评价者
     *
     * @param id 评价者ID
     * @return 评价者
     */
    Evaluator findEvaluatorById(Integer id);

    /**
     * 根据考核评价工作组查询评价者
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 评价者数据集合
     */
    List<Evaluator> findEvaluatorsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 根据用户ID查询评价者
     *
     * @param userId 用户ID
     * @return 评价者数据集合
     */
    List<Evaluator> findEvaluatorsByUser(Integer userId);

//    /**
//     * 查询未选择的评价者
//     *
//     * @param evaluationGroupId 考核评价工作组ID
//     * @return 未选择的评价者数据集合
//     */
//    List<User> findNotSelectEvaluatorsByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 查询未选择的评价者
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param name              名称
     * @return 未选择的评价者数据集合
     */
    List<User> findNotSelectEvaluatorsByEvaluationGroupAndNameLike(Integer evaluationGroupId, String name);
}
