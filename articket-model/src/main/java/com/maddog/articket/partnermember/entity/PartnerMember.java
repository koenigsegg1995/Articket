package com.maddog.articket.partnermember.entity;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.commodity.entity.Commodity;
import com.maddog.articket.venuerental.entity.VenueRental;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "partnermember")
public class PartnerMember {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "partnerID", updatable = false) // "廠商ID"
	private Integer partnerID;
	
	@NotEmpty(message="統一編號: 請勿空白")
	@Column(name = "taxID") // "統一編號(登入帳號)"
	private String taxID;
	
	@NotEmpty(message="公司名稱: 請勿空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$", message = "公司名稱: 只能是中、英文字母、數字和")
	@Column(name = "partnerName") // "公司名稱"
	private String partnerName;
	
	@NotEmpty(message="抬頭: 請勿空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$", message = "抬頭: 只能是中、英文字母")
	@Column(name = "partnerHeading") // "抬頭"
	private String partnerHeading;
	
	@NotEmpty(message="地址: 請勿空白")
	@Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$", message = "地址: 只能是中、英文字母、數字")
	@Column(name = "partnerAddress") // "地址"
	private String partnerAddress;
	
	@NotEmpty(message="連絡電話: 請勿空白")
	@Column(name = "partnerPhone") // "連絡電話"
	private String partnerPhone;
	
	@NotEmpty(message="聯絡人: 請勿空白")
	@Column(name = "partnerContactPerson") // "聯絡人"
	private String partnerContactPerson;
	
	@NotEmpty(message="密碼: 請勿空白")
	@Column(name = "partnerPassword") // "密碼"
	private String partnerPassword;
	
	@NotEmpty(message="電子信箱: 請勿空白")
	@Column(name = "partnerEmail") // "電子信箱"
	private String partnerEmail;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "partnerCreateTime", updatable = false, insertable = false) // "帳號建立時間"
	private Date partnerCreateTime;
	
	@Column(name = "partnerAccountStatus") // "帳號狀態 0:黑名單 1.使用中 2.申請中"
	private Integer partnerAccountStatus = 2;
	
	
	@OneToMany(mappedBy = "partnerMember", cascade = CascadeType.ALL)
	@OrderBy("commodityID asc")
	private Set<Commodity> commodities;
	
	@OneToMany(mappedBy = "partnerMember", cascade = CascadeType.ALL)
	@OrderBy("activityID asc")
	private Set<Activity> activities;
	
	@OneToMany(mappedBy = "partnerMember", cascade = CascadeType.ALL)
	@OrderBy("venueRentalID asc")
	private Set<VenueRental> venueRentals;

	public PartnerMember() {
		super();
	}

	public Integer getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(Integer partnerID) {
		this.partnerID = partnerID;
	}

	public String getTaxID() {
		return taxID;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerHeading() {
		return partnerHeading;
	}

	public void setPartnerHeading(String partnerHeading) {
		this.partnerHeading = partnerHeading;
	}

	public String getPartnerAddress() {
		return partnerAddress;
	}

	public void setPartnerAddress(String partnerAddress) {
		this.partnerAddress = partnerAddress;
	}

	public String getPartnerPhone() {
		return partnerPhone;
	}

	public void setPartnerPhone(String partnerPhone) {
		this.partnerPhone = partnerPhone;
	}

	public String getPartnerContactPerson() {
		return partnerContactPerson;
	}

	public void setPartnerContactPerson(String partnerContactPerson) {
		this.partnerContactPerson = partnerContactPerson;
	}

	public String getPartnerPassword() {
		return partnerPassword;
	}

	public void setPartnerPassword(String partnerPassword) {
		this.partnerPassword = partnerPassword;
	}

	public String getPartnerEmail() {
		return partnerEmail;
	}

	public void setPartnerEmail(String partnerEmail) {
		this.partnerEmail = partnerEmail;
	}

	public Date getPartnerCreateTime() {
		return partnerCreateTime;
	}

	public void setPartnerCreateTime(Date partnerCreateTime) {
		this.partnerCreateTime = partnerCreateTime;
	}

	public Integer getPartnerAccountStatus() {
		return partnerAccountStatus;
	}

	public void setPartnerAccountStatus(Integer partnerAccountStatus) {
		this.partnerAccountStatus = partnerAccountStatus;
	}

	public Set<Commodity> getCommodities() {
		return commodities;
	}

	public void setCommodities(Set<Commodity> commodities) {
		this.commodities = commodities;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public Set<VenueRental> getVenueRentals() {
		return venueRentals;
	}

	public void setVenueRentals(Set<VenueRental> venueRentals) {
		this.venueRentals = venueRentals;
	}

//	@Override
//	public String toString() {
//		return "PartnerMember [partnerID=" + partnerID + ", taxID=" + taxID + ", partnerName=" + partnerName
//				+ ", partnerHeading=" + partnerHeading + ", partnerAddress=" + partnerAddress + ", partnerPhone="
//				+ partnerPhone + ", partnerContactPerson=" + partnerContactPerson + ", partnerPassword="
//				+ partnerPassword + ", partnerEmail=" + partnerEmail + ", partnerCreateTime=" + partnerCreateTime
//				+ ", partnerAccountStatus=" + partnerAccountStatus + ", commodities=" + commodities + ", activities="
//				+ activities + ", venueRentals=" + venueRentals + "]";
//	}

	
	
	
}
