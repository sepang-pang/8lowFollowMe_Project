package com.sparta.followfollowmeproject.postMedia.entity;

import com.sparta.followfollowmeproject.common.entity.Timestamped;
import com.sparta.followfollowmeproject.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "postMedia")
@NoArgsConstructor
public class PostMedia extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mdeiaUrl", nullable = false)
	String mdeiaUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postId", nullable = false)
	private Post post;

	public PostMedia(Post post, String mdeiaUrl) {
		this.post = post;
		this.mdeiaUrl = mdeiaUrl;
	}
}
