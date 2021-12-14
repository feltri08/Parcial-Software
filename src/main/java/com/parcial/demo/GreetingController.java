package com.parcial.demo;

import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class GreetingController {

	private static final String template_fr = "Salut, %s!";
	private static final String template_es = "Hola, %s!";
	private static final String template_en = "Hello, %s!";
	private static final String template_dn = " I don't know your language";
	private final AtomicLong counter = new AtomicLong();
	private Hashtable<String, String> list = new Hashtable<String, String>();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		if (list.containsKey(name)) {
			String lang = list.get(name);
			if (lang.equals("EN")) {
				return new Greeting(counter.incrementAndGet(), String.format(template_en, name));
			} else if (lang.equals("ES")) {
				return new Greeting(counter.incrementAndGet(), String.format(template_es, name));
			} else if (lang.equals("FR")) {
				return new Greeting(counter.incrementAndGet(), String.format(template_fr, name));
			} else {
				return new Greeting(counter.incrementAndGet(), String.format(template_dn, name) );
			}
		}
		return new Greeting(counter.incrementAndGet(), "You are not registered yet");
	}
	
	@PostMapping("/register")
	
	public void register(@RequestParam(value = "name") String name, @RequestParam(value = "lang") String lang) {
		list.put(name, lang);
	}

}