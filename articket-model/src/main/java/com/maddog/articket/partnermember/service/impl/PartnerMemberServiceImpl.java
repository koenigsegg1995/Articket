package com.maddog.articket.partnermember.service.impl;

import com.maddog.articket.partnermember.dao.PartnerMemberDao;
import com.maddog.articket.partnermember.dto.PartnerMemberQueryCondition;
import com.maddog.articket.partnermember.entity.PartnerMember;
import com.maddog.articket.partnermember.service.pri.PartnerMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 廠商會員 Service Implementation
 */
@Service("partnerMemberService")
public class PartnerMemberServiceImpl implements PartnerMemberService {

	/**
	 * 廠商會員 DAO
	 */
	@Autowired
	private PartnerMemberDao dao;

	/**
	 * 新增
	 *
	 * @param partnerMember
	 * 			廠商會員
	 * @return 成功筆數
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int addPartnerMember (PartnerMember partnerMember) {
		return dao.insert(partnerMember);
	}

	/**
	 * 更新
	 *
	 * @param partnerMember
	 * 			廠商會員
	 * @return 成功筆數
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updatePartnerMember (PartnerMember partnerMember) {
		return dao.update(partnerMember);
	}

	/**
	 * 刪除
	 *
	 * @param partnerId
	 * 			廠商會員 ID
	 * @return 成功筆數
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int deletePartnerMember(Integer partnerId) {
		return dao.deleteById(partnerId);
	}

	/**
	 * 依廠商會員 ID 查詢
	 * @param partnerId
	 * 			廠商會員 ID
	 * @return 廠商會員
	 */
	@Override
	@Transactional(readOnly = true)
	public PartnerMember getOnePartnerMember(Integer partnerId) {
		return dao.findById(partnerId);
	}

	/**
	 * 查詢全部
	 *
	 * @return 廠商會員清單
	 */
	@Override
	@Transactional(readOnly = true)
	public List<PartnerMember> getAll(){
		return dao.findAll();
	}

	/**
	 * 依條件查詢
	 *
	 * @param condition
	 * 			查詢條件
	 * @return 廠商會員清單
	 */
	@Override
	@Transactional(readOnly = true)
	public List<PartnerMember> findByCondition(PartnerMemberQueryCondition condition) {
		return dao.findByCondition(condition);
	}

	/**
	 * 依統一編號查詢
	 *
	 * @param taxId
	 * 			統一編號
	 * @return 廠商會員
	 */
	@Override
	@Transactional(readOnly = true)
	public PartnerMember getByTaxId(String taxId) {
		return dao.findByTaxID(taxId);
	}

}
