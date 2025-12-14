package com.maddog.articket.coupontype.entity;

import com.maddog.articket.membercoupon.entity.MemberCoupon;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

//優惠券
@Entity
@Table(name = "coupontype")
public class CouponType implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "couponTypeID", updatable = false)
	private Integer couponTypeID; // 優惠券類型ID

	@Column(name = "couponTypeName")
	private String couponTypeName; // 優惠券名稱

	@Column(name = "couponTypeRegulation", columnDefinition = "text")
	private String couponTypeRegulation; // 使用規則

	@Column(name = "couponTypeDiscount")
	private BigDecimal couponTypeDiscount; // 折扣數
	
	@OneToMany(mappedBy = "couponType", cascade = CascadeType.ALL)
	@OrderBy("memberCouponID asc")
	private Set<MemberCoupon> memberCoupons; // 會員優惠券

	// 建構子
	public CouponType() {
		super();
	}

	public CouponType(Integer couponTypeID, String couponTypeName, String couponTypeRegulation,
			BigDecimal couponTypeDiscount, Set<MemberCoupon> memberCoupons) {
		super();
		this.couponTypeID = couponTypeID;
		this.couponTypeName = couponTypeName;
		this.couponTypeRegulation = couponTypeRegulation;
		this.couponTypeDiscount = couponTypeDiscount;
		this.memberCoupons = memberCoupons;
	}

	// Getters & Setters
	public Integer getCouponTypeID() {
		return couponTypeID;
	}

	public void setCouponTypeID(Integer couponTypeID) {
		this.couponTypeID = couponTypeID;
	}

	public String getCouponTypeName() {
		return couponTypeName;
	}

	public void setCouponTypeName(String couponTypeName) {
		this.couponTypeName = couponTypeName;
	}

	public String getCouponTypeRegulation() {
		return couponTypeRegulation;
	}

	public void setCouponTypeRegulation(String couponTypeRegulation) {
		this.couponTypeRegulation = couponTypeRegulation;
	}

	public BigDecimal getCouponTypeDiscount() {
		return couponTypeDiscount;
	}

	public void setCouponTypeDiscount(BigDecimal couponTypeDiscount) {
		this.couponTypeDiscount = couponTypeDiscount;
	}

	public Set<MemberCoupon> getMemberCoupons() {
		return memberCoupons;
	}

	public void setMemberCoupons(Set<MemberCoupon> memberCoupons) {
		this.memberCoupons = memberCoupons;
	}
	
}
