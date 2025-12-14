package com.maddog.articket.membercoupon.service.impl;

import com.maddog.articket.membercoupon.dao.MemberCouponRepository;
import com.maddog.articket.membercoupon.entity.MemberCoupon;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("membercouponService")
public class MemberCouponService {
	
	@Autowired
	MemberCouponRepository repository ;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public void addMemberCoupon(MemberCoupon membercoupon) {
		repository.save(membercoupon);
	}
	
	public void updateMemberCoupon(MemberCoupon membercoupon) {
		repository.save(membercoupon);
	}
	
//	public void deleteMemberCoupon(Integer memberCouponID) {
//	if (repository.existsById(memberCouponID))
//		repository.deleteByMemberCouponID(memberCouponID);
//		repository.deleteById(memberCouponID);
//}
	
	public MemberCoupon getOneMemberCoupon(Integer memberCouponID) {
		Optional<MemberCoupon> optional = repository.findById(memberCouponID);
//		return optional.get();
		return optional.orElse(null);
	}
	
	public List<MemberCoupon> getAll(){
		return repository.findAll();
	}
}
