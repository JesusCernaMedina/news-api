package com.news.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NoticiasRest {

	@Autowired
	private JdbcTemplate jdbc;
	
	String sql = "SELECT * FROM noticias";
	
	@GetMapping
	public ResponseEntity<List<Noticias>> getProduct() {
		List<Noticias> listNews = jdbc.query(sql, new RowMapper<Noticias>() {

			@Override
			public Noticias mapRow(ResultSet rs, int rowNum) throws SQLException {
				Noticias news = new Noticias();
				news.setId(rs.getInt("id"));
				news.setName(rs.getString("name"));
				news.setAuthor(rs.getString("author"));
				news.setTitle(rs.getString("title"));
				news.setDescription(rs.getString("description"));
				news.setUrl_img(rs.getString("url_img"));
				news.setUrl(rs.getString("url"));
				news.setDate(rs.getString("date"));
				return news;
			}
			
		});
		return ResponseEntity.ok(listNews);
	}
	
}
