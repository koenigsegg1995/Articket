package com.maddog.articket.news.entity;

import com.maddog.articket.administrator.entity.Administrator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "news")
public class News implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "newsID", updatable = false)
	private Integer newsID; // 消息ID

	@ManyToOne
	@JoinColumn(name = "administratorID", referencedColumnName = "administratorID")
	private Administrator administrator; // 管理員ID

	@NotEmpty(message = "消息標題不能空白")
	@Column(name = "newsTitle", length = 255)
	private String newsTitle; // 標題

	@NotEmpty(message = "消息內容不能空白")
	@Column(name = "newsContent", columnDefinition = "text")
	private String newsContent; // 內容

	@NotNull(message = "消息狀態不能為空")
	@Column(name = "newsStatus")
	private Integer newsStatus; // 狀態 0:隱藏 1:正常顯示 2:置頂

	@Column(name = "newsCreateTime")
	private Timestamp newsCreateTime; // 發布時間

	// 構造函數、getter 和 setter 方法
	public News() {
		super();
	}

	public News(Integer newsID, Administrator administrator, String newsTitle, String newsContent, Integer newsStatus,
			Timestamp newsCreateTime) {
		super();
		this.newsID = newsID;
		this.administrator = administrator;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsStatus = newsStatus;
		this.newsCreateTime = newsCreateTime;
	}

	public Integer getNewsID() {
		return newsID;
	}

	public void setNewsID(Integer newsID) {
		this.newsID = newsID;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Integer getNewsStatus() {
		return newsStatus;
	}

	public void setNewsStatus(Integer newsStatus) {
		this.newsStatus = newsStatus;
	}

	public Timestamp getNewsCreateTime() {
		return newsCreateTime;
	}

	public void setNewsCreateTime(Timestamp newsCreateTime) {
		this.newsCreateTime = newsCreateTime;
	}

	@PrePersist
	@PreUpdate
	protected void onUpdate() {
		newsCreateTime = new Timestamp(System.currentTimeMillis());
	}

//    @Override
//    public String toString() {
//        return "News{" +
//                "newsID=" + newsID +
//                ", administratorID=" + administratorID +
//                ", newsTitle='" + newsTitle + '\'' +
//                ", newsContent='" + newsContent + '\'' +
//                ", newsStatus=" + newsStatus +
//                ", newsCreateTime=" + newsCreateTime +
//                '}';
//    }
}