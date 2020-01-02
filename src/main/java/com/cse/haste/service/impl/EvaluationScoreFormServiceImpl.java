package com.cse.haste.service.impl;

import com.cse.haste.model.dto.EvaluationPlanExcel;
import com.cse.haste.model.dto.Excel;
import com.cse.haste.model.pojo.*;
import com.cse.haste.service.*;
import com.cse.haste.util.Constant;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_UP;

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

        evaluationScoreForms.sort(new Comparator<EvaluationScoreForm>() {
            @Override
            public int compare(EvaluationScoreForm o1, EvaluationScoreForm o2) {
                if (o1.getComplete() && !o2.getComplete()) {
                    return 1;
                } else if (!o1.getComplete() && o2.getComplete()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

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
        evaluationScoreForms.sort(new Comparator<EvaluationScoreForm>() {
            @Override
            public int compare(EvaluationScoreForm o1, EvaluationScoreForm o2) {
                if (o1.getComplete() && !o2.getComplete()) {
                    return 1;
                } else if (!o1.getComplete() && o2.getComplete()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
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

    @Override
    public EvaluationPlanExcel exportEvaluationScoreFormsByEvaluationPlan(Integer evaluationPlanId) {
        EvaluationPlan evaluationPlan = evaluationPlanService.findEvaluationPlanById(evaluationPlanId);
        List<Evaluatee> evaluatees = evaluateeService.findEvaluateesByEvaluationPlan(evaluationPlanId);
        List<Excel> excels = new ArrayList<>();
        for (Evaluatee evaluatee : evaluatees) {
            Excel excel = exportEvaluationScoreFormByEvaluatee(evaluatee);
            excels.add(excel);
        }
        return new EvaluationPlanExcel(evaluationPlan.getName(), excels);
    }

    private Excel createLeadershipScoreStatisticExcel(Evaluatee planEvaluatee, List<EvaluationGroup> evaluationGroups) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (EvaluationGroup evaluationGroup : evaluationGroups) {
            List<LeadershipScoreForm> leadershipScoreForms = leadershipScoreFormService.findLeadershipScoreFormsByEvaluationGroupAndEvaluatee(evaluationGroup.getId(), planEvaluatee.getId());

            BigDecimal x1Sum = new BigDecimal("0.00");
            BigDecimal x2Sum = new BigDecimal("0.00");
            BigDecimal x3Sum = new BigDecimal("0.00");
            BigDecimal x4Sum = new BigDecimal("0.00");
            BigDecimal x5Sum = new BigDecimal("0.00");
            BigDecimal x6Sum = new BigDecimal("0.00");
            BigDecimal x7Sum = new BigDecimal("0.00");
            BigDecimal x8Sum = new BigDecimal("0.00");
            BigDecimal x9Sum = new BigDecimal("0.00");
            BigDecimal x10Sum = new BigDecimal("0.00");
            BigDecimal x11Sum = new BigDecimal("0.00");
            BigDecimal x12Sum = new BigDecimal("0.00");
            BigDecimal x13Sum = new BigDecimal("0.00");

            Integer index = 0;

            XSSFSheet sheet = workbook.createSheet(evaluationGroup.getName());
            Row row0 = sheet.createRow(0);
            Row row1 = sheet.createRow(1);
            Row row2 = sheet.createRow(2);
            Row row3 = sheet.createRow(3);
            Row row4 = sheet.createRow(4);
            Row row5 = sheet.createRow(5);
            Row row6 = sheet.createRow(6);
            Row row7 = sheet.createRow(7);
            Row row8 = sheet.createRow(8);
            Row row9 = sheet.createRow(9);
            Row row10 = sheet.createRow(10);
            Row row11 = sheet.createRow(11);
            Row row12 = sheet.createRow(12);
            Row row13 = sheet.createRow(13);


            Cell cell0$0 = row0.createCell(index);
            cell0$0.setCellValue("一级指标");

            Cell cell1$0 = row1.createCell(index);
            cell1$0.setCellValue("政治素质");
            CellRangeAddress cra1$0 = new CellRangeAddress(1, 3, 0, 0);
            sheet.addMergedRegion(cra1$0);

            Cell cell4$0 = row4.createCell(index);
            cell4$0.setCellValue("经营业绩");
            CellRangeAddress cra4$0 = new CellRangeAddress(4, 7, 0, 0);
            sheet.addMergedRegion(cra4$0);

            Cell cell8$0 = row8.createCell(index);
            cell8$0.setCellValue("团结协作");
            CellRangeAddress cra8$0 = new CellRangeAddress(8, 10, 0, 0);
            sheet.addMergedRegion(cra8$0);

            Cell cell11$0 = row11.createCell(index);
            cell11$0.setCellValue("作风形象");
            CellRangeAddress cra11$0 = new CellRangeAddress(11, 13, 0, 0);
            sheet.addMergedRegion(cra11$0);

            index++;

            Cell cell0$1 = row0.createCell(index);
            cell0$1.setCellValue("二级指标");
            Cell cell1$1 = row1.createCell(index);
            cell1$1.setCellValue("政治方向");
            Cell cell2$1 = row2.createCell(index);
            cell2$1.setCellValue("党建工作");
            Cell cell3$1 = row3.createCell(index);
            cell3$1.setCellValue("社会责任");
            Cell cell4$1 = row4.createCell(index);
            cell4$1.setCellValue("经济效益");
            Cell cell5$1 = row5.createCell(index);
            cell5$1.setCellValue("可持续发展");
            Cell cell6$1 = row6.createCell(index);
            cell6$1.setCellValue("创新成效");
            Cell cell7$1 = row7.createCell(index);
            cell7$1.setCellValue("科学管理");
            Cell cell8$1 = row8.createCell(index);
            cell8$1.setCellValue("发扬民主");
            Cell cell9$1 = row9.createCell(index);
            cell9$1.setCellValue("整体合力");
            Cell cell10$1 = row10.createCell(index);
            cell10$1.setCellValue("运行机制");
            Cell cell11$1 = row11.createCell(index);
            cell11$1.setCellValue("联系群众");
            Cell cell12$1 = row12.createCell(index);
            cell12$1.setCellValue("选人用人");
            Cell cell13$1 = row13.createCell(index);
            cell13$1.setCellValue("廉洁自律");

            for (LeadershipScoreForm score : leadershipScoreForms) {
                index++;
                Cell cell0 = row0.createCell(index);
                cell0.setCellValue(score.getEvaluatorName());

                Cell cell1 = row1.createCell(index);
                cell1.setCellValue(score.getPoliticsQuality1());
                x1Sum = x1Sum.add(BigDecimal.valueOf(score.getPoliticsQuality1()));

                Cell cell2 = row2.createCell(index);
                cell2.setCellValue(score.getPoliticsQuality2());
                x2Sum = x2Sum.add(BigDecimal.valueOf(score.getPoliticsQuality2()));

                Cell cell3 = row3.createCell(index);
                cell3.setCellValue(score.getPoliticsQuality3());
                x3Sum = x3Sum.add(BigDecimal.valueOf(score.getPoliticsQuality3()));

                Cell cell4 = row4.createCell(index);
                cell4.setCellValue(score.getOperatePerformance1());
                x4Sum = x4Sum.add(BigDecimal.valueOf(score.getOperatePerformance1()));

                Cell cell5 = row5.createCell(index);
                cell5.setCellValue(score.getOperatePerformance2());
                x5Sum = x5Sum.add(BigDecimal.valueOf(score.getOperatePerformance2()));

                Cell cell6 = row6.createCell(index);
                cell6.setCellValue(score.getOperatePerformance3());
                x6Sum = x6Sum.add(BigDecimal.valueOf(score.getOperatePerformance3()));

                Cell cell7 = row7.createCell(index);
                cell7.setCellValue(score.getOperatePerformance4());
                x7Sum = x7Sum.add(BigDecimal.valueOf(score.getOperatePerformance4()));

                Cell cell8 = row8.createCell(index);
                cell8.setCellValue(score.getTeamwork1());
                x8Sum = x8Sum.add(BigDecimal.valueOf(score.getTeamwork1()));

                Cell cell9 = row9.createCell(index);
                cell9.setCellValue(score.getTeamwork2());
                x9Sum = x9Sum.add(BigDecimal.valueOf(score.getTeamwork2()));

                Cell cell10 = row10.createCell(index);
                cell10.setCellValue(score.getTeamwork3());
                x10Sum = x10Sum.add(BigDecimal.valueOf(score.getTeamwork3()));

                Cell cell11 = row11.createCell(index);
                cell11.setCellValue(score.getStyleAndImage1());
                x11Sum = x11Sum.add(BigDecimal.valueOf(score.getStyleAndImage1()));

                Cell cell12 = row12.createCell(index);
                cell12.setCellValue(score.getStyleAndImage2());
                x12Sum = x12Sum.add(BigDecimal.valueOf(score.getStyleAndImage2()));

                Cell cell13 = row13.createCell(index);
                cell13.setCellValue(score.getStyleAndImage3());
                x13Sum = x13Sum.add(BigDecimal.valueOf(score.getStyleAndImage3()));
            }

            Cell cells0 = row0.createCell(index + 1);
            cells0.setCellValue("平均分");

            Cell cells1 = row1.createCell(index + 1);
            BigDecimal x1 = x1Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells1.setCellValue(x1.toString());

            Cell cells2 = row2.createCell(index + 1);
            BigDecimal x2 = x2Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells2.setCellValue(x2.toString());

            Cell cells3 = row3.createCell(index + 1);
            BigDecimal x3 = x3Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells3.setCellValue(x3.toString());

            Cell cells4 = row4.createCell(index + 1);
            BigDecimal x4 = x4Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells4.setCellValue(x4.toString());

            Cell cells5 = row5.createCell(index + 1);
            BigDecimal x5 = x5Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells5.setCellValue(x5.toString());

            Cell cells6 = row6.createCell(index + 1);
            BigDecimal x6 = x6Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells6.setCellValue(x6.toString());

            Cell cells7 = row7.createCell(index + 1);
            BigDecimal x7 = x7Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells7.setCellValue(x7.toString());

            Cell cells8 = row8.createCell(index + 1);
            BigDecimal x8 = x8Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells8.setCellValue(x8.toString());

            Cell cells9 = row9.createCell(index + 1);
            BigDecimal x9 = x9Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells9.setCellValue(x9.toString());

            Cell cells10 = row10.createCell(index + 1);
            BigDecimal x10 = x10Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells10.setCellValue(x10.toString());

            Cell cells11 = row11.createCell(index + 1);
            BigDecimal x11 = x11Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells11.setCellValue(x11.toString());

            Cell cells12 = row12.createCell(index + 1);
            BigDecimal x12 = x12Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells12.setCellValue(x12.toString());

            Cell cells13 = row13.createCell(index + 1);
            BigDecimal x13 = x13Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells13.setCellValue(x13.toString());
        }
        return new Excel(planEvaluatee.getUserName(), workbook);
    }

    private Excel createLeaderCadreScoreStatisticExcel(Evaluatee planEvaluatee, List<EvaluationGroup> evaluationGroups) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (EvaluationGroup evaluationGroup : evaluationGroups) {
            Evaluatee evaluatee = evaluateeService.findEvaluateeByEvaluationGroupAndUser(evaluationGroup.getId(), planEvaluatee.getUserId());
            List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormService.findLeaderCadreScoreFormsByEvaluationGroupAndEvaluatee(evaluationGroup.getId(), evaluatee.getId());

            BigDecimal x1Sum = new BigDecimal("0.00");
            BigDecimal x2Sum = new BigDecimal("0.00");
            BigDecimal x3Sum = new BigDecimal("0.00");
            BigDecimal x4Sum = new BigDecimal("0.00");
            BigDecimal x5Sum = new BigDecimal("0.00");
            BigDecimal x6Sum = new BigDecimal("0.00");
            BigDecimal x7Sum = new BigDecimal("0.00");
            BigDecimal x8Sum = new BigDecimal("0.00");
            BigDecimal x9Sum = new BigDecimal("0.00");
            BigDecimal x10Sum = new BigDecimal("0.00");
            BigDecimal x11Sum = new BigDecimal("0.00");
            BigDecimal x12Sum = new BigDecimal("0.00");
            BigDecimal x13Sum = new BigDecimal("0.00");
            BigDecimal x14Sum = new BigDecimal("0.00");

            Integer index = 0;

            XSSFSheet sheet = workbook.createSheet(evaluationGroup.getName());
            Row row0 = sheet.createRow(0);
            Row row1 = sheet.createRow(1);
            Row row2 = sheet.createRow(2);
            Row row3 = sheet.createRow(3);
            Row row4 = sheet.createRow(4);
            Row row5 = sheet.createRow(5);
            Row row6 = sheet.createRow(6);
            Row row7 = sheet.createRow(7);
            Row row8 = sheet.createRow(8);
            Row row9 = sheet.createRow(9);
            Row row10 = sheet.createRow(10);
            Row row11 = sheet.createRow(11);
            Row row12 = sheet.createRow(12);
            Row row13 = sheet.createRow(13);
            Row row14 = sheet.createRow(14);


            Cell cell0$0 = row0.createCell(index);
            cell0$0.setCellValue("一级指标");

            Cell cell1$0 = row1.createCell(index);
            cell1$0.setCellValue("政治表现");
            CellRangeAddress cra1$0 = new CellRangeAddress(1, 5, 0, 0);
            sheet.addMergedRegion(cra1$0);

            Cell cell6$0 = row6.createCell(index);
            cell6$0.setCellValue("能力素质");
            CellRangeAddress cra6$0 = new CellRangeAddress(6, 9, 0, 0);
            sheet.addMergedRegion(cra6$0);

            Cell cell10$0 = row10.createCell(index);
            cell10$0.setCellValue("工作业绩");
            CellRangeAddress cra10$0 = new CellRangeAddress(10, 11, 0, 0);
            sheet.addMergedRegion(cra10$0);

            Cell cell12$0 = row12.createCell(index);
            cell12$0.setCellValue("廉洁从业和“一岗双责”");
            CellRangeAddress cra12$0 = new CellRangeAddress(12, 14, 0, 0);
            sheet.addMergedRegion(cra12$0);

            index++;

            Cell cell0$1 = row0.createCell(index);
            cell0$1.setCellValue("二级指标");
            Cell cell1$1 = row1.createCell(index);
            cell1$1.setCellValue("政治忠诚");
            Cell cell2$1 = row2.createCell(index);
            cell2$1.setCellValue("政治定力");
            Cell cell3$1 = row3.createCell(index);
            cell3$1.setCellValue("政治担当");
            Cell cell4$1 = row4.createCell(index);
            cell4$1.setCellValue("政治能力");
            Cell cell5$1 = row5.createCell(index);
            cell5$1.setCellValue("政治自律");
            Cell cell6$1 = row6.createCell(index);
            cell6$1.setCellValue("推动执行能力");
            Cell cell7$1 = row7.createCell(index);
            cell7$1.setCellValue("学习创新能力");
            Cell cell8$1 = row8.createCell(index);
            cell8$1.setCellValue("团队建设能力");
            Cell cell9$1 = row9.createCell(index);
            cell9$1.setCellValue("职业操守");
            Cell cell10$1 = row10.createCell(index);
            cell10$1.setCellValue("履职绩效");
            Cell cell11$1 = row11.createCell(index);
            cell11$1.setCellValue("协同成效");
            Cell cell12$1 = row12.createCell(index);
            cell12$1.setCellValue("作风建设");
            Cell cell13$1 = row13.createCell(index);
            cell13$1.setCellValue("廉洁自律");
            Cell cell14$1 = row14.createCell(index);
            cell14$1.setCellValue("“一岗双责”");

            for (LeaderCadreScoreForm score : leaderCadreScoreForms) {
                index++;
                Cell cell0 = row0.createCell(index);
                cell0.setCellValue(score.getEvaluatorName());

                Cell cell1 = row1.createCell(index);
                cell1.setCellValue(score.getPoliticsPerformance1());
                x1Sum = x1Sum.add(BigDecimal.valueOf(score.getPoliticsPerformance1()));

                Cell cell2 = row2.createCell(index);
                cell2.setCellValue(score.getPoliticsPerformance2());
                x2Sum = x2Sum.add(BigDecimal.valueOf(score.getPoliticsPerformance2()));

                Cell cell3 = row3.createCell(index);
                cell3.setCellValue(score.getPoliticsPerformance3());
                x3Sum = x3Sum.add(BigDecimal.valueOf(score.getPoliticsPerformance3()));

                Cell cell4 = row4.createCell(index);
                cell4.setCellValue(score.getPoliticsPerformance4());
                x4Sum = x4Sum.add(BigDecimal.valueOf(score.getPoliticsPerformance4()));

                Cell cell5 = row5.createCell(index);
                cell5.setCellValue(score.getPoliticsPerformance5());
                x5Sum = x5Sum.add(BigDecimal.valueOf(score.getPoliticsPerformance5()));

                Cell cell6 = row6.createCell(index);
                cell6.setCellValue(score.getAbilityAndQuality1());
                x6Sum = x6Sum.add(BigDecimal.valueOf(score.getAbilityAndQuality1()));

                Cell cell7 = row7.createCell(index);
                cell7.setCellValue(score.getAbilityAndQuality2());
                x7Sum = x7Sum.add(BigDecimal.valueOf(score.getAbilityAndQuality2()));

                Cell cell8 = row8.createCell(index);
                cell8.setCellValue(score.getAbilityAndQuality3());
                x8Sum = x8Sum.add(BigDecimal.valueOf(score.getAbilityAndQuality3()));

                Cell cell9 = row9.createCell(index);
                cell9.setCellValue(score.getAbilityAndQuality4());
                x9Sum = x9Sum.add(BigDecimal.valueOf(score.getAbilityAndQuality4()));

                Cell cell10 = row10.createCell(index);
                cell10.setCellValue(score.getWorkPerformance1());
                x10Sum = x10Sum.add(BigDecimal.valueOf(score.getWorkPerformance1()));

                Cell cell11 = row11.createCell(index);
                cell11.setCellValue(score.getWorkPerformance2());
                x11Sum = x11Sum.add(BigDecimal.valueOf(score.getWorkPerformance2()));

                Cell cell12 = row12.createCell(index);
                cell12.setCellValue(score.getIntegrity1());
                x12Sum = x12Sum.add(BigDecimal.valueOf(score.getIntegrity1()));

                Cell cell13 = row13.createCell(index);
                cell13.setCellValue(score.getIntegrity2());
                x13Sum = x13Sum.add(BigDecimal.valueOf(score.getIntegrity2()));

                Cell cell14 = row14.createCell(index);
                cell14.setCellValue(score.getIntegrity3());
                x14Sum = x14Sum.add(BigDecimal.valueOf(score.getIntegrity3()));
            }

            Cell cells0 = row0.createCell(index + 1);
            cells0.setCellValue("平均分");

            Cell cells1 = row1.createCell(index + 1);
            BigDecimal x1 = x1Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells1.setCellValue(x1.toString());

            Cell cells2 = row2.createCell(index + 1);
            BigDecimal x2 = x2Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells2.setCellValue(x2.toString());

            Cell cells3 = row3.createCell(index + 1);
            BigDecimal x3 = x3Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells3.setCellValue(x3.toString());

            Cell cells4 = row4.createCell(index + 1);
            BigDecimal x4 = x4Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells4.setCellValue(x4.toString());

            Cell cells5 = row5.createCell(index + 1);
            BigDecimal x5 = x5Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells5.setCellValue(x5.toString());

            Cell cells6 = row6.createCell(index + 1);
            BigDecimal x6 = x6Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells6.setCellValue(x6.toString());

            Cell cells7 = row7.createCell(index + 1);
            BigDecimal x7 = x7Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells7.setCellValue(x7.toString());

            Cell cells8 = row8.createCell(index + 1);
            BigDecimal x8 = x8Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells8.setCellValue(x8.toString());

            Cell cells9 = row9.createCell(index + 1);
            BigDecimal x9 = x9Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells9.setCellValue(x9.toString());

            Cell cells10 = row10.createCell(index + 1);
            BigDecimal x10 = x10Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells10.setCellValue(x10.toString());

            Cell cells11 = row11.createCell(index + 1);
            BigDecimal x11 = x11Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells11.setCellValue(x11.toString());

            Cell cells12 = row12.createCell(index + 1);
            BigDecimal x12 = x12Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells12.setCellValue(x12.toString());

            Cell cells13 = row13.createCell(index + 1);
            BigDecimal x13 = x13Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells13.setCellValue(x13.toString());

            Cell cells14 = row14.createCell(index + 1);
            BigDecimal x14 = x14Sum.divide(BigDecimal.valueOf(index - 1), 2, ROUND_HALF_UP);
            cells14.setCellValue(x14.toString());
        }
        return new Excel(planEvaluatee.getUserName(), workbook);
    }

    private Excel createDepartmentCadreScoreStatisticExcel(Evaluatee planEvaluatee, List<EvaluationGroup> evaluationGroups) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (EvaluationGroup evaluationGroup : evaluationGroups) {
            if (Constant.EvaluationScoreForm.Types.LEADER_CADRE_EVALUATION_SCORE_FORM == evaluationGroup.getEvaluationScoreFormType()) {
                Evaluatee evaluatee = evaluateeService.findEvaluateeByEvaluationGroupAndUser(evaluationGroup.getId(), planEvaluatee.getUserId());
                List<LeaderCadreScoreForm> leaderCadreScoreForms = leaderCadreScoreFormService.findLeaderCadreScoreFormsByEvaluationGroupAndEvaluatee(evaluationGroup.getId(), evaluatee.getId());

                BigDecimal x1Sum = new BigDecimal("0.00");
                BigDecimal x2Sum = new BigDecimal("0.00");
                BigDecimal x3Sum = new BigDecimal("0.00");
                BigDecimal x4Sum = new BigDecimal("0.00");
                BigDecimal x5Sum = new BigDecimal("0.00");

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
                cell30.setCellValue("工作业绩-履职绩效");
                Row row4 = sheet.createRow(4);
                Cell cell40 = row4.createCell(index);
                cell40.setCellValue("工作业绩-协同成效");
                Row row5 = sheet.createRow(5);
                Cell cell50 = row5.createCell(index);
                cell50.setCellValue("廉洁从业和“一岗双责”");

                for (LeaderCadreScoreForm score : leaderCadreScoreForms) {
                    index++;
                    Cell cell0 = row0.createCell(index);
                    cell0.setCellValue(score.getEvaluatorName());
                    Integer item1 = score.getPoliticsPerformance1() + score.getPoliticsPerformance2() + score.getPoliticsPerformance3() + score.getPoliticsPerformance4() + score.getPoliticsPerformance5();
                    Cell cell1 = row1.createCell(index);
                    cell1.setCellValue(item1);
                    x1Sum = x1Sum.add(BigDecimal.valueOf(item1));
                    Integer item2 = score.getAbilityAndQuality1() + score.getAbilityAndQuality2() + score.getAbilityAndQuality3() + score.getAbilityAndQuality4();
                    Cell cell2 = row2.createCell(index);
                    cell2.setCellValue(item2);
                    x2Sum = x2Sum.add(BigDecimal.valueOf(item2));
                    Integer item3 = score.getWorkPerformance1();
                    Cell cell3 = row3.createCell(index);
                    cell3.setCellValue(item3);
                    x3Sum = x3Sum.add(BigDecimal.valueOf(item3));
                    Integer item4 = score.getWorkPerformance2();
                    Cell cell4 = row4.createCell(index);
                    cell4.setCellValue(item4);
                    x4Sum = x4Sum.add(BigDecimal.valueOf(item4));
                    Integer item5 = score.getIntegrity1() + score.getIntegrity2() + score.getIntegrity3();
                    Cell cell5 = row5.createCell(index);
                    cell5.setCellValue(item5);
                    x5Sum = x5Sum.add(BigDecimal.valueOf(item5));
                }
                Cell cells0 = row0.createCell(index + 1);
                cells0.setCellValue("平均分");
                Cell cells1 = row1.createCell(index + 1);
                BigDecimal x1 = x1Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
                cells1.setCellValue(x1.toString());
                Cell cells2 = row2.createCell(index + 1);
                BigDecimal x2 = x2Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
                cells2.setCellValue(x2.toString());
                Cell cells3 = row3.createCell(index + 1);
                BigDecimal x3 = x3Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
                cells3.setCellValue(x3.toString());
                Cell cells4 = row4.createCell(index + 1);
                BigDecimal x4 = x4Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
                cells4.setCellValue(x4.toString());
                Cell cells5 = row5.createCell(index + 1);
                BigDecimal x5 = x5Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
                cells5.setCellValue(x5.toString());
            }
            if (Constant.EvaluationScoreForm.Types.DEPARTMENT_CADRE_EVALUATION_SCORE_FORM == evaluationGroup.getEvaluationScoreFormType()) {
                Evaluatee evaluatee = evaluateeService.findEvaluateeByEvaluationGroupAndUser(evaluationGroup.getId(), planEvaluatee.getUserId());
                List<DepartmentCadreScoreForm> departmentCadreScoreForms = departmentCadreScoreFormService.findDepartmentCadreScoreFormsByEvaluationGroupAndEvaluatee(evaluationGroup.getId(), evaluatee.getId());

                BigDecimal x1Sum = new BigDecimal("0.00");
                BigDecimal x2Sum = new BigDecimal("0.00");
                BigDecimal x3Sum = new BigDecimal("0.00");
                BigDecimal x4Sum = new BigDecimal("0.00");

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
                    x1Sum = x1Sum.add(BigDecimal.valueOf(item1));
                    Integer item2 = score.getAbilityAndQuality();
                    Cell cell2 = row2.createCell(index);
                    cell2.setCellValue(item2);
                    x2Sum = x2Sum.add(BigDecimal.valueOf(item2));
                    Integer item3 = score.getWorkPerformance();
                    Cell cell3 = row3.createCell(index);
                    cell3.setCellValue(item3);
                    x3Sum = x3Sum.add(BigDecimal.valueOf(item3));
                    Integer item4 = score.getIntegrity();
                    Cell cell4 = row4.createCell(index);
                    cell4.setCellValue(item4);
                    x4Sum = x4Sum.add(BigDecimal.valueOf(item4));
                }
                Cell cells0 = row0.createCell(index + 1);
                cells0.setCellValue("平均分");
                Cell cells1 = row1.createCell(index + 1);
                BigDecimal x1 = x1Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
                cells1.setCellValue(x1.toString());
                Cell cells2 = row2.createCell(index + 1);
                BigDecimal x2 = x2Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
                cells2.setCellValue(x2.toString());
                Cell cells3 = row3.createCell(index + 1);
                BigDecimal x3 = x3Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
                cells3.setCellValue(x3.toString());
                Cell cells4 = row4.createCell(index + 1);
                BigDecimal x4 = x4Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
                cells4.setCellValue(x4.toString());
            }
        }
        return new Excel(planEvaluatee.getUserName(), workbook);
    }

    private Excel createProfessionalScoreStatisticExcel(Evaluatee planEvaluatee, List<EvaluationGroup> evaluationGroups) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (EvaluationGroup evaluationGroup : evaluationGroups) {
            Evaluatee evaluatee = evaluateeService.findEvaluateeByEvaluationGroupAndUser(evaluationGroup.getId(), planEvaluatee.getUserId());
            List<ProfessionalScoreForm> professionalScoreForms = professionalScoreFormService.findProfessionalScoreFormsByEvaluationGroupAndEvaluatee(evaluationGroup.getId(), evaluatee.getId());

            BigDecimal x1Sum = new BigDecimal("0.00");
            BigDecimal x2Sum = new BigDecimal("0.00");
            BigDecimal x3Sum = new BigDecimal("0.00");
            BigDecimal x4Sum = new BigDecimal("0.00");

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
                x1Sum = x1Sum.add(BigDecimal.valueOf(item1));
                Integer item2 = score.getQuality1() + score.getQuality2() + score.getQuality3();
                Cell cell2 = row2.createCell(index);
                cell2.setCellValue(item2);
                x2Sum = x2Sum.add(BigDecimal.valueOf(item2));
                Integer item3 = score.getAbility1() + score.getAbility2() + score.getAbility3();
                Cell cell3 = row3.createCell(index);
                cell3.setCellValue(item3);
                x3Sum = x3Sum.add(BigDecimal.valueOf(item3));
                Integer item4 = score.getPerformance1() + score.getPerformance2() + score.getPerformance3();
                Cell cell4 = row4.createCell(index);
                cell4.setCellValue(item4);
                x4Sum = x4Sum.add(BigDecimal.valueOf(item4));
            }
            Cell cells0 = row0.createCell(index + 1);
            cells0.setCellValue("平均分");
            Cell cells1 = row1.createCell(index + 1);
            BigDecimal x1 = x1Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
            cells1.setCellValue(x1.toString());
            Cell cells2 = row2.createCell(index + 1);
            BigDecimal x2 = x2Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
            cells2.setCellValue(x2.toString());
            Cell cells3 = row3.createCell(index + 1);
            BigDecimal x3 = x3Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
            cells3.setCellValue(x3.toString());
            Cell cells4 = row4.createCell(index + 1);
            BigDecimal x4 = x4Sum.divide(BigDecimal.valueOf(index), 2, ROUND_HALF_UP);
            cells4.setCellValue(x4.toString());
        }
        return new Excel(planEvaluatee.getUserName(), workbook);
    }
}
