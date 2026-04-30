package com.maddog.articket.partnermember.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 廠商會員查詢條件 DTO
 */
@Getter
@Setter
public class PartnerMemberQueryCondition implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 廠商編號
     */
    private Integer partnerId;

    /**
     * 廠商地址
     */
    private String partnerAddress;

    /**
     * 統一編號
     */
    private Integer taxId;

}
