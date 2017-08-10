package com.common.entity;

import java.io.Serializable;
import java.util.Date;

public class UserRole extends Base implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_role.role_id
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private Integer roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_role.role_name
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private String roleName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_role.role_remark
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private String roleRemark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_role.create_time
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_role.update_time
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user_role
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_role.role_id
     *
     * @return the value of user_role.role_id
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_role.role_id
     *
     * @param roleId the value for user_role.role_id
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_role.role_name
     *
     * @return the value of user_role.role_name
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_role.role_name
     *
     * @param roleName the value for user_role.role_name
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_role.role_remark
     *
     * @return the value of user_role.role_remark
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public String getRoleRemark() {
        return roleRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_role.role_remark
     *
     * @param roleRemark the value for user_role.role_remark
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark == null ? null : roleRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_role.create_time
     *
     * @return the value of user_role.create_time
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_role.create_time
     *
     * @param createTime the value for user_role.create_time
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_role.update_time
     *
     * @return the value of user_role.update_time
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_role.update_time
     *
     * @param updateTime the value for user_role.update_time
     *
     * @mbg.generated Tue Aug 01 19:04:01 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}