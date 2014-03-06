package fmi.controllers;

import fmi.hibernate.model.*;
import fmi.repository.IndividualWorkPlanRepository;
import fmi.repository.OneYearWorkPlanRepository;
import fmi.repository.UserRepository;
import fmi.repository.WorkPlanRepository;
import fmi.specification.UserSpecification;
import org.apache.shiro.SecurityUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-7
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/plan")
public class WorkPlanController {

    @Autowired
    IndividualWorkPlanRepository individualWorkPlanRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkPlanRepository workPlanRepository;

    @Autowired
    OneYearWorkPlanRepository oneYearWorkPlanRepository;

    @RequestMapping(value = "/individual", method = RequestMethod.GET)
    public ModelAndView getFormForNewIndividualPlan(){
        User phd = userRepository.findByUsername((String) SecurityUtils.getSubject().getPrincipal());
        IndividualWorkPlan individualWorkPlan = individualWorkPlanRepository.findByPhd(phd);
        if(individualWorkPlan != null){
            ModelAndView modelAndView = new ModelAndView("workPlan/newIndividual", "plan", individualWorkPlan);
            modelAndView.getModel().put("teacherList", userRepository.findAll(Specifications.where(UserSpecification.archiveIs(new Boolean(false)))
                    .and(UserSpecification.validatedIs(new Boolean(false))).and(UserSpecification.roleIs(UserRole.TEACHER))));
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("workPlan/newIndividual", "plan", new IndividualWorkPlan());
            modelAndView.getModel().put("teacherList", userRepository.findAll(Specifications.where(UserSpecification.archiveIs(new Boolean(false)))
                    .and(UserSpecification.validatedIs(new Boolean(false))).and(UserSpecification.roleIs(UserRole.TEACHER))));
            return modelAndView;
        }
    }

    @RequestMapping(value = "/individual", method = RequestMethod.POST)
    public String saveINdPlan(@ModelAttribute("plan") IndividualWorkPlan plan, String firstDate, String secondDate){
        User phd = userRepository.findByUsername((String) SecurityUtils.getSubject().getPrincipal());
        IndividualWorkPlan individualWorkPlan = individualWorkPlanRepository.findByPhd(phd);
        if(individualWorkPlan != null){
            plan.setId(individualWorkPlan.getId());
        }
        plan.setPhd(phd);
        individualWorkPlanRepository.save(plan);
        return "redirect:/home";
    }

    @RequestMapping(value = "/individual/{dId}", method = RequestMethod.GET)
    public void exportIndividualWorkPlan(@PathVariable("dId") String dId, HttpServletResponse response){
        IndividualWorkPlan individualWorkPlan = individualWorkPlanRepository.findByPhdId(Long.valueOf(dId));
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new java.io.File("ind_plan.docx")));

            replacePlaceholder(template, individualWorkPlan.getPhd().getFullName(), "<NAME>");
            replacePlaceholder(template, individualWorkPlan.getPhd().getSpeciality(),  "<SPE>");
            replacePlaceholder(template, format.format(individualWorkPlan.getPhd().getStartOfPHD()), "<DATE_ONE>");
            replacePlaceholder(template, individualWorkPlan.getPhd().getOrderNumber(), "<ORDER_N>");
            replacePlaceholder(template, format.format(individualWorkPlan.getPhd().getOrderFrom()), "<DATE_TWO>");
            replacePlaceholder(template, format.format(individualWorkPlan.getPhd().getDeadLine()), "<DATE_THREE>");
            replacePlaceholder(template, individualWorkPlan.getThesis(), "<THESIS>");
            replacePlaceholder(template, format.format(individualWorkPlan.getDateThesis()), "<DATE_FOUR>");
            replacePlaceholder(template, individualWorkPlan.getProtocolNumber(), "<PROT_N>");
            replacePlaceholder(template, individualWorkPlan.getDeanReportNumber()+"/"+format.format(individualWorkPlan.getDeanReportDate()), "<NUM>");
            replacePlaceholder(template, individualWorkPlan.getHead().getFullName(), "<HEAD>");
            java.io.File f = new java.io.File("temp_temp.docx");
            template.save(f);

            WordprocessingMLPackage newTemp = WordprocessingMLPackage.load(new FileInputStream(new java.io.File("temp_temp.docx")));
            List<Object> list = getAllElementFromObject(newTemp, Text.class);


            java.nio.file.Path path = java.nio.file.Paths.get("temp_temp.docx");

            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.getOutputStream().write(java.nio.file.Files.readAllBytes(path));
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (Docx4JException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/yearly", method = RequestMethod.GET)
    public String getPageForYearlyWorkPlan(ModelMap model){
        User phd = userRepository.findByUsername((String) SecurityUtils.getSubject().getPrincipal());
        model.put("yearOne", oneYearWorkPlanRepository.findByPhdAndYear(phd, Long.valueOf("1")));
        model.put("yearTwo", oneYearWorkPlanRepository.findByPhdAndYear(phd, Long.valueOf("2")));
        model.put("yearThree", oneYearWorkPlanRepository.findByPhdAndYear(phd, Long.valueOf("3")));
        return "workPlan/yearly";
    }

    @RequestMapping(value = "/yearly/{year}", method = RequestMethod.GET)
    public ModelAndView getFormForOneYear(@PathVariable("year") String year){
        OneYearWorkPlan oneYearWorkPlan =  oneYearWorkPlanRepository.findByPhdAndYear(userRepository
                .findByUsername((String) SecurityUtils.getSubject().getPrincipal()), Long.valueOf(year));

        if(oneYearWorkPlan == null){
            oneYearWorkPlan = new OneYearWorkPlan();
        }

        ModelAndView modelAndView = new ModelAndView("workPlan/newOneYearWorkPlan", "workPlan", oneYearWorkPlan);
        modelAndView.getModel().put("teacherList", userRepository.findAll(Specifications.where(UserSpecification.archiveIs(new Boolean(false)))
                .and(UserSpecification.roleIs(UserRole.TEACHER))
                .and(UserSpecification.validatedIs(new Boolean(false)))));
        return modelAndView;
    }

    @RequestMapping(value = "/yearly/{year}/export/{sId}", method = RequestMethod.GET)
    public void exportYearlyWorkPlan(@PathVariable("year") String year, @PathVariable("sId") String sId, HttpServletResponse response){
        User user = userRepository.findOne(Long.valueOf(sId));
        OneYearWorkPlan oneYearWorkPlan = oneYearWorkPlanRepository.findByPhdAndYear(user, Long.valueOf(year));
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        String oneOne = "";
        String oneTwo = "";
        String oneThree = "";

        String twoOne = "";
        String twoTwo = "";
        String twoThree = "";

        String threeOne = "";
        String threeTwo = "";
        String threeThree = "";

        String fourOne = "";
        String fourTwo = "";
        String fourThree = "";

        if(oneYearWorkPlan.getPartOne() != null){
            for(Line l: oneYearWorkPlan.getPartOne()){
                oneOne = oneOne + l.getWorkContent()+"\n\n";
                oneTwo = oneTwo + l.getForm()+"\n\n";
                oneThree = oneThree + format.format(l.getDeadline()) + "\n /" + l.getTypeOfReport() + "/" + "\n";
            }
        }

        if(oneYearWorkPlan.getPartTwo() != null){
            for(Line l: oneYearWorkPlan.getPartTwo()){
                twoOne = twoOne + l.getWorkContent()+"\n\n";
                twoTwo = twoTwo + l.getForm()+"\n\n";
                twoThree = twoThree + format.format(l.getDeadline()) + "\n /" + l.getTypeOfReport() + "/" + "\n";
            }
        }

        if(oneYearWorkPlan.getPartThree() != null){
            for(Line l: oneYearWorkPlan.getPartThree()){
                threeOne = threeOne + l.getWorkContent()+"\n\n";
                threeTwo = threeTwo + l.getForm()+"\n\n";
                threeThree = threeThree + format.format(l.getDeadline()) + "\n /" + l.getTypeOfReport() + "/" + "\n";
            }
        }

        if(oneYearWorkPlan.getPartFour() != null){
            for(Line l: oneYearWorkPlan.getPartFour()){
                fourOne = fourOne + l.getWorkContent()+"\n\n";
                fourTwo = fourTwo + l.getForm()+"\n\n";
                fourThree = fourThree + format.format(l.getDeadline()) + "\n /" + l.getTypeOfReport() + "/" + "\n";
            }
        }


        try {
            WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new java.io.File("gen_plan.docx")));
            replacePlaceholder(template, oneOne, "<ONE_ONE>");
            replacePlaceholder(template, oneTwo, "<ONE_TWO>");
            replacePlaceholder(template, oneThree, "<ONE_THREE>");

            replacePlaceholder(template, twoOne, "<TWO_ONE>");
            replacePlaceholder(template, twoTwo, "<TWO_TWO>");
            replacePlaceholder(template, twoThree, "<TOW_THREE>");

            replacePlaceholder(template, threeOne, "<THREE_ONE>");
            replacePlaceholder(template, threeTwo, "<THREE_TWO>");
            replacePlaceholder(template, threeThree, "<THREE_THREE>");

            replacePlaceholder(template, fourOne, "<FOUR_ONE>");
            replacePlaceholder(template, fourTwo, "<FOUR_TWO>");
            replacePlaceholder(template, fourThree, "<FOUR_THREE>");

            java.io.File f = new java.io.File("gen_plan_temp.docx");
            template.save(f);

            WordprocessingMLPackage newTemp = WordprocessingMLPackage.load(new FileInputStream(new java.io.File("gen_plan_temp.docx")));
            List<Object> list = getAllElementFromObject(newTemp, Text.class);


            java.nio.file.Path path = java.nio.file.Paths.get("gen_plan_temp.docx");

            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.getOutputStream().write(java.nio.file.Files.readAllBytes(path));
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (Docx4JException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/yearly/{year}", method = RequestMethod.POST)
    public String saveOneYearWorkPlan(@ModelAttribute("workPlan") OneYearWorkPlan workPlan, @PathVariable("year") String year){
        User phd = userRepository.findByUsername((String) SecurityUtils.getSubject().getPrincipal());
        OneYearWorkPlan old = oneYearWorkPlanRepository.findByPhdAndYear(
                phd, Long.valueOf(year));
        if(old != null){
            workPlan.setId(old.getId());
        }
        workPlan.setPhd(phd);
        workPlan.setYear(Long.valueOf(year));
        oneYearWorkPlanRepository.save(workPlan);
        return "redirect:/home";
    }


    @RequestMapping(value = "/full", method = RequestMethod.GET)
    public ModelAndView getFormForFullWorkPlan(){
        WorkPlan workPlan = workPlanRepository.findByPhd(userRepository.findByUsername((String) SecurityUtils.getSubject().getPrincipal()));
        ModelAndView modelAndView;
        if(workPlan == null){
            workPlan = new WorkPlan();
        }
        modelAndView = new ModelAndView("workPlan/newWorkPlan", "workPlan", workPlan);
        modelAndView.getModel().put("teacherList", userRepository.findAll(Specifications.where(UserSpecification.archiveIs(new Boolean(false)))
                .and(UserSpecification.roleIs(UserRole.TEACHER))
                .and(UserSpecification.validatedIs(new Boolean(false)))));
        return modelAndView;
    }

    @RequestMapping(value = "/full", method = RequestMethod.POST)
    public String saveWorkPlan(@ModelAttribute("workPlan") WorkPlan workPlan){
        WorkPlan old = workPlanRepository.findByPhd(userRepository.findByUsername((String) SecurityUtils.getSubject().getPrincipal()));
        if(old != null){
            workPlan.setId(old.getId());
        }
        workPlan.setPhd(userRepository.findByUsername((String) SecurityUtils.getSubject().getPrincipal()));
        workPlanRepository.save(workPlan);
        return "redirect:/home";
    }

    @RequestMapping(value = "/full/{dId}", method = RequestMethod.GET)
    public void printFullWorkPlan(@PathVariable("dId") String dId, HttpServletResponse response){
        WorkPlan workPlan = workPlanRepository.findByPhdId(Long.valueOf(dId));
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        String oneOne = "";
        String oneTwo = "";
        String oneThree = "";

        String twoOne = "";
        String twoTwo = "";
        String twoThree = "";

        String threeOne = "";
        String threeTwo = "";
        String threeThree = "";

        String fourOne = "";
        String fourTwo = "";
        String fourThree = "";

        for(Line l: workPlan.getPartOne()){
            oneOne = oneOne + l.getWorkContent()+"\n\n";
            oneTwo = oneTwo + l.getForm()+"\n\n";
            oneThree = oneThree + format.format(l.getDeadline()) + "\n /" + l.getTypeOfReport() + "/" + "\n";
        }

        for(Line l: workPlan.getPartTwo()){
            twoOne = twoOne + l.getWorkContent()+"\n\n";
            twoTwo = twoTwo + l.getForm()+"\n\n";
            twoThree = twoThree + format.format(l.getDeadline()) + "\n /" + l.getTypeOfReport() + "/" + "\n";
        }

        for(Line l: workPlan.getPartThree()){
            threeOne = threeOne + l.getWorkContent()+"\n\n";
            threeTwo = threeTwo + l.getForm()+"\n\n";
            threeThree = threeThree + format.format(l.getDeadline()) + "\n /" + l.getTypeOfReport() + "/" + "\n";
        }

        for(Line l: workPlan.getPartFour()){
            fourOne = fourOne + l.getWorkContent()+"\n\n";
            fourTwo = fourTwo + l.getForm()+"\n\n";
            fourThree = fourThree + format.format(l.getDeadline()) + "\n /" + l.getTypeOfReport() + "/" + "\n";
        }

        try {
            WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new java.io.File("gen_plan.docx")));
            //TODO ima nqkakuw problem
            replacePlaceholder(template, oneOne, "<ONE_ONE>");
            replacePlaceholder(template, oneTwo, "<ONE_TWO>");
            replacePlaceholder(template, oneThree, "<ONE_THREE>");

            replacePlaceholder(template, twoOne, "<TWO_ONE>");
            replacePlaceholder(template, twoTwo, "<TWO_TWO>");
            replacePlaceholder(template, twoThree, "<TOW_THREE>");

            replacePlaceholder(template, threeOne, "<THREE_ONE>");
            replacePlaceholder(template, threeTwo, "<THREE_TWO>");
            replacePlaceholder(template, threeThree, "<THREE_THREE>");

            replacePlaceholder(template, fourOne, "<FOUR_ONE>");
            replacePlaceholder(template, fourTwo, "<FOUR_TWO>");
            replacePlaceholder(template, fourThree, "<FOUR_THREE>");

            java.io.File f = new java.io.File("gen_plan_temp.docx");
            template.save(f);

            WordprocessingMLPackage newTemp = WordprocessingMLPackage.load(new FileInputStream(new java.io.File("gen_plan_temp.docx")));
            List<Object> list = getAllElementFromObject(newTemp, Text.class);


            java.nio.file.Path path = java.nio.file.Paths.get("gen_plan_temp.docx");

            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.getOutputStream().write(java.nio.file.Files.readAllBytes(path));
            response.getOutputStream().flush();
            response.getOutputStream().close();

        } catch (Docx4JException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
        List<Object> result = new ArrayList<Object>();
        if (obj instanceof JAXBElement) obj = ((JAXBElement<?>) obj).getValue();

        if (obj.getClass().equals(toSearch))
            result.add(obj);
        else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child, toSearch));
            }

        }
        return result;
    }

    private void replacePlaceholder(WordprocessingMLPackage template, String name, String placeholder ) {
        List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);

        for (Object text : texts) {
            Text textElement = (Text) text;
            if (textElement.getValue().equals(placeholder)) {
                if(name != null){
                    textElement.setValue(name);
                } else {
                    textElement.setValue("");
                }

            }
        }
    }

}
