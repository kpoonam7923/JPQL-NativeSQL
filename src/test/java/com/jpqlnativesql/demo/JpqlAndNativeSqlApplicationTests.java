package com.jpqlnativesql.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.jpqlnativesql.demo.entities.Student;
import com.jpqlnativesql.demo.repository.StudentRepo;

@SpringBootTest
class JpqlAndNativeSqlApplicationTests {

	@Autowired
	StudentRepo repo;
	
	@Test
	public void createStudent() {
		
		Student student = new Student();
		student.setFname("Pooja");
		student.setLname("Kale");
		student.setScore(100);
		repo.save(student);
		
		Student student2 = new Student();
		student2.setFname("Siddhesh");
		student2.setLname("Kale");
		student2.setScore(99);
		repo.save(student2);
	}
	
	//Fetch all Data
	@Test
	public void fetchStudent() {
		System.out.println(repo.findAllStudent());
	}
	
	//Fetch Partial data
	@Test
	public void fetchStudentPartialData() {
		//System.out.println(repo.findAllStudentPartialData());   this will print object reference in output
		
		//here we will get user friendly data of fname and lname
		List<Object[]> studentPartialData = repo.findAllStudentPartialData();
		for (Object[] ob : studentPartialData) {
			//below will be used for lname entity to print
			System.out.println(ob[0]);
			//below will be used for fname entity to print
			System.out.println(ob[1]);
		}
	}
	
	@Test
	public void fetchStudentFirstName() {
		System.out.println(repo.findByStudentFirstName("Poonam"));
	}
	
	@Test
	public void fetchStudentByScore() {
		System.out.println(repo.findByScore(95, 85));
	}
	
	@Test
	@Transactional
	//rollback in only Junit, otherwise data won't be deleted
	@Rollback(false)
	public void deleteByFname() {
		repo.deleteByFname("Poonam");
	}

}
