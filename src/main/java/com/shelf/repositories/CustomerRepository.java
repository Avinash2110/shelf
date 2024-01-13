package com.shelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shelf.models.Customer;

/**
 * 
 * @author avinashshukla
 *
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
