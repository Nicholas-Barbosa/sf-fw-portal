package com.faraway.fwportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {

	@RequestMapping("/")
	public String hello() {
		return "hello";
	}
}
