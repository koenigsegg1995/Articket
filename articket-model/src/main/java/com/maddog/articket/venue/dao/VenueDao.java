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
     * 查全部
     *
     * @return 場館清單
     */
    List<Venue> findAll();

}
