package fmi.controllers;

import fmi.hibernate.model.Internship;
import fmi.hibernate.model.IntershipEstimation;
import fmi.hibernate.model.User;
import fmi.repository.InternshipRepository;
import fmi.repository.IntershipEstimationRepository;
import fmi.repository.UserRepository;
import fmi.util.MailManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-2
 * Time: 11:32
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/internship")
public class InternshipController {


    @Autowired
    InternshipRepository internshipRepository;

    @Autowired
    IntershipEstimationRepository intershipEstimationRepository;

    @Autowired
    UserRepository userRepository;


    // FOR Student
    // TODO da se testwa ot strana na prepodawatel
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getFormForNewInternship(){
        ModelAndView modelAndView = new ModelAndView("intern/newInternshipForm", "internship", new Internship());

        Subject currentUser = SecurityUtils.getSubject();
        User user = userRepository.findByUsername((String) currentUser.getPrincipal());
        if(internshipRepository.findByUser(user) != null){
            modelAndView.getModel().put("flag", "1");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveForm(@ModelAttribute("internship") Internship internship){
        Subject currentUser = SecurityUtils.getSubject();
        internship.setUser(userRepository.findByUsername((String) currentUser.getPrincipal()));
        internshipRepository.save(internship);
        return "redirect:/home";
    }

    @RequestMapping(value = "/estimation", method = RequestMethod.GET)
    public ModelAndView getFormForNewInternshipEstimation(){
        ModelAndView modelAndView = new ModelAndView("intern/newEstimationForm", "estimation", new IntershipEstimation());
        Subject currentUser = SecurityUtils.getSubject();
        User user = userRepository.findByUsername((String) currentUser.getPrincipal());
        if(internshipRepository.findByUser(user) == null){
            modelAndView.getModel().put("flag", "1");
        } else if(intershipEstimationRepository.findByUser(user) != null){
            modelAndView.getModel().put("flag", "2");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/estimation", method = RequestMethod.POST)
    public String saveEstimation(@ModelAttribute("estimation") IntershipEstimation intershipEstimation){
        Subject currentUser = SecurityUtils.getSubject();
        intershipEstimation.setUser(userRepository.findByUsername((String) currentUser.getPrincipal()));
        intershipEstimationRepository.save(intershipEstimation);
        return "redirect:/home";
    }

    // FOR Teacher

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getInternshipForPreview(@PathVariable("id") String id, ModelMap model){
        model.put("internship", internshipRepository.findOne(Long.valueOf(id)));
        return "intern/previewInternship";
    }

    @RequestMapping(value = "/forValidation", method = RequestMethod.GET)
    public String getNonValidatedInternships(ModelMap model){
        model.put("internshipList", internshipRepository.findByValidated(new Boolean(false)));
        return "intern/internshipList";
    }

    @RequestMapping(value = "/{id}/validate", method = RequestMethod.GET)
    public String validateInternship(@PathVariable("id") String id){
        Internship internship = internshipRepository.findOne(Long.valueOf(id));
        internship.setValidated(new Boolean(true));
        internshipRepository.save(internship);
        MailManager.sendMail(internship.getUser().getEmail(), "Одобрена молба за стаж", "Здравеите "
                + internship.getUser().getFirstName() +  ". Вашата молба за стаж беше одобрена");
        return "redirect:/internship/forValidation";
    }

    @RequestMapping(value = "/estimation/{id}", method = RequestMethod.GET)
    public String getEstimationForPreview(@PathVariable("id") String id, ModelMap model){
        model.put("estimation", intershipEstimationRepository.findOne(Long.valueOf(id)));
        return "intern/previewEstimation";
    }

    @RequestMapping(value = "/estimation/forValidation", method = RequestMethod.GET)
    public String getNonValidatedEstimations(ModelMap model){
        model.put("estimationsList", intershipEstimationRepository.findByValidated(new Boolean(false)));
        return "intern/estimationList";
    }

    @RequestMapping(value = "/estimation/{id}/validate", method = RequestMethod.GET)
    public String validateEstimation(@PathVariable("id") String id){
        IntershipEstimation intershipEstimation = intershipEstimationRepository.findOne(Long.valueOf(id));
        intershipEstimation.setValidated(new Boolean(true));
        MailManager.sendMail(intershipEstimation.getUser().getEmail(), "Одобрена оценка от стаж", "Здравеите "
                +intershipEstimation.getUser().getFirstName()+". Оценка от стаж беше одобрена");
        intershipEstimationRepository.save(intershipEstimation);
        return "redirect:/estimation/forValidation";
    }


    /*@RequestMapping(value = "/estimation/{id}/validate", method = RequestMethod.GET)
    public String */
}
