package com.watermelon.demo.tag;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "tag100", schema = "music")
public class TagGet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="key", updatable=false)
	private Long id;
	
	@Column(name="tag", nullable = false)
	private String songName;

	// get-set
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
		
}