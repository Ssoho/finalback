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

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recommend", schema = "music")
public class NewSongPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="num")
	private Long num;
	
	// Users table에 있는 id랑 recommend의 user_id랑 매칭 시켜줌.
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;
	
	@Column(name="song_ids")
	private String song_ids;

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

	public String getSong_ids() {
		return song_ids;
	}

	public void setSong_ids(String song_ids) {
		this.song_ids = song_ids;
	}
	
}
