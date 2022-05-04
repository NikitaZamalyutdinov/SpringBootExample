package org.ibs.test.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String fullName;
    String shortName;
    String phoneNumbers;
}
