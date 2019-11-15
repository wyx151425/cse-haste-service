package com.cse.haste.service;

import com.cse.haste.model.pojo.Evaluatee;
import com.cse.haste.model.pojo.EvaluationGroup;
import com.cse.haste.model.pojo.User;

import java.util.List;

/**
 * @author WangZhenqi
 */
public interface EvaluateeService {
    /**
     * 保存受评者
     *
     * @param evaluatee 受评者
     * @return 保存后的受评
     */
    Evaluatee saveEvaluatee(Evaluatee evaluatee);

    /**
     * 删除受评者
     *
     * @param id 受评者ID
     */
    void deleteEvaluatee(Integer id);

    /**
     * 根据考核评价计划组删除受评者
     *
     * @param evaluationGroupId 考核评价工作组ID
     */
    void deleteEvaluateesByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 通过evaluationGroupId字段更新EvaluationGroupName
     *
     * @param evaluationGroup 考核评价工作组
     */
    void updateEvaluationGroupName(EvaluationGroup evaluationGroup);

    /**
     * 根据ID查询受评者
     *
     * @param id 受评者ID
     * @return 受评者
     */
    Evaluatee findEvaluateeById(Integer id);

    /**
     * 根据考核评价计划查询受评者
     *
     * @param evaluationPlanId 考核评价计划ID
     * @return 受评者数据集合
     */
    List<Evaluatee> findEvaluateesByEvaluationPlan(Integer evaluationPlanId);

//    /**
//     * 查询未选择的受评者
//     *
//     * @param evaluationPlanId 考核评价计划ID
//     * @return 受评者数据集合
//     */
//    List<User> findNotSelectEvaluateesByEvaluationPlan(Integer evaluationPlanId);

    /**
     * 查询未选择的受评者
     *
     * @param evaluationPlanId 考核评价计划ID
     * @param name 名称
     * @return 受评者数据集合
     */
    List<User> findNotSelectEvaluateesByEvaluationPlanAndNameLike(Integer evaluationPlanId, String name);

    /**
     * 根据考核评价工作组查询受评者
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @return 受评者数据集合
     */
    List<Evaluatee> findEvaluateesByEvaluationGroup(Integer evaluationGroupId);

//    /**
//     * 查询未选择的受评者
//     *
//     * @param evaluationGroupId 考核评价工作组ID
//     * @return 未选择的受评者数据集合
//     */
//    List<User> findNotSelectEvaluateesByEvaluationGroup(Integer evaluationGroupId);

    /**
     * 查询未选择的受评者
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param name 名称
     * @return 未选择的受评者数据集合
     */
    List<User> findNotSelectEvaluateesByEvaluationGroupAndNameLike(Integer evaluationGroupId, String name);

    /**
     * 根据考核评价工作组和关联用户查询受评者
     *
     * @param evaluationGroupId 考核评价工作组ID
     * @param userId            关联用户ID
     * @return 受评者
     */
    Evaluatee findEvaluateeByEvaluationGroupAndUser(Integer evaluationGroupId, Integer userId);
}
