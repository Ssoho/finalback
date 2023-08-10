package com.watermelon.demo.tag;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tag")
//웹 요청을 처리하여 비즈니스 로직을 호출하고 응답을 반환
public class TagGetController {
	
	@Nonnull
	@Autowired
	private TagGetRepository tagGetRepository;
	
	@GetMapping
	Iterable<TagGet> getTag(){
		return tagGetRepository.findAll();
	}
	
	@GetMapping("/{id}")
	Optional<TagGet> getTagById(@PathVariable Long id){
		return tagGetRepository.findById(id);
	}			
	
	@PostMapping
	public TagGet postTag(@RequestBody TagGet tag) {
		return tagGetRepository.save(tag);
	}
//	
//	@DeleteMapping("/{id}")
//	public void deleteTag(@PathVariable Long id) {
//		tagRepository.deleteById(id);
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<Tag> putTag(@PathVariable Long id,
//			@RequestBody Tag tag) {
//		return (tagRepository.existsById(id))
//				? new ResponseEntity<>(tagRepository.save(tag),
//						HttpStatus.OK)
//				: new ResponseEntity<>(tagRepository.save(tag),
//						HttpStatus.CREATED);						
//	}
}
