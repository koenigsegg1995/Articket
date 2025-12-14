package com.maddog.articket.articlecollection.entity;


import com.maddog.articket.article.entity.Article;
import com.maddog.articket.generalmember.entity.GeneralMember;
import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name = "articlecollection") //文章收藏
public class ArticleCollection implements java.io.Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articleCollectionID", updatable = false) //文章收藏ID
	private Integer articleCollectionID;
	
	
	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID") //會員ID
	private GeneralMember generalMember;
	


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleID", referencedColumnName = "articleID") //文章ID
	private Article article;
	


	@Column(name = "collectionCreateTime", updatable = false, insertable = false) //收藏時間
	@Temporal(TemporalType.TIMESTAMP)
	private Date  collectionCreateTime;
		
	
	public ArticleCollection() { 
	}
	

	public Integer getArticleCollectionID() {
		return articleCollectionID;
	}
	
	
	public void setArticleCollectionID(Integer articleCollectionID) {
		this.articleCollectionID = articleCollectionID;
	}
	
	
	public Date getCollectionCreateTime() {
		return collectionCreateTime;
	}
	

	public GeneralMember getGeneralMember() {
		return this.generalMember;
	}


	public void setGeneralMember(GeneralMember generalMember) {
		this.generalMember = generalMember;
	}


	public Article getArticle() {
		return this.article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


//	@Override
//	public String toString() {
//	    return "ArticleCollection [articleCollectionID=" + articleCollectionID 
//				+ ", generalMember=" + generalMember	+ ", article=" + article
//	           + ", collectionCreateTime=" + collectionCreateTime + "]";
//	}


	



	

}
