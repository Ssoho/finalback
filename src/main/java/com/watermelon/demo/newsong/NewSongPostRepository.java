package com.watermelon.demo.newsong;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watermelon.demo.models.User;

public interface NewSongPostRepository extends JpaRepository<NewSongPost, Long>{
	
	boolean existsByUser(User user);
	NewSongPost findByUser(User user);

}