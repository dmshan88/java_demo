package com.example.util;

import java.io.File;

import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.office.OfficeException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PdfConverterUtil {
    
    private DocumentConverter documentConverter;
    
    public PdfConverterUtil(DocumentConverter converter) {
        this.documentConverter = converter;
    }
    
    /**转换*/
    public boolean convert(File sourceFile, File targetFile) {
        log.info("jodconverter conver {} to {}", sourceFile, targetFile);
        try {
            documentConverter.convert(sourceFile).to(targetFile).execute();
        } catch (OfficeException e) {
            log.error("jodconverter error:", e);
            return false;
        }
        return true;
    }
}
