package com.maddog.articket.board.service.pri;

import com.maddog.articket.board.entity.Board;

import java.util.List;

/**
 * 文章各板 Service Interface
 */
public interface BoardService {

	/**
	 * 查詢所有文章各板
	 *
	 * @return 文章各板清單
	 */
	List<Board> getAll();

}