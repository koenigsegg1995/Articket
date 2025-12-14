package com.maddog.articket.activitycollection.entity;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.generalmember.entity.GeneralMember;
import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "activitycollection")
//活動收藏
public class ActivityCollection implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activityCollectionID", updatable = false)
	private Integer activityCollectionID; //活動收藏ID
	
	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
	private GeneralMember generalMember; // 一般會員
	
	@ManyToOne
	@JoinColumn(name = "activityID", referencedColumnName = "activityID")
	private Activity activity; // 活動
	
	@Column(name = "activityCollectionTime", updatable = false, insertable = false)
	private Timestamp activityCollectionTime; //活動收藏時間
	
	//建構子
	public ActivityCollection() {
		super();
	}

	public ActivityCollection(Integer activityCollectionID, GeneralMember generalMember, Activity activity,
			Timestamp activityCollectionTime) {
		super();
		this.activityCollectionID = activityCollectionID;
		this.generalMember = generalMember;
		this.activity = activity;
		this.activityCollectionTime = activityCollectionTime;
	}

	// Getter & Setter
	public Integer getActivityCollectionID() {
		return activityCollectionID;
	}

	public void setActivityCollectionID(Integer activityCollectionID) {
		this.activityCollectionID = activityCollectionID;
	}

	public GeneralMember getGeneralMember() {
		return generalMember;
	}

	public void setGeneralMember(GeneralMember generalMember) {
		this.generalMember = generalMember;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Timestamp getActivityCollectionTime() {
		return activityCollectionTime;
	}

	public void setActivityCollectionTime(Timestamp activityCollectionTime) {
		this.activityCollectionTime = activityCollectionTime;
	}

}
