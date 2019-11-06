package com.cse.haste.service.impl;

import com.cse.haste.model.pojo.ProfessionalScoreForm;
import com.cse.haste.repository.ProfessionalScoreFormRepository;
import com.cse.haste.service.ProfessionalScoreFormService;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "professionalScoreFormService")
public class ProfessionalScoreFormServiceImpl implements ProfessionalScoreFormService {

    private final ProfessionalScoreFormRepository professionalScoreFormRepository;

    @Autowired
    public ProfessionalScoreFormServiceImpl(ProfessionalScoreFormRepository professionalScoreFormRepository) {
        this.professionalScoreFormRepository = professionalScoreFormRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProfessionalScoreForm(ProfessionalScoreForm professionalScoreForm) {
        professionalScoreForm.setObjectId(GeneratorUtil.getObjectId());
        professionalScoreForm.setStatus(Constant.Status.ENABLED);
        professionalScoreForm.setComplete(false);
        professionalScoreFormRepository.save(professionalScoreForm);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProfessionalScoreFormsByEvaluationGroup(Integer evaluationGroupId) {
        List<ProfessionalScoreForm> professionalScoreForms = professionalScoreFormRepository.findAllByEvaluationGroupId(evaluationGroupId);
        for (ProfessionalScoreForm professionalScoreForm : professionalScoreForms) {
            professionalScoreFormRepository.delete(professionalScoreForm.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProfessionalScoreForm(ProfessionalScoreForm professionalScoreForm) {
        professionalScoreForm.setComplete(false);
        professionalScoreFormRepository.update(professionalScoreForm);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProfessionalScoreForm submitProfessionalScoreForm(ProfessionalScoreForm professionalScoreForm) {
        ProfessionalScoreForm target = professionalScoreFormRepository.findOneById(professionalScoreForm.getId());
        if (!target.getComplete()) {
            professionalScoreForm.setComplete(true);
            professionalScoreForm.setCompleteAt(LocalDateTime.now().withNano(0));
            professionalScoreFormRepository.update(professionalScoreForm);
        }
        return professionalScoreFormRepository.findOneById(professionalScoreForm.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<ProfessionalScoreForm> findProfessionalScoreFormsByEvaluationGroup(Integer evaluationGroupId) {
        return professionalScoreFormRepository.findAllByEvaluationGroupId(evaluationGroupId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<ProfessionalScoreForm> findProfessionalScoreFormsByEvaluationGroupAndEvaluator(Integer evaluationGroupId, Integer evaluatorId) {
        return professionalScoreFormRepository.findAllByEvaluationGroupIdAndEvaluatorId(evaluationGroupId, evaluatorId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public ProfessionalScoreForm findProfessionalScoreFormById(Integer id) {
        return professionalScoreFormRepository.findOneById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<ProfessionalScoreForm> findProfessionalScoreFormsByEvaluator(Integer evaluatorId) {
        return professionalScoreFormRepository.findAllByEvaluatorId(evaluatorId);
    }
}
