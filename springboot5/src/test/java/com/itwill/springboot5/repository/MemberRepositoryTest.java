package com.itwill.springboot5.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.itwill.springboot5.domain.Member;
import com.itwill.springboot5.domain.MemberRole;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// @Test
	public void testDependencyInjection() {
		assertThat(memberRepo).isNotNull();
		log.info(memberRepo.toString());
		
		assertThat(passwordEncoder).isNotNull();
		log.info(passwordEncoder.toString());
	}
	
	@Test
	public void testSave() {
		// 엔터티 객체를 DB members 테이블에 저장.
		
		Member m = Member.builder()
				.username("test1")
				.password(passwordEncoder.encode("1111"))
				.email("test1@itwill.com")
				.build();
		m.addRole(MemberRole.USER);
		log.info("save 호출 전 = {}", m);
		
		m = memberRepo.save(m);
		log.info("save 호출 후 = {}", m);
	}
}
