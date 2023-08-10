package com.watermelon.demo.SearchSplit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.watermelon.demo.search.Search;

public interface SearchSplitRepository  extends JpaRepository<Search, Long>{
	
	@Query("SELECT ns.song_name, ns.artist_name, ns.lyric, ns.url_new_song FROM new_song2 ns "
	        + "WHERE ns.song_name LIKE CONCAT('%', :keyword, '%') ")
	List<Object[]> searchSongs(@Param("keyword") String keyword);
	
	@Query("SELECT ns.song_name, ns.artist_name, ns.lyric, ns.url_new_song FROM new_song2 ns "
			+ "WHERE ns.artist_name LIKE CONCAT('%', :keyword, '%') ")
	List<Object[]> searchByArtist(@Param("keyword") String keyword);
	
//	@Query("SELECT ns.song_name, ns.artist_name, ns.lyric_str FROM new_song2 ns "
//			+ "WHERE ns.lyric_str LIKE CONCAT('%', :keyword, '%') ")
//	List<Object[]> searchByLyric(@Param("keyword") String keyword);
	
	@Query(value = "SELECT ns.song_name, ns.artist_name, ns.lyric, ns.url_new_song FROM new_song2 ns "
			+ "WHERE MATCH(ns.lyric_str) AGAINST(:keyword IN BOOLEAN MODE)", nativeQuery = true)
	List<Object[]> searchByLyric(@Param("keyword") String keyword);
	
}