package com.watermelon.demo.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watermelon.demo.models.User;

public interface TagPostRepository extends JpaRepository<TagPost, Long>{
	
	boolean existsByUser(User user);
	TagPost findByUser(User user);

}
