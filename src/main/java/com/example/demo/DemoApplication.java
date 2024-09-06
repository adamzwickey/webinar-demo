package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class DemoApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	String home() {
		return "Spring is here!";
	}

	@Autowired
	private DemoController controller;

	@Override
	public void run(String... args) {
		try {
			LOG.info("Loading messages...");
			if(!controller.getAll().isEmpty()) return;

			controller.save(new DemoMessage("People are naturally attracted to you."));
			controller.save(new DemoMessage("You learn from your mistakes... You will learn a lot today."));
			controller.save(new DemoMessage("What ever your goal is in life, embrace it visualize it, and for it will be yours."));
			controller.save(new DemoMessage("Your shoes will make you happy today."));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}


}