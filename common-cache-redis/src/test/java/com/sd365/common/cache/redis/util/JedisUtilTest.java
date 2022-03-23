/**
 * @file: JedisUtilTest.java
 * @author: linlu
 * @date: 2020/8/29
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.cache.redis.util;

import static org.junit.Assert.*;

import com.sd365.common.cache.redis.service.RedisService;
import com.sd365.common.cache.redis.service.impl.RedisServiceImpl;
import org.junit.Before;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @PackgeName: com.sd365.common.cache.redis.util
 * @ClassName: JedisUtilTest
 * @Author: ll
 * Date: 2020/8/29
 * project name: sd365-common-common-g05
 * @Version: 1.0.0
 * @Description: jedisUtil测试
 */
public class JedisUtilTest {
    JedisPool jedisPool;

    @Before
    public void setUp() throws Exception {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10000);
        jedisPoolConfig.setMaxWaitMillis(10000);
        jedisPoolConfig.setMaxIdle(1000);
        jedisPool = new JedisPool(jedisPoolConfig, "47.112.119.22", 6379, 100000);
    }

    @org.junit.Test
    public void getRerouse() {
        assertNotNull(jedisPool.getResource());
    }

}