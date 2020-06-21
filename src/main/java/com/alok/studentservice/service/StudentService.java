package com.alok.studentservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alok.studentservice.model.Student;
import com.alok.studentservice.redis.RedisServiceImpl;

@Service
public class StudentService {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

	List<Student> studentList;
	
	Map<Integer, Student> studentMap;
	
	@Autowired
	RedisServiceImpl redisService;
	
	@PostConstruct
	public void init() {
		studentMap = new HashMap<Integer, Student>();
	}
	
	public Student getStudentById(String name) {
		Student student = redisService.find(name);
		if (student != null)
			LOG.info("Data found in cache #id {}", name);
		return student;

	}

	public void save(Student student) {
		redisService.save(student);
		studentMap.put(student.getId(), student);
	}
}
