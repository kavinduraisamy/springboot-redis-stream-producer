package com.kavinduraisamy91.demo.redisstreamproducer.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kavinduraisamy91.demo.model.Post;
import com.kavinduraisamy91.demo.redisstreamproducer.repo.PostRepo;

@Service
public class PostReactionService {
	
	private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Value("${stream.key}")
    private String streamKey;

    @Autowired
    private PostRepo repository;

    @Autowired
    private ReactiveRedisTemplate<String, String> redisTemplate;
	
	@Scheduled(fixedRateString= "${publish.rate}")
    public void publishEvent(){
        Post postReaction = this.repository.getRandomPost();
        ObjectRecord<String, Post> record = StreamRecords.newRecord()
                .ofObject(postReaction)
                .withStreamKey(streamKey);
        this.redisTemplate
                .opsForStream()
                .add(record)
                .subscribe();
        atomicInteger.incrementAndGet();
    }
	
	@Scheduled(fixedRate = 10000)
    public void showPublishedEventsSoFar(){
        System.out.println(
                "Total Events :: " + atomicInteger.get()
        );
    }

}
