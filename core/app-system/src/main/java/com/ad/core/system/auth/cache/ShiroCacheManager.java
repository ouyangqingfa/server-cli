package com.ad.core.system.auth.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CoderYoung
 */
@SuppressWarnings({"rawtypes", "unchecked"})
//@Component("adServerShiroCacheManager")
public class ShiroCacheManager implements CacheManager {

    //    @Autowired
//    @Qualifier("adServerShiroCache")
    private final ShiroCache shiroCache;

    public ShiroCacheManager() {
        shiroCache = new ShiroCache();
    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return shiroCache;
    }
}
