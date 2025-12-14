package com.maddog.articket.commoditypicture.entity;

import com.maddog.articket.commodity.entity.Commodity;
import jakarta.persistence.*;

@Entity
@Table(name = "commodityPicture")
public class CommodityPicture implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commodityPictureID", updatable = false)
	private Integer commodityPictureID; // 商品圖片ID

	@ManyToOne
	@JoinColumn(name = "commodityID", referencedColumnName = "commodityID")
	private Commodity commodity; // 商品ID

	@Column(name = "commodityPicture", columnDefinition = "mediumblob")
	private byte[] commodityPicture; // 商品圖片

	// 構造函數、getter 和 setter 方法
	public CommodityPicture() {
		super();
	}
	
	public CommodityPicture(Integer commodityPictureID, Commodity commodity, byte[] commodityPicture) {
		super();
		this.commodityPictureID = commodityPictureID;
		this.commodity = commodity;
		this.commodityPicture = commodityPicture;
	}

	public Integer getCommodityPictureID() {
		return commodityPictureID;
	}

	public void setCommodityPictureID(Integer commodityPictureID) {
		this.commodityPictureID = commodityPictureID;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public byte[] getCommodityPicture() {
		return commodityPicture;
	}

	public void setCommodityPicture(byte[] commodityPicture) {
		this.commodityPicture = commodityPicture;
	}
	
	

}