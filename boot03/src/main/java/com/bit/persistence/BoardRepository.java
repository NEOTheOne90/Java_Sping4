package com.bit.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bit.domain.Board;

//JPA를 담당하는 Repository 인터페이스 설계
public interface BoardRepository 
extends CrudRepository<Board, Long>{

	//findBy~
	//where title = ?
	public List<Board> findByTitle(String title);
	
	//writer 검색 : where writer = ?
	public List<Board> findByWriter(String writer);
	
	//content 검색 : where content = ?
	public Collection<Board> findByContent(String content);
	
	//writer의 like 구문처리
	public List<Board> findByWriterContaining(String writer);
	
	//title의 like 구문처리
	public List<Board> findByTitleContaining(String title);
	
	//content에 '9'로 끝나는 레코드 검색
	public List<Board> findByContentEndingWith(String content);
	
	//or조건의 처리 : title or content
	public List<Board> findByTitleOrContent(String title, String content);
	
	//and 조건의 처리  : title and content
	public List<Board> findByTitleAndContent(String title, String content);
	
	//or 조건의 처리 like 연산 :  title like '%title%' or content like '%content%'
	public List<Board> findByTitleContainingOrContentContaining
	(String title, String content);

	//title like '%title%' and bno > ?
	public List<Board> findByTitleContainingAndBnoGreaterThan
	(String title, Long bno); 
	
	//bno > ? order by bno desc
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	
	//bno > ? order by bno desc limit ? , ?
	//import org.springframework.data.domain.Pageable;
	public List<Board> findByBnoGreaterThanOrderByBnoDesc
	(Long bno, Pageable paging);
	
	//JPQL(Java Persistence Query Language)
	//Board.java에서 Entity명이 Board이기 때문에 테이블명이 Board 사용
	@Query("SELECT b FROM Board b WHERE b.title like %?1% "
			+ "and b.bno > 100 ORDER BY b.bno desc")
	public List<Board> findByTitle2(String title);
	
	@Query("SELECT b.bno, b.title, b.writer, b.regdate "
			+ " FROM Board b WHERE b.title like %?1% and "
			+ "b.bno > 0 order by b.bno desc")
	public List<Object[]> findByTitle3(String title);
	
	@Query("SELECT b FROM Board b WHERE b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByPage(Pageable paging);
	
	@Query("SELECT b FROM Board b WHERE b.bno > 0 and "
			+ "b.content LIKE %:content% ORDER BY b.bno DESC")
	public List<Board> findByContent2(@Param("content")String content); 
	
}



















