package com.watermelon.demo.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/searchtest")
public class SearchController {
	private final SearchRepository searchRepository;
	
	@Autowired
	public SearchController(SearchRepository searchRepository) {
		this.searchRepository = searchRepository;
	}
	
    @GetMapping
    public ResponseEntity<String> searchSongs(@RequestParam(value="keyword") String keyword) {
//        List<String> songs = searchRepository.searchSongs(keyword); 	// 문자열 리스트로 반환됨..
    	List<Object[]> songs = searchRepository.searchSongs(keyword);
        List<String> songResult = new ArrayList<>();
        for (Object[] result : songs) {
        	String song_name = (String) result[0];
        	String artist_name = (String) result[1];
        	String lyric_str = (String) result[2];
            String songJson = "{ \"songName\":\"" + song_name + "\", \"artistName\":\"" + artist_name + "\", \"lyric\":\"" + lyric_str + "\"} ";
            songResult.add(songJson);
        }
    
        String responseJson = "[" + String.join(",", songResult) + "]";
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseJson);

//        return new ResponseEntity<>(songs, HttpStatus.OK); // 문자열 리스트로 반환됨..
    }
	

	
}
