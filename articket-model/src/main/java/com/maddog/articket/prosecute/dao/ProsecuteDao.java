package com.maddog.articket.prosecute.dao;

import com.maddog.articket.prosecute.entity.Prosecute;
import org.apache.ibatis.annotations.Mapper;

/**
 * 檢舉 DAO
 */
@Mapper
public interface ProsecuteDao {

	/**
	 * 新增
	 *
	 * @param prosecute
	 * 			檢舉
	 * @return 成功筆數
	 */
	int insert(Prosecute prosecute);

	/**
	 * 查詢全部
	 *
	 * @return 檢舉清單
	 */
	Prosecute findAll();

}