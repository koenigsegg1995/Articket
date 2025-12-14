package com.maddog.articket.cartitem.entity;

import com.maddog.articket.cart.entity.Cart;
import com.maddog.articket.commodity.entity.Commodity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cartItem")
public class CartItem implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartItemID", updatable = false)
	private Integer cartItemID; // 購物車明細ID

	@ManyToOne
	@JoinColumn(name = "cartID", referencedColumnName = "cartID")
	private Cart cart; // 購物車ID

	@ManyToOne
	@JoinColumn(name = "commodityID", referencedColumnName = "commodityID")
	private Commodity commodity; // 商品ID

	@NotNull(message = "購買商品數量不能為空")
	@Column(name = "checkedQuantity")
	private Integer checkedQuantity; // 購買商品數量

	// 構造函數、getter 和 setter 方法
	public CartItem() {
		super();
	}

	public CartItem(Integer cartItemID, Cart cart, Commodity commodity, Integer checkedQuantity) {
		super();
		this.cartItemID = cartItemID;
		this.cart = cart;
		this.commodity = commodity;
		this.checkedQuantity = checkedQuantity;
	}

	public Integer getCartItemID() {
		return cartItemID;
	}

	public void setCartItemID(Integer cartItemID) {
		this.cartItemID = cartItemID;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Integer getCheckedQuantity() {
		return checkedQuantity;
	}

	public void setCheckedQuantity(Integer checkedQuantity) {
		this.checkedQuantity = checkedQuantity;
	}
	
	

}