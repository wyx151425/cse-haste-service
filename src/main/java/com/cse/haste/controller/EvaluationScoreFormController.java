package com.cse.haste.controller;

import com.cse.haste.context.HasteException;
import com.cse.haste.model.dto.EvaluationPlanExcel;
import com.cse.haste.model.dto.Excel;
import com.cse.haste.model.dto.Response;
import com.cse.haste.model.pojo.*;
import com.cse.haste.service.*;
import com.cse.haste.util.Constant;
import com.cse.haste.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author WangZhenqi
 */
@RestController
@RequestMapping(value = "api")
public class EvaluationScoreFormController extends HasteFacade {

    private final LeadershipScoreFormService leadershipScoreFormService;
    private final LeaderCadreScoreFormService leaderCadreScoreFormService;
    private final ProfessionalScoreFormService professionalScoreFormService;
    private final DepartmentCadreScoreFormService departmentCadreScoreFormService;
    private final EvaluationScoreFormService evaluationScoreFormService;

    @Autowired
    public EvaluationScoreFormController(LeadershipScoreFormService leadershipScoreFormService, LeaderCadreScoreFormService leaderCadreScoreFormService, ProfessionalScoreFormService professionalScoreFormService, DepartmentCadreScoreFormService departmentCadreScoreFormService, EvaluationScoreFormService evaluationScoreFormService) {
        this.leadershipScoreFormService = leadershipScoreFormService;
        this.leaderCadreScoreFormService = leaderCadreScoreFormService;
        this.professionalScoreFormService = professionalScoreFormService;
        this.departmentCadreScoreFormService = departmentCadreScoreFormService;
        this.evaluationScoreFormService = evaluationScoreFormService;
    }

    @PutMapping(value = "leadershipScoreForms")
    public Response<LeadershipScoreForm> actionUpdateLeadershipScoreForm(@RequestBody LeadershipScoreForm leadershipScoreForm) {
        leadershipScoreFormService.updateLeadershipScoreForm(leadershipScoreForm);
        return new Response<>();
    }

    @PutMapping(value = "leaderCadreScoreForms")
    public Response<LeaderCadreScoreForm> actionUpdateLeaderCadreScoreForm(@RequestBody LeaderCadreScoreForm leaderCadreScoreForm) {
        leaderCadreScoreFormService.updateLeaderCadreScoreForm(leaderCadreScoreForm);
        return new Response<>();
    }

    @PutMapping(value = "departmentCadreScoreForms")
    public Response<DepartmentCadreScoreForm> actionUpdateDepartmentCadreScoreForm(@RequestBody DepartmentCadreScoreForm departmentCadreScoreForm) {
        departmentCadreScoreFormService.updateDepartmentCadreScoreForm(departmentCadreScoreForm);
        return new Response<>();
    }

    @PutMapping(value = "professionalScoreForms")
    public Response<ProfessionalScoreForm> actionUpdateProfessionalScoreForm(@RequestBody ProfessionalScoreForm professionalScoreForm) {
        professionalScoreFormService.updateProfessionalScoreForm(professionalScoreForm);
        return new Response<>();
    }

    @PutMapping(value = "leadershipScoreForms/submit")
    public Response<LeadershipScoreForm> actionSubmitLeadershipScoreForm(@RequestBody LeadershipScoreForm leadershipScoreForm) {
        LeadershipScoreForm newLeadershipScoreForm = leadershipScoreFormService.submitLeadershipScoreForm(leadershipScoreForm);
        return new Response<>(newLeadershipScoreForm);
    }

    @PutMapping(value = "leaderCadreScoreForms/submit")
    public Response<LeaderCadreScoreForm> actionSubmitLeaderCadreScoreForm(@RequestBody LeaderCadreScoreForm leaderCadreScoreForm) {
        LeaderCadreScoreForm newLeaderCadreScoreForm = leaderCadreScoreFormService.submitLeaderCadreScoreForm(leaderCadreScoreForm);
        return new Response<>(newLeaderCadreScoreForm);
    }

    @PutMapping(value = "departmentCadreScoreForms/submit")
    public Response<DepartmentCadreScoreForm> actionSubmitDepartmentCadreScoreForm(@RequestBody DepartmentCadreScoreForm departmentCadreScoreForm) {
        DepartmentCadreScoreForm newDepartmentCadreScoreForm = departmentCadreScoreFormService.submitDepartmentCadreScoreForm(departmentCadreScoreForm);
        return new Response<>(newDepartmentCadreScoreForm);
    }

    @PutMapping(value = "professionalScoreForms/submit")
    public Response<ProfessionalScoreForm> actionSubmitProfessionalScoreForm(@RequestBody ProfessionalScoreForm professionalScoreForm) {
        ProfessionalScoreForm newProfessionalScoreForm = professionalScoreFormService.submitProfessionalScoreForm(professionalScoreForm);
        return new Response<>(newProfessionalScoreForm);
    }

    @GetMapping(value = "leadershipScoreForms/{id}")
    public Response<LeadershipScoreForm> actionQueryLeadershipScoreFormsById(@PathVariable(value = "id") Integer id) {
        LeadershipScoreForm leadershipScoreForm = leadershipScoreFormService.findLeadershipScoreFormById(id);
        return new Response<>(leadershipScoreForm);
    }

    @GetMapping(value = "leaderCadreScoreForms/{id}")
    public Response<LeaderCadreScoreForm> actionQueryLeaderCadreScoreFormsById(@PathVariable(value = "id") Integer id) {
        LeaderCadreScoreForm leaderCadreScoreForm = leaderCadreScoreFormService.findLeaderCadreScoreFormById(id);
        return new Response<>(leaderCadreScoreForm);
    }

    @GetMapping(value = "departmentCadreScoreForms/{id}")
    public Response<DepartmentCadreScoreForm> actionQueryDepartmentCadreScoreFormsById(@PathVariable(value = "id") Integer id) {
        DepartmentCadreScoreForm departmentCadreScoreForm = departmentCadreScoreFormService.findDepartmentCadreScoreFormById(id);
        return new Response<>(departmentCadreScoreForm);
    }

    @GetMapping(value = "professionalScoreForms/{id}")
    public Response<ProfessionalScoreForm> actionQueryProfessionalScoreFormsById(@PathVariable(value = "id") Integer id) {
        ProfessionalScoreForm professionalScoreForm = professionalScoreFormService.findProfessionalScoreFormById(id);
        return new Response<>(professionalScoreForm);
    }

    @GetMapping(value = "evaluators/{evaluatorId}/leadershipScoreForms")
    public Response<List<LeadershipScoreForm>> actionQueryLeadershipScoreFormsByEvaluator(@PathVariable(value = "evaluatorId") Integer evaluatorId) {
        List<LeadershipScoreForm> leadershipScoreForms = leadershipScoreFormService.findLeadershipScoreFormsByEvaluator(evaluatorId);
        return new Response<>(leadershipScoreForms);
    }

    @GetMapping(value = "evaluators/{evaluatorId}/leaderCadreScoreForms")
    public Response<List<LeaderCadreScoreForm>> actionQueryLeaderCadreScoreFormsByEvaluator(@PathVariable(value = "evaluatorId") Integer evaluatorId) {
        List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormService.findLeaderCadreScoreFormsByEvaluator(evaluatorId);
        return new Response<>(leaderCadreScoreForms);
    }

    @GetMapping(value = "evaluators/{evaluatorId}/departmentCadreScoreForms")
    public Response<List<DepartmentCadreScoreForm>> actionQueryDepartmentCadreScoreFormsByEvaluator(@PathVariable(value = "evaluatorId") Integer evaluatorId) {
        List<DepartmentCadreScoreForm> departmentCadreScoreForms = departmentCadreScoreFormService.findDepartmentCadreScoreFormsByEvaluator(evaluatorId);
        return new Response<>(departmentCadreScoreForms);
    }

    @GetMapping(value = "evaluators/{evaluatorId}/professionalScoreForms")
    public Response<List<ProfessionalScoreForm>> actionQueryProfessionalScoreFormByEvaluator(@PathVariable(value = "evaluatorId") Integer evaluatorId) {
        List<ProfessionalScoreForm> professionalScoreForms = professionalScoreFormService.findProfessionalScoreFormsByEvaluator(evaluatorId);
        return new Response<>(professionalScoreForms);
    }

    @GetMapping(value = "evaluationGroups/{evaluationGroupId}/evaluationScoreForms")
    public Response<List<EvaluationScoreForm>> actionQueryEvaluationScoreFormByEvaluator(@PathVariable(value = "evaluationGroupId") Integer evaluationGroupId) {
        List<EvaluationScoreForm> evaluationScoreForms = evaluationScoreFormService.findEvaluationScoreFormsByEvaluationGroup(evaluationGroupId);
        return new Response<>(evaluationScoreForms);
    }

    @GetMapping(value = "users/{userId}/evaluationScoreForms")
    public Response<List<EvaluationScoreForm>> actionQueryEvaluationScoreFormsByUser(@PathVariable(value = "userId") Integer userId) {
        List<EvaluationScoreForm> evaluationScoreForms = evaluationScoreFormService.findEvaluationScoreFormsByUser(userId);
        return new Response<>(evaluationScoreForms);
    }

    @PostMapping(value = "evaluationScoreForms/export")
    public void actionExportEvaluationScoreFormByEvaluatee(@RequestBody Evaluatee evaluatee) {
        Excel excel = evaluationScoreFormService.exportEvaluationScoreFormByEvaluatee(evaluatee);
        try {
            getResponse().reset();
            getResponse().setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excel.getName(), "UTF-8"));
            getResponse().setContentType("application/octet-stream");
            OutputStream out = getResponse().getOutputStream();
            BufferedOutputStream buffer = new BufferedOutputStream(out);
            buffer.flush();
            excel.getWorkbook().write(buffer);
            buffer.close();
        } catch (IOException e) {
            throw new HasteException(e, StatusCode.FILE_RESOLVE_ERROR);
        }
    }

    @PostMapping(value = "evaluationPlans/{evaluationPlanId}/evaluationScoreForms/export")
    public void actionExportEvaluationScoreFormByEvaluationPlan(@PathVariable(value = "evaluationPlanId") Integer evaluationPlanId) {
        EvaluationPlanExcel evaluationPlanExcel = evaluationScoreFormService.exportEvaluationScoreFormsByEvaluationPlan(evaluationPlanId);
        ZipOutputStream zipOutStr = null;
        try {
            getResponse().reset();
            getResponse().setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(evaluationPlanExcel.getEvaluationPlanName() + ".zip", "UTF-8"));
            getResponse().setContentType("application/octet-stream");
            OutputStream out = getResponse().getOutputStream();
            BufferedOutputStream buffer = new BufferedOutputStream(out);
            buffer.flush();
            zipOutStr = new ZipOutputStream(buffer);
            for (Excel excel : evaluationPlanExcel.getExcels()) {
                File file = File.createTempFile("file-", ".xlsx");
                FileOutputStream fos = new FileOutputStream(file);
                excel.getWorkbook().write(fos);
                zipOutStr.putNextEntry(new ZipEntry(excel.getName() + ".xlsx"));
                FileInputStream fis = new FileInputStream(file);
                int len;
                byte[] bytes = new byte[2048];
                while ((len = fis.read(bytes)) != -1) {
                    zipOutStr.write(bytes, 0, len);
                }
                zipOutStr.closeEntry();
            }
            buffer.close();
        } catch (IOException e) {
            throw new HasteException(e, StatusCode.FILE_RESOLVE_ERROR);
        } finally {
            if (null != zipOutStr) {
                try {
                    zipOutStr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
