package com.qa.cinema.service;

import java.util.concurrent.CopyOnWriteArrayList;

import com.qa.cinema.model.Movie;

public class MockMovieList {
	
	private static final CopyOnWriteArrayList<Movie> movieList = new CopyOnWriteArrayList<>();

	private MockMovieList() {
	}

	static {
		
		movieList.add(new Movie(1L, "IT","Horror","18"));
		movieList.add(new Movie(2L, "Harry Potter","Fantasy","12"));		
	}

	public static CopyOnWriteArrayList<Movie> getInstance() {
		return movieList;
	}
}
