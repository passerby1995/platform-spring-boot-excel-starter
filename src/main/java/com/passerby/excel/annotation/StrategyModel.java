package com.passerby.excel.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: sunjunyao
 * @Date: 2023/4/18 15:09
 * @Descrpition: 标识在策略的实现类上，每个类都会被识别为一个策略
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface StrategyModel {
    /**
     * 策略名称
     * @return
     */
    String strategyName();
}
