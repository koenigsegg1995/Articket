package com.maddog.articket.generalmember.service.impl;

import com.maddog.articket.email.MailService;
import com.maddog.articket.generalmember.dao.GeneralMemberRepository;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.hibernate.util.compositequery.HibernateUtil_CompositeQuery_GeneralMember;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service("generalmemberService")
public class GeneralMemberService {

	@Autowired
	GeneralMemberRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private MailService mailService;

	public void addGeneralMember(GeneralMember generalMember) {
		repository.save(generalMember);
	}

	public void updateGeneralMember(GeneralMember generalMember) {
		repository.save(generalMember);
	}

	public void deleteGeneralMember(Integer memberID) {
		if (repository.existsById(memberID))
			repository.deleteByMemberID(memberID);
//			repository.deleteById(memberID);
	}

	public GeneralMember getOneGeneralMember(Integer memberID) {
		Optional<GeneralMember> optional = repository.findById(memberID);
//		return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<GeneralMember> getAll() {
		return repository.findAll();
	}

	public List<GeneralMember> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_GeneralMember.getAllC(map, sessionFactory.openSession());
	}

	public GeneralMember getByMemberAccount(String memberAccount) {
		return repository.findByMemberAccount(memberAccount);
	}

	public GeneralMember getById(Integer id) {
		return repository.findById(id).orElse(null); // 使用 JPA 獲取會員資料
	}

	// 會員修改密碼
	@Transactional
	public void changePassword(Integer memberID, String currentPassword, String newPassword) {
		GeneralMember member = repository.findById(memberID).orElseThrow(() -> new RuntimeException("會員不存在"));

		if (!member.getMemberPassword().equals(currentPassword)) {
			throw new RuntimeException("當前密碼不正確");
		}

		member.setMemberPassword(newPassword);
		repository.save(member);
	}

	// 可選：添加一個用於註冊的方法，直接保存明文密碼
	public void registerMember(GeneralMember member) {
		// 直接保存密碼，不進行加密
		repository.save(member);
	}

	// 可選：添加一個用於登錄的方法，直接比較明文密碼
	public GeneralMember login(String memberAccount, String password) {
		GeneralMember member = repository.findByMemberAccount(memberAccount);
		if (member == null) {
			throw new RuntimeException("會員不存在");
		}

		if (!member.getMemberPassword().equals(password)) {
			throw new RuntimeException("密碼不正確");
		}

		return member;
	}

	// 會員忘記密碼
	public void sendRecoveryEmail(String email) {
		GeneralMember member = repository.findByMemberAccount(email);
		if (member == null) {
			throw new RuntimeException("找不到該郵箱對應的用戶");
		}

		String tempPassword = generateTempPassword();
		member.setMemberPassword(tempPassword); // 注意：實際應用中應該加密這個密碼
		repository.save(member);

		String subject = "密碼重置";
		String messageText = "親愛的會員，\n\n您的臨時密碼是：" + tempPassword + "\n\n請盡快登入並修改您的密碼。";

		mailService.sendMail(email, subject, messageText);
	}

	private String generateTempPassword() {
		// 生成隨機臨時密碼的邏輯
		return "TempPass" + new Random().nextInt(10000);
	}

}
