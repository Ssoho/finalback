package com.watermelon.demo.search;

import org.springframework.stereotype.Indexed;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "new_song2")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="new_song2", schema = "music")
@Indexed
public class Search {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="key", updatable=false)
	private Long id;
	
	@Column(name = "song_name")
	private String song_name;
	
	@Column(name = "artist_name")
	private String artist_name;
	
	@Column(name="lyric")
	private String lyric;
	
	@Column(name = "lyric_str")
	private String lyric_str;
	
	@Column(name = "url_new_song")
	private String url_new_song;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSong_name() {
		return song_name;
	}

	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}

	public String getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public String getLyric_str() {
		return lyric_str;
	}

	public void setLyric_str(String lyric_str) {
		this.lyric_str = lyric_str;
	}

	public String getUrl_new_song() {
		return url_new_song;
	}

	public void setUrl_new_song(String url_new_song) {
		this.url_new_song = url_new_song;
	}

	
}
