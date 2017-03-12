package com.vlas.gradpro.web;

import com.vlas.gradpro.model.User;
import com.vlas.gradpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

/**
 * Created by nassuka on 20.02.2017.
 */
@Controller
public class RootController {

//    @Autowired
//    private UserService service;
//
//    @GetMapping("/")
//    public String root() {
//        return "redirect:restaurants";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(ModelMap model,
//                        @RequestParam(value = "error", required = false) boolean error,
//                        @RequestParam(value = "message", required = false) String message) {
//        model.put("error", error);
//        model.put("message", message);
//        return "login";
//    }
//
//    @GetMapping("/register")
//    public String signUp(ModelMap model) {
//        model.addAttribute("user", new User());
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String updateProfile(@Valid User user, BindingResult result, SessionStatus status, ModelMap model) {
//        if (!result.hasErrors()) {
//            try {
//                service.save(user);
//                status.setComplete();
//                return "redirect:login?message=User registered";
//            } catch (DataIntegrityViolationException ex) {
//                return "redirect:login?message=Registration failed - duplicate e-mail";
//            }
//        }
//        return "register";
//    }
}
