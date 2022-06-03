package org.ibs.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.ibs.domain.VersionCompositeId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Calendar;

@Getter
@Setter
@ToString(exclude = {"birthday", "dateCreated"})
@Entity
@Table(name = "people_history")
public class VersionedPerson extends VersionedEntity {

    @EmbeddedId
    VersionCompositeId id;
    Boolean active;
    String firstName;
    String lastName;
    String middleName; // отчество
    Calendar birthday;
    BigDecimal monthSalary;
    Calendar dateCreated;
    Long departmentId;
}
