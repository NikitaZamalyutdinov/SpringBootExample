package org.ibs.domain;

import org.ibs.domain.entity.VersionedPerson;
import org.springframework.stereotype.Repository;

@Repository
public class VersionedPersonRepository extends VersionedRepository<VersionedPerson, VersionCompositeId> {
    public VersionedPersonRepository() {
        super(VersionedPerson.class);
    }
}
