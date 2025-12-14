package com.maddog.articket.prosecute.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maddog.articket.article.entity.Article;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.message.entity.Message;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.Date;


@Entity
@Table(name = "prosecute") //檢舉
public class Prosecute implements java.io.Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prosecuteID", updatable = false) //檢舉ID
	private Integer prosecuteID;
	

	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID") //會員ID
	private GeneralMember generalMember;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleID", referencedColumnName = "articleID") //文章ID
//	@JsonIgnore
	private Article article;
	
	
	
	@Column(name = "prosecuteReason") //檢舉原因
	@NotEmpty(message="檢舉原因請勿空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$", message = "原因只能是中、英文字母、數字和_ , 且長度必需在2到50字之間")
	private String prosecuteReason ;	
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "messageID", referencedColumnName = "messageID") //留言ID
//	@JsonIgnore
	private Message message;

	
	@Column(name = "prosecuteStatus") //檢舉狀態
	@ColumnDefault("1") //1:顯示 2:不顯示
	private Integer prosecuteStatus = 1;
		

    @Column(name = "prosecuteCreateTime")
    private Date prosecuteCreateTime;

    @PrePersist
    protected void onCreate() {
        if (prosecuteCreateTime == null) {
            prosecuteCreateTime = new Date(System.currentTimeMillis());
        }
    }
    
    @JsonProperty("articleId")
    public Integer getArticleId() {
        return article != null ? article.getArticleID() : null;
    }

    @JsonProperty("messageId")
    public Integer getMessageId() {
        return message != null ? message.getMessageID() : null;
    }

	
	public Prosecute() { 
	}
	

	public Integer getProsecuteID() {
		return prosecuteID;
	}


	public void setProsecuteID(Integer prosecuteID) {
		this.prosecuteID = prosecuteID;
	}


	public String getProsecuteReason() {
		return prosecuteReason;
	}


	public void setProsecuteReason(String prosecuteReason) {
		this.prosecuteReason = prosecuteReason;
	}




	public Integer getProsecuteStatus() {
		return prosecuteStatus;
	}


	public void setProsecuteStatus(Integer prosecuteStatus) {
		this.prosecuteStatus = prosecuteStatus;
	}


    public Date getProsecuteCreateTime() {
        return prosecuteCreateTime;
    }

    public void setProsecuteCreateTime(Date prosecuteCreateTime) {
        this.prosecuteCreateTime = prosecuteCreateTime;
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


	public Message getMessage() {
		return this.message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}


//	@Override
//	public String toString() {
//	    return "Prosecute [prosecuteID=" + prosecuteID 
//	           + ", generalMember=" + generalMember
//	           + ", article=" + (article != null ? article.getArticleID() : "null")
//	           + ", prosecuteReason=" + prosecuteReason
//	           + ", message=" + (message != null ? message.getMessageID() : "null")
//	           + ", prosecuteStatus=" + prosecuteStatus 
//	           + ", prosecuteCreateTime=" + prosecuteCreateTime + "]";
//	}



}