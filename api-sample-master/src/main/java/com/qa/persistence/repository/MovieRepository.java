package com.qa.persistence.repository;

public interface MovieRepository {
	
	String getAllMovies();
	String deleteMovie(Long id);
	String createMovie(String movie);
	String getMovie(Long id);
	String updateMovie(String movie);
	


}
