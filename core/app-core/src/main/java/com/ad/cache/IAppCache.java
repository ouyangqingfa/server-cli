package com.ad.cache;

import java.util.Collection;
import java.util.Set;

/**
 * @author CoderYoung
 */
public interface IAppCache {

    /**
     * put
     *
     * @param key
     * @param val
     */
    void put(String group, String key, Object val);

    /**
     * get
     *
     * @param key
     * @return
     */
    Object get(String group, String key);

    /**
     * remove
     *
     * @param key
     */
    void remove(String group, String key);

    /**
     * clear
     */
    void clear(String group);

    /**
     * size
     *
     * @return
     */
    int size(String group);

    /**
     * keys
     *
     * @return
     */
    Set<String> keys(String group);

    /**
     * values
     *
     * @return
     */
    Collection<?> values(String group);

    /**
     * 关闭释放缓存
     */
    void destroy();

}
