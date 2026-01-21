package com.maddog.articket.board.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 文章各板 DO
 */
@Getter
@Setter
public class Board implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 各板 ID
	 */
	private Integer boardId;

	/**
	 * 各板名稱
	 */
	private String boardName;

	@Override
	public String toString() {
	    return "Board [boardID=" + boardId + ", boardName=" + boardName + "]";
	}

}