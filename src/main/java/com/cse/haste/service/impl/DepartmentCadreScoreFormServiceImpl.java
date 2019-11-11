package com.cse.haste.service.impl;

import com.cse.haste.model.pojo.DepartmentCadreScoreForm;
import com.cse.haste.repository.DepartmentCadreScoreFormRepository;
import com.cse.haste.service.DepartmentCadreScoreFormService;
import com.cse.haste.service.EvaluationGroupService;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "departmentCadreScoreFormService")
public class DepartmentCadreScoreFormServiceImpl implements DepartmentCadreScoreFormService {

    private final DepartmentCadreScoreFormRepository departmentCadreScoreFormRepository;
    private EvaluationGroupService evaluationGroupService;

    @Autowired
    public DepartmentCadreScoreFormServiceImpl(DepartmentCadreScoreFormRepository departmentCadreScoreFormRepository) {
        this.departmentCadreScoreFormRepository = departmentCadreScoreFormRepository;
    }

    @Autowired
    @Lazy
    public void setEvaluationGroupService(EvaluationGroupService evaluationGroupService) {
        this.evaluationGroupService = evaluationGroupService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDepartmentCadreScoreForm(DepartmentCadreScoreForm departmentCadreScoreForm) {
        departmentCadreScoreForm.setObjectId(GeneratorUtil.getObjectId());
        departmentCadreScoreForm.setStatus(Constant.Status.ENABLED);
        departmentCadreScoreForm.setComplete(false);
        departmentCadreScoreFormRepository.save(departmentCadreScoreForm);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDepartmentCadreScoreFormsByEvaluationGroup(Integer evaluationGroupId) {
        List<DepartmentCadreScoreForm> departmentCadreScoreForms = departmentCadreScoreFormRepository.findAllByEvaluationGroupId(evaluationGroupId);
        for (DepartmentCadreScoreForm departmentCadreScoreForm : departmentCadreScoreForms) {
            departmentCadreScoreFormRepository.delete(departmentCadreScoreForm.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDepartmentCadreScoreForm(DepartmentCadreScoreForm departmentCadreScoreForm) {
        departmentCadreScoreForm.setComplete(false);
        departmentCadreScoreFormRepository.update(departmentCadreScoreForm);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DepartmentCadreScoreForm submitDepartmentCadreScoreForm(DepartmentCadreScoreForm departmentCadreScoreForm) {
        DepartmentCadreScoreForm target = departmentCadreScoreFormRepository.findOneById(departmentCadreScoreForm.getId());
        if (!target.getComplete()) {
            departmentCadreScoreForm.setComplete(true);
            departmentCadreScoreForm.setCompleteAt(LocalDateTime.now().withNano(0));
            departmentCadreScoreFormRepository.update(departmentCadreScoreForm);
        }
        List<DepartmentCadreScoreForm> departmentCadreScoreForms = departmentCadreScoreFormRepository.findAllByEvaluationGroupIdAndComplete(departmentCadreScoreForm.getEvaluationGroupId(), false);
        if (departmentCadreScoreForms.size() == 0) {
            evaluationGroupService.submitEvaluationGroup(departmentCadreScoreForm.getEvaluationGroupId());
        }
        return departmentCadreScoreFormRepository.findOneById(departmentCadreScoreForm.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<DepartmentCadreScoreForm> findDepartmentCadreScoreFormsByEvaluationGroup(Integer evaluationGroupId) {
        return departmentCadreScoreFormRepository.findAllByEvaluationGroupId(evaluationGroupId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<DepartmentCadreScoreForm> findDepartmentCadreScoreFormsByEvaluationGroupAndEvaluator(Integer evaluationGroupId, Integer evaluatorId) {
        return departmentCadreScoreFormRepository.findAllByEvaluationGroupIdAndEvaluatorId(evaluationGroupId, evaluatorId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public DepartmentCadreScoreForm findDepartmentCadreScoreFormById(Integer id) {
        return departmentCadreScoreFormRepository.findOneById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<DepartmentCadreScoreForm> findDepartmentCadreScoreFormsByEvaluator(Integer evaluatorId) {
        return departmentCadreScoreFormRepository.findAllByEvaluatorId(evaluatorId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<DepartmentCadreScoreForm> findDepartmentCadreScoreFormsByEvaluationGroupAndEvaluatee(Integer evaluationGroupId, Integer evaluateeId) {
        return departmentCadreScoreFormRepository.findAllByEvaluationGroupIdAndEvaluateeId(evaluationGroupId, evaluateeId);
    }
}
