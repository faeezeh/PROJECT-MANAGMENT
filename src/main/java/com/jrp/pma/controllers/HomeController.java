package com.jrp.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    /*@Value("@{version}")
    private String ver;*/
    @Autowired
    ProjectRepository proRepo;
    @Autowired
    EmployeeRepository empRepo;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

//        model.addAttribute("versionNumber",ver);
        Map<String,Object> map = new HashMap<>();

        List<Project> projects = proRepo.findAll();
        model.addAttribute("projectsList",projects);

        List<ChartData> projectStatus = proRepo.getProjectStatus();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString =  objectMapper.writeValueAsString(projectStatus);
        //["not started",1],["inprogress",2],["complete",3]
        model.addAttribute("projectStatusCnt",jsonString);

        //List<Employee> employees = empRepo.findAll();
        //model.addAttribute("employeesList",employees);
        List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
        model.addAttribute("employeesProjectCnt",employeesProjectCnt);



        return "main/home";
    }

}
