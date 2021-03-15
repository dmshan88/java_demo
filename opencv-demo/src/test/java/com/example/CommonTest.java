package com.example;

import java.net.URL;

import org.junit.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class CommonTest {
    
    @Test
    public void test() {
    	URL url = ClassLoader.getSystemResource("opencv/x64/opencv_java3413.dll");
    	System.load(url.getPath());
    	Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1 );
        System.out.println( "mat = " + mat.dump() );
    }

}
