package com.maddog.articket.venue.dao;

import com.maddog.articket.venue.entity.Venue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 場館 DAO
 */
@Mapper
public interface VenueDao {

    /**
     * 依場館 ID 查詢場館名稱
     *
     * @param venueId
     * 			場館 ID
     * @return 場館名稱
     */
    String getNameById(Integer venueId);

    /**
     * 查全部
     *
     * @return 場館清單
     */
    List<Venue> findAll();

}
