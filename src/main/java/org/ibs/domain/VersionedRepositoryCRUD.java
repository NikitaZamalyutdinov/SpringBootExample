package org.ibs.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.NoRepositoryBean;

@Slf4j
@NoRepositoryBean
public abstract class VersionedRepositoryCRUD<T> {
/*
    private final Class<T> type;

    @Autowired
    private SessionFactory sessionFactory;

    protected VersionedRepositoryCRUD(Class<T> type) {
        this.type = type;
    }

    protected T findById(Serializable id) {
        Session session = sessionFactory.openSession();
        T t = session.createQuery("FROM #{#entityName} as e WHERE active=true AND e.id=:id ORDER BY")  //get(type, id);
        session.close();
        return t;
    }

    protected Collection<T> findAll(Class<T> type) {
        Session session = sessionFactory.openSession();
        Collection<T> authorizationHistoryList = session.createQuery("From " + type.getName(), type).list();
        session.close();
        return authorizationHistoryList;
    }

    public static void save(Object obj) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();
        } catch (Exception x) {
            log.error(x);
            if (transaction != null) transaction.rollback();
        }
    }

    public static void update(Object obj) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.update(obj);
            transaction.commit();
        } catch (Exception x) {
            log.error(x);
            if (transaction != null) transaction.rollback();
        }
    }

    public static void delete(Object obj) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.delete(obj);
            transaction.commit();
        } catch (Exception x) {
            log.error(x);
            if (transaction != null) transaction.rollback();
        }
    }
*/
}
