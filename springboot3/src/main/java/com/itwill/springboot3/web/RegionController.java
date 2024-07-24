package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.domain.Region;
import com.itwill.springboot3.service.RegionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/region")
public class RegionController {
	
	private final RegionService regSvc;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list()");
		
		List<Region> list = regSvc.read();
		
		model.addAttribute("regions", list);
	}
	
	@GetMapping("/details/{id}")
	public String regionDetails(@PathVariable(name="id") Integer id, Model model) {
		log.info("regionDetails(id={})", id);
		
		Region reg = regSvc.read(id);
		model.addAttribute("regions", reg);
		
		return "region/details";
	}
	
}
