package com.shelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shelf.models.Book;

/**
 * 
 * @author avinashshukla
 *
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
