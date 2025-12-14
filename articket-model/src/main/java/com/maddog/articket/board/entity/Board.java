package com.maddog.articket.board.entity;


import com.maddog.articket.article.entity.Article;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "board")
public class Board implements java.io.Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "boardID", updatable = false)
	private Integer boardID;
	
	@Column(name = "boardName")
	private String boardName;
	
	

	@OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
	@OrderBy("articleID asc")
	private Set<Article> articles = new HashSet<Article>();
	
	
	
	public Board() { 
	}
	
	
	public Integer getBoardID() {
		return boardID;
	}

	public void setBoardID(Integer boardID) {
		this.boardID = boardID;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
	

	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	

	@Override
	public String toString() {
	    return "Board [boardID=" + boardID + ", boardName=" + boardName + ", articlesCount=" + articles.size() + "]";
	}
	
	


}