package com.ad.cache.ehcache;

import com.ad.cache.IAppCache;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;
import java.util.*;

/**
 * @author CoderYoung
 */
public class AppEhCache implements IAppCache {

    /**
     * Ehcache堆大小，超过将序列化
     */
    private static final int EHCACHE_HEAP_SIZE = 100;

    private final CacheManager cacheManager;

    public AppEhCache() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("defaultCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class, ResourcePoolsBuilder.heap(EHCACHE_HEAP_SIZE))
                        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofHours(1)))
        ).build();
        cacheManager.init();
    }


    private final Map<String, Cache<String, Object>> cacheMap = new HashMap<>();

    private Cache<String, Object> getCache(String group) {
        if (cacheMap.containsKey(group)) {
            return cacheMap.get(group);
        } else {
            Cache<String, Object> cache = cacheManager.createCache(group,
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class, ResourcePoolsBuilder.heap(EHCACHE_HEAP_SIZE))
                            .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofHours(1)))
            );
            cacheMap.put(group, cache);
            return cache;
        }
    }

    @Override
    public void put(String group, String key, Object val) {
        getCache(group).put(key, val);
    }

    @Override
    public Object get(String group, String key) {
        return getCache(group).get(key);
    }

    @Override
    public void remove(String group, String key) {
        getCache(group).remove(key);
    }

    @Override
    public void clear(String group) {
        getCache(group).clear();
    }

    @Override
    public int size(String group) {
        return 0;
    }

    @Override
    public Set<String> keys(String group) {
        Set<String> keys = new HashSet<>();
        for (Cache.Entry<String, Object> entry : getCache(group)) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    @Override
    public Collection<?> values(String group) {
        Collection<Object> values = new HashSet<>();
        for (Cache.Entry<String, Object> entry : getCache(group)) {
            values.add(entry.getValue());
        }
        return values;
    }

    @Override
    public void destroy() {
        cacheManager.close();
    }
}
