package com.watermelon.demo.tag;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.watermelon.demo.models.User;
import com.watermelon.demo.repository.UserRepository;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tag/post")
public class TagPostController {
	
	@Autowired
	@Nonnull
    private final TagPostRepository tagPostRepository;
    private final UserRepository userRepository;

    public TagPostController(TagPostRepository tagPostRepository, UserRepository userRepository) {
        this.tagPostRepository = tagPostRepository;
        this.userRepository = userRepository;
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<TagPost> createTagPost(@PathVariable Long userId, @RequestBody TagPost tagPost) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        tagPost.setUser(user);
        tagPost = tagPostRepository.save(tagPost);
        return ResponseEntity.ok(tagPost);
    }
    
    // flask @app_route
    
    // post -> react
    
    @PutMapping("/{userId}")
    public ResponseEntity<TagPost> putTag(@PathVariable Long userId,
                                          @RequestBody TagPost tagPost) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean userExists = tagPostRepository.existsByUser(user);
        if (userExists) {
            // 이미 해당 사용자의 태그가 존재하는 경우
            TagPost existingTagPost = tagPostRepository.findByUser(user);
            existingTagPost.setTag(tagPost.getTag());
            return new ResponseEntity<>(tagPostRepository.save(existingTagPost), HttpStatus.OK);
        } else {
            // 해당 사용자의 태그가 존재하지 않는 경우
            // 새로운 태그를 생성하고 CREATED 상태로 응답합니다.
            tagPost.setUser(user);
            return new ResponseEntity<>(tagPostRepository.save(tagPost), HttpStatus.CREATED);
        }
    }

}

