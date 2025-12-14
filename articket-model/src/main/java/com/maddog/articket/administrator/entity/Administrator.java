package com.maddog.articket.administrator.entity;


import com.maddog.articket.announcement.model.entity.Announcement;
import com.maddog.articket.news.entity.News;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "administrator")
public class Administrator {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "administratorID", updatable = false) // "管理員ID"
	private Integer administratorID;
	
	@Column(name = "administratorAccount") // "帳號"
	private String administratorAccount;
	
	@Column(name = "administratorPassword") // "密碼"
	private String administratorPassword;
	
	@Column(name = "administratorCreateTime", updatable = false) // "帳號建立時間"
	private Date administratorCreateTime;
	
	@Column(name = "administratorStatus") // "帳號狀態 0:帳號正常 1:帳號停用"
	private Integer administratorStatus;
	
	
	@OneToMany(mappedBy = "administrator", cascade = CascadeType.ALL)
	@OrderBy("newsID asc")
	private Set<News> news;
	
	
	@OneToMany(mappedBy = "administrator", cascade = CascadeType.ALL)
	@OrderBy("announcementID asc")
	private Set<Announcement> announcements;


	public Administrator() {
		super();
	}

	public Integer getAdministratorID() {
		return administratorID;
	}


	public void setAdministratorID(Integer administratorID) {
		this.administratorID = administratorID;
	}


	public String getAdministratorAccount() {
		return administratorAccount;
	}


	public void setAdministratorAccount(String administratorAccount) {
		this.administratorAccount = administratorAccount;
	}


	public String getAdministratorPassword() {
		return administratorPassword;
	}


	public void setAdministratorPassword(String administratorPassword) {
		this.administratorPassword = administratorPassword;
	}


	public Date getAdministratorCreateTime() {
		return administratorCreateTime;
	}


	public void setAdministratorCreateTime(Date administratorCreateTime) {
		this.administratorCreateTime = administratorCreateTime;
	}


	public Integer getAdministratorStatus() {
		return administratorStatus;
	}


	public void setAdministratorStatus(Integer administratorStatus) {
		this.administratorStatus = administratorStatus;
	}


	public Set<News> getNews() {
		return news;
	}


	public void setNews(Set<News> news) {
		this.news = news;
	}


	public Set<Announcement> getAnnouncements() {
		return announcements;
	}


	public void setAnnouncements(Set<Announcement> announcements) {
		this.announcements = announcements;
	}


//	@Override
//	public String toString() {
//		return "Administrator [administratorID=" + administratorID + ", administratorAccount=" + administratorAccount
//				+ ", administratorPassword=" + administratorPassword + ", administratorCreateTime="
//				+ administratorCreateTime + ", administratorStatus=" + administratorStatus + ", news=" + news
//				+ ", announcements=" + announcements + "]";
//	}

}
