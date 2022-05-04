package org.ibs.test.domain.entity;

import com.sun.istack.NotNull;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    String firstName;
    String lastName;
    String middleName; // отчество
    Calendar birthday;
    @ManyToOne()
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @RestResource(path = "personDepartment", rel="department")
    Department department;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public Department getDepartment() {
        return department;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
