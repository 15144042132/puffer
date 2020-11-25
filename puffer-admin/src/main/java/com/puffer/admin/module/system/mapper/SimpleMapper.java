package com.puffer.admin.module.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puffer.admin.common.mybatis.entity.SysDict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统相关
 * <p>
 * 两种形式 1.@注解 2.xml
 *
 * @author WangYongJi
 */
@Component
public interface SimpleMapper {

    @Select("select value from sys_dict where code = #{code}")
    List<SysDict> dictList(@Param("code") Object code);

    SysDict dict(@Param("code") Object code);

    @Select("select 0_template.*,a_user.name as userName, a_user.phone as userPhone "
            + "from 0_template "
            + "left join a_user on 0_template.create_uid=a_user.id "
            + "${ew.customSqlSegment}")
    IPage<Object> leftJoinPage(Page<Object> page, @Param(Constants.WRAPPER) QueryWrapper<Object> wrapper);
}
