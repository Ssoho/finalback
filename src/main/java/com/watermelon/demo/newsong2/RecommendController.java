package com.watermelon.demo.newsong2;


import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin(origins = "*")
@RestController
@Component("recommendController2")
@RequestMapping("/api/dcn")
public class RecommendController {

    private final RecommendService recommendService;

    @Autowired
    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @GetMapping("/{userId}") // userId에 매칭되는 모든 정보를 가져옴.
    public Optional<Recommend> getAllById(@PathVariable Long userId) {
        return recommendService.getAllById(userId); 
    }

    // userId에 매칭되는 추천곡의 정보를 가져옴.
    @GetMapping("/recommend-songs2/{userId}")
    public ResponseEntity<String> putRecommend(@PathVariable Long userId) {
        try {
            return recommendService.getRecommendationsForUser(userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing recommendations.");
        }
    }
    
    
    @PutMapping("/renewal-songs/{userId}")
    public ResponseEntity<String> updateSong(@PathVariable Long userId, @RequestBody Optional<Recommend> recommend) throws JsonMappingException, JsonProcessingException{
    	return recommendService.updateSong(userId, recommend);
    }

    // This method is only for testing the Flask API with Postman.
//    @PostMapping("/recommend-songs")
//    public ResponseEntity<String> getRecommendations(Model model, @RequestBody Map<String, Object> inputData) {
//        try {
//            return recommendService.fetchRecommendationsFromFlask(0L); // Replace 0L with a valid user ID
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing recommendations.");
//        }
//    }
}