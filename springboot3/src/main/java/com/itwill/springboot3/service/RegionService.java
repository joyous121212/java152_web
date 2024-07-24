package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.domain.Region;
import com.itwill.springboot3.repository.RegionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegionService {
	
	private final RegionRepository regRepo;
	
	public List<Region> read() {
		log.info("read()");
		
		return regRepo.findAll();
	}
	
	public Region read(int id) {
		log.info("read(id={})", id);
		
		return regRepo.findById(id).orElseThrow();
	}
}
