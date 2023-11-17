package com.app.aop.domain.repository;

import com.app.aop.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * A repository interface for Customer entities.
 * Extends JpaRepository to utilize built-in methods for CRUD operations.
 */
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Finds a customer by their document number.
     *
     * @param documentNumber The document number to search for.
     * @return An Optional wrapping the Customer found or an empty Optional if not found.
     */
    Optional<Customer> findByDocumentNumber(final String documentNumber);
}
