/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package com.maddog.articket.hibernate.util.compositequery;

//import hibernate.util.HibernateUtil;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.board.entity.Board;
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

public class HibernateUtil_CompositeQuery_Article3 {

	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<Article> root, String columnName, String value) {

		Predicate predicate = null;

		if ("articleID".equals(columnName) || "articleStatus".equals(columnName)) // 用於Integer
			predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
		
		else if ("articleCategory".equals(columnName) || "articleTitle".equals(columnName) ||  "articleContent".equals(columnName)) // 用於varchar
			predicate = builder.like(root.get(columnName), "%" + value + "%");
		
		else if ("articleCreateTime".equals(columnName)) // 用於date
			predicate = builder.equal(root.get(columnName), java.sql.Date.valueOf(value));
		
		else if ("memberID".equals(columnName)) {
		    GeneralMember generalMember = new GeneralMember();
		    generalMember.setMemberID(Integer.valueOf(value));
		    predicate = builder.equal(root.get("generalMember"), generalMember);
		    
		} else if ("boardID".equals(columnName)) {
		    Board board = new Board();
		    board.setBoardID(Integer.valueOf(value));
		    predicate = builder.equal(root.get("board"), board);
		}

		return predicate;
	}
	

	@SuppressWarnings("unchecked")
	public static List<Article> getAllC(Map<String, String[]> map, Session session) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<Article> list = null;
		try {
			
			System.out.println("Received map in getAllC: " + map);
			
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<Article> criteriaQuery = builder.createQuery(Article.class);
			// 【●創建 Root】
			Root<Article> root = criteriaQuery.from(Article.class);

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
			criteriaQuery.orderBy(builder.asc(root.get("articleID")));
			// 【●最後完成創建 javax.persistence.Query●】
			Query query = session.createQuery(criteriaQuery); //javax.persistence.Query; //Hibernate 5 開始 取代原 org.hibernate.Query 介面
			list = query.getResultList();
			
			
	        // 檢查查詢結果的代碼
	        System.out.println("Query result size: " + (list != null ? list.size() : "null"));
	        if (list != null) {
	            for (Article article : list) {
	                System.out.println("Found article: " + article.getArticleTitle());
	            }
	        }

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
