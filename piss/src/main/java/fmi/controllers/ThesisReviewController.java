package fmi.controllers;

import fmi.hibernate.model.Thesis;
import fmi.hibernate.model.ThesisReview;
import fmi.repository.ThesisRepository;
import fmi.repository.ThesisReviewRepository;
import jxl.CellType;
import jxl.Workbook;
import jxl.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-3
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/thesisReview")
public class ThesisReviewController {

    @Autowired
    ThesisReviewRepository thesisReviewRepository;

    @Autowired
    ThesisRepository thesisRepository;

    @RequestMapping(value = "/{id}/new", method = RequestMethod.GET)
    public ModelAndView getFormForNewReview(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView("thesis/newThesisReview", "thesisReview", new ThesisReview());
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/new", method = RequestMethod.POST)
    public String saveReview(@PathVariable("id") String id, @ModelAttribute("thesisReview") ThesisReview thesisReview){
        Thesis thesis = thesisRepository.findOne(Long.valueOf(id));
        thesis.setThesisReview(thesisReview);
        return "redirect:/home";
    }

    @RequestMapping(value = "/{sId}/export", method = RequestMethod.GET)
    public void getThesisReview(@PathVariable("sId") String sId, HttpServletResponse response){

        Thesis thesis = thesisRepository.findByUserId(Long.valueOf(sId));
        if(thesis != null){

            try{

                Workbook workbook = Workbook.getWorkbook(new File("review.xls"));

                File f = new File("temp.xls");
                WritableWorkbook wb = Workbook.createWorkbook(f, workbook);
                WritableSheet sheet = wb.getSheet(1);

                writeToCell(sheet, thesis.getUser().getFullName()+" "+thesis.getUser().getFn(), 1, 7);
                writeToCell(sheet, thesis.getThesisProposal().getThesis(), 1, 11);
                writeToCell(sheet, thesis.getThesisProposal().getTutor().getFullName() + " " + thesis.getThesisProposal().getTutor().getChair() , 1, 15);

                writeToCell(sheet, thesis.getThesisReview().getTheoretical().toString(), 4, 21);
                writeToCell(sheet, thesis.getThesisReview().getOwnIdeas().toString(), 4, 22);
                writeToCell(sheet, thesis.getThesisReview().getExecution().toString(), 4, 23);
                writeToCell(sheet, thesis.getThesisReview().getStyle().toString(), 4, 24);

                writeToCell(sheet, thesis.getThesisReview().getArchitecture().toString(), 9, 21);
                writeToCell(sheet, thesis.getThesisReview().getFunctionality().toString(), 9, 22);
                writeToCell(sheet, thesis.getThesisReview().getReliability().toString(), 9, 23);
                writeToCell(sheet, thesis.getThesisReview().getDocumentation().toString(), 9, 24);

                writeToCell(sheet, thesis.getThesisReview().getDescription().toString(), 9, 26);
                writeToCell(sheet, thesis.getThesisReview().getPresentation().toString(), 9, 27);
                writeToCell(sheet, thesis.getThesisReview().getInterpretation().toString(), 9, 28);


                writeToCell(sheet, thesis.getThesisReview().getSummary(), 0, 33);
                writeToCell(sheet, thesis.getThesisReview().getQuestions(), 0, 36);
                writeToCell(sheet, thesis.getUser().getFullName(), 3, 41);

                double overAll = thesis.getThesisReview().getTheoretical() +  thesis.getThesisReview().getOwnIdeas()
                        + thesis.getThesisReview().getExecution() + thesis.getThesisReview().getStyle()
                        + thesis.getThesisReview().getArchitecture() + thesis.getThesisReview().getFunctionality()
                        + thesis.getThesisReview().getReliability() + thesis.getThesisReview().getDocumentation()
                        + thesis.getThesisReview().getDescription() + thesis.getThesisReview().getPresentation()
                        + thesis.getThesisReview().getInterpretation();




                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                writeToCell(sheet, format.format(thesis.getThesisReview().getDateCreated()), 1, 46);
                writeToCell(sheet,(new Double(overAll/11)).toString(), 5, 43);

                wb.write();
                wb.close();

                java.nio.file.Path path = java.nio.file.Paths.get("temp.xls");

                response.setContentType("application/vnd.ms-excel");
                response.getOutputStream().write(java.nio.file.Files.readAllBytes(path));
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (Exception e){
                e.printStackTrace();

            }
        }
    }

    private void writeToCell(WritableSheet sheet, String text, int column, int row){

        WritableCell cell = sheet.getWritableCell(column, row);

        if(cell.getType().equals(CellType.LABEL)){
            Label l = (Label) cell;
            l.setString(text);
        }
    }
}
