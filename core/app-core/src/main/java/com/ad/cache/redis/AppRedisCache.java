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

    private String prefix = "";

    private String getKey(String key) {
        return prefix + "_" + key;
    }

    @Override
    public void setGroup(String group) {
        prefix = group;
    }

    @Override
    public void put(String key, Object val) {
        redisCache.opsForValue().set(getKey(key), val);
    }

    @Override
    public Object get(String key) {
        return redisCache.opsForValue().get(getKey(key));
    }

    @Override
    public void remove(String key) {
        redisCache.delete(getKey(key));
    }

    @Override
    public void clear() {
        redisCache.delete(this.keys());
    }

    @Override
    public int size() {
        return redisCache.keys(prefix + "*").size();
    }

    @Override
    public Set<String> keys() {
        return redisCache.keys(prefix + "*");
    }

    @Override
    public Collection<?> values() {
        Collection<Object> collection = new HashSet<>();
        Set<String> keys = this.keys();
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
