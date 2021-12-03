package com.bit;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.Board;
import com.bit.persistence.BoardRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardRepositoryTests2 {

	@Autowired
	private BoardRepository repo;
	
	//제목에 '5'가 포함, bno가 90보다 큰 게시물
	@Test
	public void testByTitleAndBno() {
		List<Board> results = 
				repo.findByTitleContainingAndBnoGreaterThan("5", 90L);
		results.forEach(board->System.out.println(board));
	}
	
	//bno가 90보다 큰 게시물, 정렬은 bno 내림차순
	@Test
	public void testByBnoOrderByBnoDesc() {
		List<Board> results = 
				repo.findByBnoGreaterThanOrderByBnoDesc(90L);
		results.forEach(board->System.out.println(board));   
	}
	
	//bno가 90보다 큰 게시물, 정렬은 bno 내림차순, 시작번호 0에서 10개 가져오기
	@Test
	public void testBnoOrderByPaging() {
		Pageable paging = PageRequest.of(0, 10);
		List<Board> results = 
				repo.findByBnoGreaterThanOrderByBnoDesc(90L, paging);
		results.forEach(board->System.out.println(board));  	
	}
	
	@Test
	public void testByTitle2() {
		List<Board> results = repo.findByTitle2("15");
		results.forEach(board->System.out.println(board));  
	}
	
	@Test
	public void testByTitle3() {
		List <Object[]> results = repo.findByTitle3("15");
		results.forEach(arr->System.out.println(Arrays.toString(arr)));
	}
	
	@Test //limit ?,?
	//int page, int size
	public void testByPaging() {
		Pageable paging = PageRequest.of(5, 10);//limit 50, 10 
		repo.findByPage(paging).
			forEach(board->System.out.println(board));
	}
	
	@Test
	public void testByContent2() {
		repo.findByContent2("77").
			forEach(board->System.out.println(board));
	}
}























