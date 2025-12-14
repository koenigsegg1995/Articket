package com.maddog.articket.commodity.entity;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.cartitem.entity.CartItem;
import com.maddog.articket.commoditypicture.entity.CommodityPicture;
import com.maddog.articket.orderitem.entity.OrderItem;
import com.maddog.articket.partnermember.entity.PartnerMember;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

//商品
@Entity
@Table(name = "commodity")
public class Commodity implements Serializable{
	private static final long serialVersionUID = 1L;

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commodityID", updatable = false) 
    private Integer commodityID; //商品ID

    @NotEmpty(message = "商品名稱不能空白")
    @Column(name = "commodityName", length = 255)
    private String commodityName; //商品名稱

	@DecimalMin(value = "0.00", message = "商品價格: 不能小於{value}")
    @NotNull(message = "商品價格不能空白")
    @Column(name = "commodityPrice") 
    private BigDecimal commodityPrice; //商品價格
    
    @NotNull(message = "商品庫存不能空白")
    @Column(name = "commodityStock") 
    private Integer commodityStock; //商品庫存

    @NotNull(message = "商品內容不能空白")
    @Column(name = "commodityContent", columnDefinition = "text") 
    private String commodityContent; //商品內容

    @ManyToOne
    @JoinColumn(name = "activityID", referencedColumnName = "activityID") 
    private Activity activity; //活動ID

    @ManyToOne
    @JoinColumn(name = "partnerID", referencedColumnName = "partnerID") 
    private PartnerMember partnerMember; //廠商ID

    @Column(name = "commodityStatus")
    private Integer commodityStatus; //上下架狀態 0:下架 1:在售

    @Future(message = "請選擇今天(不包含今天)之後的日期")
    @Column(name = "commodityPostTime")
    private Date commodityPostTime; //上架時間
    	
    @Column(name = "commodityRemoveTime")
    private Date commodityRemoveTime; //下架時間

    @Column(name = "commodityUpdateTime")
    private Timestamp commodityUpdateTime; //更新時間

    @Column(name = "commodityCreateTime", updatable = false, insertable = false) 
    private Timestamp commodityCreateTime; //建立時間
    
    @OneToMany(mappedBy = "commodity", cascade = CascadeType.ALL)
    @OrderBy("commodityPictureID asc")
    private Set<CommodityPicture> commodityPictures;
    
//    是不是不用連動
    @OneToMany(mappedBy = "commodity", cascade = CascadeType.ALL)
    @OrderBy("orderItemID asc")
    private Set<OrderItem> orderItems;
    
    @OneToMany(mappedBy = "commodity", cascade = CascadeType.ALL)
    @OrderBy("cartItemID asc")
    private Set<CartItem> cartItems;

    
    

	//建構子
    public Commodity() {
    	super();
    }
    
	public Commodity(Integer commodityID, String commodityName, BigDecimal commodityPrice, Integer commodityStock,
			String commodityContent, Integer activityID, Integer partnerID, Integer commodityStatus,
			Date commodityPostTime, Date commodityRemoveTime, Timestamp commodityCreateTime,
			Timestamp commodityUpdateTime, Set<CommodityPicture> commodityPictures, Set<OrderItem> orderItems, Set<CartItem> cartItems) {
		super();
		this.commodityID = commodityID;
		this.commodityName = commodityName;
		this.commodityPrice = commodityPrice;
		this.commodityStock = commodityStock;
		this.commodityContent = commodityContent;
		this.activity = activity;
		this.partnerMember = partnerMember;
		this.commodityStatus = commodityStatus;
		this.commodityPostTime = commodityPostTime;
		this.commodityRemoveTime = commodityRemoveTime;
		this.commodityCreateTime = commodityCreateTime;
		this.commodityUpdateTime = commodityUpdateTime;
		this.commodityPictures = commodityPictures;
		this.orderItems = orderItems;
		this.cartItems = cartItems;
	}

	public Integer getCommodityID() {
		return commodityID;
	}

	public void setCommodityID(Integer commodityID) {
		this.commodityID = commodityID;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public BigDecimal getCommodityPrice() {
		return commodityPrice;
	}

	public void setCommodityPrice(BigDecimal commodityPrice) {
		this.commodityPrice = commodityPrice;
	}

	public Integer getCommodityStock() {
		return commodityStock;
	}

	public void setCommodityStock(Integer commodityStock) {
		this.commodityStock = commodityStock;
	}

	public String getCommodityContent() {
		return commodityContent;
	}

	public void setCommodityContent(String commodityContent) {
		this.commodityContent = commodityContent;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public PartnerMember getPartnerMember() {
		return partnerMember;
	}

	public void setPartnerMember(PartnerMember partnerMember) {
		this.partnerMember = partnerMember;
	}

	public Integer getCommodityStatus() {
		return commodityStatus;
	}

	public void setCommodityStatus(Integer commodityStatus) {
		this.commodityStatus = commodityStatus;
	}

	public Date getCommodityPostTime() {
		return commodityPostTime;
	}

	public void setCommodityPostTime(Date commodityPostTime) {
		this.commodityPostTime = commodityPostTime;
	}

	public Date getCommodityRemoveTime() {
		return commodityRemoveTime;
	}

	public void setCommodityRemoveTime(Date commodityRemoveTime) {
		this.commodityRemoveTime = commodityRemoveTime;
	}

	public Timestamp getCommodityCreateTime() {
		return commodityCreateTime;
	}

	public void setCommodityCreateTime(Timestamp commodityCreateTime) {
		this.commodityCreateTime = commodityCreateTime;
	}

	public Timestamp getCommodityUpdateTime() {
		return commodityUpdateTime;
	}

	public void setCommodityUpdateTime(Timestamp commodityUpdateTime) {
		this.commodityUpdateTime = commodityUpdateTime;
	}
	
	public Set<CommodityPicture> getCommodityPictures() {
		return commodityPictures;
	}

	public void setCommodityPictures(Set<CommodityPicture> commodityPictures) {
		this.commodityPictures = commodityPictures;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

    @PrePersist
    protected void onCreate() {
    	commodityCreateTime = new Timestamp(System.currentTimeMillis());
    }
    
    @PreUpdate
    protected void onUpdate() {
    	commodityUpdateTime = new Timestamp(System.currentTimeMillis());
    }
   
//    Lombok 注解：
//
//    @Data：
//    自動生成所有字段的 getter 和 setter 方法
//    自動生成 toString() 方法
//    自動生成 equals() 和 hashCode() 方法
//    不會生成構造函數
//
//    @AllArgsConstructor：
//    自動生成一個包含所有字段的構造函數
//
//    @NoArgsConstructor：
//    自動生成一個無參數的構造函數
//
//    可以大大減少需要手動編寫的樣板代碼量。
    
}
