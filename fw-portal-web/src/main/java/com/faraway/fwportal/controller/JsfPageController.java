package com.faraway.fwportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JsfPageController {

	@RequestMapping("/jsf")
	public String jsfPage() {
		return "jsf.xhtml";
	}
}
