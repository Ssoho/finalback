package com.watermelon.demo.newsong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/api/newsong/post")
public class NewSongPostController {
	
	@Autowired
	@Nonnull
    private final NewSongPostRepository newSongPostRepository;
    private final UserRepository userRepository;

    public NewSongPostController(NewSongPostRepository newSongPostRepository, UserRepository userRepository) {
        this.newSongPostRepository = newSongPostRepository;
        this.userRepository = userRepository;
    }
    
    @PostMapping("/{userId}")
    public ResponseEntity<NewSongPost> createNewSongPost(@PathVariable Long userId, @RequestBody NewSongPost newSongPost) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        newSongPost.setUser(user);
        newSongPost = newSongPostRepository.save(newSongPost);
        return ResponseEntity.ok(newSongPost);
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<NewSongPost> putTag(@PathVariable Long userId,
                                          @RequestBody NewSongPost newSongPost) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean userExists = newSongPostRepository.existsByUser(user);
        if (userExists) {
            // 이미 해당 사용자의 곡 정보가 존재하는 경우
            NewSongPost existingNewSongPost = newSongPostRepository.findByUser(user);
            existingNewSongPost.setSong_ids(newSongPost.getSong_ids());
            return new ResponseEntity<>(newSongPostRepository.save(existingNewSongPost), HttpStatus.OK);
        } else {
            // 해당 사용자의 곡이 아예없는 존재하지 않는 경우
            // 새로운 태그를 생성하고 CREATED 상태로 응답
        	newSongPost.setUser(user);
            return new ResponseEntity<>(newSongPostRepository.save(newSongPost), HttpStatus.CREATED);
        }
    }


}

