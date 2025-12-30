package com.maddog.articket.ticket.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 票券
 */
@Getter
@Setter
public class Ticket implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 票券 ID
	 */
	private Integer ticketId;

	/**
	 * 一般會員(擁有者)ID
	 */
	private Integer memberId;

	/**
	 * 座位狀態 ID
	 */
	private Integer seatStatusId;

	/**
	 * 活動區域價格 ID
	 */
	private Integer activityAreaPriceId;

	/**
	 * 票券訂單 ID
	 */
	private Integer bookTicketId;

	/**
	 * 活動時段 ID
	 */
	private Integer activityTimeSlotId;

	@Override
	public String toString() {
		return "Ticket [ticketID=" + ticketId + ", memberId()=" + memberId + ", seatStatusID=" + seatStatusId
				+ ", activityAreaPriceID=" + activityAreaPriceId + ", bookTicketID=" + bookTicketId + ", activityTimeSlotID="
				+ activityTimeSlotId + "]";
	}

}
