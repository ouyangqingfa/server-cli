package com.ad.cache;

import java.util.Collection;
import java.util.Set;

/**
 * @author CoderYoung
 */
public interface IAppCache {

    /**
     * 当前操作的缓存分组
     *
     * @param group
     */
    void setGroup(String group);

    /**
     * put
     *
     * @param key
     * @param val
     */
    void put(String key, Object val);

    /**
     * get
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * remove
     *
     * @param key
     */
    void remove(String key);

    /**
     * clear
     */
    void clear();

    /**
     * size
     *
     * @return
     */
    int size();

    /**
     * keys
     *
     * @return
     */
    Set<String> keys();

    /**
     * values
     *
     * @return
     */
    Collection<?> values();

    /**
     * 关闭释放缓存
     */
    void destroy();

}
