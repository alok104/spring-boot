package com.alok.studentservice.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alok.studentservice.model.Student;

@Service
public class RedisServiceImpl {
	
	@Autowired
	@Qualifier(value = "redisTemplate")
	RedisTemplate<String, Object> redisTemplate;
	
	private HashOperations<String, String, Student> hashOps;
	
	@PostConstruct
	public void init() {
		hashOps = redisTemplate.opsForHash();
	}
	
	public void save(Student student) {
		hashOps.put("token"+student.getName(), student.getName(), student);
		redisTemplate.expire("token"+student.getName(), 30, TimeUnit.SECONDS);
	}
	
	public Student find(String name) {
		return hashOps.get("token"+name, name);
	}
	
}
