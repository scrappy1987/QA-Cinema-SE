package com.qa.cinema.model;

public class Movie {
	
	private long id;
	private String title;
	private String genre;
	private String rating;
	
	public Movie(long id, String title, String genre, String rating){
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.rating = rating;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
}
