package com.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.PDSBoard;
import com.bit.domain.PDSFile;
import com.bit.persistence.PDSBoardRepository;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log
@Commit
public class PDSBoardTests {

	@Autowired
	PDSBoardRepository repo;
	
	@Test
	public void testCreate() {
		log.info("*********create table...");
	}
	
	@Test
	public void testInsertPDS() {
		PDSBoard pds = new PDSBoard();
		pds.setPname("DOCUMENT 1-2");
		pds.setPwriter("user1");
		PDSFile file1 = new PDSFile();
		file1.setPdsfile("file1.doc");
		PDSFile file2 = new PDSFile();
		file2.setPdsfile("file2.doc");
		pds.setFiles(Arrays.asList(file1, file2));//java.util 
		log.info("try to saved pds");
		repo.save(pds);
	}
	
	@Transactional
	@Test
	public void testUpdateFileName() {
		Long fno = 1L;
		String newName = "updateFile2.doc";
		int count = repo.updatePDSFile(fno, newName);
		log.info("************update count : " + count);
	}
	
	@Transactional
	@Test
	public void testDeletePDSFile() {
		Long fno = 2L;
		int count = repo.deletePDSFile(fno);
		log.info("**********delete count : " + count);
	}
	
	@Test
	public void insertDummies() {
		List<PDSBoard> list = new ArrayList<PDSBoard>();
		IntStream.range(1, 100).forEach(i->{
			PDSBoard pds = new PDSBoard();
			pds.setPname("자료 " + i);
			PDSFile file1 = new PDSFile();
			file1.setPdsfile("file1.doc");
			PDSFile file2 = new PDSFile();
			file2.setPdsfile("file2.doc");
			//asList : 가변인수 메소드
			pds.setFiles(Arrays.asList(file1, file2));
			log.info("try to save pds");
			list.add(pds);
		});
		repo.saveAll(list);
	}
	
	
	
	@Test
	public void testViewSummary() {
		repo.getSummary().forEach(arr->
			log.info(Arrays.toString(arr)));
	}
}



















