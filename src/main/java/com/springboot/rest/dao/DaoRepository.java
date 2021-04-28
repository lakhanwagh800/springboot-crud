package com.springboot.rest.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.rest.model.Student;
@Repository
public interface DaoRepository extends PagingAndSortingRepository<Student, Integer>{

}
