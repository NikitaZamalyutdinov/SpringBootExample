package org.ibs.middleware;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith( SpringRunner.class )
@SpringBootTest()
@ActiveProfiles("test")
class DepartmentExpensesServiceTest {

    @Autowired
    private DepartmentExpensesService departmentExpensesService;

    final static int DEPARTMENT_ID = 1;

    @Test
    @Transactional
    void calculateDepartmentSalaryExpenses() {
        Assert.assertEquals(Optional.of(200000), departmentExpensesService.calculateDepartmentSalaryExpenses(DEPARTMENT_ID));
    }
}