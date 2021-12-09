package com.bit;

import java.util.Arrays;

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
}

















