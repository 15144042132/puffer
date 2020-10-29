package com.sting.security.rbac.config;

import java.util.List;

public class PuffSecurity {
    public int a = 10;

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

}
