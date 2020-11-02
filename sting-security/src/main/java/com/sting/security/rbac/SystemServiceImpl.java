//package com.sting.security.rbac;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.sting.security.rbac.entity.*;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.cache.CacheKey;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * <p>
// * 资源表 服务实现类
// * </p>
// *
// * @author WangYongJi
// */
//@Slf4j
//@Service
//public class SystemServiceImpl {
//    @Autowired
//    private MiDao dao;
//    @Autowired
//    private DictService dictService;
//    @Autowired
//    private RoleService roleService;
//    @Autowired
//    private LogServiceImpl logService;
//
//    //资源列表
//    public List<SysResource> resources() {
//        String s = CacheKit.instance().get(CacheKey.key_resource_list);
//        if (s == null) {
//            List<SysResource> list = dao.list(SysResource.class);
//            CacheKit.instance().set(CacheKey.key_resource_list, JSON.toJSONString(list));
//            return list;
//        }
//        return JSON.parseArray(s, SysResource.class);
//
//    }
//
//    //菜单列表
//    public List<SysMenu> menus() {
//        String s = CacheKit.instance().get(CacheKey.key_menu_list);
//        if (s == null) {
//            List<SysMenu> list = dao.list(SysMenu.class);
//            CacheKit.instance().set(CacheKey.key_menu_list, JSON.toJSONString(list));
//            return dao.list(SysMenu.class);
//        }
//        return JSON.parseArray(s, SysMenu.class);
//
//    }
//
//    //1.清除已失效资源
//    //2.添加新资源
//    @Transactional(rollbackFor = Exception.class)
//    public SRS systemResourceCleanAdd(SRS param) {
//        //项目中的全部资源
//        ArrayList<ResEntity> resMList = ResourceHandler.getResMList();
//        ArrayList<ResEntity> resCList = ResourceHandler.getResCList();
//        ArrayList<ResEntity> resList = new ArrayList<>();
//        resList.addAll(resCList);
//        resList.addAll(resMList);
//        List<String> resUrlList = resList.stream().map(ResEntity::getUrl).collect(Collectors.toList());
//
//        //数据库中的全部资源
//        List<SysResource> dbResList = dao.list(SysResource.class);
//        List<String> dbUrlList = dbResList.stream().map(SysResource::getUrl).collect(Collectors.toList());
//
//        //筛选Method
//        //待删除模块 -- deleteResCIds
//        //新增模块   -- insertResC
//        //可更新模块 -- updateResC
//        ArrayList<SysResource> insertResCList = new ArrayList<>();
//        ArrayList<String> deleteResCIds = new ArrayList<>();
//        ArrayList<SysResource> updateResCList = new ArrayList<>();
//
//        //筛选controller
//        for (ResEntity resEntity : resList) {
//            String resUrl = resEntity.getUrl();
//            //待新增  -- 项目中存在，数据库中不存在
//            if (!dbUrlList.contains(resUrl)) {
//                SysResource sysResource = new SysResource();
//                sysResource.setName(resEntity.getName());
//                sysResource.setParentName(resEntity.getParentName());
//                sysResource.setUrl(resEntity.getUrl());
//                sysResource.setPid("0");//初始为0后面会做修改
//                insertResCList.add(sysResource);
//            }
//        }
//        for (SysResource sysResource : dbResList) {
//            String dbUrl = sysResource.getUrl();
//            //待更新 -- 数据库中存在，项目中存在
//            if (resUrlList.contains(dbUrl)) {
//                List<ResEntity> collect = resList.stream().filter(it -> it.getUrl().equals(dbUrl)).collect(Collectors.toList());
//                ResEntity resEntity = collect.get(0);
//                sysResource.setName(resEntity.getName());
//                sysResource.setParentName(resEntity.getParentName());
//                updateResCList.add(sysResource);
//            }
//
//            //待删除 -- 数据库中存在，项目中不存在
//            if (!resUrlList.contains(dbUrl)) {
//                deleteResCIds.add(sysResource.getId());
//            }
//        }
//
//        //删除失效资源
//        if (deleteResCIds.size() > 0) {
//            //删除资源表
//            long l = dao.deleteByIds(SysResource.class, deleteResCIds);
//            //删除角色资源关联表
//            MiWrapper<SysLinkRoleResource> wrapper = new MiWrapper<>(SysLinkRoleResource.class);
//            wrapper.in(SysLinkRoleResource.ROLE_ID, deleteResCIds);
//            long delete = dao.delete(wrapper);
//            log.info("失效资源ids:{}", deleteResCIds);
//            log.info("删除失效资源      共{}条", l);
//            log.info("删除角色资源关联表 共{}条", delete);
//        }
//
//        //更新资源
//        if (updateResCList.size() > 0) {
//            //更新资源
//            long l = dao.updateBatchById(updateResCList);
//            log.info("更新资源      共{}条", l);
//        }
//
//        //添加新资源
//        //
//        //批量插入资源
//        long l = dao.insertBatch(insertResCList);
//        log.info("批量插入资源     共{}条", l);
//        log.info("批量插入资源      {}", JSON.toJSONString(insertResCList));
//
//        //把新增 和 修改的资源 合并
//        ArrayList<SysResource> allResC = new ArrayList<>();
//        allResC.addAll(insertResCList);
//        allResC.addAll(updateResCList);
//
//        //遍历 新增模块
//        for (SysResource insertRes : insertResCList) {
//            //处理非根节点
//            if (!insertRes.getName().equals(insertRes.getParentName())) {
//                String parentName = insertRes.getParentName();
//                //从合并的模块集合内
//                for (SysResource sysResource : allResC) {
//                    //找到自己的pid
//                    if (sysResource.getName().equals(parentName)) {
//                        insertRes.setPid(sysResource.getId());
//                        break;
//                    }
//                }
//            }
//        }
//
//        //更新这个集合，模块新增完成
//        long l1 = dao.updateBatchById(insertResCList);
//        log.info("全部资源新增完成      共{}条", l1);
//        log.info("全部资源新增完成      {}", JSON.toJSONString(insertResCList));
//        //TODO 这里注释
//        List<SysResource> list = dao.list(SysResource.class);
//        List<SysResource> gen = list.stream().filter(it -> it.getPid().equals("0")).collect(Collectors.toList());
//        List<SysResource> notGen = list.stream().filter(it -> !it.getPid().equals("0")).collect(Collectors.toList());
//        log.info("list     {}", JSON.toJSONString(list));
//        log.info("gen     {}", JSON.toJSONString(gen));
//        log.info("notGen  {}", JSON.toJSONString(notGen));
//
//        ArrayList<JSONObject> arrayList = TreeKit.listToTree(gen, notGen);
//        log.info("全部资源树化结果      {}", JSON.toJSONString(arrayList));
//        resources();
//        return SRS.bySuccess();
//    }
//
//    public SRS refreshRoleMenuAndResource(SRS param) {
//        //缓存全部角色
//        List<SysRole> sysRoles = roleService.roles();
//        //查询角色菜单关联表
//        List<String> roleIds = sysRoles.stream().map(SysRole::getId).collect(Collectors.toList());
//
//        //缓存角色资源，角色菜单
//        for (String roleId : roleIds) {
//            //当前角色的全部资源
//            List<String> roleResourceIds = dao.listObj(new MiWrapper<>(SysLinkRoleResource.class).select("resource_id").eq("role_id", roleId), String.class);
//            //当前角色的全部菜单
//            List<String> roleMenuIds = dao.listObj(new MiWrapper<>(SysLinkRoleMenu.class).select("menu_id").eq("role_id", roleId), String.class);
//
//            roleService.updateRoleResource(SRS.create().set("roleId", roleId).set("resourceIds", roleResourceIds));
//            roleService.updateRoleMenu(SRS.create().set("roleId", roleId).set("menuIds", roleMenuIds));
//        }
//        return SRS.bySuccess();
//    }
//
//    public SRS insertDict(SysDict dict) {
//        dao.insert(dict);
//        removeDictCache();
//        return SRS.bySuccess();
//    }
//
//    public SRS deleteDict(Object[] ids) {
//        dao.deleteByIds(SysDict.class, Arrays.asList(ids));
//        removeDictCache();
//        return SRS.bySuccess();
//    }
//
//    public SRS removeDictCache() {
//        List<SysDict> list = dao.list(SysDict.class);
//        CacheKit.instance().delete(CacheKey.key_dict_list);
//        return SRS.bySuccess().data(list).message("字典缓存已经刷新");
//    }
//
//    public SRS updateDict(SysDict dict) {
//        dao.update(dict);
//        removeDictCache();
//        return SRS.bySuccess();
//    }
//
//    public SRS pageDict(SRS param) {
//        MiWrapper<SysDict> wrapper = new MiWrapper<>(SysDict.class);
//        wrapper.eq("1", 1);
//
//        wrapper.like(notBlank(param.get("id")), SysDict.ID, param.get("id"));
//        wrapper.like(notBlank(param.get("code")), SysDict.CODE, param.get("code"));
//        wrapper.like(notBlank(param.get("value")), SysDict.VALUE, param.get("value"));
//        wrapper.like(notBlank(param.get("name")), SysDict.NAME, param.get("name"));
//        MiPage<SysDict> page = dao.page(new MiPage<>(param), wrapper);
//        return SRS.bySuccess(page);
//    }
//
//    public SRS listDict(SRS param) {
//        MiWrapper<SysDict> wrapper = new MiWrapper<>(SysDict.class);
//        wrapper.eq("1", 1);
//        wrapper.like(notBlank(param.get("id")), SysDict.ID, param.get("id"));
//        List<SysDict> list = dao.list(wrapper);
//        return SRS.bySuccess(list);
//    }
//
//    public SRS infoDict(SRS param) {
//        SysDict dict = dao.selectById(SysDict.class, param.getString("id"));
//        return SRS.bySuccess(dict);
//    }
//
//    public SRS insertMenu(SysMenu menu) {
//        dao.insert(menu);
//        //删除菜单缓存
//        CacheKit.instance().delete(CacheKey.key_menu_list);
//        return SRS.bySuccess();
//    }
//
//    public SRS deleteMenu(Object[] ids) {
//        dao.deleteByIds(SysMenu.class, Arrays.asList(ids));
//
//        //删除菜单缓存
//        CacheKit.instance().delete(CacheKey.key_menu_list);
//        //删除角色菜单缓存
//        List<String> roleIds = dao.listObj(new MiWrapper<>(SysLinkRoleMenu.class).select(SysLinkRoleMenu.ROLE_ID).in(SysLinkRoleMenu.MENU_ID, Arrays.asList(ids)), String.class);
//        for (String roleId : roleIds) {
//            CacheKit.instance().delete(CacheKey.key_role_menu_list + roleId);
//        }
//        return SRS.bySuccess();
//    }
//
//    public SRS updateMenu(SysMenu menu) {
//        dao.update(menu);
//        //删除菜单缓存
//        CacheKit.instance().delete(CacheKey.key_menu_list);
//        //删除角色菜单缓存
//        String menuId = menu.getId();
//        List<String> roleIds = dao.listObj(new MiWrapper<>(SysLinkRoleMenu.class).select(SysLinkRoleMenu.ROLE_ID).eq(SysLinkRoleMenu.MENU_ID, menuId), String.class);
//        for (String roleId : roleIds) {
//            deleteRoleMenuCache(roleId);
//        }
//        return SRS.bySuccess();
//    }
//
//    public SRS pageMenu(SRS param) {
//        MiWrapper<SysMenu> wrapper = new MiWrapper<>(SysMenu.class);
//        wrapper.eq("1", 1);
//        wrapper.orderByAsc("sort", "path");
//        wrapper.like(notBlank(param.get("id")), SysMenu.ID, param.get("id"));
//        wrapper.like(notBlank(param.get("title")), SysMenu.TITLE, param.get("title"));
//        wrapper.like(notBlank(param.get("path")), SysMenu.PATH, param.get("path"));
//        MiPage<SysMenu> page = dao.page(new MiPage<>(param), wrapper);
//        return SRS.bySuccess(page);
//    }
//
//    public SRS listMenu(SRS param) {
//        MiWrapper<SysMenu> wrapper = new MiWrapper<>(SysMenu.class);
//        wrapper.eq("1", 1);
//
//        wrapper.like(notBlank(param.get("id")), SysMenu.ID, param.get("id"));
//        List<SysMenu> list = dao.list(wrapper);
//        return SRS.bySuccess(list);
//    }
//
//    public SRS infoMenu(SRS param) {
//        SysMenu dict = dao.selectById(SysMenu.class, param.getString("id"));
//        return SRS.bySuccess(dict);
//    }
//
//    public SRS updateResource(SysResource resource) {
//        dao.update(resource);
//        // 删除资源缓存
//        CacheKit.instance().delete(CacheKey.key_resource_list);
//        // 删除角色资源缓存
//        String resourceId = resource.getId();
//        List<String> roleIds = dao.listObj(new MiWrapper<>(SysLinkRoleResource.class).select(SysLinkRoleResource.ROLE_ID).eq(SysLinkRoleResource.RESOURCE_ID, resourceId), String.class);
//        for (String roleId : roleIds) {
//            CacheKit.instance().delete(CacheKey.key_role_resource_url + roleId);
//            CacheKit.instance().delete(CacheKey.key_role_resource_list + roleId);
//        }
//        return SRS.bySuccess();
//    }
//
//    public SRS pageResource(SRS param) {
//        MiWrapper<SysResource> wrapper = new MiWrapper<>(SysResource.class);
//        wrapper.eq("1", 1);
//
//        wrapper.like(notBlank(param.get("id")), SysResource.ID, param.get("id"));
//        wrapper.like(notBlank(param.get("name")), SysResource.NAME, param.get("name"));
//        wrapper.like(notBlank(param.get("url")), SysResource.URL, param.get("url"));
//        MiPage<SysResource> page = dao.page(new MiPage<>(param), wrapper);
//        return SRS.bySuccess(page);
//    }
//
//    public SRS listResource(SRS param) {
//        MiWrapper<SysResource> wrapper = new MiWrapper<>(SysResource.class);
//        wrapper.eq("1", 1);
//
//        wrapper.like(notBlank(param.get("id")), SysResource.ID, param.get("id"));
//        List<SysResource> list = dao.list(wrapper);
//        return SRS.bySuccess(list);
//    }
//
//    public SRS infoResource(SRS param) {
//        SysResource dict = dao.selectById(SysResource.class, param.getString("id"));
//        return SRS.bySuccess(dict);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public SRS login(SRS param) {
//        String passwordParam = param.getString("password");
//        String account = param.get("account") + "";
//        String password = getPassword(passwordParam);
//
//        //用户信息
//        MiWrapper<SysUser> wrapper = new MiWrapper<>(SysUser.class);
//        SysUser userInfo = dao.selectOne(
//                wrapper.eq(SysUser.ACCOUNT, account)
//                        .eq(SysUser.PASSWORD, password)
//                        .eq(SysUser.IS_USE, 1)
//                        .eq(SysUser.IS_DELETE, 2));
//        if (userInfo == null) {
//            return SRS.byError("账号或密码错误！");
//        }
//
//        //角色信息
//        List<String> roleIds = dao.listObj(
//                new MiWrapper<>(SysLinkRoleUser.class)
//                        .select(SysLinkRoleUser.ROLE_ID)
//                        .eq(SysLinkRoleUser.USER_ID, userInfo.getId()),
//                String.class
//        );
//
//        //TODO 检验是否可登录
//        //        if (!roleIds.contains("1")) {
//        //            return SRS.byError("登录权限不足");
//        //        }
//
//        //生成Token
//        String loginIp = RequestKit.getIpAddr();
//        SRS tokenParam = SRS.create()
//                .set("userAccount", userInfo.getAccount())
//                .set("userId", userInfo.getId())
//                .set("roleIds", roleIds)
//                .set("ip", loginIp);
//        String token = JwtKit.createToken(tokenParam, dictService.getValue("system_jwt_token_time_out", "2"));
//        String refreshToken = MathKit.getMD5(UUID.randomUUID().toString());
//
//        //更新用户信息
//        userInfo.setPassword(null);
//        userInfo.setLoginIp(loginIp);
//        userInfo.setLoginTime(LocalDateTime.now());
//        userInfo.setRefreshToken(refreshToken);
//        dao.update(userInfo);
//
//        //角色
//        List<SysRole> roles = roleService.roles().stream().filter(it -> roleIds.contains(it.getId())).collect(Collectors.toList());
//
//        //菜单
//        ArrayList<SysMenu> sysMenus = getSysMenus(roleIds);
//        sysMenus.sort(Comparator.comparing(SysMenu::getSort));
//
//        //登录日志
//        SysLogLogin sysLogLogin = new SysLogLogin();
//        sysLogLogin.setIp(loginIp);
//        sysLogLogin.setTime(LocalDateTime.now());
//        sysLogLogin.setUserId(userInfo.getId());
//        logService.saveLog(sysLogLogin);
//
//        return SRS.bySuccess(
//                SRS.create()
//                        .set("userInfo", userInfo)
//                        .set("roles", roles)
//                        .set("menus", sysMenus)
//                        .set("token", token)
//                        .set("refreshToken", refreshToken)
//        );
//    }
//
//    public SRS updatePassword(SRS param) {
//        SysUser userInfo = dao.selectById(SysUser.class, userId());
//        String oldPassword = getPassword(param.getString("oldPassword"));
//        String newPassword = getPassword(param.getString("newPassword"));
//
//        if (!oldPassword.equals(userInfo.getPassword())) {
//            return SRS.byError("原密码输入错误");
//        }
//
//        userInfo.setPassword(newPassword);
//        dao.update(userInfo);
//        return SRS.bySuccess();
//    }
//
//    public SRS insertSysUser(SysUser param) {
//        String password = getPassword(param.getPassword());
//        param.setPassword(password);
//        dao.insert(param);
//        return SRS.bySuccess();
//    }
//
//    public SRS deleteSysUser(Object[] ids) {
//        dao.deleteByIds(SysMenu.class, Arrays.asList(ids));
//        return SRS.bySuccess();
//    }
//
//
//    @Transactional(rollbackFor = Exception.class)
//    public SRS updateSysUser(SysUser param) {
//        //修改角色资源
//        List roleIds = param.getParamRoleIds();
//        if (roleIds != null && roleIds.size() > 0) {
//            //删除关联
//            MiWrapper<SysLinkRoleUser> wrapper = new MiWrapper<>(SysLinkRoleUser.class);
//            wrapper.eq(SysLinkRoleUser.USER_ID, param.getId());
//            dao.delete(wrapper);
//            //添加关联
//            ArrayList<SysLinkRoleUser> linkRoleUsers = new ArrayList<>();
//            for (Object roleId : roleIds) {
//                SysLinkRoleUser linkRoleUser = new SysLinkRoleUser();
//                linkRoleUser.setRoleId(roleId + "");
//                linkRoleUser.setUserId(param.getId());
//                linkRoleUsers.add(linkRoleUser);
//            }
//            dao.insertBatch(linkRoleUsers);
//        }
//
//
//        //修改用户信息
//        dao.update(param);
//
//        return SRS.bySuccess();
//    }
//
//    public SRS listSysUser(SysUser param) {
//        return SRS.bySuccess(dao.list(SysUser.class));
//    }
//
//    public SRS pageSysUser(SRS param) {
//        MiWrapper<SysUser> wrapper = new MiWrapper<>(SysUser.class);
//
//        wrapper.select("sys_user.*", "group_concat( lu.role_id ) AS roleIds", "group_concat( sr.name ) AS roleNames");
//        wrapper.leftJoin("sys_link_role_user lu", "lu.user_id = sys_user.id");
//        wrapper.leftJoin("sys_role sr", "sr.id = lu.role_id");
//
//        wrapper.eq("1", 1);
//        wrapper.like(notBlank(param.get("name")), "sys_user.name", param.get("name"));
//        wrapper.like(notBlank(param.get("account")), "sys_user.account", param.get("account"));
//        wrapper.groupBy("sys_user.id ");
//
//        MiPage<SysUser> page = dao.page(new MiPage<>(param), wrapper);
//        return SRS.bySuccess(page);
//    }
//
//
//    public SRS infoSysUser(SRS param) {
//        return SRS.bySuccess(dao.selectById(SysUser.class, param.getString("id")));
//    }
//
//
//    public SRS refreshToken(SRS param) {
//        return null;
//    }
//
//    //    登录日志
//
//    public SRS loginTimeline(SRS param) {
//        List<SysLogLogin> list = dao.list("select * from sys_log_login where user_id=" + userId() + " order by id desc limit 10", SysLogLogin.class);
//        return SRS.bySuccess(list);
//    }
//
//
//    public SRS resetPassword(SRS param) {
//        String password = getPassword(param.getString("password"));
//        SysUser sysUser = new SysUser();
//        sysUser.setId(param.getString("id"));
//        sysUser.setPassword(password);
//
//        dao.update(sysUser);
//        return SRS.bySuccess();
//    }
//
//
//    public void updateVersion(Object o) {
//        String admin_version = dictService.getValue("admin_version");
//        String server_version = dictService.getValue("server_version");
//
//        int html_version = Integer.parseInt(admin_version) + 1;
//        int api_version = Integer.parseInt(server_version) + 1;
//        dictService.setValue("admin_version", html_version + "");
//        dictService.setValue("server_version", api_version + "");
//
//        log.info("更新版本号 admin_version  {}===>{} ", admin_version, html_version);
//        log.info("更新版本号 server_version   {}===>{} ", server_version, api_version);
//    }
//
//
//    @Transactional(rollbackFor = Exception.class)
//    public SRS refreshMenu(SysMenu[] menus) {
//        //前台当前全部路由
//        List<SysMenu> frontMenus = Arrays.asList(menus);
//        List<String> frontMenuPath = frontMenus.stream().map(SysMenu::getPath).collect(Collectors.toList());
//        //数据库中的全部路由
//        List<SysMenu> dbMenus = dao.list(SysMenu.class);
//        List<String> dbMenuPath = dbMenus.stream().map(SysMenu::getPath).collect(Collectors.toList());
//
//        //筛选处理结果
//        List<SysMenu> insertMenus = new ArrayList<>();
//        List<SysMenu> updateMenus = new ArrayList<>();
//        List<String> deleteMenuIds = new ArrayList<>();
//        for (SysMenu frontMenu : frontMenus) {
//            String path = frontMenu.getPath();
//            //数据库中不存在-添加
//            if (!dbMenuPath.contains(path)) {
//                insertMenus.add(frontMenu);
//            }
//            //数据库中存在-更新
//            if (dbMenuPath.contains(path)) {
//                List<SysMenu> dbList = dbMenus.stream().filter(it -> it.getPath().equals(path)).collect(Collectors.toList());
//                List<SysMenu> frontList = frontMenus.stream().filter(it -> it.getPath().equals(path)).collect(Collectors.toList());
//                frontList.get(0).setId(dbList.get(0).getId());
//                updateMenus.add(frontList.get(0));
//            }
//        }
//
//        // 暂时注释掉删除功能
//        // 筛选前端不存在-删除
//        //        for (SysMenu dbMenu : dbMenus) {
//        //            if (!frontMenuPath.contains(dbMenu.getPath())) {
//        //                deleteMenuIds.add(dbMenu.getId());
//        //            }
//        //        }
//        //删除不存在的菜单
//        //        if (deleteMenuIds.size() > 0) {
//        //            //删除菜单
//        //            dao.deleteByIds(SysMenu.class, deleteMenuIds);
//        //            //删除关联
//        //            dao.delete(new MiWrapper<>(SysLinkRoleMenu.class).in(SysLinkRoleMenu.MENU_ID, deleteMenuIds));
//        //        }
//
//        //更新菜单信息
//        dao.updateBatchById(updateMenus);
//
//        //批量插入新增菜单
//        dao.insertBatch(insertMenus);
//        //修改自己的父ID
//        ArrayList<SysMenu> allMenus = new ArrayList<>();
//        allMenus.addAll(insertMenus);
//        allMenus.addAll(updateMenus);
//        for (SysMenu insertMenu : insertMenus) {
//            String path = insertMenu.getPath();
//            String parentPath = insertMenu.getParentPath();
//            if (path.equals(parentPath) || parentPath == null) {
//                insertMenu.setPid("0");
//                insertMenu.setParentPath(parentPath);
//                continue;
//            }
//
//            for (SysMenu menu : allMenus) {
//                if (menu.getPath().equals(parentPath)) {
//                    insertMenu.setPid(menu.getId());
//                    insertMenu.setParentPath(menu.getPath());
//
//                }
//            }
//
//        }
//
//        //批量修改菜单
//        dao.updateBatchById(insertMenus);
//
//        //删除菜单缓存
//        CacheKit.instance().delete(CacheKey.key_menu_list);
//        //删除角色菜单缓存
//        List<String> roleIds = dao.listObj(new MiWrapper<>(SysRole.class).select("id").eq("1", 1), String.class);
//        for (String roleId : roleIds) {
//            deleteRoleMenuCache(roleId);
//        }
//        return SRS.bySuccess();
//    }
//
//    //     * 获取用户的全部菜单
//    private ArrayList<SysMenu> getSysMenus(List<String> roleIds) {
//        HashSet<SysMenu> hashSetMenus = new HashSet<>();
//        List<SysMenu> menus = roleService.roleMenuList(roleIds);
//        List<SysMenu> menus1 = menus();
//        for (SysMenu menu : menus) {
//            String pid = menu.getPid();
//            List<SysMenu> collect = menus1.stream().filter(it -> it.getId().equals(pid)).collect(Collectors.toList());
//            hashSetMenus.addAll(collect);
//        }
//        hashSetMenus.addAll(menus);
//        return new ArrayList<>(hashSetMenus);
//    }
//
//
//    //清除角色菜单缓存
//    private void deleteRoleMenuCache(Object roleId) {
//        CacheKit.instance().delete(CacheKey.key_role_menu_url + roleId);
//        CacheKit.instance().delete(CacheKey.key_role_menu_id + roleId);
//        CacheKit.instance().delete(CacheKey.key_role_menu_list + roleId);
//    }
//}
//
//
////    //    @Transactional
////
////    public SRS test0() {
//////        //一主多从
//////        SysDict sysDict = new SysDict();
//////        sysDict.setId("200");
//////        sysDict.setCode("标记" + biaoji);
//////        sysDict.setValue("主库");
//////        long deleteById1 = miDao.deleteById(SysDict.class, 200);
//////        long insert1 = miDao.insert(sysDict);
//////        String master = miDao.selectById(SysDict.class, 200).getValue();
//////
//////        SysDict sysDict1 = new SysDict();
//////        sysDict1.setId("200");
//////        sysDict1.setCode("标记" + biaoji);
//////        sysDict1.setValue("从库1");
//////        long deleteById2 = miDao.deleteById(SysDict.class, 200, "slave_1");
//////        long insert2 = miDao.insert(sysDict1, "slave_1");
//////        String slave1 = miDao.selectById(SysDict.class, 200, "slave_1").getValue();
//////
//////
//////        SysDict sysDict2 = new SysDict();
//////        sysDict2.setId("200");
//////        sysDict2.setCode("标记" + biaoji);
//////        sysDict2.setValue("从库2");
//////        long deleteById3 = miDao.deleteById(SysDict.class, 200, "slave_2");
//////        long insert3 = miDao.insert(sysDict2, "slave_2");
//////        String slave2 = miDao.selectById(SysDict.class, 200, "slave_2").getValue();
////
//////        if (1 == 1)throw new MiException("故意报错");
//////
//////        SRS srs = test1();
//////        SRS srs1 = test2();
//////        SRS srs2 = test3();
//////        System.out.println(srs.toString());
//////        System.out.println(srs1.toString());
//////        System.out.println(srs2.toString());
////        return SRS.bySuccess();
////    }
////
////    @DS("master")
////
////    @Transactional(rollbackFor = Throwable.class)
////    public SRS test1(String biaoji) {
////        SysDict sysDict = new SysDict();
////        sysDict.setId("200");
////        sysDict.setCode("00000" + biaoji);
////        sysDict.setValue("主库" + biaoji);
////        long deleteById1 = dao.deleteById(SysDict.class, 200);
////        long insert1 = dao.insert(sysDict);
////        String master = dao.selectById(SysDict.class, 200).getValue();
//////        return SRS.bySuccess();
////        if (1 == 1) throw new MiException("故意报错");
////        return SRS.bySuccess(master);
////    }
////
////    @DS("slave_1")
////
////    @Transactional(rollbackFor = Throwable.class)
////    public SRS test2(String biaoji) { //一主多从
////        SysDict sysDict1 = new SysDict();
////        sysDict1.setId("200");
////        sysDict1.setCode("00000" + biaoji);
////        sysDict1.setValue("从库1" + biaoji);
////        long deleteById2 = dao.deleteById(SysDict.class, 200);
////        long insert2 = dao.insert(sysDict1);
////        String slave1 = dao.selectById(SysDict.class, 200).getValue();
////        return SRS.bySuccess(slave1);
//////        return SRS.bySuccess();
////    }
////
////
////    @DS("slave_2")
////    @Transactional(rollbackFor = Throwable.class)
////    public SRS test3(String biaoji) { //一主多从
////        SysDict sysDict2 = new SysDict();
////        sysDict2.setId("200");
////        sysDict2.setCode("00000" + biaoji);
////        sysDict2.setValue("从库2" + biaoji);
////        long deleteById3 = dao.deleteById(SysDict.class, 200);
////        long insert3 = dao.insert(sysDict2);
////        String slave2 = dao.selectById(SysDict.class, 200).getValue();
//////        return SRS.bySuccess();
////        return SRS.bySuccess(slave2);
////    }
//
