package org.ibs.soap.calculator;

import org.ibs.config.CalculatorClientConfig;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest(classes = CalculatorClientConfig.class)
@ActiveProfiles("test")
class CalculatorClientTest {

    @Autowired
    CalculatorClient calculatorClient;

    @Test
    void addNumbers() {
        Assert.assertEquals(7, calculatorClient.addNumbers(5, 2));
    }
}