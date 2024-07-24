package com.itwill.springboot3.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot3.domain.Location;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class LocationRepositoryTest {
	
	@Autowired
	private LocationRepository locRepo;
	
	@Transactional
	@Test
	public void testFindById() {
		Location loc = locRepo.findById(1700).orElseThrow();
		log.info("loc={}", loc);
	}
}
