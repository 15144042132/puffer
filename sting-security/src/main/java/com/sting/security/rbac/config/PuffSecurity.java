package com.sting.security.rbac.config;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PuffSecurity {
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
