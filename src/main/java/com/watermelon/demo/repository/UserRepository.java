package com.watermelon.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.watermelon.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  Optional<User> findById(User id); // 수정
  
  // 쿼리가 맞는지 확인 요망!
	@Query("SELECT t.id FROM TagPost t WHERE t.user.id = :userId")
	List<Long> findTagPostIdsByUserId(@Param("userId") Long userId);

//	void deleteById(User id);

}
