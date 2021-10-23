package com.news.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Noticias {
	
	private int id;
	private String name;
	private String author;
	private String title;
	private String description;
	private String url_img;
	private String url;
	private String date;

}
