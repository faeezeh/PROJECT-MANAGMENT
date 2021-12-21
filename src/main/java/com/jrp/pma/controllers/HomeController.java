package com.jrp.pma.controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProjectRepository proRepo;
    @Autowired
    EmployeeRepository empRepo;

    @GetMapping("/")
    public String displayHome(Model model){

        List<Project> projects = proRepo.findAll();
        model.addAttribute("projectsList",projects);

        //List<Employee> employees = empRepo.findAll();
        //model.addAttribute("employeesList",employees);
        List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
        model.addAttribute("employeesProjectCnt",employeesProjectCnt);



        return "main/home";
    }

}
