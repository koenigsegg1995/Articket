package com.maddog.articket.cart.entity;


import com.maddog.articket.commodity.entity.Commodity;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.cartitem.entity.CartItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartID", updatable = false)
	private Integer cartID; // 購物車ID

	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
	private GeneralMember generalMember; // 會員ID

	@NotNull(message = "購物車總價不能為空")
	@Column(name = "cartTotalPrice")
	private BigDecimal cartTotalPrice; // 購物車總價

	@Column(name = "cartCreateTime")
	private Timestamp cartCreateTime; // 建立時間

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("cartItemID asc")
	private Set<CartItem> cartItems;

	// 構造函數、getter 和 setter 方法
	public Cart() {
		super();
	}

	public Cart(Integer cartID, GeneralMember generalMember, BigDecimal cartTotalPrice, Timestamp cartCreateTime,
			Set<CartItem> cartItems) {
		super();
		this.cartID = cartID;
		this.generalMember = generalMember;
		this.cartTotalPrice = cartTotalPrice;
		this.cartCreateTime = cartCreateTime;
		this.cartItems = cartItems;
	}

	public Integer getCartID() {
		return cartID;
	}

	public void setCartID(Integer cartID) {
		this.cartID = cartID;
	}

	public GeneralMember getGeneralMember() {
		return generalMember;
	}

	public void setGeneralMember(GeneralMember generalMember) {
		this.generalMember = generalMember;
	}

	public BigDecimal getCartTotalPrice() {
		return cartTotalPrice;
	}

	public void setCartTotalPrice(BigDecimal cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}
	
	public void addItem(Commodity commodity, int quantity) {
        CartItem item = cartItems.stream()
                .filter(i -> i.getCommodity().getCommodityID().equals(commodity.getCommodityID()))
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.setCheckedQuantity(item.getCheckedQuantity() + quantity);
        } else {
            item = new CartItem();
            item.setCommodity(commodity);
            item.setCheckedQuantity(quantity);
            item.setCart(this);
            cartItems.add(item);
        }
    }

    public void removeItem(Commodity commodity) {
        cartItems.removeIf(item -> item.getCommodity().getCommodityID().equals(commodity.getCommodityID()));
    }

//    public double getTotalPrice() {
//        return cartItems.stream()
//                .mapToDouble(item -> item.getCommodity().getCommodityPrice() * item.getQuantity())
//                .sum();
//    }

	public void calculateTotalPrice() {
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem item : this.cartItems) {
			BigDecimal itemPrice = item.getCommodity().getCommodityPrice();
			BigDecimal itemQuantity = new BigDecimal(item.getCheckedQuantity());
			total = total.add(itemPrice.multiply(itemQuantity));
		}
		this.setCartTotalPrice(total);
	}

	public void updateTotalPrice() {
		calculateTotalPrice();
	}

	public Timestamp getCartCreateTime() {
		return cartCreateTime;
	}

	public void setCartCreateTime(Timestamp cartCreateTime) {
		this.cartCreateTime = cartCreateTime;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@PrePersist
	@PreUpdate
	protected void onUpdate() {
		cartCreateTime = new Timestamp(System.currentTimeMillis());
	}
}
