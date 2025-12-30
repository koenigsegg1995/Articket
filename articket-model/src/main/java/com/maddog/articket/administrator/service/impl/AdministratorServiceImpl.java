package com.maddog.articket.administrator.service.impl;

import com.maddog.articket.administrator.dao.AdministratorDao;
import com.maddog.articket.administrator.entity.Administrator;
import com.maddog.articket.administrator.service.pri.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理員 Service Implementation
 */
@Service("administratorService")
public class AdministratorServiceImpl implements AdministratorService {

	/**
	 * 管理員 DAO
	 */
	@Autowired
	private AdministratorDao administratorDao;

	/**
	 * 依帳號查詢管理員
	 *
	 * @param administratorAccount
	 * 			String
	 * @return 管理員帳號
	 * 			Administrator
	 */
	@Override
	@Transactional(readOnly = true)
	public Administrator getAdministratorAccount(String administratorAccount) {
		return administratorDao.findByAdministratorAccount(administratorAccount);
	}

}
