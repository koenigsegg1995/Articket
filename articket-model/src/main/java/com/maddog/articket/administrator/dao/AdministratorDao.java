package com.maddog.articket.administrator.dao;

import com.maddog.articket.administrator.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 管理員 DAO
 */
@Mapper
public interface AdministratorDao {

	/**
	 * 依帳號查詢管理員
	 *
	 * @param administratorAccount
	 * 			String
	 * @return 管理員帳號
	 * 			Administrator
	 */
	Administrator findByAdministratorAccount (String administratorAccount);

}
