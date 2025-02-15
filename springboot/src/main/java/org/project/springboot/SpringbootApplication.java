package org.project.springboot;

import org.project.springboot.db.db.cli.CliManager;
import org.project.springboot.db.db.service.AutoreService;
import org.project.springboot.db.db.service.GenereService;
import org.project.springboot.db.db.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {
	@Autowired
	public LibroService libroService;
	@Autowired
    public GenereService genereService;
	@Autowired
    public AutoreService autoreService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new CliManager(libroService, genereService, autoreService);

	}

}
