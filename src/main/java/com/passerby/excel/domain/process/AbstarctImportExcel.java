package com.passerby.excel.domain.process;

import com.passerby.excel.domain.IExcelServiceFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
public class AbstarctImportExcel implements IExcelServiceFactory {
    @Override
    public String doImportExcel(MultipartFile file, String strategyName, String importType) {
        return null;
    }
}
