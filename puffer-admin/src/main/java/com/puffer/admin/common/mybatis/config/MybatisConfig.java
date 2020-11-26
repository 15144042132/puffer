package com.puffer.admin.common.mybatis.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.puffer.admin.common.util.HumpKit;
import com.puffer.admin.common.util.RequestKit;
import com.sting.security.rbac.JwtKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * MybatisPlus功能配置
 *
 * @author WangYongJi
 * <p>
 * 1.分页配置
 * 2.驼峰转换
 */
@Slf4j
@Configuration
public class MybatisConfig {

    /**
     * 1.分页配置
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

    /**
     * 2.驼峰转换
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setObjectWrapperFactory(new WrapperFactory());
    }

    /**
     * 字段填充
     */
    @Bean
    public FillHandler fillHandler() {
        return new FillHandler();
    }

    /**
     * 驼峰转换
     */
    static class WrapperFactory implements ObjectWrapperFactory {
        @Override
        public boolean hasWrapperFor(Object o) {
            return o instanceof Map;
        }

        @Override
        public ObjectWrapper getWrapperFor(MetaObject metaObject, Object o) {
            return new MiMapWrapper(metaObject, (Map) o);
        }

        static class MiMapWrapper extends MapWrapper {
            MiMapWrapper(MetaObject metaObject, Map<String, Object> map) {
                super(metaObject, map);
            }

            @Override
            public String findProperty(String name, boolean useCamelCaseMapping) {
                if (useCamelCaseMapping) return HumpKit._ToHump(name);
                return name;
            }
        }
    }

    //字段填充
    static class FillHandler implements com.sting.db.util.FillHandler {
        //数据创建时间
        private final String createTime = "createTime";
        //数据修改时间
        private final String updateTime = "updateTime";
        //数据创建人
        private final String createUid = "createUid";
        //数据修改人
        private final String updateUid = "updateUid";

        @Override
        public void insertFill(HashMap<String, Object> fill) {
            try {
                fill.put(createTime, LocalDateTime.now());
                fill.put(updateTime, LocalDateTime.now());
                String token = JwtKit.getUserId(RequestKit.request().getHeader("token"));
                fill.put(createUid, token);
                fill.put(updateUid, token);
            } catch (Exception ignored) {

            }
        }

        @Override
        public void updateFill(HashMap<String, Object> fill) {
            try {
                fill.put(updateTime, LocalDateTime.now());
                String token = JwtKit.getUserId(RequestKit.request().getHeader("token"));
                fill.put(updateUid, token);
            } catch (Exception ignored) {
            }
        }

    }

}
