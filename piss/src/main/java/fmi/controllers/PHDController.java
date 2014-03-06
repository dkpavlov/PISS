package fmi.controllers;

import fmi.hibernate.model.Attestation;
import fmi.hibernate.model.UserRole;
import fmi.repository.AttestationRepository;
import fmi.repository.UserRepository;
import fmi.specification.UserSpecification;
import org.apache.shiro.SecurityUtils;
import org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: dkpavlov
 * Date: 1/6/14
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/phd")
public class PHDController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AttestationRepository attestationRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllActivePHDs(ModelMap model){
        model.put("pHDsList", userRepository.findAll(Specifications.where(UserSpecification.roleIs(UserRole.PHD))
                .and(UserSpecification.validatedIs(new Boolean(false)))
                .and(UserSpecification.archiveIs(new Boolean(false)))));

        return "phd/pHDsList";
    }

    @RequestMapping(value = "/{id}/ate", method = RequestMethod.GET)
    public ModelAndView getFormForAttestation(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView("phd/attestation", "attestation", new Attestation());
        modelAndView.getModel().put("phd", userRepository.findOne(Long.valueOf(id)));
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/ate", method = RequestMethod.POST)
    public String saveAttestation(@ModelAttribute("attestation") Attestation attestation, @PathVariable("id") String id){
        attestation.setPhd(userRepository.findOne(Long.valueOf(id)));
        attestation.setTeacher(userRepository.findByUsername((String) SecurityUtils.getSubject().getPrincipal()));
        attestationRepository.save(attestation);
        return "redirect:/phd/all";
    }
}
