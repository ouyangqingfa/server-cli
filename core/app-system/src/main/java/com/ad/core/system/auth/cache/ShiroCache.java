package com.ad.core.system.auth.cache;

import com.ad.cache.AppCacheUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author CoderYoung
 */
@SuppressWarnings({"unchecked"})
@Service("adServerShiroCache")
public class ShiroCache<K, V> implements Cache<K, V> {

    public static final String SHIRO_CACHE_KEY = "shiroTokenCache";

    @Override
    public V get(K k) throws CacheException {
        return (V) AppCacheUtil.group(SHIRO_CACHE_KEY).get(k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        AppCacheUtil.group(SHIRO_CACHE_KEY).put(k.toString(), v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = get(k);
        AppCacheUtil.group(SHIRO_CACHE_KEY).remove(k.toString());
        return v;
    }

    @Override
    public void clear() throws CacheException {
        AppCacheUtil.group(SHIRO_CACHE_KEY).clear();
    }

    @Override
    public int size() {
        return AppCacheUtil.group(SHIRO_CACHE_KEY).size();
    }

    @Override
    public Set<K> keys() {
        return (Set<K>) AppCacheUtil.group(SHIRO_CACHE_KEY).keys();
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new HashSet<>();
        for (Object value : AppCacheUtil.group(SHIRO_CACHE_KEY).values()) {
            values.add((V) value);
        }
        return values;
    }
}
