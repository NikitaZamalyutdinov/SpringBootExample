package org.ibs.test.domain.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Calendar;

@Getter
@Setter
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
    @RestResource(path = "department", rel="department", description = @Description("Url to department."))
    Department department;
}
