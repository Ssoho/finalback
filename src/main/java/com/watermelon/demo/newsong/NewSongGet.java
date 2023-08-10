package com.watermelon.demo.newsong;

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

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "new_song2", schema = "music") // 홈페이지에서 인풋을 new_song2에서만 가져오게함.
public class NewSongGet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="key", updatable=false)
	private Long id;
	
	@Column(name="song_name", nullable = false)
	private String songName;
	
	@Column(name="artist_name", nullable = false)
	private String artistName;
	
	@Column(name="url_new_song", nullable = false)
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
		
}