package com.watermelon.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.watermelon.demo.newsong.RecommendRepository;
import com.watermelon.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
    @Autowired
    private RecommendRepository recommendRepository;
	
	@Transactional
	public void deleteUser(Long id) {
		
		// recommendRepository.deleteById(id);		
		recommendRepository.deleteByUserId(id);		
		userRepository.deleteById(id);
	}

}
