/**
 * @file: JedisDistributeLock.java
 * @author: linlu
 * @date: 2020/8/27
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.cache.redis.lock;

import com.sd365.common.cache.redis.util.JedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import java.util.Collections;

/**
 * @PackgeName: com.sd365.common.cache.redis.lock
 * @ClassName: JedisDistributeLock
 * @Author: ll
 * Date: 2020/8/27
 * project name: sd365-common-common-g05
 * @Version: 1.0.0
 * @Description: jedis实现分布式锁
 */
public class JedisDistributeLock implements Lock{
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;
    private Jedis jedis = null;

    /**
     * 主机从主应用配置文件取得
     */
    @Value(value = "spring.redis.host")
    private String host;
    /**
     * 端口从主配置文件取得
     */
    @Value(value = "spring.redis.port")
    private int port;
    /**
     *
     */
    @Value(value = "spring.redis.timeout")
    private int timeout;

    @Override
    public  boolean getLock(String key, String id, int expireTime) {
        jedis=  JedisUtil.getResource(host,port,timeout);
        String result = jedis.set(key, id, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        return LOCK_SUCCESS.equals(result);
    }

    @Override
    public boolean releaseLock(String key, String id) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(id));
        return RELEASE_SUCCESS.equals(result);
    }

//    @Override
//    public boolean getLock(String key, int expireTime) {
//        return false;
//    }
//
//    @Override
//    public boolean releaseLock(String key) {
//        return false;
//    }
}
