package com.watermelon.demo.newsong;

import com.watermelon.demo.models.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Recommend1")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recommend", schema = "music")
public class Recommend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="num")
	private Long num;
	
	// Users table에 있는 id랑 recommend의 user_id랑 매칭 시켜줌.
	@ManyToOne // PK
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;
	
	@Column(name = "user_id", insertable = false, updatable = false)
	private Long user_id;
	
	@Column(name="song_ids")
	private String song_ids;
	
	@Column(name="recommended_songs", columnDefinition = "LONGTEXT")
	private String recommended_songs;
	

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getSong_ids() {
		return song_ids;
	}

	public void setSong_ids(String song_ids) {
		this.song_ids = song_ids;
	}

	public String getRecommended_songs() {
		return recommended_songs;
	}

	public void setRecommended_songs(String recommended_songs) {
		this.recommended_songs = recommended_songs;
	}
	
	
}
