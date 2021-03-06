package com.faraway.fwportal.internationalization;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ObjRBundle {

	private final ResourceBundle resource;

	public ObjRBundle() {

		this.resource = ResourceBundle.getBundle("resourceBundle/message", LocaleContextHolder.getLocale());
//				new Control() {
//
//					@Override
//					public List<Locale> getCandidateLocales(String baseName, Locale locale) {
//						System.out.println("Locale to candidates " + locale);
//						return super.getCandidateLocales(baseName, locale).parallelStream().filter(l -> {
//							System.out.println("Locale on filter " + l);
//							return l.getLanguage().equals(locale.getLanguage()) || l.getLanguage().isBlank();
//						}).collect(Collectors.toList());
//					}
//				});
	}

	public String getMessage(String key, Object... args) {
		String format = resource.getString(key);
		return MessageFormat.format(format, args);
	}
}
