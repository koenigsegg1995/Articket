package com.maddog.articket.announcement.entity;

import com.maddog.articket.administrator.entity.Administrator;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "announcement")
public class Announcement implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "announcementID", updatable = false)
	private Integer announcementID; // 公告ID

	@ManyToOne
	@JoinColumn(name = "administratorID", referencedColumnName = "administratorID")
	private Administrator administrator; // 管理員ID

	@NotEmpty(message = "公告標題不能空白")
	@Column(name = "announcementTitle", length = 255)
	private String announcementTitle; // 標題

	@NotEmpty(message = "公告內容不能空白")
	@Column(name = "announcementContent", columnDefinition = "text")
	private String announcementContent; // 內容

	@NotNull(message = "公告狀態不能為空")
	@Column(name = "announcementStatus")
	private Integer announcementStatus; // 狀態 0:隱藏 1:正常顯示 2:置頂

	@Column(name = "announcementCreateTime")
	private Timestamp announcementCreateTime; // 發布時間

	// 構造函數、getter 和 setter 方法
	public Announcement() {
		super();
	}

	public Announcement(Integer announcementID, Administrator administrator, String announcementTitle,
			String announcementContent, Integer announcementStatus, Timestamp announcementCreateTime) {
		super();
		this.announcementID = announcementID;
		this.administrator = administrator;
		this.announcementTitle = announcementTitle;
		this.announcementContent = announcementContent;
		this.announcementStatus = announcementStatus;
		this.announcementCreateTime = announcementCreateTime;
	}
	
	

	public Integer getAnnouncementID() {
		return announcementID;
	}

	public void setAnnouncementID(Integer announcementID) {
		this.announcementID = announcementID;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public String getAnnouncementTitle() {
		return announcementTitle;
	}

	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}

	public String getAnnouncementContent() {
		return announcementContent;
	}

	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}

	public Integer getAnnouncementStatus() {
		return announcementStatus;
	}

	public void setAnnouncementStatus(Integer announcementStatus) {
		this.announcementStatus = announcementStatus;
	}

	public Timestamp getAnnouncementCreateTime() {
		return announcementCreateTime;
	}

	public void setAnnouncementCreateTime(Timestamp announcementCreateTime) {
		this.announcementCreateTime = announcementCreateTime;
	}

	@PrePersist
	@PreUpdate
	protected void onUpdate() {
		announcementCreateTime = new Timestamp(System.currentTimeMillis());
	}

}
