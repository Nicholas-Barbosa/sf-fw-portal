package com.faraway.fwportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Object Not Found, Use Another Param for Pipeline!")
public class ObjectNotFoundException extends RuntimeException {

}
