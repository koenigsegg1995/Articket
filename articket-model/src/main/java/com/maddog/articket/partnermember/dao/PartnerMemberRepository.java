package com.maddog.articket.partnermember.dao;

import com.maddog.articket.partnermember.entity.PartnerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PartnerMemberRepository extends JpaRepository<PartnerMember, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete PartnerMember where partnerID =?1", nativeQuery = true)
	void deleteBypartnerID(int partnerID);

	PartnerMember findByTaxID(String taxID);
}
