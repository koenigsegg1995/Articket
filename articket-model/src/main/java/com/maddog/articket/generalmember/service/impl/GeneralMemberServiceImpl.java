package com.maddog.articket.generalmember.service.impl;

import com.maddog.articket.email.pri.MailService;
import com.maddog.articket.generalmember.dao.GeneralMemberDao;
import com.maddog.articket.generalmember.dto.GeneralMemberQueryCondition;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.pri.GeneralMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * 會員 Service Implementation
 */
@Service("generalMemberService")
public class GeneralMemberServiceImpl implements GeneralMemberService {

	/**
	 * 會員 DAO
	 */
	@Autowired
	private GeneralMemberDao generalMemberDao;

	/**
	 * 寄送 Email Service
	 */
	@Autowired
	private MailService mailService;

	/**
	 * 新增
	 *
	 * @param generalMember
	 * 			新增的會員
	 * @return 成功筆數
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int add(GeneralMember generalMember) {
		return generalMemberDao.insert(generalMember);
	}

	/**
	 * 更新
	 *
	 * @param generalMember
	 * 			更新的會員
	 * @return 成功筆數
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int update(GeneralMember generalMember) {
		return generalMemberDao.update(generalMember);
	}

	/**
	 * 刪除
	 *
	 * @param memberId
	 * 			會員編號
	 * @return 成功筆數
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int deleteById(Integer memberId) {
		return generalMemberDao.deleteById(memberId);
	}

	/**
	 * 依 ID 查詢
	 *
	 * @param memberId
	 * 			會員編號
	 * @return 會員
	 */
	@Override
	@Transactional(readOnly = true)
	public GeneralMember getById(Integer memberId) {
		return generalMemberDao.findById(memberId);
	}

	/**
	 * 查詢所有
	 *
	 * @return 會員列表
	 */
	@Override
	@Transactional(readOnly = true)
	public List<GeneralMember> getAll() {
		return generalMemberDao.findAll();
	}

	/**
	 * 依條件查詢
	 *
	 * @param condition
	 * 			查詢條件
	 * @return 會員列表
	 */
	@Override
	@Transactional(readOnly = true)
	public List<GeneralMember> getByCondition(GeneralMemberQueryCondition condition) {
		return generalMemberDao.findByCondition(condition);
	}

	/**
	 * 依帳號查詢
	 *
	 * @param memberAccount
	 * 			會員帳號
	 * @return 會員
	 */
	@Override
	@Transactional(readOnly = true)
	public GeneralMember getByMemberAccount(String memberAccount) {
		return generalMemberDao.findByMemberAccount(memberAccount);
	}

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
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int changePassword(Integer memberId, String currentPassword, String newPassword) {
		GeneralMember member = generalMemberDao.findById(memberId);
		if (!member.getMemberPassword().equals(currentPassword)) {
			throw new RuntimeException("當前密碼不正確");
		}

		GeneralMember memberForUpdate = new GeneralMember();
		memberForUpdate.setMemberId(memberId);
		memberForUpdate.setMemberPassword(newPassword);

		return generalMemberDao.update(memberForUpdate);
	}

	/**
	 * 忘記密碼
	 *
	 * @param email
	 * 			會員 Email
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void sendRecoveryEmail(String email) {
		GeneralMember member = generalMemberDao.findByMemberAccount(email);
		if (member == null) {
			throw new RuntimeException("找不到該郵箱對應的用戶");
		}

		String tempPassword = generateTempPassword();
		GeneralMember memberForUpdate = new GeneralMember();
		memberForUpdate.setMemberId(member.getMemberId());
		memberForUpdate.setMemberPassword(tempPassword);
		generalMemberDao.update(memberForUpdate);

		String subject = "密碼重置";
		String messageText = "親愛的會員，\n\n您的臨時密碼是：" + tempPassword + "\n\n請盡快登入並修改您的密碼。";

		mailService.sendMail(email, subject, messageText);
	}

	/**
	 * 生成臨時密碼
	 *
	 * @return
	 */
	private String generateTempPassword() {
		// 生成隨機臨時密碼的邏輯
		return "TempPass" + new Random().nextInt(10000);
	}

}
