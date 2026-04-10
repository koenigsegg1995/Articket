package com.maddog.articket.generalmember.service.pri;

import com.maddog.articket.generalmember.dto.GeneralMemberQueryCondition;
import com.maddog.articket.generalmember.entity.GeneralMember;

import java.util.List;

/**
 * 會員 Service Interface
 */
public interface GeneralMemberService {

	/**
	 * 新增
	 *
	 * @param generalMember
	 * 			新增的會員
	 * @return 成功筆數
	 */
	int add(GeneralMember generalMember);

	/**
	 * 更新
	 *
	 * @param generalMember
	 * 			更新的會員
	 * @return 成功筆數
	 */
	int update(GeneralMember generalMember);

	/**
	 * 刪除
	 *
	 * @param memberId
	 * 			會員編號
	 * @return 成功筆數
	 */
	int deleteById(Integer memberId);

	/**
	 * 依 ID 查詢
	 *
	 * @param memberId
	 * 			會員編號
	 * @return 會員
	 */
	GeneralMember getById(Integer memberId);

	/**
	 * 查詢所有
	 *
	 * @return 會員列表
	 */
	List<GeneralMember> getAll();

	/**
	 * 依條件查詢
	 *
	 * @param condition
	 * 			查詢條件
	 * @return 會員列表
	 */
	List<GeneralMember> getByCondition(GeneralMemberQueryCondition condition);

	/**
	 * 依帳號查詢
	 *
	 * @param memberAccount
	 * 			會員帳號
	 * @return 會員
	 */
	GeneralMember getByMemberAccount(String memberAccount);

	/**
	 * 修改密碼
	 *
	 * @param memberId
	 * 			會員編號
	 * @param currentPassword
	 * 			當前密碼
	 * @param newPassword
	 * 			新密碼
	 * @return 成功筆數
	 */
	int changePassword(Integer memberId, String currentPassword, String newPassword);

	/**
	 * 忘記密碼
	 *
	 * @param email
	 * 			會員 Email
	 */
	void sendRecoveryEmail(String email);

}
