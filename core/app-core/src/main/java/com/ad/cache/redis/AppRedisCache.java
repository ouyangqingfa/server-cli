package com.ad.cache.redis;

import com.ad.cache.IAppCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 使用Redis缓存
 *
 * @author CoderYoung
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class AppRedisCache implements IAppCache {

    private final RedisTemplate redisCache;

    public AppRedisCache(RedisTemplate redisTemplate) {
        this.redisCache = redisTemplate;
    }


    private String getKey(String group, String key) {
        return group + "_" + key;
    }

    @Override
    public void put(String group, String key, Object val) {
        redisCache.opsForValue().set(getKey(group, key), val);
    }

    @Override
    public Object get(String group, String key) {
        return redisCache.opsForValue().get(getKey(group, key));
    }

    @Override
    public void remove(String group, String key) {
        redisCache.delete(getKey(group, key));
    }

    @Override
    public void clear(String group) {
        redisCache.delete(this.keys(group));
    }

    @Override
    public int size(String group) {
        return redisCache.keys(group + "*").size();
    }

    @Override
    public Set<String> keys(String group) {
        return redisCache.keys(group + "*");
    }

    @Override
    public Collection<?> values(String group) {
        Collection<Object> collection = new HashSet<>();
        Set<String> keys = this.keys(group);
        ValueOperations ops = redisCache.opsForValue();
        for (String s : keys) {
            collection.add(ops.get(s));
        }
        return collection;
    }

    @Override
    public void destroy() {

    }
}
