package com.news.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/news")
public class NoticiasRest {

	@Autowired
	private JdbcTemplate jdbc;
	
	String sql = "SELECT * FROM usuario";
	
	final static String API_KEY = "a6b9be6c0870462b80c8af86a003a279";
	
	private HttpClient httpClient = HttpClient.newBuilder().version(Version.HTTP_2).build();
	private ObjectMapper mapper = new ObjectMapper();
	
	public <T> T converObject(final String json, final TypeReference<T> reference) {
		try {
			return this.mapper.readValue(json, reference);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Noticias apiNews() {
		final HttpRequest requestPosts = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create("https://newsapi.org/v2/top-headlines?country=mx&category=business&apiKey=" + API_KEY))
				.build();
		try {
			final HttpResponse<String> response = httpClient.send(requestPosts, HttpResponse.BodyHandlers.ofString());
			Noticias news = converObject(response.body(), new TypeReference<Noticias>(){} );
//			System.out.println(response.body());
			return news;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/bbva")
	public ResponseEntity<List<Articles>> getNews() {
		return ResponseEntity.ok(apiNews().getArticles());
	}
	
	@GetMapping
	public ResponseEntity<List<Noticias>> getProduct() {
		List<Noticias> listNews = jdbc.query(sql, new RowMapper<Noticias>() {

			@Override
			public Noticias mapRow(ResultSet rs, int rowNum) throws SQLException {
				Noticias news = new Noticias();
//				news.setId(rs.getInt("id"));
//				news.setName(rs.getString("name"));
//				news.setAuthor(rs.getString("author"));
//				news.setTitle(rs.getString("title"));
//				news.setDescription(rs.getString("description"));
//				news.setUrl_img(rs.getString("url_img"));
//				news.setUrl(rs.getString("url"));
//				news.setDate(rs.getString("date"));
//				System.out.println(news.getName());
				return news;
			}
			
		});
		return ResponseEntity.ok(listNews);
	}
}