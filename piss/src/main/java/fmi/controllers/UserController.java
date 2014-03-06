package fmi.controllers;

import fmi.hibernate.model.User;
import fmi.hibernate.model.UserRole;
import fmi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.LinkedHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 13-8-21
 * Time: 11:00
 * To change this template use File | Settings | File Templates.
 */
@Controller
@SessionAttributes
@RequestMapping("/cms/user")
public class UserController {
    //private final static Logger LOGGER = Logger.getLogger(AdminBannerController.class.getName());

    @Autowired
    private UserRepository userDAO;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    private String showAllUsers(ModelMap model){
        model.put("users", userDAO.findAll());
        return "user";
    }

    @RequestMapping(value = "/{uId}", method = RequestMethod.GET)
    public String displayImage(@PathVariable("uId") String uId,
                             ModelMap model) {
        model.put("user", userDAO.findOne(Long.valueOf(uId)));
        return "viewUser";
    }

    @RequestMapping(value = "/{uId}/edit", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable("uId") String uId,
                                 ModelMap model){
        LinkedHashMap<UserRole, String> roles = new LinkedHashMap<UserRole, String>();
        for(UserRole r : UserRole.values()){
            roles.put(r, r.getDisplayValue());
        }
        model.put("roles", roles);
        model.put("oldUser", userDAO.findOne(Long.valueOf(uId)));
        ModelAndView modelAndView = new ModelAndView("user", "user", new User());
        modelAndView.setViewName("newUser");
        return modelAndView;
    }

    @RequestMapping(value = "/{uId}/edit", method = RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute("user") User user,
                                BindingResult result, @PathVariable("uId") String uId,
                                ModelMap model){
        if(result.hasErrors()){
            model.put("oldUser", user);
            return "newUser";
        } else {
            User oldUser = userDAO.findOne(Long.valueOf(uId));
            oldUser.setFullName(user.getFullName());
            oldUser.setEmail(user.getEmail());
            userDAO.save(oldUser);
            return "redirect:/cms/user/all";
        }

    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView createUser(ModelMap model){
        LinkedHashMap<UserRole, String> roles = new LinkedHashMap<UserRole, String>();
        for(UserRole r : UserRole.values()){
            roles.put(r, r.getDisplayValue());
        }
        model.put("roles", roles);
        ModelAndView modelAndView = new ModelAndView("user", "user", new User());
        modelAndView.setViewName("newUser");
        return modelAndView;
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("user") User user,
                        BindingResult result, ModelMap model){
        if(result.hasErrors()){
            LinkedHashMap<UserRole, String> roles = new LinkedHashMap<UserRole, String>();
            for(UserRole r : UserRole.values()){
                roles.put(r, r.getDisplayValue());
            }
            model.put("roles", roles);
            return "newUser";
        } else {
            System.err.println("NO ERROR");
            userDAO.save(user);
            return "redirect:/cms/user/all";
        }

    }
}
