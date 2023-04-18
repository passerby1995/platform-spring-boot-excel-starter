package com.passerby.excel.domain;

import org.springframework.web.multipart.MultipartFile;

/**
 * Excel工厂类
 */
public interface IExcelServiceFactory {
    /**
     *
     * @param file excel文件流
     * @param strategyName 策略名称
     * @param importType 倒入类型 增量/全量
     * @return
     */
    String doImportExcel(MultipartFile file,String strategyName,String importType);
}
