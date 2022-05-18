package org.ibs.rest.service;

import org.ibs.middleware.DepartmentExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("expense")
public class ExpensesService {

    @Autowired
    private DepartmentExpensesService departmentExpensesService;

    @GetMapping(path = "department/{id}", produces = "application/json")
    public Integer calculateDepartmentSalaryExpenses(@PathVariable int id) {
        Optional<Integer> expensesOpt = departmentExpensesService.calculateDepartmentSalaryExpenses(id);
        if (expensesOpt.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Department with id ({id}) not found.");
        }
        return expensesOpt.get();
    }
}
