package com.alok.studentservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alok.studentservice.model.Student;
import com.alok.studentservice.service.StudentService;

@RestController
public class StudentController {
	
    private final Logger LOG = LoggerFactory.getLogger(getClass());
	@Autowired
	StudentService studentService;
	
	@GetMapping("/student")
	public Student getStudent(@RequestParam String name) {
		LOG.info("Getting Student info for #ID : {}",name);
		return studentService.getStudentById(name);
	}
	
	@PostMapping("/student")
	public void addStudent(Student student) {
		LOG.info("Getting Student info for #ID : {}",student.getId());
		studentService.save(student);
	}
}
