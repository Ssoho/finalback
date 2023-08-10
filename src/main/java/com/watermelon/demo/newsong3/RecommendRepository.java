package com.watermelon.demo.newsong3;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("recommendRepository3")
public interface RecommendRepository extends JpaRepository<Recommend, Long>{
	
	Optional<Recommend> findByUser_id(Long user_id);
	// 1
    @Query(nativeQuery = true, value = "SELECT ns.key, ns.song_name, ns.artist_name, ns.lyric, ns.url_new_song " +
            "FROM new_song2 ns " +
            "WHERE ns.key IN (:recommended_songs3)")
    List<Object[]> findSongInfoByRecommendedSongs(@Param("recommended_songs3") List<Long> recommendedSongs);
	
    void deleteByUserId(Long user_id);
    
//  // 수정된 쿼리
//  @Query("SELECT r.song_ids FROM Recommend r WHERE r.user.id = ?1")
//  String getSong_ids(Long user_id);
  
    
}