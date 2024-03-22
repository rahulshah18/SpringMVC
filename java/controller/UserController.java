/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.DataService;

/**
 *
 * @author HP
 */
@Controller
public class UserController {

    public UserController() {
        System.out.println("I'm in Cotroller");
    }

    @RequestMapping("form")
    public ModelAndView getForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register");
        return mv;
    }

    @RequestMapping("save")
    public ModelAndView saveRecord(@RequestParam("name") String name,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("gender") String gender,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("loginId") String loginId,
            @RequestParam("passwordHash") String passwordHash) {
        ModelAndView mv = new ModelAndView();
        DataService dataService = new DataService();
        if (dataService.isLoginIdExists(loginId) != true) {
            int maxId = dataService.getMaxId() + 1;
            Student u = new Student(maxId, name, dateOfBirth, gender, address, city, state, loginId, passwordHash);

            dataService.saveRecord(u);
            mv.setViewName("login");
            return mv;

        } else {

            mv.setViewName("login");
            return mv;
        }

    }

    @RequestMapping("validate")
    public ModelAndView validateUP(@RequestParam String loginId,
            @RequestParam String password) {

        DataService d = new DataService();
        ModelAndView mv = new ModelAndView();
        boolean validateCredentials = d.validateCredentials(loginId, password);
        if (validateCredentials != true) {
            mv.setViewName("failure");
        } else {
            mv.setViewName("welcome");
            int maxId = d.getMaxId();
            Student u = d.getUserById(maxId);
            mv.addObject("u1", u);
            List<Student> allRecords = d.getAllRecords();
            mv.addObject("allRecords", allRecords);
        }
        return mv;

    }
    
    
    @RequestMapping("login")
    public ModelAndView getLogin()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

}
