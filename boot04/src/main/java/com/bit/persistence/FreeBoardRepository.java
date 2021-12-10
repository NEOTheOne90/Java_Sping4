package com.bit.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bit.domain.FreeBoard;

public interface FreeBoardRepository 
extends CrudRepository<FreeBoard, Long> {
	
	public List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable pageable);
	
	@Query("SELECT b.bno, b.title, count(r) "
			+ "FROM FreeBoard b LEFT OUTER JOIN b.replies r "
			+ "WHERE b.bno > 0 group by b ")
	/*select f.bno, f.title , count(r.rno) AS '댓글 갯수' 
	from tbl_freeboards f left OUTER join tbl_free_replies r on f.bno=r.board_bno 
	where f.bno>0 group by f.bno 
	order by f.bno DESC LIMIT 10;*/
	public List<Object[]> getPage(Pageable pageable);
}









