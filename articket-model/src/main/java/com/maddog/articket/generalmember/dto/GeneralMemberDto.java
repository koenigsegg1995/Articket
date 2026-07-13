package com.maddog.articket.generalmember.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 一般會員新增 / 更新 DTO
 */
@Getter
@Setter
public class GeneralMemberDto {

    /**
     * 會員 ID
     */
    private Integer memberId;

    /**
     * 姓名
     */
    private String memberName;

    /**
     * 電話
     */
    private String memberPhone;

    /**
     * 地址
     */
    private String memberAddress;

    /**
     * 帳號
     */
    private String memberAccount;

    /**
     * 身分證字號
     */
    private String nationalId;

    /**
     * 暱稱
     */
    private String memberNickName;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性別
     */
    private String gender;

}
