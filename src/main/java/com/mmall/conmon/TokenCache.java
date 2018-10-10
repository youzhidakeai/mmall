package com.mmall.conmon;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: kkc
 * Date: 2018-09-13
 * Time: 下午9:38
 */
@Slf4j
public class TokenCache {

    public static final String TOKEN_PREFIX = "token_";

    //构建本地cache
    //LRU算法
    private static LoadingCache<String,String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).
            maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS).build(new CacheLoader<String, String>() {
                //默认加载数据实现,当调用get取值的时候,如果key没有对应的值,就调用这个方法进行加载
                @Override
                public String load(String s) throws Exception {
                     return "null";
                 }
            });

    public static void setKey(String key,String value){
        localCache.put(key,value);
    }

    public static String getKey(String key){
        String value = null;
        try {
            value = localCache.get(key);
            if ("null".equals(value)){
                return null;
            }
        }catch (Exception e){
            log.error("localcache get error",e);
        }
        return null;
    }
}
