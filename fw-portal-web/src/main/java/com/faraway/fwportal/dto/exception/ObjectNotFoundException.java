package com.faraway.fwportal.dto.exception;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.faraway.fwportal.dto.ConhecimentoDto;

public class ObjectNotFoundException extends ConhecimentoDto {

	private ZonedDateTime date = ZonedDateTime.now();
	private String reason;

	public ZonedDateTime getDate() {
		return date;
	}

	public String getReason() {
		return reason;
	}

}
