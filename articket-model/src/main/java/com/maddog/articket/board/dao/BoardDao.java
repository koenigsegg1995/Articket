package com.maddog.articket.board.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BoardDao {

	@Transactional
	@Modifying
	@Query(value = "delete from board where boardID =?1", nativeQuery = true)
	void deleteByBoardID(int boardID);

}