package com.watermelon.demo.song;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/testsong")
public class SongController {
	
	@Nonnull
	@Autowired
	private SongRepository songRepository;
	
	@GetMapping
	Iterable<Song> getSong(){
		return songRepository.findAll();
	}
	
	@GetMapping("/{id}")
	Optional<Song> getSongById(@PathVariable Long id){
		return songRepository.findById(id);
	}
	
	// 실제 DB랑 붙여서 일단은 가져오는 것빼고 주석처리.
	
//	@PostMapping
//	public Song postSong(@RequestBody Song song) {
//		return songRepository.save(song);
//	}
//	
//	@DeleteMapping("/{id}")
//	public void deleteSong(@PathVariable Long id) {
//		songRepository.deleteById(id);
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<Song> putSong(@PathVariable Long id, 
//			@RequestBody Song song){
//		return (songRepository.existsById(id))
//				? new ResponseEntity<>(songRepository.save(song),
//						HttpStatus.OK)
//				: new ResponseEntity<>(songRepository.save(song),
//						HttpStatus.CREATED);		
//	}
	
}
