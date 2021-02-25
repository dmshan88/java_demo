package com.example;

import java.io.File;

import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.office.OfficeException;
import org.jodconverter.local.JodConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    
    @Autowired(required = false)
    private DocumentConverter documentConverter;
    
    @Test
    public void testRun() {
        String path = "d:\\11";
        File inputFile = new File(path + ".docx");
        File outputFile = new File(path + ".pdf");
        try {
            documentConverter.convert(inputFile).to(outputFile).execute();
        } catch (OfficeException e1) {
            e1.printStackTrace();
        }

//        OfficeManager officeManager = LocalOfficeManager.builder().install()
//                .officeHome("D:\\pf\\LibreOffice").build();
//        try {
//            officeManager.start();
//            JodConverter.convert(inputFile).to(outputFile).execute();
//        } catch (OfficeException e) {
//            e.printStackTrace();
//        } finally {
//            OfficeUtils.stopQuietly(officeManager);
//        }
    }
    


}
