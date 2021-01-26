package org.mybaits.ex.mapper;


import org.mybaits.ex.base.BaseMapper;
import org.mybaits.ex.base.InsertBatchMapper;
import org.mybaits.ex.base.SelectOptionMapper;
import org.mybaits.ex.entity.SysUserIdAlias;
import org.mybaits.ex.entity.SysUserIdAliasExample;

/**
 * 类描述: sys_user_id_alias表的Mapper对象，已包含CRUD操作，可在该接口中自定义方法<br>
 * 创建者: 由 MBG(mybatis generator plug) 生成<br> 
 * 创建时间: 2020-11-26<br>
 * @version 0.0.3
 */
public interface SysUserIdAliasMapper extends   BaseMapper<SysUserIdAlias, Long, SysUserIdAliasExample>, InsertBatchMapper<SysUserIdAlias>, SelectOptionMapper<SysUserIdAlias, Long, SysUserIdAliasExample> {
}
