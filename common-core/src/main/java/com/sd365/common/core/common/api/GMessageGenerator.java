package com.sd365.common.core.common.api;

/**
 * @file: sd365-common-common-g05 com.sd365.common.log.exception.utils
 * @author: Sukaiting
 * @date: 2020/9/7 22:02
 * @copyright: 2020-2023 www.bosssoft.com.cn Inc. All rights reserved.
 */

import cn.hutool.core.date.DateTime;

import com.sd365.common.cache.redis.service.impl.RedisServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDate;


/**
 * @class GMessageGenerator
 * @classdesc 全局流水号生成
 * 组成：用户工号-YYYYMMDDHHMMSS-全局流水(4位) 每日从0000开始更新
 * @author Sukaiting
 * @date 2020/9/7  22:03
 * @version 1.0.0
 * @see
 * @since
 */
@Slf4j
@Component
public class GMessageGenerator {

        private static final String STR_FORMAT = "0000";
        @Autowired
        private static RedisServiceImpl redisDao;

        /**
         * 生成全局流水号
         * @param
         * @return
         */
        public static String getGMessage(){
                final String STR_FORMAT = "0000";
                //当日全局流水号，存储在redis中的key
                final String GLOBAL_CODE= "GLOBAL_CODE:"+ LocalDate.now();
                long sequence =redisDao.incr(GLOBAL_CODE,1);

                //TODO:用户工号获取
                String code ="123456";
                //当前日期
                String date = DateTime.now().toString("yyyyMMddHHmmss");

                return code+date+getSequence(sequence);
        }

        /**
         * 获取全局流水
         * @param sequence
         * @return
         */
        public static String getSequence(long sequence){

                if (sequence >= 9999) {
                      sequence=1;
              }
                DecimalFormat df = new DecimalFormat(STR_FORMAT);
                return df.format(sequence);
        }


}
