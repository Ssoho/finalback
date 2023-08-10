package com.watermelon.demo.newsong;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/newsong")
//웹 요청을 처리하여 비즈니스 로직을 호출하고 응답을 반환
public class NewSongGetController {
	
	@Nonnull
	@Autowired
	private NewSongGetRepository newSongGetRepository;
	
	@GetMapping
	Iterable<NewSongGet> getNewSong(){
		return newSongGetRepository.findAll();
	}
	
	@GetMapping("/{id}")
	Optional<NewSongGet> getNewSongById(@PathVariable Long id){
		return newSongGetRepository.findById(id);
	}	
	
}