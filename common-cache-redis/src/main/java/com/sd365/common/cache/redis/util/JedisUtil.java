/**
 * @file: JedisUtil.java
 * @author: linlu
 * @date: 2020/8/27
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.cache.redis.util;

import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @PackgeName: com.sd365.common.cache.redis.util
 * @ClassName: JedisUtil
 * @Author: ll
 * Date: 2020/8/27
 * project name: sd365-common-common-g05
 * @Version:
 * @Description: jedis工具类
 */
public class JedisUtil {
    private int port;
    private static JedisPool jedisPool ;
    private static JedisPoolConfig jedisPoolConfig;
    static {
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10000);
        jedisPoolConfig.setMaxWaitMillis(10000);
        jedisPoolConfig.setMaxIdle(1000);
    }

    public static Jedis getResource(String host,int port,int timeout ){
           jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout);
           return  jedisPool.getResource();
    }

}
