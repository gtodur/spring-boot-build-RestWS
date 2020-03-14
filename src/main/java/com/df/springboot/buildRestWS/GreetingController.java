package com.df.springboot.buildRestWS;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	private static final String greetingTemplate = "Hello, %s!";
	private static final String sayByeTemplate = "Goodbye, %s!";
	private static final String saySomethingElseTemplate = "How are you, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping(value = "/greeting")
	//@PreAuthorize("hasAuthority('greetingAPI')")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(greetingTemplate, name));
	}
	
	@GetMapping(value = "/sayBye")
	//@PreAuthorize("hasRole('Client2')")
	public Greeting sayBye(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(sayByeTemplate, name));
	}
	
	@GetMapping(value = "/saySomethingElse")
	//@PreAuthorize("hasAnyRole('Client1', 'Client2')")
	public Greeting saySomethingElse(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(saySomethingElseTemplate, name));
	}
	
}
