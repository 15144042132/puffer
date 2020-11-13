package com.sting.security.rbac;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 权限相关配置
 * 继承这个方法，实现自定义配置
 */
@Slf4j
public abstract class PufferSecurityConfig {
    public PuffSecurity puffSecurity;

    public static class PuffSecurity {
        public int a = 10;

        private ConcurrentHashMap<String, HashSet<String>> roleResource = new ConcurrentHashMap<>();

        public PuffSecurity addRequired(String role, String urls) {
            return this;
        }

        public PuffSecurity addRequired(String role, List<String> urls) {
            return this;
        }

        public PuffSecurity addRequired(List<String> role, String urls) {
            return this;
        }

        public PuffSecurity addRequired(List<String> role, List<String> urls) {
            return this;
        }

        public PuffSecurity addPublic(String... urls) {
            return this;
        }

        public PuffSecurity addPublic(List<String> urls) {
            return this;
        }

        public PuffSecurity excludeRes(List<String> role, List<String> urls) {
            return this;
        }

    }


    public PufferSecurityConfig() {
        puffSecurity = new PuffSecurity();
        security(puffSecurity);
    }

    public abstract void security(PuffSecurity puffSecurity);

}
