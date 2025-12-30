package com.maddog.articket.administrator.service.pri;

import com.maddog.articket.administrator.entity.Administrator;

/**
 * 管理員 Service Interface
 */
public interface AdministratorService {

	/**
	 * 依帳號查詢管理員
	 *
	 * @param administratorAccount
	 * 			String
	 * @return 管理員帳號
	 * 			Administrator
	 */
	Administrator getAdministratorAccount(String administratorAccount);

}
