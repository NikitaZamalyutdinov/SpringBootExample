package org.ibs.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.Calendar;

@Getter
@Setter
@ToString
@Entity
@Table(name = "new_people")
public class NewPerson {

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

    Calendar recordCreateDate;

    public static NewPerson fromPerson(Person p, Calendar recordCreateDate) {
        NewPerson n = new NewPerson();
        n.id = p.id;
        n.firstName = p.firstName;
        n.middleName = p.middleName;
        n.lastName = p.lastName;
        n.birthday = p.birthday;
        n.department = p.department;
        n.recordCreateDate = recordCreateDate;

        return n;
    }
}
