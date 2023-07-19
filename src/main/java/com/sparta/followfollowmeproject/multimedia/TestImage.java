package com.sparta.followfollowmeproject.multimedia;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "testImage")
@NoArgsConstructor
public class TestImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "imageUrl", nullable = false)
	String imageUrl;

	public TestImage(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
