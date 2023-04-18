package com.passerby.excel.support;

import cn.hutool.core.annotation.AnnotationUtil;
import com.alibaba.fastjson.JSON;
import com.passerby.excel.annotation.StrategyModel;
import com.passerby.excel.strategy.IExcelStrategyProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: sunjunyao
 * @Date: 2023/4/18 15:01
 * @Descrpition: 实现excel策略初始化配置
 */
public class ExcelStrategySupport implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(ExcelStrategySupport.class);

    private ApplicationContext applicationContext;

    /**
     * 策略模式所有策略的集合组
     */
    private static Map<String, IExcelStrategyProcess> STRATEGY_MAP = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 容器启动后获取所有策略类型并注入到STRATEGY_MAP中
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
      try {
          Map<String, Object> strategyMap = applicationContext.getBeansWithAnnotation(StrategyModel.class);
          for (Map.Entry<String, Object> entry : strategyMap.entrySet()) {
              StrategyModel strategyModel = AnnotationUtil.getAnnotation(entry.getValue().getClass(), StrategyModel.class);
              if (entry.getValue() instanceof IExcelStrategyProcess) {
                  STRATEGY_MAP.put(strategyModel.strategyName(), (IExcelStrategyProcess) entry.getValue());
              }
              logger.info("excelstarter load strategy done {}", JSON.toJSONString(STRATEGY_MAP));
          }
      }catch (Exception e) {
          logger.info("excelstarter load strategy errot {}", JSON.toJSONString(e.getMessage()),e);
          throw e;
      }
    }
}
