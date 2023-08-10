package com.watermelon.demo.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/genre")
//웹 요청을 처리하여 비즈니스 로직을 호출하고 응답을 반환
public class GenreController {
	
	@Nonnull
	@Autowired
	private GenreRepository genreRepository;
	
	@GetMapping("/all")
	Iterable<Genre> getAll(){
		return genreRepository.findAll();
	}
	
}