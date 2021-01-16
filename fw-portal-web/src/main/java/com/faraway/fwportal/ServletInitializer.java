package com.faraway.fwportal;

import java.util.Locale;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		Locale.setDefault(new Locale("pt", "BR"));
		return application.sources(SfFwPortalApplication.class);
	}

}
