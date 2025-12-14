package com.maddog.articket.orders.entity;

import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.membercoupon.entity.MemberCoupon;
import com.maddog.articket.orderitem.entity.OrderItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Orders implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderID", updatable = false)
	private Integer orderID; // 訂單ID

	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
	private GeneralMember generalMember; // 會員ID

	@NotEmpty(message = "收件人姓名不能空白")
	@Column(name = "recipient")
	private String recipient; // 收件人姓名

	@NotEmpty(message = "收件人電話不能空白")
	@Column(name = "recipientPhone")
	private String recipientPhone; // 連絡電話

	@NotEmpty(message = "收件人Email不能空白")
	@Column(name = "recipientEmail")
	private String recipientEmail; // E-mail

	@NotEmpty(message = "收件地址不能空白")
	@Column(name = "recipientAddress")
	private String recipientAddress; // 收件地址

	@ManyToOne
	@JoinColumn(name = "memberCouponID", referencedColumnName = "memberCouponID")
	private MemberCoupon memberCoupon; // 會員優惠券ID

	@NotNull(message = "實付金額不能為空")
	@Column(name = "actualAmount")
	private BigDecimal actualAmount; // 實付金額

	@NotNull(message = "訂單狀態不能為空")
	@Column(name = "orderStatus")
	private Integer orderStatus; // 訂單狀態 0:取消 1:未出貨 2:已出貨 3:完成訂單

	// 支付狀態有需要嗎?
	@NotNull(message = "支付狀態不能為空")
	@Column(name = "payStatus")
	private Integer payStatus; // 支付狀態

	// 時間上的設定有無錯誤
	@Column(name = "payTime", updatable = false, insertable = false)
	private Timestamp payTime; // 付款時間

	// 先以手動更新
	@Column(name = "shipTime")
	private Timestamp shipTime; // 出貨時間

//    需要連動嗎
	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
	@OrderBy("orderItemID asc")
	private Set<OrderItem> orderItems;

	// 構造函數、getter 和 setter 方法
	public Orders() {
		super();
	}

	public Orders(Integer orderID, GeneralMember generalMember, String recipient, String recipientPhone,
			String recipientEmail, String recipientAddress, MemberCoupon memberCoupon, BigDecimal actualAmount,
			Integer orderStatus, Integer payStatus, Timestamp payTime, Timestamp shipTime, Set<OrderItem> orderItems) {
		super();
		this.orderID = orderID;
		this.generalMember = generalMember;
		this.recipient = recipient;
		this.recipientPhone = recipientPhone;
		this.recipientEmail = recipientEmail;
		this.recipientAddress = recipientAddress;
		this.memberCoupon = memberCoupon;
		this.actualAmount = actualAmount;
		this.orderStatus = orderStatus;
		this.payStatus = payStatus;
		this.payTime = payTime;
		this.shipTime = shipTime;
		this.orderItems = orderItems;

	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public GeneralMember getGeneralMember() {
		return generalMember;
	}

	public void setGeneralMember(GeneralMember generalMember) {
		this.generalMember = generalMember;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public MemberCoupon getMemberCoupon() {
		return memberCoupon;
	}

	public void setMemberCoupon(MemberCoupon memberCoupon) {
		this.memberCoupon = memberCoupon;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public Timestamp getShipTime() {
		return shipTime;
	}

	public void setShipTime(Timestamp shipTime) {
		this.shipTime = shipTime;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	// 在會員中心商品訂單顯示訂單狀態
	public String getOrderStatusDescription() {
	        switch (this.orderStatus) {
	            case 0:
	                return "取消";
	            case 1:
	                return "未出貨";
	            case 2:
	                return "已出貨";
	            case 3:
	                return "完成訂單";
	            default:
	                return "未知狀態";
	        }
	}

	@PrePersist
	protected void onCreate() {
		payTime = new Timestamp(System.currentTimeMillis());
	}
}