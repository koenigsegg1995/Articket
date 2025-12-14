package com.maddog.articket.message.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.prosecute.entity.Prosecute;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "message") //留言
public class Message implements java.io.Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "messageID", updatable = false) //留言ID
	private Integer messageID;
	
	
	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID") //會員ID
	private GeneralMember generalMember;


	@JsonBackReference //解決循環引用問題,不序列化父端
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleID", referencedColumnName = "articleID") //文章ID
	private Article article;
	
	
	@Column(name = "messageContent") //留言內容
	@NotEmpty(message="請輸入留言內容")
	@Size(min=2,max=500,message="留言內容必需在{min}字到{max}字之間")
	private String messageContent ;	
	
	
	@Column(name = "messageStatus ") //留言狀態
	private Integer messageStatus = 1 ;
	

	@Column(name = "messageCreateTime", updatable = false, insertable = false) //留言時間
	@Temporal(TemporalType.TIMESTAMP)
	private Date  messageCreateTime;

	
	

	@OneToOne(mappedBy = "message",cascade = CascadeType.ALL, fetch = FetchType.LAZY) //檢舉
	@JsonIgnore
	private Prosecute prosecute;
	
	//RowMapper中的相關擴充欄位
	//會員id(無關聯的)
	@Transient
	private Integer memberIDRM;
	
	//文章id(無關聯的)
	@Transient
	private Integer articleIDRM;
	
	//會員暱稱(無關聯的)
	@Transient
	private String memberNameRM;
	
	//留言時間(無關聯的)
	@Transient
	private Date messageCreateTimeRM;
	
	
	public Message() { 
	}
	

	public Integer getMessageID() {
		return messageID;
	}


	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}


	public String getMessageContent() {
		return messageContent;
	}


	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}


	public Integer getMessageStatus() {
		return messageStatus;
	}


	public void setMessageStatus(Integer messageStatus) {
		this.messageStatus = messageStatus;
	}


	public Date getMessageCreateTime() {
		return messageCreateTime;
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

	


	public Prosecute getProsecute() {
		return this.prosecute;
	}


	public void setProsecute(Prosecute prosecute) {
		this.prosecute = prosecute;
	}


	public Integer getMemberIDRM() {
		return memberIDRM;
	}


	public void setMemberIDRM(Integer memberIDRM) {
		this.memberIDRM = memberIDRM;
	}


	public Integer getArticleIDRM() {
		return articleIDRM;
	}


	public void setArticleIDRM(Integer articleIDRM) {
		this.articleIDRM = articleIDRM;
	}


	public String getMemberNameRM() {
		return memberNameRM;
	}


	public void setMemberNameRM(String memberNameRM) {
		this.memberNameRM = memberNameRM;
	}


	public Date getMessageCreateTimeRM() {
		return messageCreateTimeRM;
	}


	public void setMessageCreateTimeRM(Date messageCreateTimeRM) {
		this.messageCreateTimeRM = messageCreateTimeRM;
	}

	

	
//	@Override
//	public String toString() {
//	    return "Message [messageID=" + messageID 
//	           + ", generalMember=" + generalMember
//	           + ", article=" + (article != null ? article.getArticleID() : "null")
//	           + ", messageContent=" + messageContent 
//	           + ", messageStatus=" + messageStatus 
//	           + ", messageCreateTime=" + messageCreateTime 
//	           + ", prosecute=" + (prosecute != null ? prosecute.getProsecuteID() : "null") + "]";
//	}




}