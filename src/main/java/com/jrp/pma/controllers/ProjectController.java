package com.jrp.pma.controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository proRepo;

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping
    public String displayEmployees(Model model){

        List<Project> projects = proRepo.findAll();
        model.addAttribute("projectList",projects);

        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model){

        Project aProject = new Project();
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employeesList",employees);
        model.addAttribute("project",aProject);

        return "projects/new-project";

    }

    @PostMapping("/save")
    public String craeteProject(Project project,  Model model){

        proRepo.save(project);
        //@RequestParam List<Long> employees ,
        //Iterable<Employee> employeeList = empRepo.findAllById(employees);
        /*for (Employee emp : employeeList){
            emp.setProject(project);
            empRepo.save(emp);
        }*/



        //use a redirect to prevent duplicate submissions
        return "redirect:/projects/new";
    }








}
