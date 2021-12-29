package com.jrp.pma.services;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServices {

    //feild injection
    @Autowired
    EmployeeRepository empRepo;

    //setter inject
    /*@Autowired
    public void setEmpRepo(EmployeeRepository empRepo){
        this.empRepo = empRepo;
    }*/

    //constructor injection
    /*public EmployeeServices(){
        super();
        this.empRepo = empRepo;
    }*/

    IStaffRepository iStaffRepo ;

    //public EmployeeServices(@Qualifier("staffRepositoryImpl1") IStaffRepository iStaffRepo)
    public EmployeeServices(IStaffRepository iStaffRepo){
        super();
        this.empRepo = empRepo;
    }

    public List<Employee> getAll(){
        return empRepo.findAll();
    }

    public List<EmployeeProject> employeeProjetcs(){
        return empRepo.employeeProjects();
    }
}
