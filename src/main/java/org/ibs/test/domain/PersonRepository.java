package org.ibs.test.domain;

import org.ibs.test.domain.entity.Department;
import org.ibs.test.domain.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastName(String lastName);
    List<Person> findByBirthday(Calendar birthday);
    List<Person> findByDepartment(Department department);
}
