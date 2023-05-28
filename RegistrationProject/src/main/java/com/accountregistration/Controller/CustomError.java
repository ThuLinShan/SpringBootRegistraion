package com.accountregistration.Controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;

@Controller
public class CustomError implements ErrorController{

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(status != null && Integer.valueOf(status.toString()) == HttpStatus.NOT_FOUND.value()) {
			return "404";
		}
		
		return "error";
	}
}
