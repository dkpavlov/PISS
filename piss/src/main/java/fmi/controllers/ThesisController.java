package fmi.controllers;

import fmi.hibernate.model.*;
import fmi.hibernate.model.File;
import fmi.repository.ThesisProposalRepository;
import fmi.repository.ThesisRepository;
import fmi.repository.UserRepository;
import fmi.specification.UserSpecification;
import fmi.util.FileUploadForm;
import fmi.util.MailManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.xml.bind.JAXBElement;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-2
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/thesis")
public class ThesisController {
    // TODO da se napulnqt stranicite w thesis papkata
    @Autowired
    ThesisProposalRepository thesisProposalRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ThesisRepository thesisRepository;

    // STUDENT

    @RequestMapping(value = "/final/new", method = RequestMethod.GET)
    public ModelAndView getFormForFinal(){
        Subject currentUser = SecurityUtils.getSubject();
        ThesisProposal thesisProposal = thesisProposalRepository.findByUserUsername((String) currentUser.getPrincipal());
        ModelAndView modelAndView = new ModelAndView("thesis/newThesis", "thesis", new FileUploadForm());
        if(thesisProposal != null && (thesisProposal.getStatus().equals(ThesisProposalStatus.APPROVED) || thesisProposal.getStatus().equals(ThesisProposalStatus.CONDITION))){
            modelAndView.getModel().put("flag", "0");
        } else {
            modelAndView.getModel().put("flag", "1");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/final/new", method = RequestMethod.POST)
    public String saveFinalThesis(@ModelAttribute("thesis") FileUploadForm fuf, String t1, String t2, String t3) throws IOException {
        System.err.println("\n\n\n TEST \n\n\n");
        try{
            Blob blob = new SerialBlob(fuf.getFile().getBytes());
            File code_file = new File();
            code_file.setContent(blob);
            code_file.setOriginalName(fuf.getFile().getOriginalFilename());
            code_file.setSize(fuf.getFile().getSize());
            code_file.setContentType(fuf.getFile().getContentType());
            Thesis thesis = new Thesis();
            Subject currentUser = SecurityUtils.getSubject();
            thesis.setUser(userRepository.findByUsername((String) currentUser.getPrincipal()));
            thesis.setText(t1);
            thesis.setSummaryBulgarian(t2);
            thesis.setSummaryEnglish(t3);
            thesis.setThesisProposal(thesisProposalRepository.findByUserUsername((String) currentUser.getPrincipal()));
            thesis.setFile(code_file);
            thesisRepository.save(thesis);

            /*thesis.setFile(code_file);


            thesisRepository.save(thesis);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getFormForNewThesis(){
        ModelAndView modelAndView = new ModelAndView("thesisProposal/newThesis", "thesis", new ThesisProposal());
        modelAndView.getModel().put("teachersList", userRepository.findAll(Specifications.where(UserSpecification.archiveIs(new Boolean(false)))
                .and(UserSpecification.validatedIs(new Boolean(false)))
                .and(UserSpecification.roleIs(UserRole.TEACHER))));
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveThesis(@ModelAttribute("thesis") ThesisProposal thesis){
        Subject currentUser = SecurityUtils.getSubject();
        thesis.setUser(userRepository.findByUsername((String) currentUser.getPrincipal()));
        thesisProposalRepository.save(thesis);
        return "redirect:/home";
    }

    // TEACHER
    @RequestMapping(value = "/final/forReview", method = RequestMethod.GET)
    public String getFinalForReview(ModelMap model){
        Subject subject = SecurityUtils.getSubject();
        model.put("thesisList", thesisRepository.findByThesisProposalTutorUsername((String) subject.getPrincipal()));
        return "thesis/thesisList";
    }

    @RequestMapping(value = "/final/{id}", method = RequestMethod.GET)
    public ModelAndView previewThesisAndAddReview(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView("thesis/previewThesis", "thesisReview", new ThesisReview());
        modelAndView.getModel().put("thesis", thesisRepository.findOne(Long.valueOf(id)));
        return modelAndView;
    }

    @RequestMapping(value = "/final/{id}", method = RequestMethod.POST)
    public String saveReview(@ModelAttribute("thesisReview") ThesisReview thesisReview, @PathVariable("id") String id){
        Thesis thesis = thesisRepository.findOne(Long.valueOf(id));
        thesis.setThesisReview(thesisReview);
        thesisRepository.save(thesis);
        return "redirect:/thesis/final/forReview";
    }

    @RequestMapping(value = "/final/file/{id}", method = RequestMethod.GET)
    public void getFileFromThesis(@PathVariable("id") String id, HttpServletResponse response){
        Thesis thesis = thesisRepository.findOne(Long.valueOf(id));
        response.setContentType(thesis.getFile().getContentType());
        try{
            response.getOutputStream().write(thesis.getFile().getContent().getBytes(1, (int) thesis.getFile().getContent().length()));
            response.setContentType(thesis.getFile().getContentType());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

    @RequestMapping(value = "/forValidation", method = RequestMethod.GET)
    public String getThesisForValidation(ModelMap model){
        Subject subject = SecurityUtils.getSubject();
        User user = userRepository.findByUsername((String) subject.getPrincipal());
        model.put("thesisList", thesisProposalRepository.findByStatusAndTutor(ThesisProposalStatus.PENDING, user));
        return "thesisProposal/thesisList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getThesisForPreview(@PathVariable("id") String id, ModelMap model){
        model.put("thesisProposal", thesisProposalRepository.findOne(Long.valueOf(id)));
        model.put("thesisProposalStatus", ThesisProposalStatus.values());
        return "thesisProposal/previewThesis";
    }


    @RequestMapping(value = "/{id}/{status}", method = RequestMethod.GET)
    public String setStatusToThesisProposal(@PathVariable("id") String id, @PathVariable("status") String status, String note){
        if(status != null){
            if(status.trim().length() > 0){

                ThesisProposal thesisProposal = thesisProposalRepository.findOne(Long.valueOf(id));
                if(status.equals("NOT_APPROVED")){
                    thesisProposal.setStatus(ThesisProposalStatus.NOT_APPROVED);
                } else if(status.equals("CONDITION")){
                    thesisProposal.setStatus(ThesisProposalStatus.CONDITION);
                    thesisProposal.setNote(note);
                } else if(status.equals("APPROVED")){
                    thesisProposal.setStatus(ThesisProposalStatus.APPROVED);
                    MailManager.sendMail(thesisProposal.getUser().getEmail(), "Одобрено задание за дипломна работа", "Вашето задание за дипломна работа беше одобрено");
                }

                thesisProposalRepository.save(thesisProposal);
            }
        }

        return "redirect:/thesis/forValidation";
    }

    @RequestMapping(value = "/{id}/export", method = RequestMethod.GET)
    public void exportThesisProposal(@PathVariable("id") String id, HttpServletResponse response) {
        ThesisProposal thesisProposal = thesisProposalRepository.findOne(Long.valueOf(id));
        //String pwd = System.getProperty("user.dir");
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            //TODO problem s nqkoi poleta
            WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new java.io.File("predlojenie_dipl_rabota.docx")));
            replacePlaceholder(template, thesisProposal.getUser().getFullName(), "<NAME>");
            replacePlaceholder(template, thesisProposal.getUser().getSpeciality(),  "<SPE>");
            replacePlaceholder(template, thesisProposal.getUser().getFn(), "<FN>");
            replacePlaceholder(template, thesisProposal.getTutor().getFullName(), "<HEAD_NAME>");
            replacePlaceholder(template, thesisProposal.getTutor().getChair(), "<HEAD_CHAIR>");
            replacePlaceholder(template, thesisProposal.getConsultant().getFullName(), "<CONSULTANT>");
            replacePlaceholder(template, thesisProposal.getConsultant().getChair(), "<HEAD_CHAIR>");
            replacePlaceholder(template, thesisProposal.getThesis(), "<THESIS>");
            replacePlaceholder(template, thesisProposal.getAnnotation(), "<ANNOTATION>");
            replacePlaceholder(template, thesisProposal.getThesisGoal(), "<GOAL>");
            replacePlaceholder(template, thesisProposal.getTasks(), "<GOAL-2>");
            replacePlaceholder(template, thesisProposal.getConstraints(), "<CONSTRAINTS>");
            replacePlaceholder(template, thesisProposal.getDeadline(), "<DEAD-LINE>");
            replacePlaceholder(template, format.format(thesisProposal.getDateCreated()), "<DATE>");
            java.io.File f = new java.io.File("temp.docx");
            template.save(f);

            WordprocessingMLPackage newTemp = WordprocessingMLPackage.load(new FileInputStream(new java.io.File("temp.docx")));
            List<Object> list = getAllElementFromObject(newTemp, Text.class);


            java.nio.file.Path path = java.nio.file.Paths.get("temp.docx");

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
