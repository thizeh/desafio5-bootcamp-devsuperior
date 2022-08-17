package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.MovieReviewsDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired 
	private GenreRepository genreRepository;
	
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDTO(entity);
	}

	
	@Transactional(readOnly = true)
	public Page<MovieGenreDTO> findMovieGenre(Long genreId, Pageable pageable){
		Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
		Page<Movie> page = (genre == null) ? repository.findAllOrdeByTitle(pageable) : repository.findByGenre(genre, pageable);
		return page.map(x -> new MovieGenreDTO(x));

	}
	
	@Transactional(readOnly = true)
	public List<MovieReviewsDTO> findReviews(Long movieId){
		Movie movie = repository.getOne(movieId);
		List<Review> list = reviewRepository.findReviews(movie);
		return list.stream().map(x -> new MovieReviewsDTO(x)).collect(Collectors.toList());

	}
	
	
}