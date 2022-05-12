package com.ad.core.system.auth.cache;

import com.ad.cache.AppCacheUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author CoderYoung
 */
@SuppressWarnings({"rawtypes", "unchecked"})
//@Component("adServerShiroCache")
public class ShiroCache<K, V> implements Cache<K, V> {

    @Override
    public V get(K k) throws CacheException {
        return (V) AppCacheUtil.TOKEN_CACHE.get(k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        AppCacheUtil.TOKEN_CACHE.put(k.toString(), v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = get(k);
        AppCacheUtil.TOKEN_CACHE.remove(k.toString());
        return v;
    }

    @Override
    public void clear() throws CacheException {
        AppCacheUtil.TOKEN_CACHE.clear();
    }

    @Override
    public int size() {
        return AppCacheUtil.TOKEN_CACHE.size();
    }

    @Override
    public Set<K> keys() {
        return (Set<K>) AppCacheUtil.TOKEN_CACHE.keys();
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new HashSet<>();
        for (Object value : AppCacheUtil.TOKEN_CACHE.values()) {
            values.add((V) value);
        }
        return values;
    }
}
