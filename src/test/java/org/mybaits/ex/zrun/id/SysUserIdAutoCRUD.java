/**
 * FileName：SysUserIdAutoCRUD
 * Version：
 * Date：2020/11/26
 * Copyright 马丁洛克 Corporation 2020 版权所有
 */
package org.mybaits.ex.zrun.id;

import org.apache.ibatis.annotations.Update;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mybaits.ex.entity.SysUserIdAuto;
import org.mybaits.ex.entity.SysUserIdAutoExample;
import org.mybaits.ex.entity.SysUserIdVarchar;
import org.mybaits.ex.entity.SysUserIdAutoExample;
import org.mybaits.ex.mapper.SysUserIdAutoMapper;
import org.mybaits.ex.zrun.BaseRunApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className SysUserIdAutoCRUD
 * @describe 主键由MYSQL 自增
 * @author rtry
 * @date 2020/11/26 10:40
 */
public class SysUserIdAutoCRUD extends BaseRunApplication {

    SysUserIdAutoMapper sysUserIdAutoMapper;

    @Before
    public void initMapper() {
        sysUserIdAutoMapper = super.getMapper(SysUserIdAutoMapper.class);
    }

    // 方法没有设置主键
    private SysUserIdAuto createSysUserNoPK(String phone) {
        SysUserIdAuto sysUser = new SysUserIdAuto();
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

        SysUserIdAutoExample example = new SysUserIdAutoExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAutoMapper.deleteByExample(example);

        SysUserIdAuto sysUser = createSysUserNoPK(phone);
        sysUserIdAutoMapper.insertSelective(sysUser);

        Long aLong = sysUserIdAutoMapper.countByExample(example);
        Assert.assertTrue(aLong == 1);
    }

    /**
     * deleteByExample:通过Example删除
     * @param example 刪除条件
     */
    @Test
    public void deleteByExample() {
        String phone = "18300001112";

        SysUserIdAutoExample example = new SysUserIdAutoExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAutoMapper.deleteByExample(example);

        SysUserIdAuto sysUser = createSysUserNoPK(phone);
        sysUserIdAutoMapper.insertSelective(sysUser);

        int i = sysUserIdAutoMapper.deleteByExample(example);
        Assert.assertEquals(1, i);

    }

    /**
     * deleteByPrimaryKey:通过主键删除
     * @param id 表的主键
     */
    @Test
    public void deleteByPrimaryKey() {
        String phone = "18300001113";

        SysUserIdAutoExample example = new SysUserIdAutoExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAutoMapper.deleteByExample(example);

        SysUserIdAuto sysUser = createSysUserNoPK(phone);
        sysUserIdAutoMapper.insertSelective(sysUser);

        Long id = sysUser.getId();
        int i = sysUserIdAutoMapper.deleteByPrimaryKey(id);
        Assert.assertEquals(1, i);

    }

    /**
     * insertSelective:有选择性的插入数据(空数据不会插入)
     * @param record 表对象
     */
    @Test
    public void insertSelective() {
        String phone = "18300001114";
        SysUserIdAuto sysUser = createSysUserNoPK(phone);
        int i = sysUserIdAutoMapper.insertSelective(sysUser);
        Assert.assertEquals(1, i);
    }

    /**
     * selectByExample:通过Example查询条件集合
     * @param example 查询条件
     */
    @Test
    public void selectByExample() {
        String phone = "18300001115";

        SysUserIdAutoExample example = new SysUserIdAutoExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAutoMapper.deleteByExample(example);

        SysUserIdAuto sysUser = createSysUserNoPK(phone);
        sysUserIdAutoMapper.insertSelective(sysUser);

        List<SysUserIdAuto> sysUserIdAutos = sysUserIdAutoMapper.selectByExample(example);
        Assert.assertTrue(sysUserIdAutos != null);
        Assert.assertTrue(sysUserIdAutos.size() == 1);
    }

    /**
     * selectByPrimaryKey:通过主键查询单个对象
     */
    @Test
    public void selectByPrimaryKey() {
        String phone = "18300001116";

        SysUserIdAutoExample example = new SysUserIdAutoExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAutoMapper.deleteByExample(example);

        SysUserIdAuto sysUser = createSysUserNoPK(phone);
        sysUserIdAutoMapper.insertSelective(sysUser);

        Long id = sysUser.getId();
        SysUserIdAuto sysUserIdAuto = sysUserIdAutoMapper.selectByPrimaryKey(id);
        Assert.assertTrue(sysUserIdAuto.getPhone().equals(phone));
    }

    /**
     * updateByExampleSelective:通过Example有选择性的更新数据
     * @param record 表对象（不为空的数据都会更新）
     */
    @Test
    public void updateByExampleSelective() {
        String phone = "18300001117";
        SysUserIdAutoExample example = new SysUserIdAutoExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAutoMapper.deleteByExample(example);

        SysUserIdAuto sysUser = createSysUserNoPK(phone);
        sysUserIdAutoMapper.insertSelective(sysUser);

        SysUserIdAuto sysUserUp = new SysUserIdAuto();
        sysUserUp.setUserName("Update");
        int i = sysUserIdAutoMapper.updateByExampleSelective(sysUserUp, example);

        Assert.assertEquals(1, i);

        List<SysUserIdAuto> sysUserIdAutos = sysUserIdAutoMapper.selectByExample(example);
        Assert.assertNotNull(sysUserIdAutos);
        Assert.assertEquals(1, sysUserIdAutos.size());

        Assert.assertEquals("Update", sysUserIdAutos.get(0).getUserName());

    }

    /**
     * updateByPrimaryKeySelective:通过主键有选择性的更新对象
     * @param record  表对象（不为空的数据都会更新）对象中必须含有主键
     */
    @Test
    public void updateByPrimaryKeySelective() {
        String phone = "18300001118";
        SysUserIdAutoExample example = new SysUserIdAutoExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAutoMapper.deleteByExample(example);

        SysUserIdAuto sysUser = createSysUserNoPK(phone);
        sysUserIdAutoMapper.insertSelective(sysUser);

        Long id = sysUser.getId();

        SysUserIdAuto sysUserUp = new SysUserIdAuto();
        sysUserUp.setUserName("UpdateById");
        sysUserUp.setId(id);
        int i = sysUserIdAutoMapper.updateByPrimaryKeySelective(sysUserUp);

        Assert.assertEquals(1, i);

        SysUserIdAuto sysUserIdAuto = sysUserIdAutoMapper.selectByPrimaryKey(id);

        Assert.assertEquals("UpdateById", sysUserIdAuto.getUserName());

    }

    @Test
    public void insertBatch() {
        String phone = "18300002221";
        SysUserIdAutoExample example = new SysUserIdAutoExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAutoMapper.deleteByExample(example);

        List<SysUserIdAuto> records = new ArrayList<>();
        int length = 5;
        for (int i = 0; i < length; i++) {
            SysUserIdAuto sysUser = createSysUserNoPK(phone);
            sysUser.setUserName(sysUser.getUserName() + "-" + i);
            records.add(sysUser);
        }
        int i = sysUserIdAutoMapper.insertBatch(records);

        Assert.assertEquals(i, length);

    }

    @Test
    public void insertBatchSelect() {
        String phone = "18300077771";
        SysUserIdAutoExample example = new SysUserIdAutoExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAutoMapper.deleteByExample(example);

        List<SysUserIdAuto> records = new ArrayList<>();
        int length = 5;
        for (int i = 0; i < length; i++) {
            SysUserIdAuto sysUser = createSysUserNoPK(phone);
            sysUser.setUserName(sysUser.getUserName() + "-" + i);
            if (i % 2 == 1)
                sysUser.setAddress("TK");
            else
                sysUser.setAddress(null);
            records.add(sysUser);
        }
        sysUserIdAutoMapper.insertBatchSelect(records);

    }

    // 批量更新
    @Test
    public void updateBatch() {

        String phone = "18300007771";
        SysUserIdAutoExample example = new SysUserIdAutoExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAutoMapper.deleteByExample(example);

        List<SysUserIdAuto> records = new ArrayList<>();
        int length = 5;
        for (int i = 0; i < length; i++) {
            SysUserIdAuto sysUser = createSysUserNoPK(phone);
            sysUser.setUserName(sysUser.getUserName() + "-" + i);
            records.add(sysUser);
        }
        int i = sysUserIdAutoMapper.insertBatch(records);

        Assert.assertEquals(i, length);
        List<SysUserIdAuto> sysUserIdVarchars = sysUserIdAutoMapper.selectByExample(example);
        for (int m = 0; m < sysUserIdVarchars.size(); m++) {
            SysUserIdAuto SysUserIdAuto = sysUserIdVarchars.get(m);
            if (m % 2 == 0) {
                SysUserIdAuto.setAddress("JJJ");
            } else {
                SysUserIdAuto.setHeadImage("BBB");
            }
        }
        sysUserIdAutoMapper.updateBatchById(sysUserIdVarchars);

    }

    @Test
    public void updateMapByExample() {
        String phone = "18300008888";
        SysUserIdAutoExample example = new SysUserIdAutoExample();
        example.createCriteria().andPhoneEqualTo(phone);
        sysUserIdAutoMapper.deleteByExample(example);

        SysUserIdAuto sysUser = createSysUserNoPK(phone);
        sysUserIdAutoMapper.insertSelective(sysUser);

        Long aLong = sysUserIdAutoMapper.countByExample(example);

        Map<String, Object> map = new HashMap<>();
        map.put("address", "JDNLDFJDSLFJLSD");
        map.put("address", "ddd");
        map.put("sex", null);
        int i = sysUserIdAutoMapper.updateMapByExample(map, example);

        Assert.assertEquals(i, aLong.intValue());

    }

}
