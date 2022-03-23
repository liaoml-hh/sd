package com.sd365.common.core.common.aop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param
 * @description:
 * @author: Administrator
 * @create: 2020-11-22 22:11
 * @since
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPageInfo {
    private Long total;
    private int pages;
}
