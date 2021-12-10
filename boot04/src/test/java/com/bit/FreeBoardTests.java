package com.bit;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.FreeBoard;
import com.bit.domain.FreeBoardReply;
import com.bit.persistence.FreeBoardReplyRepository;
import com.bit.persistence.FreeBoardRepository;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log
@Commit
public class FreeBoardTests {
	
	@Autowired
	FreeBoardRepository bRepo;
	
	@Autowired
	FreeBoardReplyRepository rRepo;
	
	@Test
	public void insertDummy() {
		//log.info("********* 테이블 생성");
		IntStream.range(1, 200).forEach(i->{
			FreeBoard board = new FreeBoard();
			board.setTitle("Free Board..."+i);
			board.setContent("Free Content..."+i);
			board.setWriter("user"+i%10);
			bRepo.save(board);
		});
	}

	@Transactional
	@Test 
	//트랜잭션과 cascade 기능이 추가
	public void insertReply2Way() {
		Optional<FreeBoard> result = bRepo.findById(198L);
		result.ifPresent(board->{
			List<FreeBoardReply> replies = board.getReplies();
			FreeBoardReply reply = new FreeBoardReply();
			reply.setReply("REPLY........");
			reply.setReplyer("reply00");
			reply.setBoard(board);
			replies.add(reply);
			board.setReplies(replies);
			bRepo.save(board);
		});
	}
	
	@Test
	public void insertReply1Way() {
		FreeBoard board = new FreeBoard();
		board.setBno(199L);
		FreeBoardReply reply = new FreeBoardReply();
		reply.setReply("REPLY........");
		reply.setReplyer("reply00");
		reply.setBoard(board);
		rRepo.save(reply);
	}
	
	@Test
	public void testList1() {
		Pageable pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "bno");
		bRepo.findByBnoGreaterThan(0L, pageable).forEach(board->{
			System.out.println("***"+board.getBno() + " : " + board.getTitle());
		});
	}
	
	@Transactional
	@Test
	public void testList2() {
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		bRepo.findByBnoGreaterThan(0L, pageable).forEach(board->{
			System.out.println("***"+board.getBno() + " : " 
					+ board.getTitle() + " : " + board.getReplies().size());
		});
	}
	
	@Test
	public void testList3() {
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		bRepo.getPage(pageable).forEach(arr->{
			System.out.println("***"+Arrays.toString(arr));
		});
	}
	
}












