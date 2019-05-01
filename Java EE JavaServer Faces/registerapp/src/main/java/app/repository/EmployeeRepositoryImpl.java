package app.repository;

import app.domain.entities.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final String FIND_BY_ID_QUERY = "SELECT e FROM employees e WHERE e.id=:id";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM employees e WHERE e.id=:id";
    private static final String FIND_ALL_QUERY = "SELECT e FROM employees e";

    private EntityManager entityManager;

    @Inject
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee save(Employee entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Employee> findAll() {
        return this.entityManager.createQuery(FIND_ALL_QUERY, Employee.class)
                .getResultList();
    }

    @Override
    public Employee findById(String id) {
        return this.entityManager.createQuery(FIND_BY_ID_QUERY, Employee.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void remove(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery(DELETE_BY_ID_QUERY)
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
