package com.shelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shelf.models.Customer;

/**
 * 
 * @author avinashshukla
 *
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	public Customer findByEmail(String email);
	
	@Query(value = "Select * from customer c where c.email = ?1 and c.password = ?2", nativeQuery = true)
	public Customer findByEmailAndPassword(String email, String password);
}
