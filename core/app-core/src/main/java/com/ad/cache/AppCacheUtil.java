package com.ad.cache;

import com.ad.cache.ehcache.AppEhCache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author CoderYoung
 */
public class AppCacheUtil {

    private static final IAppCache CACHE_MANAGER = new AppEhCache();

    public static final String CACHE_SHIRO_TOKEN_KEY = "shiroTokenCache";
    public static final String CACHE_SHIRO_USER = "shiro_user_cache";
    public static final String CACHE_USER_KEY = "userCache";

    private static final Map<String, AppCache> CACHE_GROUPS_MAP = new HashMap<>();

    public static final AppCache USER_CACHE = new AppCache(CACHE_MANAGER, CACHE_USER_KEY);
//    public static final AppCache SHIRO_CACHE = new AppCache(CACHE_MANAGER, CACHE_SHIRO_USER);
    public static final AppCache TOKEN_CACHE = new AppCache(CACHE_MANAGER, CACHE_SHIRO_TOKEN_KEY);

    static {
        CACHE_GROUPS_MAP.put(CACHE_USER_KEY, USER_CACHE);
//        CACHE_GROUPS_MAP.put(CACHE_SHIRO_USER, SHIRO_CACHE);
        CACHE_GROUPS_MAP.put(CACHE_SHIRO_TOKEN_KEY, TOKEN_CACHE);
    }


    public static AppCache group(String gName) {
        if (CACHE_GROUPS_MAP.containsKey(gName)) {
            return CACHE_GROUPS_MAP.get(gName);
        } else {
            AppCache cache = new AppCache(CACHE_MANAGER, gName);
            CACHE_GROUPS_MAP.put(gName, cache);
            return cache;
        }
    }

    public static class AppCache {

        private IAppCache cache;
        private final String cacheGroup;

        public AppCache(IAppCache _cache, String group) {
            this.cacheGroup = group;
            this.cache = _cache;
        }

        public void put(String key, Object val) {
            cache.put(cacheGroup, key, val);
        }

        public Object get(String key) {
            return cache.get(cacheGroup, key);
        }

        public boolean exists(String key) {
            return this.get(key) != null;
        }

        public void remove(String key) {
            cache.remove(cacheGroup, key);
        }

        public void clear() {
            cache.clear(cacheGroup);
        }

        public int size() {
            return cache.size(cacheGroup);
        }

        public Set<String> keys() {
            return cache.keys(cacheGroup);
        }

        public Collection<?> values() {
            return cache.values(cacheGroup);
        }

        public void destroy() {
            cache.destroy();
        }
    }

}
