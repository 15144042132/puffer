package com.puffer.admin.module.system.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Mapper操作模板（不可以存在同名方法）
 * <p>
 * 基于基本类型参数的  增 / 删 / 改 / 查 / 分页
 * <p>
 * 基于对象参数的  增 / 删 / 改 / 查 / 分页
 *
 * @author WangYongJi
 */
@Component
public interface SimpleMapper {

    @Select("select id from sys_user where id = #{id}")
    Object getId(@Param("id") Object id);

    @Select("select id from sys_user where id = #{param.id}")
    Object getIdByMap(@Param("param") Map<String, Object> param);

}
