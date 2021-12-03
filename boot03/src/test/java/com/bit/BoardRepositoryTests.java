package com.bit;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.Board;
import com.bit.persistence.BoardRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardRepositoryTests {

	@Autowired
	private BoardRepository repo;
	
	@Test
	public void testCreate() {
		System.out.println("테이블 생성");
	}
	
	@Test
	public void testInsert200() {
		for (int i = 0; i < 200; i++) {
			Board board = new Board();
			board.setTitle("제목.."+i);
			board.setContent("내용..."+i);
			board.setWriter("user0"+(i%10));
			repo.save(board);
		}
	}
	
	//번호로 검색
	@Test
	public void testById() {
		 Optional<Board> board = repo.findById(1L);
		 System.out.println("board : " + board);
	}
	
	//제목으로 검색
	@Test
	public void testByTitle() {
		List<Board> results = repo.findByTitle("제목..177");
		results.forEach(board->System.out.println(board));
		//repo.findByTitle("제목..177").
			//forEach(board->System.out.println(board));
	}
	
	//글쓴이(user00) 검색
	@Test
	public void testByWriter() {
		List<Board> results = repo.findByWriter("user00");
		results.forEach(board->System.out.println(board));
	}
	
	//제목으로 검색
	@Test
	public void testByContent() {
		Collection<Board> results = repo.findByContent("내용...11");
		results.forEach(board->System.out.println(board));
	}
	
	//writer의 like 구문처리
	@Test
	public void testByWriterContaining() {
		List<Board> results = repo.findByWriterContaining("05");
		results.forEach(board->System.out.println(board));
	}
	
	//title에 '7'이 들어가 있는 레코드 검색
	@Test
	public void testByTitleContaining() {
		List<Board> results = repo.findByTitleContaining("7");
		results.forEach(board->System.out.println(board));
	}
	
	//content에 '9'로 끝나는 레코드 검색
	@Test
	public void testByContentEndingWith() {
		List<Board> results = repo.findByContentEndingWith("9");
		results.forEach(board->System.out.println(board));
	}
	//제목..100 or 내용...10 
	@Test
	public void testByTitleOrContent() {
		List<Board> results = repo.findByTitleOrContent("제목..100","내용...10");
		results.forEach(board->System.out.println(board));
	}
	
	//제목..10 and 내용...10 
		@Test
		public void testByTitleAndContent() {
			List<Board> results = repo.findByTitleAndContent("제목..10","내용...10");
			results.forEach(board->System.out.println(board));
		}
	
	//제목 '9' 들어감 or 내용 '7' 들어감
	@Test
	public void testByTitleContainingOrContentContaining() {
		List<Board> results = 
				repo.findByTitleContainingOrContentContaining("9", "7");
		results.forEach(board->System.out.println(board));
	}	
}

















