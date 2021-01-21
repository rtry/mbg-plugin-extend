/**
 * FileName：SysUserIdVarcharCRUD
 * Version：
 * Date：2020/11/26
 * Copyright 马丁洛克 Corporation 2020 版权所有
 */
package org.mybaits.ex.zrun.id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mybaits.ex.entity.SysUserIdVarchar;
import org.mybaits.ex.entity.SysUserIdVarcharExample;
import org.mybaits.ex.mapper.SysUserIdVarcharMapper;
import org.mybaits.ex.zrun.BaseRunApplication;

/**
 * @className SysUserIdVarcharCRUD
 * @describe 主键由MYSQL 自增
 * @author panxw
 * @date 2020/11/26 10:40
 */
public class SysUserIdVarcharCRUD extends BaseRunApplication {

    SysUserIdVarcharMapper SysUserIdVarcharMapper;

    @Before
    public void initMapper() {
        SysUserIdVarcharMapper = super.getMapper(SysUserIdVarcharMapper.class);
    }

    // 方法没有设置主键
    private SysUserIdVarchar createSysUserWithSnowId(String phone) {
        SysUserIdVarchar sysUser = new SysUserIdVarchar();
        sysUser.setUserName("System");
        sysUser.setAddress("Address");
        sysUser.setHeadImage("Image");
        sysUser.setSex(1);
        sysUser.setPhone(phone);
        sysUser.setUserId(super.nextStringId());
        return sysUser;
    }

    /**
     * countByExample:通过Example计算总数
     * @param example 查询条件
     */
    @Test
    public void countByExample() {

        String phone = "18300001111";

        SysUserIdVarcharExample example = new SysUserIdVarcharExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdVarcharMapper.deleteByExample(example);

        SysUserIdVarchar sysUser = createSysUserWithSnowId(phone);
        SysUserIdVarcharMapper.insertSelective(sysUser);

        Long aLong = SysUserIdVarcharMapper.countByExample(example);
        Assert.assertTrue(aLong == 1);
    }

    /**
     * deleteByExample:通过Example删除
     * @param example 刪除条件
     */
    @Test
    public void deleteByExample() {
        String phone = "18300001112";

        SysUserIdVarcharExample example = new SysUserIdVarcharExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdVarcharMapper.deleteByExample(example);

        SysUserIdVarchar sysUser = createSysUserWithSnowId(phone);
        SysUserIdVarcharMapper.insertSelective(sysUser);

        int i = SysUserIdVarcharMapper.deleteByExample(example);
        Assert.assertEquals(1, i);

    }

    /**
     * deleteByPrimaryKey:通过主键删除
     * @param id 表的主键
     */
    @Test
    public void deleteByPrimaryKey() {
        String phone = "18300001113";

        SysUserIdVarcharExample example = new SysUserIdVarcharExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdVarcharMapper.deleteByExample(example);

        SysUserIdVarchar sysUser = createSysUserWithSnowId(phone);
        SysUserIdVarcharMapper.insertSelective(sysUser);

        String id = sysUser.getUserId();
        int i = SysUserIdVarcharMapper.deleteByPrimaryKey(id);
        Assert.assertEquals(1, i);

    }

    /**
     * insertSelective:有选择性的插入数据(空数据不会插入)
     * @param record 表对象
     */
    @Test
    public void insertSelective() {
        String phone = "18300001114";
        SysUserIdVarchar sysUser = createSysUserWithSnowId(phone);
        int i = SysUserIdVarcharMapper.insertSelective(sysUser);
        Assert.assertEquals(1, i);
    }

    /**
     * selectByExample:通过Example查询条件集合
     * @param example 查询条件
     */
    @Test
    public void selectByExample() {
        String phone = "18300001115";

        SysUserIdVarcharExample example = new SysUserIdVarcharExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdVarcharMapper.deleteByExample(example);

        SysUserIdVarchar sysUser = createSysUserWithSnowId(phone);
        SysUserIdVarcharMapper.insertSelective(sysUser);

        List<SysUserIdVarchar> SysUserIdVarchars = SysUserIdVarcharMapper.selectByExample(example);
        Assert.assertTrue(SysUserIdVarchars != null);
        Assert.assertEquals(1, SysUserIdVarchars.size());
    }

    /**
     * selectByPrimaryKey:通过主键查询单个对象
     */
    @Test
    public void selectByPrimaryKey() {
        String phone = "18300001116";

        SysUserIdVarcharExample example = new SysUserIdVarcharExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdVarcharMapper.deleteByExample(example);

        SysUserIdVarchar sysUser = createSysUserWithSnowId(phone);
        SysUserIdVarcharMapper.insertSelective(sysUser);

        String id = sysUser.getUserId();
        SysUserIdVarchar SysUserIdVarchar = SysUserIdVarcharMapper.selectByPrimaryKey(id);
        Assert.assertEquals(SysUserIdVarchar.getPhone(), phone);
    }

    /**
     * updateByExampleSelective:通过Example有选择性的更新数据
     * @param record 表对象（不为空的数据都会更新）
     */
    @Test
    public void updateByExampleSelective() {
        String phone = "18300001117";
        SysUserIdVarcharExample example = new SysUserIdVarcharExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdVarcharMapper.deleteByExample(example);

        SysUserIdVarchar sysUser = createSysUserWithSnowId(phone);
        SysUserIdVarcharMapper.insertSelective(sysUser);

        SysUserIdVarchar sysUserUp = new SysUserIdVarchar();
        sysUserUp.setUserName("Update");
        int i = SysUserIdVarcharMapper.updateByExampleSelective(sysUserUp, example);

        Assert.assertEquals(1, i);

        List<SysUserIdVarchar> SysUserIdVarchars = SysUserIdVarcharMapper.selectByExample(example);
        Assert.assertNotNull(SysUserIdVarchars);
        Assert.assertEquals(1, SysUserIdVarchars.size());

        Assert.assertEquals("Update", SysUserIdVarchars.get(0).getUserName());

    }

    /**
     * updateByPrimaryKeySelective:通过主键有选择性的更新对象
     * @param record  表对象（不为空的数据都会更新）对象中必须含有主键
     */
    @Test
    public void updateByPrimaryKeySelective() {
        String phone = "18300001118";
        SysUserIdVarcharExample example = new SysUserIdVarcharExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdVarcharMapper.deleteByExample(example);

        SysUserIdVarchar sysUser = createSysUserWithSnowId(phone);
        SysUserIdVarcharMapper.insertSelective(sysUser);

        String id = sysUser.getUserId();

        SysUserIdVarchar sysUserUp = new SysUserIdVarchar();
        sysUserUp.setUserName("UpdateById");
        sysUserUp.setUserId(id);
        int i = SysUserIdVarcharMapper.updateByPrimaryKeySelective(sysUserUp);

        Assert.assertEquals(1, i);

        SysUserIdVarchar SysUserIdVarchar = SysUserIdVarcharMapper.selectByPrimaryKey(id);

        Assert.assertEquals("UpdateById", SysUserIdVarchar.getUserName());

    }

    @Test
    public void insertBatch() {
        String phone = "18300002221";
        SysUserIdVarcharExample example = new SysUserIdVarcharExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdVarcharMapper.deleteByExample(example);

        List<SysUserIdVarchar> records = new ArrayList<>();
        int length = 5;
        for (int i = 0; i < length; i++) {
            SysUserIdVarchar sysUser = createSysUserWithSnowId(phone);
            sysUser.setUserName(sysUser.getUserName() + "-" + i);
            records.add(sysUser);
        }
        int i = SysUserIdVarcharMapper.insertBatch(records);

        Assert.assertEquals(i, length);
    }

    @Test
    public void insertBatchSelect() {
        String phone = "18300077771";
        SysUserIdVarcharExample example = new SysUserIdVarcharExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdVarcharMapper.deleteByExample(example);

        List<SysUserIdVarchar> records = new ArrayList<>();
        int length = 5;
        for (int i = 0; i < length; i++) {
            SysUserIdVarchar sysUser = createSysUserWithSnowId(phone);
            sysUser.setUserName(sysUser.getUserName() + "-" + i);
            if (i % 2 == 1)
                sysUser.setAddress("TK");
            else
                sysUser.setAddress(null);
            records.add(sysUser);
        }
        SysUserIdVarcharMapper.insertBatchSelect(records);

    }

    // 批量更新
    @Test
    public void updateBatch() {

        String phone = "18300007771";
        SysUserIdVarcharExample example = new SysUserIdVarcharExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdVarcharMapper.deleteByExample(example);

        List<SysUserIdVarchar> records = new ArrayList<>();
        int length = 5;
        for (int i = 0; i < length; i++) {
            SysUserIdVarchar sysUser = createSysUserWithSnowId(phone);
            sysUser.setUserName(sysUser.getUserName() + "-" + i);
            records.add(sysUser);
        }
        int i = SysUserIdVarcharMapper.insertBatch(records);

        Assert.assertEquals(i, length);
        List<SysUserIdVarchar> sysUserIdVarchars = SysUserIdVarcharMapper.selectByExample(example);
        for (int m = 0; m < sysUserIdVarchars.size(); m++) {
            SysUserIdVarchar sysUserIdVarchar = sysUserIdVarchars.get(m);
            if (m % 2 == 0) {
                sysUserIdVarchar.setAddress("JJJ");
            } else {
                sysUserIdVarchar.setHeadImage("BBB");
            }
        }
        SysUserIdVarcharMapper.updateBatchById(sysUserIdVarchars);

    }

    @Test
    public void updateMapByExample() {
        String phone = "18300008888";
        SysUserIdVarcharExample example = new SysUserIdVarcharExample();
        example.createCriteria().andPhoneEqualTo(phone);
        SysUserIdVarcharMapper.deleteByExample(example);

        SysUserIdVarchar sysUser = createSysUserWithSnowId(phone);
        SysUserIdVarcharMapper.insertSelective(sysUser);

        Long aLong = SysUserIdVarcharMapper.countByExample(example);

        Map<String, Object> map = new HashMap<>();
        map.put("address", "JDNLDFJDSLFJLSD");
        map.put("address", "ddd");
        map.put("sex", null);
        int i = SysUserIdVarcharMapper.updateMapByExample(map, example);

        Assert.assertEquals(i, aLong.intValue());

    }

}
