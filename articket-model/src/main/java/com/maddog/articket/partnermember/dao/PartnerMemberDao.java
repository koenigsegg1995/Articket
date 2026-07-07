package com.maddog.articket.partnermember.dao;

import com.maddog.articket.partnermember.dto.PartnerMemberQueryCondition;
import com.maddog.articket.partnermember.entity.PartnerMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 廠商會員 DAO
 */
@Mapper
public interface PartnerMemberDao {

	/**
	 * 新增
	 *
	 * @param partnerMember
	 * 			廠商會員
	 * @return 成功筆數
	 */
	int insert(PartnerMember partnerMember);

	/**
	 * 更新
	 *
	 * @param partnerMember
	 * 			廠商會員
	 * @return 成功筆數
	 */
	int update(PartnerMember partnerMember);

	/**
	 * 刪除
	 *
	 * @param partnerId
	 * 			廠商會員 ID
	 * @return 成功筆數
	 */
	int deleteById(Integer partnerId);

	/**
	 * 依廠商會員 ID 查詢
	 * @param partnerId
	 * 			廠商會員 ID
	 * @return 廠商會員
	 */
	PartnerMember findById(Integer partnerId);

	/**
	 * 查詢全部
	 *
	 * @return 廠商會員清單
	 */
	List<PartnerMember> findAll();

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
	PartnerMember findByTaxId(String taxId);

}
