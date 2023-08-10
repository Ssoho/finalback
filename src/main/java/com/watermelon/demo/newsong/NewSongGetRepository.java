package com.watermelon.demo.newsong;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewSongGetRepository extends JpaRepository<NewSongGet, Long>{

	Optional<NewSongGet> findById(Long id);
	
}
