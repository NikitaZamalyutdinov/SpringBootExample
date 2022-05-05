package org.ibs.domain;

import org.ibs.domain.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastName(String lastName);
    List<Person> findByBirthday(Calendar birthday);
    List<Person> findByDepartmentId(Long departmentId);
}
