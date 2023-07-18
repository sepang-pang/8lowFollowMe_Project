package com.sparta.followfollowmeproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FollowFollowMeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FollowFollowMeProjectApplication.class, args);
	}

}
