package org.ibs.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.ibs.domain.VersionCompositeId;

import javax.persistence.NamedQuery;

@Getter
@Setter
@NamedQuery(name = "Get max Id", query = "SELECT max(id) FROM :table")
public class VersionedEntity {

    VersionCompositeId id;
}
