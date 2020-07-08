package com.example.controller;

import java.io.File;

import org.jodconverter.core.office.OfficeException;
import org.jodconverter.core.office.OfficeManager;
import org.jodconverter.core.office.OfficeUtils;
import org.jodconverter.local.JodConverter;
import org.jodconverter.local.office.LocalOfficeManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Value("${app.path}")
    private String path;

    @Value("${app.uploadPath}")
    private String uploadPath;


    @GetMapping(path = "/test")
    String test(String name) {
        File inputFile = new File(path + name + ".doc");
        File outputFile = new File(path + name + ".pdf");
        OfficeManager officeManager = LocalOfficeManager.builder().install()
                .officeHome("/snap/libreoffice/current/lib/libreoffice").build();

        try {

            // Start an office process and connect to the started instance (on port 2002).
            officeManager.start();

            // Convert
            JodConverter.convert(inputFile).to(outputFile).execute();
        } catch (OfficeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // Stop the office process
            OfficeUtils.stopQuietly(officeManager);
        }
        return "aa";
    }

}
