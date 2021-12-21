package com.jrp.pma.controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping
    public String displayEmployees(Model model){

        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employeesList",employees);


        return "employees/list-employeees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model){

        Employee aEmployee = new Employee();

        model.addAttribute("employee",aEmployee);
        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String craeteEmployee(Employee employee,Model model){
        empRepo.save(employee);

        //use a redirect to prevent duplicate submissions
        return "redirect:/employees/new";
    }
}
