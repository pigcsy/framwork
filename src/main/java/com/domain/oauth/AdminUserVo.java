package com.domain.oauth;

import lombok.Data;

@Data
public class AdminUserVo {
    private Integer userId;
    private String code;
    private String pwd;
    private Byte status;
    private String userName;
    private String userPhone;
    private Integer systemId;

    private int dailyAccessNum;
    private int minuteAccessNum;

}
