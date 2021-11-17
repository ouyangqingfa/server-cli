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
    private final List<String> cacheGroupList;
    private Cache<String, Object> cache;

    public AppEhCache() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("defaultCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class, ResourcePoolsBuilder.heap(EHCACHE_HEAP_SIZE))
                        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofHours(1)))
        ).build();
        cacheManager.init();
        cacheGroupList = new ArrayList<>();
    }

    @Override
    public void setGroup(String group) {
        if (!cacheGroupList.contains(group)) {
            cache = cacheManager.createCache(group,
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class, ResourcePoolsBuilder.heap(EHCACHE_HEAP_SIZE))
                            .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofHours(1)))
            );
            cacheGroupList.add(group);
        } else {
            cache = cacheManager.getCache(group, String.class, Object.class);
        }
    }

    @Override
    public void put(String key, Object val) {
        cache.put(key, val);
    }

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<String> keys() {
        Set<String> keys = new HashSet<>();
        for (Cache.Entry<String, Object> entry : cache) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    @Override
    public Collection<?> values() {
        Collection<Object> values = new HashSet<>();
        for (Cache.Entry<String, Object> entry : cache) {
            values.add(entry.getValue());
        }
        return values;
    }

    @Override
    public void destroy() {
        cacheManager.close();
    }
}
