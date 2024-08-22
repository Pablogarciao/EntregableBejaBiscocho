package com.example.proyectospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ProyectospringApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        // Programmatically set system properties so Spring can access them
        System.setProperty("CONNECTION_URL",
                dotenv.get("CONNECTION_URL"));
        System.setProperty("USER",
                dotenv.get("USER"));
        System.setProperty("PASSWORD",
                dotenv.get("PASSWORD"));

        SpringApplication.run(ProyectospringApplication.class, args);
    }

}
