package com.maddog.articket.hibernate.util.compositequery;

import com.maddog.articket.partnermember.entity.PartnerMember;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HibernateUtil_CompositeQuery_partnerMember3 {

	
	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<PartnerMember> root, String columnName, String value) {

		Predicate predicate = null;

		if ("partnerID".equals(columnName)) // 用於Integer
			predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
		else if ("partnerAddress".equals(columnName) || "taxID".equals(columnName)) // 用於varchar
			predicate = builder.like(root.get(columnName), "%" + value + "%");
		
//		else if ("activityID".equals(columnName)) {
//			Activity activity = new Activity();
//			activity.setActivityID(Integer.valueOf(value));
//		    predicate = builder.equal(root.get("activity"), activity);
//		    
//		} else if ("commodityID".equals(columnName)) {
//		    Commodity commodity = new Commodity();
//		    commodity.setCommodityID(Integer.valueOf(value));
//		    predicate = builder.equal(root.get("commodity"), commodity);
//		    
//		} else if ("venueRentalID".equals(columnName)) {
//			VenueRental venueRental = new VenueRental();
//			venueRental.setVenueRentalID(Integer.valueOf(value));
//			predicate = builder.equal(root.get("venueRental"), venueRental);
//		}

		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<PartnerMember> getAllC(Map<String, String[]> map, Session session) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<PartnerMember> list = null;
		try {
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<PartnerMember> criteriaQuery = builder.createQuery(PartnerMember.class);
			// 【●創建 Root】
			Root<PartnerMember> root = criteriaQuery.from(PartnerMember.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();
			
			Set<String> keys = map.keySet();
			int count = 0;
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value.trim()));
					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			System.out.println("predicateList.size()="+predicateList.size());
criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.asc(root.get("partnerID")));
			// 【●最後完成創建 javax.persistence.Query●】
			Query query = session.createQuery(criteriaQuery); //javax.persistence.Query; //Hibernate 5 開始 取代原 org.hibernate.Query 介面
			list = query.getResultList();

			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex; // System.out.println(ex.getMessage());
		} finally {
			session.close();
			// HibernateUtil.getSessionFactory().close();
		}

		return list;
	}
}
