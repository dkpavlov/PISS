package fmi.controllers;

import fmi.hibernate.model.User;
import fmi.repository.UserRepository;
import fmi.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class GeneralController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getUserProfileForEdit(){
        Subject currentSubject = SecurityUtils.getSubject();
        ModelAndView modelAndView = new ModelAndView("general/editProfile", "user",
                userRepository.findByUsername((String) currentSubject.getPrincipal()));
        return modelAndView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute("user") User user, String newPassword){
        Subject currentSubject = SecurityUtils.getSubject();
        User oldUser = userRepository.findByUsername((String) currentSubject.getPrincipal());
        if(newPassword != null){
            if(newPassword.trim().length() > 0){
                oldUser.setPassword(new MD5Util().generateMD5(newPassword));
            }
        }
        oldUser.setEmail(user.getEmail());
        oldUser.setFullName(user.getFullName());
        userRepository.save(oldUser);
        return "redirect:/home";
    }
}
