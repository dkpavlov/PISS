package fmi.controllers;

import fmi.hibernate.model.Internship;
import fmi.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: dkpavlov
 * Date: 12/29/13
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    InternshipRepository internshipRepository;

    @RequestMapping(value = "/internship/new", method = RequestMethod.GET)
    public ModelAndView getFormForNewInternship(){
        ModelAndView modelAndView = new ModelAndView("student/newInternship", "internship", new Internship());
        return modelAndView;
    }

    @RequestMapping(value = "/internship/new", method = RequestMethod.POST)
    public String saveInternship(@ModelAttribute("internship") Internship internship){
        internshipRepository.save(internship);
        return "redirect:/home";
    }
}
