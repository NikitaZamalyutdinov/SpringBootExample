package org.ibs.test.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String fullName;
    String shortName;
    String phoneNumbers;

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
