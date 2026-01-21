package com.maddog.articket.generalmember.dao;

import com.maddog.articket.generalmember.entity.GeneralMember;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GeneralMemberDao {

	@Query(value = "delete GeneralMember where memberID =?1", nativeQuery = true)
	void deleteByMemberID(int memberID);

	GeneralMember findByMemberAccount(String memberAccount);
	
}
