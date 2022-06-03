package org.ibs.domain;

import lombok.extern.slf4j.Slf4j;
import org.ibs.domain.entity.VersionedPerson;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class VersionedRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private VersionedPersonRepository repository;

    @BeforeEach
    void logPeopleHistoryTable() {
        List<VersionedPerson> list = em.createQuery("FROM VersionedPerson", VersionedPerson.class)
                .getResultList();
        list.forEach(p -> log.info(p.toString()));
    }

    @Test
    void findById_twoRowsExists_returnsRowWithLastVersion() {
        Assert.assertEquals(2L, repository.findById(1L).get().getId().versionId.longValue());
    }

    @Test
    void save_newEntity_newRowWithIncrementedIdCreated() {
        VersionedPerson p = repository.save(getNewPerson());
        Assert.assertEquals(2L, p.getId().id.longValue());
    }

    @Test
    void save_updatedEntity_newRowWithOldIdCreated() {
        VersionedPerson p = getUpdatedPerson();
        p.setMonthSalary(BigDecimal.valueOf(300000));
        VersionedPerson u = repository.save(p);
        Assert.assertEquals(1L, u.getId().id.longValue());
        Assert.assertEquals(3L, u.getId().versionId.longValue());
    }

    private VersionedPerson getUpdatedPerson() {
        return repository.findById(1L).get();
    }

    private VersionedPerson getNewPerson() {
        VersionedPerson p = new VersionedPerson();
        p.setActive(true);
        p.setDateCreated(Calendar.getInstance()); // ToDo: Repo should do it
        p.setFirstName("A");
        p.setLastName("A");
        p.setMiddleName("A");
        p.setDepartmentId(1L);
        p.setMonthSalary(BigDecimal.valueOf(500000));
        p.setBirthday(Calendar.getInstance());
        return p;
    }
}