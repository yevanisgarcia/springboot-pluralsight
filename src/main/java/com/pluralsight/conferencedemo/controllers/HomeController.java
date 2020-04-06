package com.pluralsight.conferencedemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;

@RestController
public class HomeController {
	@Value("${app.version}")
	private String appVersion;

	@GetMapping("/")
	public Map getStatus() {
		Map map = new HashMap<String, String>();
		map.put("app-version", appVersion);

		return map;
	}
}
