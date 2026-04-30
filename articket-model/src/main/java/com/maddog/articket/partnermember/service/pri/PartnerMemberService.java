package com.maddog.articket.partnermember.service.pri;

import com.maddog.articket.partnermember.dto.PartnerMemberQueryCondition;
import com.maddog.articket.partnermember.entity.PartnerMember;

import java.util.List;

/**
 * 廠商會員 Service Interface
 */
public interface PartnerMemberService {

	/**
	 * 新增
	 *
	 * @param partnerMember
	 * 			廠商會員
	 * @return 成功筆數
	 */
	int addPartnerMember (PartnerMember partnerMember);

	/**
	 * 更新
	 *
	 * @param partnerMember
	 * 			廠商會員
	 * @return 成功筆數
	 */
	int updatePartnerMember (PartnerMember partnerMember);

	/**
	 * 刪除
	 *
	 * @param partnerId
	 * 			廠商會員 ID
	 * @return 成功筆數
	 */
	int deletePartnerMember(Integer partnerId);

	/**
	 * 依廠商會員 ID 查詢
	 * @param partnerId
	 * 			廠商會員 ID
	 * @return 廠商會員
	 */
	PartnerMember getOnePartnerMember(Integer partnerId);

	/**
	 * 查詢全部
	 *
	 * @return 廠商會員清單
	 */
	List<PartnerMember> getAll();

	/**
	 * 依條件查詢
	 *
	 * @param condition
	 * 			查詢條件
	 * @return 廠商會員清單
	 */
	List<PartnerMember> findByCondition(PartnerMemberQueryCondition condition);

	/**
	 * 依統一編號查詢
	 *
	 * @param taxId
	 * 			統一編號
	 * @return 廠商會員
	 */
	PartnerMember getByTaxId(String taxId);

}
