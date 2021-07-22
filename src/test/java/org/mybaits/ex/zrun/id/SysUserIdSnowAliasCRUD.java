/**
 * FileName：SysUserIdSnowAliasCRUD
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
import org.mybaits.ex.entity.SysUserIdSnowAlias;
import org.mybaits.ex.entity.SysUserIdSnowAliasExample;
import org.mybaits.ex.mapper.SysUserIdSnowAliasMapper;
import org.mybaits.ex.zrun.BaseRunApplication;

/**
 * @className SysUserIdSnowAliasCRUD
 * @describe 主键由MYSQL 自增
 * @author rtry
 * @date 2020/11/26 10:40
 */
public class SysUserIdSnowAliasCRUD extends BaseRunApplication {

    SysUserIdSnowAliasMapper SysUserIdSnowAliasMapper;

    @Before
    public void initMapper() {
        SysUserIdSnowAliasMapper = super.getMapper(SysUserIdSnowAliasMapper.class);
    }

    // 方法没有设置主键
    private SysUserIdSnowAlias createSysUserWithSnowId(String phone) {
        SysUserIdSnowAlias sysUser = new SysUserIdSnowAlias();
        sysUser.setUserName("System");
        sysUser.setAddress("Address");
        sysUser.setHeadImage("Image");
        sysUser.setSex(1);
        sysUser.setPhone(phone);
        sysUser.setUserId(super.nextId());
        return sysUser;
    }

    /**
     * countByExample:通过Example计算总数
     * @param example 查询条件
     */
    @Test
    public void countByExample() {

        String phone = "18300001111";

        SysUserIdSnowAliasExample example = new SysUserIdSnowAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowAliasMapper.deleteByExample(example);

        SysUserIdSnowAlias sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowAliasMapper.insertSelective(sysUser);

        Long aLong = SysUserIdSnowAliasMapper.countByExample(example);
        Assert.assertTrue(aLong == 1);
    }

    /**
     * deleteByExample:通过Example删除
     * @param example 刪除条件
     */
    @Test
    public void deleteByExample() {
        String phone = "18300001112";

        SysUserIdSnowAliasExample example = new SysUserIdSnowAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowAliasMapper.deleteByExample(example);

        SysUserIdSnowAlias sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowAliasMapper.insertSelective(sysUser);

        int i = SysUserIdSnowAliasMapper.deleteByExample(example);
        Assert.assertEquals(1, i);

    }

    /**
     * deleteByPrimaryKey:通过主键删除
     * @param id 表的主键
     */
    @Test
    public void deleteByPrimaryKey() {
        String phone = "18300001113";

        SysUserIdSnowAliasExample example = new SysUserIdSnowAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowAliasMapper.deleteByExample(example);

        SysUserIdSnowAlias sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowAliasMapper.insertSelective(sysUser);

        Long id = sysUser.getUserId();
        int i = SysUserIdSnowAliasMapper.deleteByPrimaryKey(id);
        Assert.assertEquals(1, i);

    }

    /**
     * insertSelective:有选择性的插入数据(空数据不会插入)
     * @param record 表对象
     */
    @Test
    public void insertSelective() {
        String phone = "18300001114";
        SysUserIdSnowAlias sysUser = createSysUserWithSnowId(phone);
        int i = SysUserIdSnowAliasMapper.insertSelective(sysUser);
        Assert.assertEquals(1, i);
    }

    /**
     * selectByExample:通过Example查询条件集合
     * @param example 查询条件
     */
    @Test
    public void selectByExample() {
        String phone = "18300001115";

        SysUserIdSnowAliasExample example = new SysUserIdSnowAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowAliasMapper.deleteByExample(example);

        SysUserIdSnowAlias sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowAliasMapper.insertSelective(sysUser);

        List<SysUserIdSnowAlias> SysUserIdSnowAliass = SysUserIdSnowAliasMapper.selectByExample(example);
        Assert.assertTrue(SysUserIdSnowAliass != null);
        Assert.assertEquals(1, SysUserIdSnowAliass.size());
    }

    /**
     * selectByPrimaryKey:通过主键查询单个对象
     */
    @Test
    public void selectByPrimaryKey() {
        String phone = "18300001116";

        SysUserIdSnowAliasExample example = new SysUserIdSnowAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowAliasMapper.deleteByExample(example);

        SysUserIdSnowAlias sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowAliasMapper.insertSelective(sysUser);

        Long id = sysUser.getUserId();
        SysUserIdSnowAlias SysUserIdSnowAlias = SysUserIdSnowAliasMapper.selectByPrimaryKey(id);
        Assert.assertEquals(SysUserIdSnowAlias.getPhone(), phone);
    }

    /**
     * updateByExampleSelective:通过Example有选择性的更新数据
     * @param record 表对象（不为空的数据都会更新）
     */
    @Test
    public void updateByExampleSelective() {
        String phone = "18300001117";
        SysUserIdSnowAliasExample example = new SysUserIdSnowAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowAliasMapper.deleteByExample(example);

        SysUserIdSnowAlias sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowAliasMapper.insertSelective(sysUser);

        SysUserIdSnowAlias sysUserUp = new SysUserIdSnowAlias();
        sysUserUp.setUserName("Update");
        int i = SysUserIdSnowAliasMapper.updateByExampleSelective(sysUserUp, example);

        Assert.assertEquals(1, i);

        List<SysUserIdSnowAlias> SysUserIdSnowAliass = SysUserIdSnowAliasMapper.selectByExample(example);
        Assert.assertNotNull(SysUserIdSnowAliass);
        Assert.assertEquals(1, SysUserIdSnowAliass.size());

        Assert.assertEquals("Update", SysUserIdSnowAliass.get(0).getUserName());

    }

    /**
     * updateByPrimaryKeySelective:通过主键有选择性的更新对象
     * @param record  表对象（不为空的数据都会更新）对象中必须含有主键
     */
    @Test
    public void updateByPrimaryKeySelective() {
        String phone = "18300001118";
        SysUserIdSnowAliasExample example = new SysUserIdSnowAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowAliasMapper.deleteByExample(example);

        SysUserIdSnowAlias sysUser = createSysUserWithSnowId(phone);
        SysUserIdSnowAliasMapper.insertSelective(sysUser);

        Long id = sysUser.getUserId();

        SysUserIdSnowAlias sysUserUp = new SysUserIdSnowAlias();
        sysUserUp.setUserName("UpdateById");
        sysUserUp.setUserId(id);
        int i = SysUserIdSnowAliasMapper.updateByPrimaryKeySelective(sysUserUp);

        Assert.assertEquals(1, i);

        SysUserIdSnowAlias SysUserIdSnowAlias = SysUserIdSnowAliasMapper.selectByPrimaryKey(id);

        Assert.assertEquals("UpdateById", SysUserIdSnowAlias.getUserName());

    }

    @Test
    public void insertBatch() {
        String phone = "18300002221";
        SysUserIdSnowAliasExample example = new SysUserIdSnowAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdSnowAliasMapper.deleteByExample(example);

        List<SysUserIdSnowAlias> records = new ArrayList<>();
        int length = 5;
        for (int i = 0; i < length; i++) {
            SysUserIdSnowAlias sysUser = createSysUserWithSnowId(phone);
            sysUser.setUserName(sysUser.getUserName() + "-" + i);
            records.add(sysUser);
        }
        int i = SysUserIdSnowAliasMapper.insertBatch(records);

        Assert.assertEquals(i, length);

    }

}
