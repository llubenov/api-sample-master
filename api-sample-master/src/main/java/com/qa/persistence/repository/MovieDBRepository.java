package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.business.service.MovieService;
import com.qa.persistence.domain.Movies;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class MovieDBRepository implements MovieRepository {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	public String getAllMovies() {
		Query query = manager.createQuery("Select a FROM Movies a");
		Collection<Movies> movies = (Collection<Movies>) query.getResultList();
		return util.getJSONForObject(movies);
	}

	@Transactional(REQUIRED)
	public String createMovie(String movie) {
		Movies aMovie = util.getObjectForJSON(movie, Movies.class);
		if(aMovie.getGenre() == "romantic comedy") {
			aMovie.setGenre("NOT ROMANTIC");
		}
		manager.persist(aMovie);
		return "{\"message\": \"movie has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String deleteMovie(Long id) {
		Movies moveInDB = findMovie(id);
		if (moveInDB != null) {
			manager.remove(moveInDB);
		}
		return "{\"message\": \"movie sucessfully deleted\"}";
	}
	
	@Transactional(REQUIRED)
	public String updateMovie(String movie) {
		Movies aMovie = util.getObjectForJSON(movie, Movies.class);

		manager.persist(aMovie);
		return "{\"message\": \"movie has been sucessfully updated\"}";
	}
	
	

	private Movies findMovie(Long id) {
		return manager.find(Movies.class, id);
	}
	

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	public String getMovie(Long id) {
		Movies moveInDB = findMovie(id);
		
		return util.getJSONForObject(moveInDB);
	}




}
