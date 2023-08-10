package com.watermelon.demo.search;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SearchRepository extends JpaRepository<Search, Long>{
	
	@Query("SELECT ns.song_name, ns.artist_name, ns.lyric_str FROM new_song2 ns "
	        + "WHERE ns.song_name LIKE CONCAT('%', :keyword, '%') "
	        + "OR ns.artist_name LIKE CONCAT('%', :keyword, '%') "
	        + "OR ns.lyric_str LIKE CONCAT('%', :keyword, '%') ")
	List<Object[]> searchSongs(@Param("keyword") String keyword);
//	List<String> searchSongs(@Param("keyword") String keyword);
	
//	List<Search> findBySong_nameContainingOrArtist_nameContainingOrLyric_strContaining(String Song_name, String Artist_name, String Lyric_str);

	
}
