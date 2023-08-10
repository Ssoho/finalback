package com.watermelon.demo.tag;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

public interface TagGetRepository extends JpaRepository<TagGet, Long>{

	Optional<TagGet> findById(Long id);

}