package fmi.controllers;

import fmi.hibernate.model.UserRole;
import fmi.repository.UserRepository;
import fmi.specification.UserSpecification;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String moveToHome(){
        return "redirect:/home";
    }

    @RequestMapping("/logout")
    public String logOut(){
        Subject currentSubject = SecurityUtils.getSubject();
        currentSubject.logout();
        return "redirect:/";
    }

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String printWelcome(ModelMap model){

        Subject currentSubject = SecurityUtils.getSubject();
        if(currentSubject.hasRole(UserRole.ADMINISTRATOR.toString())){
           return "redirect:/home/admin";
        } else if(currentSubject.hasRole(UserRole.STUDENT.toString())){
            return "redirect:/home/student";
        } else if(currentSubject.hasRole(UserRole.PHD.toString())){
            return "redirect:/home/phd";
        } else{
            return "redirect:/home/teacher";
        }
	}

    @RequestMapping(value = "/home/admin", method = RequestMethod.GET)
    public String homePageForAdmin(String username, UserRole role, ModelMap model){
        model.put("userList", userRepository.findAll(Specifications.where(UserSpecification.roleIs(role))
                .and(UserSpecification.searchByUsername(username))
                .and(UserSpecification.archiveIs(new Boolean(false)))
                .and(UserSpecification.validatedIs(new Boolean(false)))));
        model.put("rolesList", UserRole.values());
        model.put("oldUsername", username);
        model.put("oldRole", role);
        return "admin/home";

    }

    @RequestMapping(value = "/home/student", method = RequestMethod.GET)
    public String homePageForStudent(ModelMap model){
        return "student/home";
    }

    @RequestMapping(value = "/home/teacher", method = RequestMethod.GET)
    public String homePageForTeacher(ModelMap model){
        return "teacher/home";
    }

    @RequestMapping(value = "/home/phd", method = RequestMethod.GET)
    public String homePageForPHD(ModelMap model){
        return "phd/home";
    }


}
