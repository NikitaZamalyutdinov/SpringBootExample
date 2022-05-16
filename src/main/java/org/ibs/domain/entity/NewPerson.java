package org.ibs.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;

@Getter
@Setter
@ToString
@Entity
@Table(name = "new_people")
public class NewPerson extends Person {

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
