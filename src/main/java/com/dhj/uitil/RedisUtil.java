package com.dhj.uitil;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

//放入spring中当做组件
@Component
public class RedisUtil {


    // 注入Spring自动配置的RedisTemplate
    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    // ====================== ✅ Hash（哈希）核心操作 ======================

    //设置单个属性-值
    public void hashPut(String hashKey, String field, Object value) {
        redisTemplate.opsForHash().put(hashKey, field, value);
    }

    //获取单个属性值
    public Object hashGet(String hashKey, String field) {
        return redisTemplate.opsForHash().get(hashKey, field);
    }

    //批量设置属性-值
    public void hashPutAll(String hashKey, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(hashKey, map);
    }

    //获取哈希表中所有属性和值
    public Map<Object, Object> hashGetAll(String hashKey) {
        return redisTemplate.opsForHash().entries(hashKey);
    }

    // ====================== ✅ List（列表）核心操作 ======================

    //从尾部添加元素(队列) 1,2,3 添加4 ===>1,2,3,4
    public Long listPush(String listKey, Object value) {
//        Long l = redisTemplate.opsForList().leftPush(listKey, value);
        return redisTemplate.opsForList().rightPush(listKey, value);

    }

    //从头部添加元素(栈)




    //获取列表中的全部元素




    //从尾部删除元素,并返回元素


}
