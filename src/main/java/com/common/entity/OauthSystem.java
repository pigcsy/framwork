package com.common.entity;

import java.io.Serializable;
import java.util.Date;

public class OauthSystem extends Base implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oauth_system
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_system.system_id
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private Integer systemId;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_system.system_name
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private String systemName;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_system.system_remark
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private String systemRemark;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_system.client_id
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private String clientId;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_system.system_type
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private Integer systemType;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_system.create_time
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private Date createTime;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_system.update_time
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private Date updateTime;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_system.daily_access_num
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private Integer dailyAccessNum;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_system.minute_access_num
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private Integer minuteAccessNum;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_system.system_id
     *
     * @return the value of oauth_system.system_id
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public Integer getSystemId() {
        return systemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_system.system_id
     *
     * @param systemId the value for oauth_system.system_id
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_system.system_name
     *
     * @return the value of oauth_system.system_name
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_system.system_name
     *
     * @param systemName the value for oauth_system.system_name
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName == null ? null : systemName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_system.system_remark
     *
     * @return the value of oauth_system.system_remark
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public String getSystemRemark() {
        return systemRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_system.system_remark
     *
     * @param systemRemark the value for oauth_system.system_remark
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setSystemRemark(String systemRemark) {
        this.systemRemark = systemRemark == null ? null : systemRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_system.client_id
     *
     * @return the value of oauth_system.client_id
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_system.client_id
     *
     * @param clientId the value for oauth_system.client_id
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_system.system_type
     *
     * @return the value of oauth_system.system_type
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public Integer getSystemType() {
        return systemType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_system.system_type
     *
     * @param systemType the value for oauth_system.system_type
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_system.create_time
     *
     * @return the value of oauth_system.create_time
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_system.create_time
     *
     * @param createTime the value for oauth_system.create_time
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_system.update_time
     *
     * @return the value of oauth_system.update_time
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_system.update_time
     *
     * @param updateTime the value for oauth_system.update_time
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_system.daily_access_num
     *
     * @return the value of oauth_system.daily_access_num
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public Integer getDailyAccessNum() {
        return dailyAccessNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_system.daily_access_num
     *
     * @param dailyAccessNum the value for oauth_system.daily_access_num
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setDailyAccessNum(Integer dailyAccessNum) {
        this.dailyAccessNum = dailyAccessNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_system.minute_access_num
     *
     * @return the value of oauth_system.minute_access_num
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public Integer getMinuteAccessNum() {
        return minuteAccessNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_system.minute_access_num
     *
     * @param minuteAccessNum the value for oauth_system.minute_access_num
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setMinuteAccessNum(Integer minuteAccessNum) {
        this.minuteAccessNum = minuteAccessNum;
    }
}