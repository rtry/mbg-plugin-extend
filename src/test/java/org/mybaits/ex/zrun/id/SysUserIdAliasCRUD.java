/**
 * FileName：SysUserIdAliasCRUD
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
import org.mybaits.ex.entity.SysUserIdAlias;
import org.mybaits.ex.entity.SysUserIdAliasExample;
import org.mybaits.ex.mapper.SysUserIdAliasMapper;
import org.mybaits.ex.zrun.BaseRunApplication;

/**
 * @className SysUserIdAliasCRUD
 * @describe 主键由MYSQL 自增
 * @author panxw
 * @date 2020/11/26 10:40
 */
public class SysUserIdAliasCRUD extends BaseRunApplication {

    SysUserIdAliasMapper sysUserIdAliasMapper;

    @Before
    public void initMapper() {
        sysUserIdAliasMapper = super.getMapper(SysUserIdAliasMapper.class);
    }

    // 方法没有设置主键
    private SysUserIdAlias createSysUserNoPK(String phone) {
        SysUserIdAlias sysUser = new SysUserIdAlias();
        sysUser.setUserName("System");
        sysUser.setAddress("Address");
        sysUser.setHeadImage("Image");
        sysUser.setSex(1);
        sysUser.setPhone(phone);
        return sysUser;
    }

    /**
     * countByExample:通过Example计算总数
     * @param example 查询条件
     */
    @Test
    public void countByExample() {

        String phone = "18300001111";

        SysUserIdAliasExample example = new SysUserIdAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAliasMapper.deleteByExample(example);

        SysUserIdAlias sysUser = createSysUserNoPK(phone);
        sysUserIdAliasMapper.insertSelective(sysUser);

        Long aLong = sysUserIdAliasMapper.countByExample(example);
        Assert.assertTrue(aLong == 1);
    }

    /**
     * deleteByExample:通过Example删除
     * @param example 刪除条件
     */
    @Test
    public void deleteByExample() {
        String phone = "18300001112";

        SysUserIdAliasExample example = new SysUserIdAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAliasMapper.deleteByExample(example);

        SysUserIdAlias sysUser = createSysUserNoPK(phone);
        sysUserIdAliasMapper.insertSelective(sysUser);

        int i = sysUserIdAliasMapper.deleteByExample(example);
        Assert.assertEquals(1, i);

    }

    /**
     * deleteByPrimaryKey:通过主键删除
     * @param id 表的主键
     */
    @Test
    public void deleteByPrimaryKey() {
        String phone = "18300001113";

        SysUserIdAliasExample example = new SysUserIdAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAliasMapper.deleteByExample(example);

        SysUserIdAlias sysUser = createSysUserNoPK(phone);
        sysUserIdAliasMapper.insertSelective(sysUser);

        Long id = sysUser.getUserId();
        int i = sysUserIdAliasMapper.deleteByPrimaryKey(id);
        Assert.assertEquals(1, i);

    }

    /**
     * insertSelective:有选择性的插入数据(空数据不会插入)
     * @param record 表对象
     */
    @Test
    public void insertSelective() {
        String phone = "18300001114";
        SysUserIdAlias sysUser = createSysUserNoPK(phone);
        int i = sysUserIdAliasMapper.insertSelective(sysUser);
        Assert.assertEquals(1, i);
    }

    /**
     * selectByExample:通过Example查询条件集合
     * @param example 查询条件
     */
    @Test
    public void selectByExample() {
        String phone = "18300001115";

        SysUserIdAliasExample example = new SysUserIdAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAliasMapper.deleteByExample(example);

        SysUserIdAlias sysUser = createSysUserNoPK(phone);
        sysUserIdAliasMapper.insertSelective(sysUser);

        List<SysUserIdAlias> SysUserIdAliass = sysUserIdAliasMapper.selectByExample(example);
        Assert.assertTrue(SysUserIdAliass != null);
        Assert.assertTrue(SysUserIdAliass.size() == 1);
    }

    /**
     * selectByPrimaryKey:通过主键查询单个对象
     */
    @Test
    public void selectByPrimaryKey() {
        String phone = "18300001116";

        SysUserIdAliasExample example = new SysUserIdAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAliasMapper.deleteByExample(example);

        SysUserIdAlias sysUser = createSysUserNoPK(phone);
        sysUserIdAliasMapper.insertSelective(sysUser);

        Long id = sysUser.getUserId();
        SysUserIdAlias SysUserIdAlias = sysUserIdAliasMapper.selectByPrimaryKey(id);
        Assert.assertTrue(SysUserIdAlias.getPhone().equals(phone));
    }

    /**
     * updateByExampleSelective:通过Example有选择性的更新数据
     * @param record 表对象（不为空的数据都会更新）
     */
    @Test
    public void updateByExampleSelective() {
        String phone = "18300001117";
        SysUserIdAliasExample example = new SysUserIdAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAliasMapper.deleteByExample(example);

        SysUserIdAlias sysUser = createSysUserNoPK(phone);
        sysUserIdAliasMapper.insertSelective(sysUser);

        SysUserIdAlias sysUserUp = new SysUserIdAlias();
        sysUserUp.setUserName("Update");
        int i = sysUserIdAliasMapper.updateByExampleSelective(sysUserUp, example);

        Assert.assertEquals(1, i);

        List<SysUserIdAlias> SysUserIdAliass = sysUserIdAliasMapper.selectByExample(example);
        Assert.assertNotNull(SysUserIdAliass);
        Assert.assertEquals(1, SysUserIdAliass.size());

        Assert.assertEquals("Update", SysUserIdAliass.get(0).getUserName());

    }

    /**
     * updateByPrimaryKeySelective:通过主键有选择性的更新对象
     * @param record  表对象（不为空的数据都会更新）对象中必须含有主键
     */
    @Test
    public void updateByPrimaryKeySelective() {
        String phone = "18300001118";
        SysUserIdAliasExample example = new SysUserIdAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAliasMapper.deleteByExample(example);

        SysUserIdAlias sysUser = createSysUserNoPK(phone);
        sysUserIdAliasMapper.insertSelective(sysUser);

        Long id = sysUser.getUserId();

        SysUserIdAlias sysUserUp = new SysUserIdAlias();
        sysUserUp.setUserName("UpdateById");
        sysUserUp.setUserId(id);
        int i = sysUserIdAliasMapper.updateByPrimaryKeySelective(sysUserUp);

        Assert.assertEquals(1, i);

        SysUserIdAlias SysUserIdAlias = sysUserIdAliasMapper.selectByPrimaryKey(id);

        Assert.assertEquals("UpdateById", SysUserIdAlias.getUserName());

    }

    @Test
    public void insertBatch() {
        String phone = "18300002221";
        SysUserIdAliasExample example = new SysUserIdAliasExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAliasMapper.deleteByExample(example);

        List<SysUserIdAlias> records = new ArrayList<>();
        int length = 5;
        for (int i = 0; i < length; i++) {
            SysUserIdAlias sysUser = createSysUserNoPK(phone);
            sysUser.setUserName(sysUser.getUserName() + "-" + i);
            records.add(sysUser);
        }
        int i = sysUserIdAliasMapper.insertBatch(records);

        Assert.assertEquals(i, length);

    }

}
