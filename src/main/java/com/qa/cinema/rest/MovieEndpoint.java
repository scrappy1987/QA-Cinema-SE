package com.qa.cinema.rest;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.qa.cinema.exception.JsonError;
import com.qa.cinema.exception.NotFoundException;
import com.qa.cinema.model.Movie;
import com.qa.cinema.service.MockMovieList;
import com.qa.cinema.util.JSONUtil;


@Path("cinema")
public class MovieEndpoint {

	private final CopyOnWriteArrayList<Movie> movieList = MockMovieList.getInstance();

	private JSONUtil util = new JSONUtil();

	@GET
	@Path("/getmovies")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMovies() {
		return util.getJSONForObject(movieList);
	}

	@GET
	@Path("/getmovie/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMovie(@PathParam("id") long id) {
		Optional<Movie> match = movieList.stream().filter(movie -> movie.getId() == id).findFirst();
		if (match.isPresent()) {
			return util.getJSONForObject(match.get());
		} else {
			return "Movie not found";
		}
	}

	@POST
	@Path("/addMovie")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMovie(String movie) {
		Movie newMovie = util.getObjectForJSON(movie, Movie.class);
		movieList.add(newMovie);
		return Response.status(201).build();
	}

	@PUT
	@Path("/updateMovie/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMovie(String movieString) {
		int matchIdx = 0;
		Movie updateMovie = util.getObjectForJSON(movieString, Movie.class);
		Optional<Movie> match = movieList.stream().filter(movie -> movie.getId() == updateMovie.getId()).findFirst();
		if (match.isPresent()) {
			matchIdx = movieList.indexOf(match.get());
			movieList.set(matchIdx, updateMovie);
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/removeMovie/{id}")
	public void deleteMovie(@PathParam("id") long id) {
		Predicate<Movie> movie = m -> m.getId() == id;
		if (!movieList.removeIf(movie)) {
			  throw new NotFoundException(new JsonError("Error", "Movie " + id + " not found"));
		}
	}
}
