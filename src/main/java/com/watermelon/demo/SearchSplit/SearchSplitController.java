package com.watermelon.demo.SearchSplit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/search")
public class SearchSplitController {
   
private final SearchSplitRepository searchSplitRepository;
   
   @Autowired
   public SearchSplitController(SearchSplitRepository searchSplitRepository) {
      this.searchSplitRepository = searchSplitRepository;
   }
    
    @GetMapping("/song")
    public ResponseEntity<String> searchSongs(@RequestParam(value="keyword") String keyword) {
       
       if (StringUtils.isEmpty(keyword)) {
          return ResponseEntity.noContent().build();
       }
       
       List<Object[]> songs = searchSplitRepository.searchSongs(keyword);
        List<String> songResult = new ArrayList<>();
        for (Object[] result : songs) {
           String song_name = (String) result[0];
           String artist_name = (String) result[1];
           String lyric = (String) result[2];
           String url = (String) result[3];
            String songJson = "{ \"songName\":\"" + song_name + "\", \"artistName\":\"" + artist_name + "\", \"lyric\":\"" + lyric + "\", \"url\":\"" + url + "\" }";
            songResult.add(songJson);
        }
    
        String responseJson = "[" + String.join(",", songResult) + "]";
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseJson);

    }
    
    @GetMapping("/artist")
    public ResponseEntity<String> searchByArtist(@RequestParam(value="keyword") String keyword) {
       
       if (StringUtils.isEmpty(keyword)) {
          return ResponseEntity.noContent().build();
       }
       
       List<Object[]> songs = searchSplitRepository.searchByArtist(keyword);
        List<String> songResult = new ArrayList<>();
        for (Object[] result : songs) {
           String song_name = (String) result[0];
           String artist_name = (String) result[1];
           String lyric = (String) result[2];
           String url = (String) result[3];
            String songJson = "{ \"songName\":\"" + song_name + "\", \"artistName\":\"" + artist_name + "\", \"lyric\":\"" + lyric + "\", \"url\":\"" + url + "\" }";
            
            songResult.add(songJson);
        }
    
        String responseJson = "[" + String.join(",", songResult) + "]";
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseJson);

    }
    
    @GetMapping("/lyric")
    public ResponseEntity<String> searchByLyric(@RequestParam(value="keyword") String keyword) {
       
       if (StringUtils.isEmpty(keyword)) {
          return ResponseEntity.noContent().build();
       }
       
       List<Object[]> songs = searchSplitRepository.searchByLyric(keyword);
        List<String> songResult = new ArrayList<>();
        for (Object[] result : songs) {
           String song_name = (String) result[0];
           String artist_name = (String) result[1];
           String lyric = (String) result[2];
           String url = (String) result[3];
            String songJson = "{ \"songName\":\"" + song_name + "\", \"artistName\":\"" + artist_name + "\", \"lyric\":\"" + lyric + "\", \"url\":\"" + url + "\" }";
            songResult.add(songJson);
        }
    
        String responseJson = "[" + String.join(",", songResult) + "]";
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseJson);

    }

   
}