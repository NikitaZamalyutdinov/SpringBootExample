package org.ibs.domain;

import org.ibs.domain.entity.VersionedEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;

@NoRepositoryBean
public class VersionedRepository<T extends VersionedEntity, ID extends VersionCompositeId> implements CrudRepository<T, ID> {
    @PersistenceContext
    private EntityManager em;

    private final Class<T> domainClass;

    public VersionedRepository(Class<T> domainClass) {
        this.domainClass = domainClass;
    }

    /**
     * Find entity with the latest version.
     *
     * @param id
     * @return
     */
    public Optional<T> findById(Long id) {
        String hql = "FROM " + domainClass.getSimpleName() + " as e WHERE e.active=true AND e.id.id= :id ORDER BY e.id.versionId DESC";
        TypedQuery<T> query = em.createQuery(hql, domainClass);
        query.setParameter("id", id);
        query.setMaxResults(1);
        return Optional.of(query.getResultList().get(0));
    }

    @Transactional
    @Override
    public <S extends T> @NotNull S save(@NotNull S entity) {
        Assert.notNull(entity, "Entity must not be null.");
        VersionCompositeId id = entity.getId();
        if (id == null) { // new
            Long newId = em.createQuery("SELECT max(id.id) FROM " + domainClass.getSimpleName(), Long.class)
                    //.setParameter("table", domainClass.getSimpleName())
                    .getSingleResult() + 1;
            Long versionId = 1L; // version starts from 1
            entity.setId(new VersionCompositeId(newId, versionId));
            em.persist(entity);
            return entity;
        } else {
            em.detach(entity);  // Always add new row on entity updates

            Assert.notNull(id.versionId, "Entity version id must not be null.");
            entity.setId(new VersionCompositeId(id.id, ++id.versionId));
            em.persist(entity);
            return entity;
        }
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<T> findById(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        Class<T> domainType = this.domainClass;

        return Optional.ofNullable(this.em.find(domainType, id));
    }

    @Override
    public boolean existsById(ID id) {
        return false;
    }

    @Override
    public Iterable<T> findAll() {
        return null;
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
        return null;
    }

    @Override
    public long count() {
        return em.createQuery("FROM " + domainClass.getSimpleName())
                .getResultList()
                .size();
    }

    @Override
    public void deleteById(ID id) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {

    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {

    }

    @Override
    public void deleteAll() {

    }

    //@Query("FROM #{#entityName} as e WHERE active=true AND e.id=:#{#id} ORDER BY e.versionId")
    //T findFirstById(@Param("id")ID id);
}
