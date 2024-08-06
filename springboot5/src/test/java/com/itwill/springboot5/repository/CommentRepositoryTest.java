package com.itwill.springboot5.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot5.domain.Comment;
import com.itwill.springboot5.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CommentRepositoryTest {
	// CommentRepository의 CRUD 기능을 테스트
	@Autowired private CommentRepository commentRepo;
	@Autowired private PostRepository postRepo;
	
	// @Test
	public void test() {
		Comment comment = commentRepo.findById((long) 1).orElseThrow();
		log.info("comment={}", comment);
	}
	
	// @Test
	public void testSave() {
		Post post = postRepo.findById((long) 1).orElseThrow();
		Comment entity = Comment.builder()
				.post(post).ctext("댓글 테스트").writer("admin").build();
		log.info("save 전: {}", entity);
		
		commentRepo.save(entity);
		log.info("save 후: {}", entity);
	}
	
	// @Test
	public void testUpdate() {
		Comment entity = commentRepo.findById(1L).orElseThrow();
		entity.update("댓글 업데이트");
		
		entity = commentRepo.save(entity);
		log.info("entity={}", entity);
	}
	
	@Test
	public void testDelete() {
		commentRepo.deleteById(1L);
	}
	
}
