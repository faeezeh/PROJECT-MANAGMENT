package com.jrp.pma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long projectId;
    private String name;
    private String stage;
    private String description;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
        fetch = FetchType.LAZY)
    @JoinTable(name="project_employee",
        joinColumns=@JoinColumn(name="project_id"),
        inverseJoinColumns= @JoinColumn(name="employee_id")
    )
    @JsonIgnore
    private List<Employee> employees;

    /*@OneToMany(mappedBy = "project")
    private List<Employee> employees;*/

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public Project(){

    }
    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // convenience method:
    public void addEmployee(Employee emp) {
        if(employees==null) {
            employees = new ArrayList<>();
        }
        employees.add(emp);
    }


}
