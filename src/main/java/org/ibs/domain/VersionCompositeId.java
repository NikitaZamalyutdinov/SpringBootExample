package org.ibs.domain;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@ToString
@AllArgsConstructor
public class VersionCompositeId implements Serializable {
    public Long id;
    public Long versionId;

    public VersionCompositeId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VersionCompositeId that = (VersionCompositeId) o;
        return Objects.equals(id, that.id) && Objects.equals(versionId, that.versionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, versionId);
    }
}
