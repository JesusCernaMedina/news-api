package com.news.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;

import javax.swing.table.DefaultTableModel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewsApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NewsApiApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
	}

}
