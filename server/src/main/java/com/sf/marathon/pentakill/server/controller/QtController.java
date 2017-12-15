package com.sf.marathon.pentakill.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QtController {
	
	@RequestMapping("/qc/{id1}/{id2}")
	public ModelAndView index(@PathVariable String id1, @PathVariable String id2){
		Map<String,String> m = new HashMap<>();
		m.put("id1", id1);
		m.put("id2", id2);
		return new ModelAndView("jump", m);
	}
	
}
