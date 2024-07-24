package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot3.domain.Country;
import com.itwill.springboot3.service.CountryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/country")
public class CountryController {
	
	private final CountryService countrySvc;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list()");
		
		List<Country> list = countrySvc.read();
		
		model.addAttribute("countries", list);
	}
	
	@GetMapping("/details/{id}")
	public String countryDetails(@PathVariable(name="id") String id, Model model) {
		log.info("CountryDetails(id={})", id);
		
		Country country = countrySvc.read(id);
		model.addAttribute("countries", country);
		
		return "country/details";
	}
}
