/**
 * @file: Lock.java
 * @author: linlu
 * @date: 2020/8/27
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.cache.redis.lock;

/**
 * @PackgeName: com.sd365.common.cache.redis.lock
 * @ClassName: Lock
 * @Author: ll
 * Date: 2020/8/27
 * project name: sd365-common-common-g05
 * @Version: 1.0.0
 * @Description: 锁接口
 */
public interface Lock {

    boolean getLock(String key, String id, int expireTime);

    boolean releaseLock(String key, String id);
}
