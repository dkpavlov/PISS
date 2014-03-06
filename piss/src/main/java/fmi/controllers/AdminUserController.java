package fmi.controllers;

import fmi.hibernate.model.User;
import fmi.hibernate.model.UserRole;
import fmi.repository.UserRepository;
import fmi.util.MailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminUserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/singUp/{type}", method = RequestMethod.GET)
    public ModelAndView getModelForSingUp(@PathVariable("type") String types){
        ModelAndView modelAndView = new ModelAndView("singUp", "user", new User());
        modelAndView.getModel().put("type", types);
        return modelAndView;
    }

    @RequestMapping(value = "/singUp/{type}", method = RequestMethod.POST)
    public String saveNewUser(@ModelAttribute("user") User user, @PathVariable("type") String type, ModelMap model){
        if(type.equals("student")){
            user.setRole(UserRole.STUDENT);
        } else if(type.equals("teacher")) {
            user.setRole(UserRole.TEACHER);
        } else if(type.equals("phd")){
            user.setRole(UserRole.PHD);
        }

        user.passwordMD5();
        userRepository.save(user);
        return "redirect:/cms";
    }

    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
    public String getUserForPreview(@PathVariable("id") String id, ModelMap model){
        model.put("user", userRepository.findOne(Long.valueOf(id)));
        return "admin/user";
    }

    @RequestMapping(value = "/admin/forValidation", method = RequestMethod.GET)
    public String getUsersForValidation(String role, ModelMap model){
        model.put("roles", UserRole.values());
        if(role != null){
            if(role.equals("STUDENT")){
                model.put("users", userRepository.findByRoleAndForValidationTrue(UserRole.STUDENT));
            } else if(role.equals("TEACHER")){
                model.put("users", userRepository.findByRoleAndForValidationTrue(UserRole.TEACHER));
            } else if(role.equals("PHD")){
                model.put("users", userRepository.findByRoleAndForValidationTrue(UserRole.PHD));
            } else if(role.equals("ADMINISTRATOR")){
                model.put("users", userRepository.findByRoleAndForValidationTrue(UserRole.ADMINISTRATOR));
            }
            model.put("oldRole", role);
        }else{
            model.put("users", userRepository.findByForValidationTrue());
        }
        return "admin/forValidation";
    }

    // OLD CODE

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAllUsers(ModelMap model){
        model.put("userList", userRepository.findByArchivedFalse());
        model.put("menu", "3");
        return "user/userList";
    }

    @RequestMapping(value = "/user/{username}/{role}", method = RequestMethod.GET)
    public String getSelectedUsers(@PathVariable("username") String username,
                                   @PathVariable("role") String role, ModelMap model){
        if(username.equals("_")){
            model.put("oldUsername", "");
            if(role.equals("admin")){
                model.put("userList", userRepository.findByRoleAndArchivedFalse(UserRole.ADMINISTRATOR));
            } else if(role.equals("all")){
                model.put("userList", userRepository.findByArchivedFalse());
            } else {
                //model.put("userList", userRepository.findByRoleAndArchivedFalse(UserRole.OWNER));
            }
        } else {
            model.put("oldUsername", username);
            if(role.equals("admin")){
                model.put("userList", userRepository.findByUsernameContainingAndRoleAndArchivedFalse(username, UserRole.ADMINISTRATOR));
            } else  if(role.equals("all")){
                List<User> tempList = new ArrayList<User>();
                tempList.add(userRepository.findByUsernameAndArchivedFalse(username));
                model.put("userList", tempList);
            } else {
                //model.put("userList", userRepository.findByUsernameContainingAndRoleAndArchivedFalse(username, UserRole.OWNER));
            }
        }
        model.put("menu", "3");
        model.put("oldRole", role);
        return "user/userList";
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.GET)
    public ModelAndView formForNewUSer(ModelMap model){
        model.put("roleList", UserRole.values());
        ModelAndView modelAndView = new ModelAndView("user", "user", new User());
        modelAndView.setViewName("user/newUser");
        model.put("menu", "3");
        return modelAndView;
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    public String saveNewUser(@Valid @ModelAttribute("user") User user,
                              BindingResult result, ModelMap model){
        if(result.hasErrors() || userRepository.findByUsername(user.getUsername()) != null){
            model.put("roleList", UserRole.values());
            model.put("usernameError", "Username is taken");
            return "user/newUser";
        }else{
            user.passwordMD5();
            userRepository.save(user);
            return "redirect:/cms/admin/user";
        }

    }

    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.GET)
    public ModelAndView getUserForEdit(@PathVariable("id") String id, ModelMap model){
        model.put("roleList", UserRole.values());
        ModelAndView modelAndView = new ModelAndView("user", "user", userRepository.findOne(Long.valueOf(id)));
        modelAndView.setViewName("user/editUser");
        model.put("menu", "3");
        return modelAndView;
    }

    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute("user") User user,
                             BindingResult result, @PathVariable("id") String id,
                             ModelMap model){

        User oldUser = userRepository.findOne(Long.valueOf(id));
        oldUser.setRole(user.getRole());
        oldUser.setFullName(user.getFullName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(oldUser);
        return "redirect:/cms/admin/user";

    }

    @RequestMapping(value = "/user/{id}/archive", method = RequestMethod.GET)
    public String archiveUser(@PathVariable("id") String id){
        User user = userRepository.findOne(Long.valueOf(id));
        user.setArchived(true);
        userRepository.save(user);
        return "redirect:/home";
    }

    @RequestMapping(value = "user/{id}/validate", method = RequestMethod.GET)
    public String validateUser(@PathVariable("id") String id){
        User user = userRepository.findOne(Long.valueOf(id));
        user.setForValidation(false);
        MailManager.sendMail(user.getEmail(), "Одобрен акаунт", "Ващия акаунт беше одобрен");
        userRepository.save(user);
        return "redirect:/admin/forValidation";
    }

    @RequestMapping(value = "user/{id}/password/{flag}", method = RequestMethod.GET)
    public ModelAndView displayPage(@PathVariable("id") String id, @PathVariable("flag") String flag, ModelMap model){
        ModelAndView modelAndView = new ModelAndView("user", "user", userRepository.findOne(Long.valueOf(Long.valueOf(id))));
        modelAndView.setViewName("user/changePassword");
        model.put("flag", flag);
        return modelAndView;
    }

    @RequestMapping(value = "user/{id}/password/{flag}", method = RequestMethod.POST)
    public String updatePassword(@PathVariable("id") String id, @PathVariable("flag") String flag,
                                 @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
                                 ModelMap model){
        if(newPassword.equals(confirmPassword)){
            User user = userRepository.findOne(Long.valueOf(id));
            user.setPassword(newPassword);
            user.passwordMD5();
            userRepository.save(user);
            return "redirect:/cms/admin/user";
        } else {
            return "redirect:/cms/admin/user/"+id+"/password/"+"1";
        }
    }
}
