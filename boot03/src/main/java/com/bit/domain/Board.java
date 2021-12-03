package com.bit.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//엔티티 클래스 설계
@Getter @ Setter @ToString
@Entity
@Table (name = "tbl_boards") //테이블명
public class Board {
	
	@Id //pk지정
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;//게시물번호
	private String title;//제목
	private String writer;//글쓴이
	private String content;//내용
	
	@CreationTimestamp //처음입력
	private Timestamp regdate;
	@UpdateTimestamp //수정입력
	private Timestamp updatedate;
	
}




