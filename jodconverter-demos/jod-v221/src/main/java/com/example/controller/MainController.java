package com.example.controller;

import java.io.File;
import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

@RestController
public class MainController {

    @Value("${app.path}")
    private String path;

    @GetMapping(path = "/test")
    String test(String name) {
        File inputFile = new File(path + name + ".doc");
        File outputFile = new File(path + name + ".pdf");
        long date1 = System.currentTimeMillis();
        System.out.println(date1);
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            e.printStackTrace();
        }
        long date2 = System.currentTimeMillis();
        System.out.println(date2 - date1);
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(inputFile, outputFile);
        long date3 = System.currentTimeMillis();
        System.out.println(date3 - date2);
        connection.disconnect();
        return "aa";
    }

}
