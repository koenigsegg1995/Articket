package com.maddog.articket.articleimg.entity;

import com.maddog.articket.article.entity.Article;
import jakarta.persistence.*;
import java.util.Arrays;
import java.util.Date;


@Entity
@Table(name = "articleimg") //文章圖片
public class ArticleImg implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articleImgID", updatable = false) //文章圖片ID
	private Integer articleImgID;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleID", referencedColumnName = "articleID") //文章ID
	private Article article;


	
	@Column(name = "articlePic", columnDefinition = "mediumblob") //文章圖片
	private byte[] articlePic;

	@Column(name = "articleImgCreateTime", updatable = false, insertable = false) //文章圖片時間
	@Temporal(TemporalType.TIMESTAMP)
	private Date  articleImgCreateTime;
	
//	@PrePersist
//	protected void onCreate() {
//	    this.articleImgCreateTime = new Date();
//	}
	
	
	
	public ArticleImg() { 
	}
		
	
	public Integer getArticleImgID() {
		return articleImgID;
	}

	public void setArticleImgID(Integer articleImgID) {
		this.articleImgID = articleImgID;
	}

	
	public byte[] getArticlePic() {
		return articlePic;
	}

	public void setArticlePic(byte[] articlePic) {
		this.articlePic = articlePic;
	}


	public Date getArticleImgCreateTime() {
		return articleImgCreateTime;
	}


	public Article getArticle() {
		return this.article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	@Override
	public String toString() {
		return "ArticleImg [articleImgID=" + articleImgID + ", article=" + article + ", articlePic="
				+ Arrays.toString(articlePic) + ", articleImgCreateTime=" + articleImgCreateTime + "]";
	}


	

}
