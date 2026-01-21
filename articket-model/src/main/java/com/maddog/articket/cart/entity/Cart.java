package com.maddog.articket.cart.entity;

import com.maddog.articket.commodity.entity.Commodity;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.cartitem.entity.CartItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * 購物車 DO
 */
@Getter
@Setter
public class Cart implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 購物車 ID
	 */
	private Integer cartId;

	/**
	 * 會員 ID
	 */
	private Integer memberId;

	/**
	 * 購物車總價
	 */
	private BigDecimal cartTotalPrice;

	/**
	 * 建立時間
	 */
	private Date cartCreateTime;

	public void addItem(Commodity commodity, int quantity) {
        CartItem item = cartItems.stream()
                .filter(i -> i.getCommodity().getCommodityId().equals(commodity.getCommodityId()))
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
        cartItems.removeIf(item -> item.getCommodity().getCommodityId().equals(commodity.getCommodityId()));
    }

	public void calculateTotalPrice() {
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem item : this.cartItems) {
			BigDecimal itemPrice = item.getCommodity().getCommodityPrice();
			BigDecimal itemQuantity = new BigDecimal(item.getCheckedQuantity());
			total = total.add(itemPrice.multiply(itemQuantity));
		}
		this.setCartTotalPrice(total);
	}

}
