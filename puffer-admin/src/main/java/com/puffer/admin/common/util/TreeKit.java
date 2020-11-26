package com.puffer.admin.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * 遍历树结构 根据 key parentKey
 *
 * @author WangYongJi
 */
public class TreeKit {

    public static void main(String[] args) {
        testListToTree();
    }

    /**
     * 测试无线层级路由
     * 启动main方法调用即可
     */
    public static void testListToTree() {
        String key = "id";
        String parentKey = "pid";
        String childArrayName = "children";
        JSONArray genList = getGenList(key, parentKey);
        JSONArray notGenList = getNotGenList(key, parentKey);
        ArrayList<JSONObject> arrayList = listToTree(genList, notGenList, key, parentKey, childArrayName);
        System.out.println(JSON.toJSONString(arrayList));
    }

    /**
     * 把完整的数据集合，排成树结构
     * <p>
     * 返回按指定 key parentKey 进行排序
     *
     * @param parentObject     根节点对象
     * @param removeParentList 去除根节点的之后的全部数据
     * @param key              自己的标记，例：id
     * @param parentKey        父标记，例 pid
     * @param childArrayName   子数组名称
     */
    private static JSONArray listToTree(JSONObject parentObject, JSONArray removeParentList, String key, String parentKey, String childArrayName) {
        JSONArray childList = new JSONArray();
        for (int i = 0; i < removeParentList.size(); i++) {
            JSONObject now_obj = (JSONObject) removeParentList.get(i);
            String now_pid = now_obj.getString(parentKey);
            //查得到
            if (now_pid.equals(parentObject.getString(key))) {
                childList.add(now_obj); // 同级别添加
                listToTree(now_obj, removeParentList, key, parentKey, childArrayName);//继续递归
            }
            //非同级别不作处理
            else {

            }
        }
        if (childList.size() != 0) {
            parentObject.put(childArrayName, childList);
        }
        return childList;
    }

    /**
     * 把完整的数据集合，排成树结构
     * <p>
     * 返回按指定 key parentKey 进行排序
     *
     * @param jsonGenList          全部根节点数据
     * @param jsonRemoveParentList 去除根节点的之后的全部数据
     * @param key                  自己的标记，例：id
     * @param parentKey            父标记，例 pid
     * @param childArrayName       子数组名称
     */
    public static ArrayList<JSONObject> listToTree(List jsonGenList, List jsonRemoveParentList, String key, String parentKey, String childArrayName) {
        ArrayList<JSONObject> arrayList = new ArrayList<>();
        JSONArray jsonGenList1 = JSON.parseArray(JSON.toJSONString(jsonGenList, SerializerFeature.WriteMapNullValue));
        JSONArray jsonNotGenList1 = JSON.parseArray(JSON.toJSONString(jsonRemoveParentList, SerializerFeature.WriteMapNullValue));
        for (Object o : jsonGenList1) {
            JSONArray array = listToTree(((JSONObject) o), jsonNotGenList1, key, parentKey, childArrayName);
            ((JSONObject) o).put(childArrayName, array);
            arrayList.add(((JSONObject) o));
        }
        return arrayList;
    }

    public static ArrayList<JSONObject> listToTree(List jsonGenList, List jsonRemoveParentList) {
        return listToTree(jsonGenList, jsonRemoveParentList, "id", "pid", "children");
    }

    private static JSONArray getGenList(String key, String parentKey) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(key, "1");
        jsonObject.put(parentKey, "0");
        jsonObject.put("name", "根节点1");
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put(key, "2");
        jsonObject.put(parentKey, "0");
        jsonObject.put("name", "根节点2");
        jsonArray.add(jsonObject);

        return jsonArray;
    }

    private static JSONArray getNotGenList(String key, String parentKey) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, "10");
        jsonObject.put(parentKey, "1");
        jsonObject.put("name", "子节点10");
        jsonArray.add(jsonObject);


        jsonObject = new JSONObject();
        jsonObject.put(key, "20");
        jsonObject.put(parentKey, "2");
        jsonObject.put("name", "子节点20");
        jsonArray.add(jsonObject);


        jsonObject = new JSONObject();
        jsonObject.put(key, "30");
        jsonObject.put(parentKey, "10");
        jsonObject.put("name", "子节点30");
        jsonArray.add(jsonObject);


        jsonObject = new JSONObject();
        jsonObject.put(key, "40");
        jsonObject.put(parentKey, "20");
        jsonObject.put("name", "子节点40");
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put(key, "50");
        jsonObject.put(parentKey, "30");
        jsonObject.put("name", "子节点50");
        jsonArray.add(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put(key, "60");
        jsonObject.put(parentKey, "40");
        jsonObject.put("name", "子节点60");
        jsonArray.add(jsonObject);

        return jsonArray;
    }
}
