package com.maddog.articket.generalmember.dao;

import com.maddog.articket.generalmember.dto.GeneralMemberQueryCondition;
import com.maddog.articket.generalmember.entity.GeneralMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 會員 DAO
 */
@Mapper
public interface GeneralMemberDao {

	/**
	 * 新增
	 *
	 * @param generalMember
	 * 			新增的會員
	 */
	int insert(GeneralMember generalMember);

	/**
	 * 更新
	 *
	 * @param generalMember
	 * 			更新的會員
	 */
	int update(GeneralMember generalMember);

	/**
	 * 刪除
	 *
	 * @param memberId
	 * 			會員編號
	 * @return 成功筆數
	 */
	int deleteById(int memberId);

	/**
	 * 依 ID 查詢
	 *
	 * @param memberId
	 * 			會員編號
	 * @return 會員
	 */
	GeneralMember findById(Integer memberId);

	/**
	 * 查詢所有 (依創建時間排序)
	 *
	 * @return 會員列表
	 */
	List<GeneralMember> findAll();

	/**
	 * 依條件查詢 (依創建時間排序)
	 *
	 * @param condition
	 * 			查詢條件
	 * @return 會員列表
	 */
	List<GeneralMember> findByCondition(GeneralMemberQueryCondition condition);

	/**
	 * 依帳號查詢
	 *
	 * @param memberAccount
	 * 			會員帳號
	 * @return 會員
	 */
	GeneralMember findByMemberAccount(String memberAccount);
	
}
