package com.dhj.controller;

import com.dhj.uitil.RedisUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisTemplateController {
    //注入封装好的工具类
    @Resource
    private RedisUtil redisUtil;

    // ====================== ✅测试Hash 数据结构 ======================

    //设置单个hash表的值
    @GetMapping("/hash/put")
    public Map<String, Object> hashPut(
            @RequestParam String hashKey,
            @RequestParam String field,
            @RequestParam Object value
    ) {
        redisUtil.hashPut(hashKey, field, value);
        HashMap<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("msg", "Hash单个属性设置成功");
        return res;
    }

    //获取单个hash属性值
    @GetMapping("/hash/get")
    public Map<String, Object> hashGet(
            @RequestParam String hashKey,
            @RequestParam String field
    ) {
        Object value = redisUtil.hashGet(hashKey, field);
        HashMap<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("msg", "Hash单个属性查询成功");
        res.put("value", value);
        return res;
    }

    //获取Hash所有属性
    @GetMapping("/hash/getAll")
    public Map<String, Object> hashGetAll(
            @RequestParam String hashKey
    ) {
        Map<Object, Object> value = redisUtil.hashGetAll(hashKey);
        HashMap<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("msg", "Hash单个属性查询成功");
        res.put("value", value);
        return res;
    }

    // ====================== ✅测试 List 数据结构 ======================
    //从右边插入新元素
    @PostMapping("/list/push")
    public Map<String, Object> listPut(
        @RequestParam String listKey,
        @RequestParam Object value
    ){
        Long l = redisUtil.listPush(listKey, value);
        HashMap<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("msg", "list插入元素成功");
        res.put("当前list元素个数:", l);
        return res;
    }






}
