package org.ibs.middleware;

import org.ibs.domain.DepartmentRepository;
import org.ibs.domain.entity.Department;
import org.ibs.domain.entity.Person;
import org.ibs.soap.calculator.CalculatorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentExpensesService {

    @Autowired
    private CalculatorClient calculatorClient;

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Calculates department salary expenses.
     *
     * @param departmentId Id of department to calculate.
     * @return null if department not found.
     */
    public Optional<Integer> calculateDepartmentSalaryExpenses(int departmentId) {
        Optional<Department> depOpt = departmentRepository.findById(((long) departmentId));
        if (depOpt.isEmpty()) {
            return Optional.empty();
        }
        List<BigDecimal> monthSalaries = departmentRepository.findById(((long) departmentId)).get()
                .getPeople().stream()
                .map(Person::getMonthSalary)
                .toList();

        return Optional.of(monthSalaries.stream()
                .map(BigDecimal::intValue)
                .reduce(0, (a, b) -> calculatorClient.addNumbers(a, b)));
    }
}
