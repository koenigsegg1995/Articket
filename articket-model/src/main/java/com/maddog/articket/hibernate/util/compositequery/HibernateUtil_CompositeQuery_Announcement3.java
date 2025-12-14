package com.maddog.articket.hibernate.util.compositequery;

import com.maddog.articket.announcement.model.entity.Announcement;
import com.maddog.articket.administrator.entity.Administrator;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HibernateUtil_CompositeQuery_Announcement3 {

    public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<Announcement> root, String columnName, String value) {

        Predicate predicate = null;

        if ("announcementID".equals(columnName) || "announcementStatus".equals(columnName)) // 用於Integer
            predicate = builder.equal(root.get(columnName), Integer.valueOf(value));

        else if ("announcementTitle".equals(columnName) || "announcementContent".equals(columnName)) // 用於varchar
            predicate = builder.like(root.get(columnName), "%" + value + "%");

        else if ("announcementCreateTime".equals(columnName)) // 用於date
            predicate = builder.equal(root.get(columnName), java.sql.Date.valueOf(value));

        else if ("administratorID".equals(columnName)) {
            Administrator administrator = new Administrator();
            administrator.setAdministratorID(Integer.valueOf(value));
            predicate = builder.equal(root.get("administrator"), administrator);
        }

        return predicate;
    }

    @SuppressWarnings("unchecked")
    public static List<Announcement> getAllC(Map<String, String[]> map, Session session) {

        Transaction tx = session.beginTransaction();
        List<Announcement> list = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Announcement> criteriaQuery = builder.createQuery(Announcement.class);
            Root<Announcement> root = criteriaQuery.from(Announcement.class);

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
            criteriaQuery.orderBy(builder.asc(root.get("announcementID")));
            Query query = session.createQuery(criteriaQuery);
            list = query.getResultList();

            tx.commit();
        } catch (RuntimeException ex) {
            if (tx != null)
                tx.rollback();
            throw ex;
        } finally {
            session.close();
        }

        return list;
    }
}