package com.maddog.articket.cartitem.dao;

import com.maddog.articket.cartitem.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 購物車明細 DAO
 */
@Mapper
public interface CartItemDao {

	/**
	 * 新增
	 *
	 * @param cartItem
	 * 			購物車明細
	 * @return 成功筆數
	 */
	int insert(CartItem cartItem);

	/**
	 * 更新
	 *
	 * @param cartItem
	 * 			購物車明細
	 * @return 成功筆數
	 */
	int update(CartItem cartItem);

	/**
	 * 刪除
	 *
	 * @param cartItemId
	 * 			購物車明細ID
	 * @return 成功筆數
	 */
	int deleteById(Integer cartItemId);

	/**
	 * 依購物車明細ID查詢
	 *
	 * @param cartItemId
	 * 			購物車明細ID
	 * @return 購物車明細
	 */
	CartItem findById(Integer cartItemId);

	/**
	 * 依購物車ID查詢
	 *
	 * @param cartId
	 * 			購物車ID
	 * @return 購物明細清單
	 */
    List<CartItem> findByCartId(Integer cartId);

}
