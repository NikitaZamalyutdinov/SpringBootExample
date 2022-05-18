package org.ibs.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

@Getter
@Setter
@ToString(exclude = "department") // Avoid eager fetch
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
    BigDecimal monthSalary;

    @ManyToOne()
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @RestResource(path = "department", rel="department", description = @Description("Url to department."))
    Department department;
}
