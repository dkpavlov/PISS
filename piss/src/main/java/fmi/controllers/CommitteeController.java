package fmi.controllers;

import fmi.hibernate.model.*;
import fmi.repository.StudentListRepository;
import fmi.repository.ThesisCommitteeRepository;
import fmi.repository.ThesisRepository;
import fmi.repository.UserRepository;
import fmi.specification.UserSpecification;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-3
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/committee")
public class CommitteeController {

    @Autowired
    ThesisCommitteeRepository thesisCommitteeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentListRepository studentListRepository;

    @Autowired
    ThesisRepository thesisRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getForm(){
        ModelAndView modelAndView = new ModelAndView("committee/newCommittee", "committee", new ThesisCommittee());
        modelAndView.getModel().put("teacherList", userRepository.findAll(Specifications.where(UserSpecification.archiveIs(new Boolean(false)))
                .and(UserSpecification.roleIs(UserRole.TEACHER))
                .and(UserSpecification.validatedIs(new Boolean(false)))));
        modelAndView.getModel().put("upcomingCommittees", thesisCommitteeRepository.findByDateAfterOrderByDateAsc(new Date()));
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveCommittee(@ModelAttribute("committee") ThesisCommittee committee, String date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            committee.setDate(format.parse(date));
        } catch (ParseException e){
            e.printStackTrace();
        }
        thesisCommitteeRepository.save(committee);
        return "redirect:/committee/new";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCommitteeForPreview(@PathVariable("id") String id, ModelMap model){
        ThesisCommittee thesisCommittee = thesisCommitteeRepository.findOne(Long.valueOf(id));
        List<StudentList> studentList = studentListRepository.findByCommittee(thesisCommittee);
        model.put("committee", thesisCommittee);
        model.put("committeeStudents", studentList);
        model.put("studentList", userRepository.findAll(Specifications.where(UserSpecification.archiveIs(new Boolean(false)))
                .and(UserSpecification.validatedIs(new Boolean(false)))
                .and(UserSpecification.roleIs(UserRole.STUDENT))));
        return "committee/previewCommittee";
    }

    @RequestMapping(value = "/{cId}/{sId}", method = RequestMethod.GET)
    public String addStudentToCommittee(@PathVariable("cId") String cId, @PathVariable("sId") String sId){
        ThesisCommittee thesisCommittee = thesisCommitteeRepository.findOne(Long.valueOf(cId));
        User user = userRepository.findOne(Long.valueOf(sId));
        StudentList studentList = new StudentList();
        studentList.setCommittee(thesisCommittee);
        studentList.setUser(user);
        studentListRepository.save(studentList);
        return "redirect:/committee/"+cId;
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String getMyCommittees(ModelMap model){
        List<ThesisCommittee> thesisCommittees = (List<ThesisCommittee>) thesisCommitteeRepository.findAll();
        List<ThesisCommittee> shortList = new ArrayList<ThesisCommittee>();
        User me = userRepository.findByUsername((String) SecurityUtils.getSubject().getPrincipal());

        for(ThesisCommittee tc: thesisCommittees){
            if(tc.getCommittee().contains(me)){
                shortList.add(tc);
            }
        }

        List<StudentList> studentLists = studentListRepository.findByCommitteeIn(shortList);
        List<StudentList> shortUserList = new ArrayList<StudentList>();



        model.put("studentList", studentLists);
        return "committee/myCommittees";
    }

    @RequestMapping(value = "/score/{sId}", method = RequestMethod.GET)
    public String addScore(@PathVariable("sId") String sId, String score){

        Thesis thesis = thesisRepository.findByUserId(Long.valueOf(sId));
        User student = userRepository.findOne(Long.valueOf(sId));

        thesis.setScore(Double.valueOf(score));
        thesisRepository.save(thesis);

        student.setGraduated(true);
        student.setGraduatedOn(new Date());
        userRepository.save(student);

        return "redirect:/committee/my";

    }

}
