package com.maddog.articket.board.dao;

import com.maddog.articket.board.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章各板 DAO
 */
@Mapper
public interface BoardDao {

	/**
	 * 查詢所有文章各板
	 *
	 * @return 文章各板清單
	 */
	List<Board> findAll();

}