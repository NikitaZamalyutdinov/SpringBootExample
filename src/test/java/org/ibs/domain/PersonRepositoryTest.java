package org.ibs.domain;

import org.ibs.domain.entity.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void getPerson_successfull() {
        Person person = personRepository.findById(1L).get();
        Assert.assertEquals("Nikita", person.getFirstName());
        Assert.assertEquals("backend", person.getDepartment().getShortName());
    }
}