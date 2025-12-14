package com.maddog.articket.activitypicture.entity;


import com.maddog.articket.activity.entity.Activity;
import jakarta.persistence.*;
import java.io.Serializable;

//活動圖片
@Entity
@Table(name = "activitypicture")
public class ActivityPicture implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activityPictureID", updatable = false)
	private Integer activityPictureID; // 活動圖片ID

	@ManyToOne
	@JoinColumn(name = "activityID", referencedColumnName = "activityID")
	private Activity activity; // 活動

	@Column(name = "activityPicture", columnDefinition = "mediumblob")
	private byte[] activityPicture; // 活動圖片

	// 建構子
	public ActivityPicture() {
		super();
	}

	public ActivityPicture(Integer activityPictureID, Activity activity, byte[] activityPicture) {
		super();
		this.activityPictureID = activityPictureID;
		this.activity = activity;
		this.activityPicture = activityPicture;
	}

	// Getters & Setters
	public Integer getActivityPictureID() {
		return activityPictureID;
	}

	public void setActivityPictureID(Integer activityPictureID) {
		this.activityPictureID = activityPictureID;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public byte[] getActivityPicture() {
		return activityPicture;
	}

	public void setActivityPicture(byte[] activityPicture) {
		this.activityPicture = activityPicture;
	}

}
