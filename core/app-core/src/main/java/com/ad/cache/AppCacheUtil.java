package com.ad.cache;

import com.ad.cache.ehcache.AppEhCache;
import com.ad.cache.redis.AppRedisCache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * @author CoderYoung
 */
@Component
public class AppCacheUtil {

    private static final AppCache cache = new AppCache();

    public static AppCache group(String gName) {
        cache.setGroup(gName);
        return cache;
    }


    public static class AppCache implements IAppCache {

        private AppEhCache ehCache;
        private AppRedisCache redisCache;

        private IAppCache getCache() {
            if (ehCache == null) {
                ehCache = new AppEhCache();
            }
            return ehCache;
        }

        @Override
        public void setGroup(String group) {
            getCache().setGroup(group);
        }

        @Override
        public void put(String key, Object val) {
            getCache().put(key, val);
        }

        @Override
        public Object get(String key) {
            return getCache().get(key);
        }


        public boolean exists(String key) {
            return this.get(key) != null;
        }

        @Override
        public void remove(String key) {
            getCache().remove(key);
        }

        @Override
        public void clear() {
            getCache().clear();
        }

        @Override
        public int size() {
            return getCache().size();
        }

        @Override
        public Set<String> keys() {
            return getCache().keys();
        }

        @Override
        public Collection<?> values() {
            return getCache().values();
        }

        @Override
        public void destroy() {
            getCache().destroy();
        }
    }

}
