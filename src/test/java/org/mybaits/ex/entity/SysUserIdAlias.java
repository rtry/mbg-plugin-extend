package org.mybaits.ex.entity;

/**
 * 类描述: sys_user_id_alias表的实体类，请勿修改<br>
 * 创建者: 由 MBG(mybatis generator plug) 生成<br>
 * 创建时间: 2020-11-26<br>
 * @version 0.0.3
 */
public class SysUserIdAlias {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String headImage;

    /**
     * 手机
     */
    private String phone;

    /**
     * 性别 0-女 1-男
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;

    /**
     * getUserId: 获取 用户id
     * @return user_id 
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * setUserId: 设置 用户id
     * @param userId 用户id 
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * getUserName: 获取 用户名称
     * @return user_name 
     */
    public String getUserName() {
        return userName;
    }

    /**
     * setUserName: 设置 用户名称
     * @param userName 用户名称 
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * getHeadImage: 获取 用户头像
     * @return head_image 
     */
    public String getHeadImage() {
        return headImage;
    }

    /**
     * setHeadImage: 设置 用户头像
     * @param headImage 用户头像 
     */
    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    /**
     * getPhone: 获取 手机
     * @return phone 
     */
    public String getPhone() {
        return phone;
    }

    /**
     * setPhone: 设置 手机
     * @param phone 手机 
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * getSex: 获取 性别 0-女 1-男
     * @return sex 
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * setSex: 设置 性别 0-女 1-男
     * @param sex 性别 0-女 1-男 
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * getAddress: 获取 地址
     * @return address 
     */
    public String getAddress() {
        return address;
    }

    /**
     * setAddress: 设置 地址
     * @param address 地址 
     */
    public void setAddress(String address) {
        this.address = address;
    }
}