package com.maddog.articket.venuetimeslot.entity;

import java.io.Serial;
import java.util.Date;

/**
 * 場館時段
 */
public class VenueTimeSlot implements java.io.Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 場館時段 ID
	 */
	private Integer venueTimeSlotId;

	/**
	 * 場地申請資料 ID
	 */
	private Integer venueRentalId;

	/**
	 * 日期
	 */
	private Date venueTimeSlotDate;

	/**
	 * 時段 1:早 2:午 3:晚
	 */
	private Integer venueTimeSlot;

	/**
	 * 時段狀態 0: 未被租借 1: 已被租借 2: 不提供租借 3: 活動
	 */
	private Integer venueTimeSlotStatus;

	/**
	 *
	 * @return venueTimeSlotId
	 * 			Integer
	 */
	public Integer getVenueTimeSlotId() {
		return venueTimeSlotId;
	}

	/**
	 *
	 * @param venueTimeSlotId
	 * 			Integer
	 */
	public void setVenueTimeSlotId(Integer venueTimeSlotId) {
		this.venueTimeSlotId = venueTimeSlotId;
	}

	/**
	 *
	 * @return venueRentalId
	 * 			Integer
	 */
	public Integer getVenueRentalId() {
		return venueRentalId;
	}

	/**
	 *
	 * @param venueRentalId
	 * 			Integer
	 */
	public void setVenueRentalId(Integer venueRentalId) {
		this.venueRentalId = venueRentalId;
	}

	/**
	 *
	 * @return venueTimeSlotDate
	 * 			Date
	 */
	public Date getVenueTimeSlotDate() {
		return venueTimeSlotDate;
	}

	/**
	 *
	 * @param venueTimeSlotDate
	 * 			Date
	 */
	public void setVenueTimeSlotDate(Date venueTimeSlotDate) {
		this.venueTimeSlotDate = venueTimeSlotDate;
	}

	/**
	 *
	 * @return venueTimeSlot
	 * 			Integer
	 */
	public Integer getVenueTimeSlot() {
		return venueTimeSlot;
	}

	/**
	 *
	 * @param venueTimeSlot
	 * 			Integer
	 */
	public void setVenueTimeSlot(Integer venueTimeSlot) {
		this.venueTimeSlot = venueTimeSlot;
	}

	/**
	 *
	 * @return venueTimeSlotStatus
	 * 			Integer
	 */
	public Integer getVenueTimeSlotStatus() {
		return venueTimeSlotStatus;
	}

	/**
	 *
	 * @param venueTimeSlotStatus
	 * 			Integer
	 */
	public void setVenueTimeSlotStatus(Integer venueTimeSlotStatus) {
		this.venueTimeSlotStatus = venueTimeSlotStatus;
	}

}
