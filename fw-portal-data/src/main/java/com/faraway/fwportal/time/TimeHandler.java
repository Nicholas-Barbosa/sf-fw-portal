package com.faraway.fwportal.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public final class TimeHandler {

	public static ZonedDateTime getZonedDateTime() {
		return ZonedDateTime.now().minusHours(1);
	}

	public static LocalDate getLocalDate() {
		return LocalDate.now();
	}

	public static LocalDateTime getLocalDateTime() {
		return LocalDateTime.now().minusHours(1);
	}
}
