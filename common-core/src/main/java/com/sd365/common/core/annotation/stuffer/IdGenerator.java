/**
 * @file:  IdGenerator.java
 * @author: liang_xiaojian
 * @date:   2020/9/2 10:11
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */
package com.sd365.common.core.annotation.stuffer;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @class IdGenerator
 * @classdesc 雪花算法 ID 生成
 * @author liang_xiaojian
 * @date 2020/9/2  10:11
 * @version 1.0.0
 * @see
 * @since
 */
@Slf4j
@Component
public class IdGenerator {

    @Value("${snowflake.workerId: 0}")
    private long workerId;
    @Value("${snowflake.dataCenterId: 1}")
    private long dataCenterId;

    private Snowflake snowflake;

    @PostConstruct
    public void init() {
        try {
            log.info("当前机器的 workerId: {}", workerId);
            snowflake = IdUtil.createSnowflake(workerId, dataCenterId);
        } catch (Exception e) {
            log.error("当前机器的 workerId 获取失败: {}", e.getMessage());
            workerId = NetUtil.getLocalhostStr().hashCode();
            log.error("当前机器 workId:{}", workerId);
        }
    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        IdGenerator idGenerator = new IdGenerator();
        idGenerator.snowflake = IdUtil.createSnowflake(1, 1);
        log.info("{}", idGenerator.snowflakeId());
    }
}
