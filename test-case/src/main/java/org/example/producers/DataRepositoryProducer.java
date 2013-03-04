package org.example.producers;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.example.qualifiers.DataRepository;

/**
 * This is the stateless EJB that produced our {@link EntityManager} instance. This EJB has an entity manager injected and we
 * return it to CDI in our {@link Produces} annotated method. We qualify the producer with the {@link DataRepository} qualifier.
 * 
 */
public class DataRepositoryProducer {

    @Produces
    @DataRepository
    @PersistenceContext
    private EntityManager entityManager;

}
