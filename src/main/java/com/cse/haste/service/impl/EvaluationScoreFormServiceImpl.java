package com.cse.haste.service.impl;

import com.cse.haste.model.dto.Excel;
import com.cse.haste.model.pojo.*;
import com.cse.haste.service.*;
import com.cse.haste.util.Constant;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "evaluationScoreFormService")
public class EvaluationScoreFormServiceImpl implements EvaluationScoreFormService {

    private final EvaluatorService evaluatorService;
    private final LeadershipScoreFormService leadershipScoreFormService;
    private final LeaderCadreScoreFormService leaderCadreScoreFormService;
    private final DepartmentCadreScoreFormService departmentCadreScoreFormService;
    private final ProfessionalScoreFormService professionalScoreFormService;

    private EvaluationPlanService evaluationPlanService;
    private EvaluationGroupService evaluationGroupService;
    private EvaluateeService evaluateeService;

    @Autowired
    public EvaluationScoreFormServiceImpl(EvaluatorService evaluatorService, LeadershipScoreFormService leadershipScoreFormService, LeaderCadreScoreFormService leaderCadreScoreFormService, DepartmentCadreScoreFormService departmentCadreScoreFormService, ProfessionalScoreFormService professionalScoreFormService) {
        this.evaluatorService = evaluatorService;
        this.leadershipScoreFormService = leadershipScoreFormService;
        this.leaderCadreScoreFormService = leaderCadreScoreFormService;
        this.departmentCadreScoreFormService = departmentCadreScoreFormService;
        this.professionalScoreFormService = professionalScoreFormService;
    }

    @Autowired
    public void setEvaluationPlanService(EvaluationPlanService evaluationPlanService) {
        this.evaluationPlanService = evaluationPlanService;
    }

    @Autowired
    public void setEvaluationGroupService(EvaluationGroupService evaluationGroupService) {
        this.evaluationGroupService = evaluationGroupService;
    }

    @Autowired
    public void setEvaluateeService(EvaluateeService evaluateeService) {
        this.evaluateeService = evaluateeService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<EvaluationScoreForm> findEvaluationScoreFormsByEvaluationGroup(Integer evaluationGroupId) {
        List<EvaluationScoreForm> evaluationScoreForms = new ArrayList<>();
        List<LeadershipScoreForm> leadershipScoreForms = leadershipScoreFormService.findLeadershipScoreFormsByEvaluationGroup(evaluationGroupId);
        List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormService.findLeaderCadreScoreFormsByEvaluationGroup(evaluationGroupId);
        List<DepartmentCadreScoreForm> departmentCadreScoreForms = departmentCadreScoreFormService.findDepartmentCadreScoreFormsByEvaluationGroup(evaluationGroupId);
        List<ProfessionalScoreForm> professionalScoreForms = professionalScoreFormService.findProfessionalScoreFormsByEvaluationGroup(evaluationGroupId);
        evaluationScoreForms.addAll(leadershipScoreForms);
        evaluationScoreForms.addAll(leaderCadreScoreForms);
        evaluationScoreForms.addAll(departmentCadreScoreForms);
        evaluationScoreForms.addAll(professionalScoreForms);
        return evaluationScoreForms;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<EvaluationScoreForm> findEvaluationScoreFormsByUser(Integer userId) {
        List<Evaluator> evaluators = evaluatorService.findEvaluatorsByUser(userId);
        List<EvaluationScoreForm> evaluationScoreForms = new ArrayList<>();
        for (Evaluator evaluator : evaluators) {
            List<LeadershipScoreForm> leadershipScoreForms = leadershipScoreFormService.findLeadershipScoreFormsByEvaluator(evaluator.getId());
            List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormService.findLeaderCadreScoreFormsByEvaluator(evaluator.getId());
            List<DepartmentCadreScoreForm> departmentCadreScoreForms = departmentCadreScoreFormService.findDepartmentCadreScoreFormsByEvaluator(evaluator.getId());
            List<ProfessionalScoreForm> professionalScoreForms = professionalScoreFormService.findProfessionalScoreFormsByEvaluator(evaluator.getId());
            evaluationScoreForms.addAll(leadershipScoreForms);
            evaluationScoreForms.addAll(leaderCadreScoreForms);
            evaluationScoreForms.addAll(departmentCadreScoreForms);
            evaluationScoreForms.addAll(professionalScoreForms);
        }
        return evaluationScoreForms;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Excel exportEvaluationScoreFormByEvaluatee(Evaluatee evaluatee) {
        Integer evaluationPlanId = evaluatee.getEvaluationPlanId();
        EvaluationPlan evaluationPlan = evaluationPlanService.findEvaluationPlanById(evaluatee.getEvaluationPlanId());
        List<EvaluationGroup> evaluationGroups = evaluationGroupService.findEvaluationGroupsByEvaluationPlan(evaluationPlanId);

        Excel excel = null;
        if (Constant.EvaluationPlan.Types.LEADERSHIP_EVALUATION_PLAN == evaluationPlan.getType()) {
            excel = createLeadershipScoreStatisticExcel(evaluatee, evaluationGroups);
        } else if (Constant.EvaluationPlan.Types.LEADER_CADRE_EVALUATION_PLAN == evaluationPlan.getType()) {
            excel = createLeaderCadreScoreStatisticExcel(evaluatee, evaluationGroups);
        } else if (Constant.EvaluationPlan.Types.DEPARTMENT_CADRE_EVALUATION_PLAN == evaluationPlan.getType()) {
            excel = createDepartmentCadreScoreStatisticExcel(evaluatee, evaluationGroups);
        } else if (Constant.EvaluationPlan.Types.PROFESSIONAL_EVALUATION_PLAN == evaluationPlan.getType()) {
            excel = createProfessionalScoreStatisticExcel(evaluatee, evaluationGroups);
        }

        return excel;
    }

    private Excel createLeadershipScoreStatisticExcel(Evaluatee planEvaluatee, List<EvaluationGroup> evaluationGroups) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (EvaluationGroup evaluationGroup : evaluationGroups) {
            List<LeadershipScoreForm> leadershipScoreForms = leadershipScoreFormService.findLeadershipScoreFormsByEvaluationGroupAndEvaluatee(evaluationGroup.getId(), planEvaluatee.getId());

            Integer sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
            Integer index = 0;

            XSSFSheet sheet = workbook.createSheet(evaluationGroup.getName());
            Row row0 = sheet.createRow(0);
            Cell cell00 = row0.createCell(index);
            cell00.setCellValue("");
            Row row1 = sheet.createRow(1);
            Cell cell10 = row1.createCell(index);
            cell10.setCellValue("政治素质");
            Row row2 = sheet.createRow(2);
            Cell cell20 = row2.createCell(index);
            cell20.setCellValue("经营业绩");
            Row row3 = sheet.createRow(3);
            Cell cell30 = row3.createCell(index);
            cell30.setCellValue("团结协作");
            Row row4 = sheet.createRow(4);
            Cell cell40 = row4.createCell(index);
            cell40.setCellValue("作风形象");

            for (LeadershipScoreForm score : leadershipScoreForms) {
                index++;
                Cell cell0 = row0.createCell(index);
                cell0.setCellValue(score.getEvaluatorName());
                Integer item1 = score.getPoliticsQuality1() + score.getPoliticsQuality2() + score.getPoliticsQuality3();
                Cell cell1 = row1.createCell(index);
                cell1.setCellValue(item1);
                sum1 += item1;
                Integer item2 = score.getOperatePerformance1() + score.getOperatePerformance2() + score.getOperatePerformance3() + score.getOperatePerformance4();
                Cell cell2 = row2.createCell(index);
                cell2.setCellValue(item2);
                sum2 += item2;
                Integer item3 = score.getTeamwork1() + score.getTeamwork2() + score.getTeamwork3();
                Cell cell3 = row3.createCell(index);
                cell3.setCellValue(item3);
                sum3 += item3;
                Integer item4 = score.getStyleAndImage1() + score.getTeamwork2() + score.getTeamwork3();
                Cell cell4 = row4.createCell(index);
                cell4.setCellValue(item4);
                sum4 += item4;
            }
            Cell cells0 = row0.createCell(index + 1);
            cells0.setCellValue("平均分");
            Cell cells1 = row1.createCell(index + 1);
            cells1.setCellValue(sum1 / index);
            Cell cells2 = row2.createCell(index + 1);
            cells2.setCellValue(sum2 / index);
            Cell cells3 = row3.createCell(index + 1);
            cells3.setCellValue(sum3 / index);
            Cell cells4 = row4.createCell(index + 1);
            cells4.setCellValue(sum4 / index);
        }
        return new Excel(planEvaluatee.getUserName(), workbook);
    }

    private Excel createLeaderCadreScoreStatisticExcel(Evaluatee planEvaluatee, List<EvaluationGroup> evaluationGroups) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (EvaluationGroup evaluationGroup : evaluationGroups) {
            Evaluatee evaluatee = evaluateeService.findEvaluateeByEvaluationGroupAndUser(evaluationGroup.getId(), planEvaluatee.getUserId());
            List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormService.findLeaderCadreScoreFormsByEvaluationGroupAndEvaluatee(evaluationGroup.getId(), evaluatee.getId());

            Integer sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
            Integer index = 0;

            XSSFSheet sheet = workbook.createSheet(evaluationGroup.getName());
            Row row0 = sheet.createRow(0);
            Cell cell00 = row0.createCell(index);
            cell00.setCellValue("");
            Row row1 = sheet.createRow(1);
            Cell cell10 = row1.createCell(index);
            cell10.setCellValue("政治表现");
            Row row2 = sheet.createRow(2);
            Cell cell20 = row2.createCell(index);
            cell20.setCellValue("能力素质");
            Row row3 = sheet.createRow(3);
            Cell cell30 = row3.createCell(index);
            cell30.setCellValue("工作业绩");
            Row row4 = sheet.createRow(4);
            Cell cell40 = row4.createCell(index);
            cell40.setCellValue("廉洁从业和“一岗双责”");

            for (LeaderCadreScoreForm score : leaderCadreScoreForms) {
                index++;
                Cell cell0 = row0.createCell(index);
                cell0.setCellValue(score.getEvaluatorName());
                Integer item1 = score.getPoliticsPerformance1() + score.getPoliticsPerformance2() + score.getPoliticsPerformance3() + score.getPoliticsPerformance4() + score.getPoliticsPerformance5();
                Cell cell1 = row1.createCell(index);
                cell1.setCellValue(item1);
                sum1 += item1;
                Integer item2 = score.getAbilityAndQuality1() + score.getAbilityAndQuality2() + score.getAbilityAndQuality3() + score.getAbilityAndQuality4();
                Cell cell2 = row2.createCell(index);
                cell2.setCellValue(item2);
                sum2 += item2;
                Integer item3 = score.getWorkPerformance1() + score.getWorkPerformance2();
                Cell cell3 = row3.createCell(index);
                cell3.setCellValue(item3);
                sum3 += item3;
                Integer item4 = score.getIntegrity1() + score.getIntegrity2() + score.getIntegrity3();
                Cell cell4 = row4.createCell(index);
                cell4.setCellValue(item4);
                sum4 += item4;
            }
            Cell cells0 = row0.createCell(index + 1);
            cells0.setCellValue("平均分");
            Cell cells1 = row1.createCell(index + 1);
            cells1.setCellValue(sum1 / index);
            Cell cells2 = row2.createCell(index + 1);
            cells2.setCellValue(sum2 / index);
            Cell cells3 = row3.createCell(index + 1);
            cells3.setCellValue(sum3 / index);
            Cell cells4 = row4.createCell(index + 1);
            cells4.setCellValue(sum4 / index);
        }
        return new Excel(planEvaluatee.getUserName(), workbook);
    }

    private Excel createDepartmentCadreScoreStatisticExcel(Evaluatee planEvaluatee, List<EvaluationGroup> evaluationGroups) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (EvaluationGroup evaluationGroup : evaluationGroups) {
            if (Constant.EvaluationScoreForm.Types.LEADER_CADRE_EVALUATION_SCORE_FORM == evaluationGroup.getEvaluationScoreFormType()) {
                Evaluatee evaluatee = evaluateeService.findEvaluateeByEvaluationGroupAndUser(evaluationGroup.getId(), planEvaluatee.getUserId());
                List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormService.findLeaderCadreScoreFormsByEvaluationGroupAndEvaluatee(evaluationGroup.getId(), evaluatee.getId());

                Integer sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
                Integer index = 0;

                XSSFSheet sheet = workbook.createSheet(evaluationGroup.getName());
                Row row0 = sheet.createRow(0);
                Cell cell00 = row0.createCell(index);
                cell00.setCellValue("");
                Row row1 = sheet.createRow(1);
                Cell cell10 = row1.createCell(index);
                cell10.setCellValue("政治表现");
                Row row2 = sheet.createRow(2);
                Cell cell20 = row2.createCell(index);
                cell20.setCellValue("能力素质");
                Row row3 = sheet.createRow(3);
                Cell cell30 = row3.createCell(index);
                cell30.setCellValue("工作业绩");
                Row row4 = sheet.createRow(4);
                Cell cell40 = row4.createCell(index);
                cell40.setCellValue("廉洁从业和“一岗双责”");

                for (LeaderCadreScoreForm score : leaderCadreScoreForms) {
                    index++;
                    Cell cell0 = row0.createCell(index);
                    cell0.setCellValue(score.getEvaluatorName());
                    Integer item1 = score.getPoliticsPerformance1() + score.getPoliticsPerformance2() + score.getPoliticsPerformance3() + score.getPoliticsPerformance4() + score.getPoliticsPerformance5();
                    Cell cell1 = row1.createCell(index);
                    cell1.setCellValue(item1);
                    sum1 += item1;
                    Integer item2 = score.getAbilityAndQuality1() + score.getAbilityAndQuality2() + score.getAbilityAndQuality3() + score.getAbilityAndQuality4();
                    Cell cell2 = row2.createCell(index);
                    cell2.setCellValue(item2);
                    sum2 += item2;
                    Integer item3 = score.getWorkPerformance1() + score.getWorkPerformance2();
                    Cell cell3 = row3.createCell(index);
                    cell3.setCellValue(item3);
                    sum3 += item3;
                    Integer item4 = score.getIntegrity1() + score.getIntegrity2() + score.getIntegrity3();
                    Cell cell4 = row4.createCell(index);
                    cell4.setCellValue(item4);
                    sum4 += item4;
                }
                Cell cells0 = row0.createCell(index + 1);
                cells0.setCellValue("平均分");
                Cell cells1 = row1.createCell(index + 1);
                cells1.setCellValue(sum1 / index);
                Cell cells2 = row2.createCell(index + 1);
                cells2.setCellValue(sum2 / index);
                Cell cells3 = row3.createCell(index + 1);
                cells3.setCellValue(sum3 / index);
                Cell cells4 = row4.createCell(index + 1);
                cells4.setCellValue(sum4 / index);
            }
            if (Constant.EvaluationScoreForm.Types.DEPARTMENT_CADRE_EVALUATION_SCORE_FORM == evaluationGroup.getEvaluationScoreFormType()) {
                Evaluatee evaluatee = evaluateeService.findEvaluateeByEvaluationGroupAndUser(evaluationGroup.getId(), planEvaluatee.getUserId());
                List<DepartmentCadreScoreForm> departmentCadreScoreForms = departmentCadreScoreFormService.findDepartmentCadreScoreFormsByEvaluationGroupAndEvaluatee(evaluationGroup.getId(), evaluatee.getId());

                Integer sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
                Integer index = 0;

                XSSFSheet sheet = workbook.createSheet(evaluationGroup.getName());
                Row row0 = sheet.createRow(0);
                Cell cell00 = row0.createCell(index);
                cell00.setCellValue("");
                Row row1 = sheet.createRow(1);
                Cell cell10 = row1.createCell(index);
                cell10.setCellValue("政治表现");
                Row row2 = sheet.createRow(2);
                Cell cell20 = row2.createCell(index);
                cell20.setCellValue("能力素质");
                Row row3 = sheet.createRow(3);
                Cell cell30 = row3.createCell(index);
                cell30.setCellValue("工作业绩");
                Row row4 = sheet.createRow(4);
                Cell cell40 = row4.createCell(index);
                cell40.setCellValue("廉洁从业和“一岗双责”");

                for (DepartmentCadreScoreForm score : departmentCadreScoreForms) {
                    index++;
                    Cell cell0 = row0.createCell(index);
                    cell0.setCellValue(score.getEvaluatorName());
                    Integer item1 = score.getPoliticsPerformance();
                    Cell cell1 = row1.createCell(index);
                    cell1.setCellValue(item1);
                    sum1 += item1;
                    Integer item2 = score.getAbilityAndQuality();
                    Cell cell2 = row2.createCell(index);
                    cell2.setCellValue(item2);
                    sum2 += item2;
                    Integer item3 = score.getWorkPerformance();
                    Cell cell3 = row3.createCell(index);
                    cell3.setCellValue(item3);
                    sum3 += item3;
                    Integer item4 = score.getIntegrity();
                    Cell cell4 = row4.createCell(index);
                    cell4.setCellValue(item4);
                    sum4 += item4;
                }
                Cell cells0 = row0.createCell(index + 1);
                cells0.setCellValue("平均分");
                Cell cells1 = row1.createCell(index + 1);
                cells1.setCellValue(sum1 / index);
                Cell cells2 = row2.createCell(index + 1);
                cells2.setCellValue(sum2 / index);
                Cell cells3 = row3.createCell(index + 1);
                cells3.setCellValue(sum3 / index);
                Cell cells4 = row4.createCell(index + 1);
                cells4.setCellValue(sum4 / index);
            }
        }
        return new Excel(planEvaluatee.getUserName(), workbook);
    }

    private Excel createProfessionalScoreStatisticExcel(Evaluatee planEvaluatee, List<EvaluationGroup> evaluationGroups) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (EvaluationGroup evaluationGroup : evaluationGroups) {
            Evaluatee evaluatee = evaluateeService.findEvaluateeByEvaluationGroupAndUser(evaluationGroup.getId(), planEvaluatee.getUserId());
            List<ProfessionalScoreForm> professionalScoreForms = professionalScoreFormService.findProfessionalScoreFormsByEvaluationGroupAndEvaluatee(evaluationGroup.getId(), evaluatee.getId());

            Integer sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
            Integer index = 0;

            XSSFSheet sheet = workbook.createSheet(evaluationGroup.getName());
            Row row0 = sheet.createRow(0);
            Cell cell00 = row0.createCell(index);
            cell00.setCellValue("");
            Row row1 = sheet.createRow(1);
            Cell cell10 = row1.createCell(index);
            cell10.setCellValue("品德");
            Row row2 = sheet.createRow(2);
            Cell cell20 = row2.createCell(index);
            cell20.setCellValue("素质");
            Row row3 = sheet.createRow(3);
            Cell cell30 = row3.createCell(index);
            cell30.setCellValue("能力");
            Row row4 = sheet.createRow(4);
            Cell cell40 = row4.createCell(index);
            cell40.setCellValue("业绩");

            for (ProfessionalScoreForm score : professionalScoreForms) {
                index++;
                Cell cell0 = row0.createCell(index);
                cell0.setCellValue(score.getEvaluatorName());
                Integer item1 = score.getMoral1() + score.getMoral2();
                Cell cell1 = row1.createCell(index);
                cell1.setCellValue(item1);
                sum1 += item1;
                Integer item2 = score.getQuality1() + score.getQuality2() + score.getQuality3();
                Cell cell2 = row2.createCell(index);
                cell2.setCellValue(item2);
                sum2 += item2;
                Integer item3 = score.getAbility1() + score.getAbility2() + score.getAbility3();
                Cell cell3 = row3.createCell(index);
                cell3.setCellValue(item3);
                sum3 += item3;
                Integer item4 = score.getPerformance1() + score.getPerformance2() + score.getPerformance3();
                Cell cell4 = row4.createCell(index);
                cell4.setCellValue(item4);
                sum4 += item4;
            }
            Cell cells0 = row0.createCell(index + 1);
            cells0.setCellValue("平均分");
            Cell cells1 = row1.createCell(index + 1);
            cells1.setCellValue(sum1 / index);
            Cell cells2 = row2.createCell(index + 1);
            cells2.setCellValue(sum2 / index);
            Cell cells3 = row3.createCell(index + 1);
            cells3.setCellValue(sum3 / index);
            Cell cells4 = row4.createCell(index + 1);
            cells4.setCellValue(sum4 / index);
        }
        return new Excel(planEvaluatee.getUserName(), workbook);
    }
}
