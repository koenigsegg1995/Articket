// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/


package com.maddog.articket.generalmember.dao;

import com.maddog.articket.generalmember.entity.GeneralMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GeneralMemberRepository extends JpaRepository<GeneralMember, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete GeneralMember where memberID =?1", nativeQuery = true)
	void deleteByMemberID(int memberID);
	
	
	GeneralMember findByMemberAccount(String memberAccount);
	
}
