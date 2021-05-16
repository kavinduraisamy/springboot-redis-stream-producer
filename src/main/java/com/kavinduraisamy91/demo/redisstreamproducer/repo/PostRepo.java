package com.kavinduraisamy91.demo.redisstreamproducer.repo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Repository;

import com.kavinduraisamy91.demo.model.Post;

@Repository
public class PostRepo {
	private static final List<Post> posts=Arrays.asList(
			
			new Post("post1",false,true),
			new Post("post2",true,false),
			new Post("post1",true,false),
			new Post("post2",false,true),
			new Post("post3",true,false),
			new Post("post3",false,true)
			
			
			);
	
	 public Post getRandomPost(){
	        int random = ThreadLocalRandom.current().nextInt(0, 5);
	        return posts.get(random);
	    }

}
