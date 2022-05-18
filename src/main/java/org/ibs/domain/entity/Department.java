package org.ibs.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@ToString
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String fullName;
    String shortName;
    String phoneNumbers;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    Collection<Person> people;
}
