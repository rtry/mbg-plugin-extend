/**
 * FileName：SysUserIdSnowCRUD
 * Version：
 * Date：2020/11/26
 * Copyright 马丁洛克 Corporation 2020 版权所有
 */
package org.mybaits.ex.zrun.id;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mybaits.ex.entity.SysUserIdSnow;
import org.mybaits.ex.entity.SysUserIdSnowExample;
import org.mybaits.ex.mapper.SysUserIdSnowMapper;
import org.mybaits.ex.zrun.BaseRunApplication;

/**
 * @className SysUserIdSnowCRUD
 * @describe 主键由MYSQL 自增
 * @author rtry
 * @date 2020/11/26 10:40
 */
public class SysUserIdSnowCRUD extends BaseRunApplication {

    SysUserIdSnowMapper SysUserIdSnowMapper;

    @Before
    public void initMapper() {
        SysUserIdSnowMapper = super.getMapper(SysUserIdSnowMapper.class);
    }

    // 方法没有设置主键
    private SysUserIdSnow createSysUserWithSnowId(String phone) {
        SysUserIdSnow sysUser = new SysUserIdSnow();
        sysUser.setUserName("System");
        sysUser.setAddress("Address");
        sysUser.setHeadImage("Image");
        sysUser.setSex(1);
        sysUser.setPhone(phone);
        sysUser.setId(super.nextId());
        return sysUser;
    }

    /**
     * countByExample:通过Example计算总数
     * @param example 查询条件
     */
    @Test
    public void countByExample() {

        String phone = "18300001111";

        SysUserIdSnowExample example = new SysUserIdSnowExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowMapper.deleteByExample(example);

        SysUserIdSnow sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowMapper.insertSelective(sysUser);

        Long aLong = SysUserIdSnowMapper.countByExample(example);
        Assert.assertTrue(aLong == 1);
    }

    /**
     * deleteByExample:通过Example删除
     * @param example 刪除条件
     */
    @Test
    public void deleteByExample() {
        String phone = "18300001112";

        SysUserIdSnowExample example = new SysUserIdSnowExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowMapper.deleteByExample(example);

        SysUserIdSnow sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowMapper.insertSelective(sysUser);

        int i = SysUserIdSnowMapper.deleteByExample(example);
        Assert.assertEquals(1, i);

    }

    /**
     * deleteByPrimaryKey:通过主键删除
     * @param id 表的主键
     */
    @Test
    public void deleteByPrimaryKey() {
        String phone = "18300001113";

        SysUserIdSnowExample example = new SysUserIdSnowExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowMapper.deleteByExample(example);

        SysUserIdSnow sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowMapper.insertSelective(sysUser);

        Long id = sysUser.getId();
        int i = SysUserIdSnowMapper.deleteByPrimaryKey(id);
        Assert.assertEquals(1, i);

    }

    /**
     * insertSelective:有选择性的插入数据(空数据不会插入)
     * @param record 表对象
     */
    @Test
    public void insertSelective() {
        String phone = "18300001114";
        SysUserIdSnow sysUser = createSysUserWithSnowId(phone);
        int i = SysUserIdSnowMapper.insertSelective(sysUser);
        Assert.assertEquals(1, i);
    }

    /**
     * selectByExample:通过Example查询条件集合
     * @param example 查询条件
     */
    @Test
    public void selectByExample() {
        String phone = "18300001115";

        SysUserIdSnowExample example = new SysUserIdSnowExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowMapper.deleteByExample(example);

        SysUserIdSnow sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowMapper.insertSelective(sysUser);

        List<SysUserIdSnow> SysUserIdSnows = SysUserIdSnowMapper.selectByExample(example);
        Assert.assertTrue(SysUserIdSnows != null);
        Assert.assertEquals(1, SysUserIdSnows.size());
    }

    /**
     * selectByPrimaryKey:通过主键查询单个对象
     */
    @Test
    public void selectByPrimaryKey() {
        String phone = "18300001116";

        SysUserIdSnowExample example = new SysUserIdSnowExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowMapper.deleteByExample(example);

        SysUserIdSnow sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowMapper.insertSelective(sysUser);

        Long id = sysUser.getId();
        SysUserIdSnow SysUserIdSnow = SysUserIdSnowMapper.selectByPrimaryKey(id);
        Assert.assertEquals(SysUserIdSnow.getPhone(), phone);
    }

    /**
     * updateByExampleSelective:通过Example有选择性的更新数据
     * @param record 表对象（不为空的数据都会更新）
     */
    @Test
    public void updateByExampleSelective() {
        String phone = "18300001117";
        SysUserIdSnowExample example = new SysUserIdSnowExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowMapper.deleteByExample(example);

        SysUserIdSnow sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowMapper.insertSelective(sysUser);

        SysUserIdSnow sysUserUp = new SysUserIdSnow();
        sysUserUp.setUserName("Update");
        int i = SysUserIdSnowMapper.updateByExampleSelective(sysUserUp, example);

        Assert.assertEquals(1, i);

        List<SysUserIdSnow> SysUserIdSnows = SysUserIdSnowMapper.selectByExample(example);
        Assert.assertNotNull(SysUserIdSnows);
        Assert.assertEquals(1, SysUserIdSnows.size());

        Assert.assertEquals("Update", SysUserIdSnows.get(0).getUserName());

    }

    /**
     * updateByPrimaryKeySelective:通过主键有选择性的更新对象
     * @param record  表对象（不为空的数据都会更新）对象中必须含有主键
     */
    @Test
    public void updateByPrimaryKeySelective() {
        String phone = "18300001118";
        SysUserIdSnowExample example = new SysUserIdSnowExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowMapper.deleteByExample(example);

        SysUserIdSnow sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowMapper.insertSelective(sysUser);

        Long id = sysUser.getId();

        SysUserIdSnow sysUserUp = new SysUserIdSnow();
        sysUserUp.setUserName("UpdateById");
        sysUserUp.setId(id);
        int i = SysUserIdSnowMapper.updateByPrimaryKeySelective(sysUserUp);

        Assert.assertEquals(1, i);

        SysUserIdSnow SysUserIdSnow = SysUserIdSnowMapper.selectByPrimaryKey(id);

        Assert.assertEquals("UpdateById", SysUserIdSnow.getUserName());

    }

    @Test
    public void insertBatch() {
        String phone = "18300002221";
        SysUserIdSnowExample example = new SysUserIdSnowExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowMapper.deleteByExample(example);

        List<SysUserIdSnow> records = new ArrayList<>();
        int length = 5;
        for (int i = 0; i < length; i++) {
            SysUserIdSnow sysUser = createSysUserWithSnowId(phone);
            sysUser.setUserName(sysUser.getUserName() + "-" + i);
            records.add(sysUser);
        }
        int i = SysUserIdSnowMapper.insertBatch(records);

        Assert.assertEquals(i, length);

    }

}
