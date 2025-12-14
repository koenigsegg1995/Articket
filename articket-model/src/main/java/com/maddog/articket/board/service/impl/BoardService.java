package com.maddog.articket.board.service.impl;

import com.maddog.articket.board.dao.BoardRepository;
import com.maddog.articket.board.entity.Board;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("boardService")
public class BoardService {

	@Autowired
	BoardRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;

	public void addBoard(Board board) {
		repository.save(board);
	}

	public void updateBoard(Board board) {
		repository.save(board);
	}

	public void deleteBoard(Integer board) {
		if (repository.existsById(board))
			repository.deleteByBoardID(board);
//		    repository.deleteById(board);
	}

	public Board getOneBoard(Integer boardID) {
		Optional<Board> optional = repository.findById(boardID);
//		return optional.get();
		return optional.orElse(null);  // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<Board> getAll() {
		return repository.findAll();
	}

//	public List<Board> getAll(Map<String, String[]> map) {
//		return HibernateUtil_CompositeQuery_Board3.getAllC(map,sessionFactory.openSession());
//	}

}