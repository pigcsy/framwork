package com.domain.oauth;

import lombok.Data;


@Data
public class OauthSystemVo {
    private Integer systemId;
    private String systemName;
    private String systemRemark;
    private String clientId;
    private Integer systemType;
    private Integer dailyAccessNum;
    private Integer minuteAccessNum;

}
