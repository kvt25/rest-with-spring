package com.kien.restwithspring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Document {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    @JsonIgnoreProperties("documents")
    private Employee employee;

    Document() {};

    public Document(String name, Employee emp) {
        this.name = name;
        this.employee = emp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
