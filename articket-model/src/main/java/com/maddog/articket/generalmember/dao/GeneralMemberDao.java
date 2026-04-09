package com.maddog.articket.generalmember.dao;

import com.maddog.articket.generalmember.entity.GeneralMember;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GeneralMemberDao {

	int save(GeneralMember generalMember);

	int update(GeneralMember generalMember);

	@Query(value = "delete GeneralMember where memberID =?1", nativeQuery = true)
	void deleteById(int memberId);

	GeneralMember findById(Integer memberId);

	List<GeneralMember> findAll();

	List<GeneralMember> findByCondition();

	GeneralMember findByMemberAccount(String memberAccount);
	
}
