package com.qa.business.service;

public interface MovieService {

	String getAllMovies();

	String addMovie(String movie);

	String deleteMovie(Long id);
	
	String getMovie(Long id);

	String updateMovie(String movie);
}