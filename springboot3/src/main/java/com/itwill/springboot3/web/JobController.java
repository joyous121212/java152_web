package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot3.domain.Job;
import com.itwill.springboot3.service.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/job")
public class JobController {
	
	private final JobService jobSvc;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list()");
		
		List<Job> list = jobSvc.read();
		
		model.addAttribute("jobs", list);
	}
	
	@GetMapping("/details/{id}")
	public String jobDetails(@PathVariable(name="id") String id, Model model) {
		log.info("employeeDetails(id={})", id);
		
		Job emp = jobSvc.read(id);
		model.addAttribute("jobs", emp);
		
		return "job/details";
	}
}
