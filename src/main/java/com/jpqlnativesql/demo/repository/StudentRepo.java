package com.jpqlnativesql.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpqlnativesql.demo.entities.Student;

public interface StudentRepo extends CrudRepository<Student, Integer> {

	//Read All data using JPQL
	@Query("from Student")
	List<Student> findAllStudent();
	
	//Read Partial data using JPQL
	//Object array will contain each column data
	@Query("select fname, lname from Student")
	List<Object[]> findAllStudentPartialData();

	@Query("from Student where fname=:firstname")
	List<Student> findByStudentFirstName(@Param("firstname") String firstname);
	
	@Query("from Student where score>:min and score<:max ")
	List<Student> findByScore(@Param("max") int max, @Param("min") int min);


	//non select query
	@Modifying
	@Query("delete from Student where fname =:firstname")
	void deleteByFname(@Param("firstname") String firstname);
}
