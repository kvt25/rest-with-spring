package com.kien.restwithspring.controller;


import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.kien.restwithspring.exception.EmployeeNotFoundException;
import com.kien.restwithspring.model.Document;
import com.kien.restwithspring.model.Employee;
import com.kien.restwithspring.repository.DocumentRepository;
import com.kien.restwithspring.repository.EmployeeRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeeController {

    private final EmployeeRepository empRepository;
    private final DocumentRepository docRepository;

    EmployeeController(EmployeeRepository empRepository, DocumentRepository docRepository) {
        this.empRepository = empRepository;
        this.docRepository = docRepository;
    }

    // Aggregate root

    @GetMapping("/employees")
    List<Employee> all() {
        return empRepository.findAll();
    }

    @GetMapping("/documents")
    List<Document> allDocs() {
        return docRepository.findAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return empRepository.save(newEmployee);
    }

    // Single item

    @GetMapping(value = "/employees/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    Resource<Employee> one(@PathVariable Long id) throws EmployeeNotFoundException {
        Employee emp = empRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return new Resource<Employee>(emp,
                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));

    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return empRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return empRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return empRepository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        empRepository.deleteById(id);
    }
}