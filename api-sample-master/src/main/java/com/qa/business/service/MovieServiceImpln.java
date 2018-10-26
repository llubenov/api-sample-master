package com.qa.business.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.persistence.repository.MovieRepository;

public class MovieServiceImpln implements MovieService {
	// business logic
	@Inject
	private MovieRepository repo;

	public String getAllMovies() {
		return repo.getAllMovies();
	}

	public String addMovie(String movie) {
		return repo.createMovie(movie);
	}

	public String deleteMovie(Long id) {
		return repo.deleteMovie(id);
	}

	public String getMovie(Long id) {
		return repo.getMovie(id);
	}

	public String updateMovie(String movie) {
		return repo.updateMovie(movie);
	}


}
