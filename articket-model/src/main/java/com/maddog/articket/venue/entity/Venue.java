package com.maddog.articket.venue.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 場館 DO
 */
@Getter
@Setter
public class Venue implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 場館ID
	 */
	private Integer venueId;

	/**
	 * 場館名稱
	 */
	private String venueName;

	/**
	 * 電話
	 */
	private String venuePhone;

	/**
	 * 聯絡人
	 */
	private String venueContactPerson;

	/**
	 * 地址
	 */
	private String venueAddress;

	/**
	 * 地區 如北區、中區、南區
	 */
	private String venueLocation;

}
