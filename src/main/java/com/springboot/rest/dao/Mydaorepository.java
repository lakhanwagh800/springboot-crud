package com.springboot.rest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.springboot.rest.model.Student;

@Repository
public interface Mydaorepository extends JpaRepository<Student, Integer> {

	
	
	Optional<Student> findByEmail(String email);
}