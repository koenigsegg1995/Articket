package com.maddog.articket.board.service.impl;

import com.maddog.articket.board.dao.BoardDao;
import com.maddog.articket.board.entity.Board;
import com.maddog.articket.board.service.pri.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章各板 Service Implementation
 */
@Service("boardService")
public class BoardServiceImpl implements BoardService {

	/**
	 * 文章各板 DAO
	 */
	@Autowired
	private BoardDao boardDao;

	/**
	 * 查詢所有文章各板
	 *
	 * @return 文章各板清單
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Board> getAll() {
		return boardDao.findAll();
	}

}