package com.maddog.articket.venuearea.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 場館區域 DO
 */
@Getter
@Setter
public class VenueArea implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 場館區域ID
	 */
	private Integer venueAreaId;

	/**
	 * 場館ID
	 */
	private Integer venueId;

	/**
	 * 區域代號 如NA, NB, NC, MA, MB, MC, SA, SB, SC
	 */
	private String venueAreaName;

}
