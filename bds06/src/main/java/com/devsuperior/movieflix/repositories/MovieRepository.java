package com.devsuperior.movieflix.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	Optional<Movie> findById(Long id);
	
	@Query("select m FROM Movie m "
			+ "INNER JOIN m.genre genres "
			+ "WHERE :genre IN genres ORDER BY m.title")
	Page<Movie> findByGenre(Genre genre, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM tb_movie m ORDER BY m.title")
	Page<Movie> findAllOrdeByTitle(Pageable pageable);
	
	
}