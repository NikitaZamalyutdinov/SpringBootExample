package org.ibs.domain;

import org.ibs.domain.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
@RepositoryRestResource(path = "people")
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastName(String lastName);
    List<Person> findByBirthday(Calendar birthday);
    List<Person> findByDepartmentId(Long departmentId);
}
