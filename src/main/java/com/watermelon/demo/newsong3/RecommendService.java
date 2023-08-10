package com.watermelon.demo.newsong3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service("recommendService3")
public class RecommendService {

    private final RecommendRepository recommendRepository;

//    @Autowired
    public RecommendService(RecommendRepository recommendRepository) {
        this.recommendRepository = recommendRepository;
    }
    
    
    public Optional<Recommend> getAllById(Long userId) {
        return recommendRepository.findByUser_id(userId);
    }
    
    @Transactional
    public ResponseEntity<String> updateSong(@PathVariable Long userId, @RequestBody Optional<Recommend> recommend) throws JsonMappingException, JsonProcessingException {
        Optional<Recommend> data = recommendRepository.findByUser_id(userId);
        if (data.isPresent()) {
            Recommend objectDB = data.get(); // DB에서 가져옴
            Recommend objectReact = recommend.get(); // react에서 가져옴
            objectDB.setSong_ids(objectReact.getSong_ids()); // react에서 가져온 곡들의 DB에서 가져온 객체에 업데이트
            Recommend updateSong_ids = recommendRepository.save(objectDB); // DB에 저장
            // 플라스크와 소통하여 추가 작업
            return fetchRecommendationsFromFlask(userId, Optional.ofNullable(updateSong_ids)); // 저장 대상을 바로 모델에 전달
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }    

    
    @Transactional
    public ResponseEntity<String> getRecommendationsForUser(Long userId) throws JsonMappingException, JsonProcessingException {
    	// 유저에 대한 데이터가 있는지 확인
//        boolean userExists = recommendRepository.existsByUser_id(userId);
    	Optional<Recommend> data = recommendRepository.findByUser_id(userId); // 유저아이디에 맵핑된 데이터를 가져오기.
        if (data.isPresent()) { // 매칭되는 데이터가 있다면, 
            Recommend existingRecommendation = data.get();
            String recommended_songs = existingRecommendation.getRecommended_songs3();

            // 데이터베이스에 추천곡이 있으면, 그냥 전달.
            if (recommended_songs != null && !recommended_songs.isEmpty()) {
            	// 가져온 데이터를 리스트로 변환
            	// DB에서 가져온 것을 변환하는 방식 1) return값이 String list -> list<Long>으로 변환
                ObjectMapper objectMapper = new ObjectMapper();
                List<Long> recommendedSongsList = objectMapper.readValue(recommended_songs, new TypeReference<List<Long>>() {}); // song_ids가 담긴 리스트
                List<Object[]> songInfoList = recommendRepository.findSongInfoByRecommendedSongs(recommendedSongsList); // 곡이 담긴 리스트를 가지고 DB에서 부가정보를 가져옴
                
                // DB에서 가져온 부가정보들을 json 전달 방식에 맞게 변환
                List<String> songInfoResult = new ArrayList<>();
                for (Object[] songInfo : songInfoList) {
                    Long song_id = (Long) songInfo[0];
                    String song_Name = (String) songInfo[1];
                    String artist_Name = (String) songInfo[2];
                    String lyric_Str = (String) songInfo[3];
                    String url = (String) songInfo[4];
                    // json
                    String songJson = "{\"songId\": \"" + song_id + "\", \"songName\":\"" + song_Name + "\", \"artistName\":\"" + artist_Name + "\", \"lyric_Str\":\"" + lyric_Str + "\", \"url\":\"" + url + "\"} ";
                    songInfoResult.add(songJson);
                }
                // 마지막 포장 후 리액트로 전달
                String responseJson = "[" + String.join(",", songInfoResult) + "]";  // 리액트로 전달할때는 song_id도 최종적으로 String json
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseJson);
            } else {
                // 데이터베이스에 추천곡이 없으면, 모델을 돌려서 가져와라.
                return fetchRecommendationsFromFlask(userId, data); // 아래에 모델에서 가져오는 것을 정의함
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //데이터가 없으면 없는거. (404)
    }

    
    @Transactional
    ResponseEntity<String> fetchRecommendationsFromFlask(Long userId, Optional<Recommend> data) throws JsonMappingException, JsonProcessingException {
//    	System.out.println("start!");
    	// flask와 소통을 위한 정의
        String flaskAppUrl = "http://localhost:5000/recommend-songs3/" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // DB에서 userID를 통해 가지고 온 데이터를 가져온 것을 할당
        Optional<Recommend> requestData = data;

        //요청엔티티 생성해서 플라스크에 전달
        RequestEntity<Optional<Recommend>> requestEntity = RequestEntity.post(flaskAppUrl).headers(headers).body(requestData);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) { 
        	// 200으로 성공하면, 플라스크에서 온 응답을 사용하기 위해 정의
            String responseBody = responseEntity.getBody();
            // 가져온 데이터를 리스트로 변환
            // flask에서 가져온 것을 변환하는 방식 2) return값이 String으로 정보들이 담긴 json
            ObjectMapper objectMapper = new ObjectMapper(); // String json에서 song_ids만 Long으로 변환
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {}); // 플라스크에서 가져온 객체를 맵핑
            // flask에서 json.dump로 던져주므로, String형태의 데이터를 song_ids가 담긴 인트형 리스트로 변환
            List<Long> recommendedSongs = objectMapper.readValue((String) responseMap.get("recommended_songs3"), new TypeReference<List<Long>>() {}); // String list -> list<Long>으로 변환
            List<Object[]> songInfoList = recommendRepository.findSongInfoByRecommendedSongs(recommendedSongs);  // 곡이 담긴 리스트를 가지고 DB에서 부가정보를 가져옴

            // DB에서 가져온 부가정보들을 json 전달방식에 맞게 변환
            List<String> songInfoResult = new ArrayList<>();
            for (Object[] songInfo : songInfoList) {
                Long song_id = (Long) songInfo[0];
                String song_Name = (String) songInfo[1];
                String artist_Name = (String) songInfo[2];
                String lyric_Str = (String) songInfo[3];
                String url = (String) songInfo[4];
                // json 
                String songJson = "{\"songId\": \"" + song_id + "\", \"songName\":\"" + song_Name + "\", \"artistName\":\"" + artist_Name + "\", \"lyric_Str\":\"" + lyric_Str + "\", \"url\":\"" + url + "\"} ";
                songInfoResult.add(songJson);
            }
            // 마지막 포장 후 리액트에 전달
            String responseJson = "[" + String.join(",", songInfoResult) + "]"; // 리액트로 전달할때는 song_id도 최종적으로 String json
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseJson);
            
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode()).body("Error");
        }
    }
    
    
}
