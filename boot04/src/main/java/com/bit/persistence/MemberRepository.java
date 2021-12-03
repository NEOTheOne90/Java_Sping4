package com.bit.persistence;

import org.springframework.data.repository.CrudRepository;

import com.bit.domain.Member;

//CrudRepository : 연관관계 테이블 설계 사용
public interface MemberRepository 
extends CrudRepository<Member, String> {

}
