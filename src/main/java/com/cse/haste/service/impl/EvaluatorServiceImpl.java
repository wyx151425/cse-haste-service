package com.cse.haste.service.impl;

import com.cse.haste.model.pojo.*;
import com.cse.haste.repository.EvaluatorRepository;
import com.cse.haste.repository.UserRepository;
import com.cse.haste.service.*;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "evaluatorService")
public class EvaluatorServiceImpl implements EvaluatorService {

    private final UserRepository userRepository;
    private final EvaluatorRepository evaluatorRepository;

    private LeadershipScoreFormService leadershipScoreFormService;
    private LeaderCadreScoreFormService leaderCadreScoreFormService;
    private DepartmentCadreScoreFormService departmentCadreScoreFormService;
    private ProfessionalScoreFormService professionalScoreFormService;

    @Autowired
    public EvaluatorServiceImpl(UserRepository userRepository, EvaluatorRepository evaluatorRepository) {
        this.userRepository = userRepository;
        this.evaluatorRepository = evaluatorRepository;
    }

    @Autowired
    public void setLeadershipScoreFormService(LeadershipScoreFormService leadershipScoreFormService) {
        this.leadershipScoreFormService = leadershipScoreFormService;
    }

    @Autowired
    public void setLeaderCadreScoreFormService(LeaderCadreScoreFormService leaderCadreScoreFormService) {
        this.leaderCadreScoreFormService = leaderCadreScoreFormService;
    }

    @Autowired
    public void setDepartmentCadreScoreFormService(DepartmentCadreScoreFormService departmentCadreScoreFormService) {
        this.departmentCadreScoreFormService = departmentCadreScoreFormService;
    }

    @Autowired
    public void setProfessionalScoreFormService(ProfessionalScoreFormService professionalScoreFormService) {
        this.professionalScoreFormService = professionalScoreFormService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Evaluator saveEvaluator(Evaluator evaluator) {
        evaluator.setObjectId(GeneratorUtil.getObjectId());
        evaluator.setStatus(Constant.Status.ENABLED);
        evaluatorRepository.save(evaluator);
        return evaluatorRepository.findOneById(evaluator.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEvaluator(Integer id) {
        evaluatorRepository.delete(id);
        leadershipScoreFormService.deleteLeadershipScoreFormsByEvaluator(id);
        leaderCadreScoreFormService.deleteLeaderCadreScoreFormsByEvaluator(id);
        departmentCadreScoreFormService.deleteDepartmentCadreScoreFormsByEvaluator(id);
        professionalScoreFormService.deleteProfessionalScoreFormsByEvaluator(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEvaluatorsByEvaluationGroup(Integer evaluationGroupId) {
        List<Evaluator> evaluators = evaluatorRepository.findAllByEvaluationGroupId(evaluationGroupId);
        for (Evaluator evaluator : evaluators) {
            evaluatorRepository.delete(evaluator.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEvaluationGroupName(EvaluationGroup evaluationGroup) {
        evaluatorRepository.updateEvaluationGroupNameByEvaluationGroupId(evaluationGroup);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Evaluator findEvaluatorById(Integer id) {
        return evaluatorRepository.findOneById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Evaluator> findEvaluatorsByEvaluationGroup(Integer evaluationGroupId) {
        return evaluatorRepository.findAllByEvaluationGroupId(evaluationGroupId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Evaluator> findEvaluatorsByUser(Integer userId) {
        return evaluatorRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<User> findNotSelectEvaluatorsByEvaluationGroupAndNameLike(Integer evaluationGroupId, String name) {
        List<Evaluator> evaluators = evaluatorRepository.findAllByEvaluationGroupId(evaluationGroupId);
        if (evaluators.size() > 0) {
            List<Integer> ids = new ArrayList<>();
            for (Evaluator evaluator : evaluators) {
                ids.add(evaluator.getUserId());
            }
            if (null != name) {
                return userRepository.findAllByIdNotInAndNameLike(ids, name);
            } else {
                return userRepository.findAllByIdNotIn(ids);
            }
        }
        if (null != name) {
            return userRepository.findAllByRoleAndNameLike(Constant.Roles.USER, name);
        } else {
            return userRepository.findAllByRole(Constant.Roles.USER);
        }
    }
}
