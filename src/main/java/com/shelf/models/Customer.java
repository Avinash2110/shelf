package com.shelf.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author avinashshukla
 *
 */

@Entity(name="customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="firstName", nullable = false)
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="email", unique = true)
	private String email;
	
	@Column(name="password", nullable = false)
	@JsonIgnore
	private String password;
	
	@Column(name="customerType", nullable = false)
	private String customerType;
	
	@Column(name="birtDate")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date birthDate;
	
	@Column(name="joiningDate")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date joiningDate;

}
