package com.dhj.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis-string")
public class StringRedisController {

    //注入StringRedisTemplate(Spring自动配置,直接使用)
    //StringRedisTemplates是RedisTemplate的子类,不会出现乱码
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/set")
    public Map<String, Object> set(@RequestParam String key, @RequestParam String value, @RequestParam(defaultValue = "60") long timeout) {
        //设置key-value
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "缓存设置成功");
        return result;
    }

    @GetMapping("/get")
    public Map<String, Object> get(@RequestParam String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        HashMap<String, Object> result = new HashMap<>();
        if (value != null) {
            result.put("code", 200);
            result.put("msg", "缓存查询成功===>"+value);
        } else {
            result.put("code", 200);
            result.put("msg", "不存在该键");
        }
        return result;
    }


}

