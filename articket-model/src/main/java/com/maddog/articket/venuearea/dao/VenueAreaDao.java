package com.maddog.articket.venuearea.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * 場館區域 DAO
 */
@Mapper
public interface VenueAreaDao {

    /**
     * 依場館 ID 和區域名稱查詢場館區域 ID
     *
     * @param venueId
     * 			場館 ID
     * @param venueAreaName
     * 			區域代號
     * @return 場館區域 ID
     */
    Integer findVenueAreaIdByVenueIdAndVenueAreaName(Integer venueId, String venueAreaName);

}
