package com.bit.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bit.domain.Member;
import com.bit.domain.PDSBoard;
import com.bit.domain.Profile;

public interface PDSBoardRepository 
extends CrudRepository<PDSBoard, Long> {

	@Modifying
	@Query("UPDATE FROM PDSFile f set f.pdsfile = ?2 where f.fno = ?1")
	public int updatePDSFile(Long fno, String newFileName);
	
	
	@Modifying
	@Query("DELETE FROM PDSFile f where f.fno=?1")
	public int deletePDSFile(Long fno);

}












