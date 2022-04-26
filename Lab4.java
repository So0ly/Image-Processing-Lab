/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Mat;
import org.opencv.core.Point;

/**
 *
 * @author Soldi
 */
public class Lab4 {
static {       System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mat img = Imgcodecs.imread(
                "C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab1\\src\\lab1\\baza.jpg",0);
        Imgproc.threshold(img, img, 110, 255, 0);
        Mat baza = img.clone();
        Mat kernel = new Mat(3, 3, CvType.CV_8U);
        Imgproc.dilate(img, img, kernel);
        Imgproc.erode(img, img, kernel);
        //Imgproc.erode(img, img, kernel,new Point(-1,-1),10);
        
       // Imgproc.dilate(img, img, kernel,new Point(-1,-1),10);
        
        HighGui.imshow("Otwarcie", img);
        HighGui.imshow("Baza", baza);
        HighGui.waitKey(0);
        System.exit(0);
        
    }
    
}
