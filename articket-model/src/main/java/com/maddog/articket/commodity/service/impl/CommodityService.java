package com.maddog.articket.commodity.service.impl;

import com.maddog.articket.activity.dao.ActivityDao;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.commodity.dao.CommodityRepository;
import com.maddog.articket.commodity.entity.Commodity;
import com.maddog.articket.commoditypicture.entity.CommodityPicture;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service("commodityService")
public class CommodityService {

    @Autowired
    CommodityRepository repository;

    @Autowired
    private ActivityDao activityDao;
    
    @Autowired
    private SessionFactory sessionFactory;

    public void addCommodity(Commodity commodity) {
        repository.save(commodity);
    }

    public void updateCommodity(Commodity commodity) {
        repository.save(commodity);
    }

    public void deleteCommodity(Integer commodityID) {
        if (repository.existsById(commodityID))
            repository.deleteByCommodityID(commodityID);
    }

    public Commodity getOneCommodity(Integer commodityID) {
        Optional<Commodity> optional = repository.findById(commodityID);
        return optional.orElse(null);
        
    }
    
    public Activity getActivityById(Integer id) {
        return repository.findById(id)
                .map(Commodity::getActivity)
                .orElse(null);
    }

    public List<Commodity> getAll() {
        return repository.findAll();
    }
    
//    public List<Activity> getAllactivity() {
//        return activityRepository.findAll();
//    }

   
    
  public List<Commodity> getCommoditiesByActivity(Integer activityID) {
  return repository.findByActivity_ActivityID(activityID);
}
  
//  public Page<Activity> getActivity(Integer activityID,PageRequest pageRequest){
//	  return repository.findActivity(activityID, pageRequest);
//  }
//    
  public Optional<Commodity> getActivityByCommodityId(Integer commodityId) {
	    Commodity commodity = repository.findById(commodityId).orElseThrow();
	    return repository.findById(commodity.getActivity().getActivityId());
	}


//    複合查詢
//    public List<Commodity> getAll(Map<String, String[]> map) {
//        return HibernateUtil_CompositeQuery_Commodity3.getAllC(map, sessionFactory.openSession());
//    }

    public Set<CommodityPicture> getCommodityPicturesByCommodityID(Integer commodityID) {
        return getOneCommodity(commodityID).getCommodityPictures();
    }

    public List<Activity> getAllActivities() {
        return repository.findAllDistinctActivities();
    }
    
//    public List<Activity> getAllActivitiesPicture() {
//        return repository.findAllDistinctActivitiesPicture();
//    }


//    public List<Activity> getActivitiesByPartner(Integer partnerID){
//        return repository.findByPartnerMember_PartnerID(partnerID);
//    }

    public List<Activity> getActivitiesByPartnerMember(Integer partnerMemberID) {
        List<Activity> activitiesFromCommodities = repository.findActivitiesByPartnerMemberID(partnerMemberID);
        List<Activity> allActivities = repository.findAllActivitiesByPartnerMemberID(partnerMemberID);

        // 合併兩個列表並去重
        Set<Activity> uniqueActivities = new HashSet<>(activitiesFromCommodities);
        uniqueActivities.addAll(allActivities);

        return new ArrayList<>(uniqueActivities);

//        return repository.findActivitiesByPartnerMemberID(partnerMemberID);
    }

    public boolean isActivityOwnedByPartner(Integer activityID, Integer partnerID) {
         return activityDao.isActivityOwnedByPartner(activityID, partnerID);
    }

//    public Page<Commodity> getCommoditiesByActivityPaginated(Integer activityID, Pageable pageable) {
//        return repository.findByActivityActivityID(activityID, pageable);
//    }
    
    public Page<Commodity> getCommoditiesByActivityPaginated(Integer activityID, Pageable pageable) {
        return repository.findByActivity_ActivityID(activityID, pageable);
    }
    
    public Page<Activity> getAllActivitiesPaginated(Pageable pageable) {
//        return activityRepository.findAll(pageable);
        return new Page<Activity>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super Activity, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<Activity> getContent() {
                return List.of();
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<Activity> iterator() {
                return null;
            }
        };
    }


//    public List<Activity> getActivitiesByPartnerMember(Integer partnerID) {
//        return repository.findByPartnerMember_PartnerMemberID(partnerID);
//    }

//    public void addCommodity(Commodity commodity, Integer activityID, Integer partnerID) {
//        Activity activity = ActivityRepository.findById(activityID).orElseThrow(() -> new RuntimeException("Activity not found"));
//        PartnerMember partnerMember = PartnerMemberRepository.findById(partnerID).orElseThrow(() -> new RuntimeException("PartnerMember not found"));
//
//        commodity.setActivity(activity);
//        commodity.setPartnerMember(partnerMember);
//
//        repository.save(commodity);
//    }

    // 可以根據需求添加其他方法，例如：
    // public List<Commodity> getCommoditiesByStatus(Integer status) {
    //     return repository.findByCommodityStatus(status);
    // }

    // public List<Commodity> getCommoditiesByPartner(Integer partnerID) {
    //     return repository.findByPartnerMember_PartnerID(partnerID);
    // }
}