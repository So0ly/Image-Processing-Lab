package lab1;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

public class Lab1 {

    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args) {
        z1();
        Mat zdj = z2();
        //3
        HighGui.imshow("Baza", zdj);
        z4();
        //5
        //Mat gif = Imgcodecs.imread("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab1\\src\\lab1\\baza.gif");//GIF nie działa :c
        //HighGui.imshow("Baza", gif);
        //6
        Mat txt = Imgcodecs.imread("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab1\\src\\lab1\\baza.jpg");
        //Imgproc.putText(txt, "Baza", new Point(zdj.cols() / 4, zdj.rows() / 2), Imgproc.FONT_HERSHEY_COMPLEX, 1, new Scalar(255,255,0));
        //6b
        Imgproc.putText(txt, "Baza", new Point(zdj.cols() / 3, zdj.rows() / 6), Imgproc.FONT_HERSHEY_SCRIPT_COMPLEX, 4, new Scalar(255,0,0),2, Imgproc.LINE_AA, true);
        HighGui.imshow("Tekst", txt);
        //7
        Mat fig = Imgcodecs.imread("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab1\\src\\lab1\\baza.jpg");
        Imgproc.line(fig,new Point(200,200) , new Point(200,1250), new Scalar(0,0,0),3, -1,2);
        Imgproc.rectangle(fig, new Point(280,150), new Point(700,700), new Scalar(200),14);
        Imgproc.circle(fig, new Point(528,452), 300, new Scalar(0,0,255),20);//BGR
        MatOfPoint kontur=new MatOfPoint();
        kontur.fromArray(
                    new Point(467,350),
                    new Point (428,400),
                    new Point(505,410));
        List<MatOfPoint> lista=new ArrayList<MatOfPoint>() ;
        lista.add(kontur);
        Imgproc.polylines(fig, lista,true, new Scalar(255,0,0));
        HighGui.imshow("Figury", fig);
        HighGui.waitKey(0);
        System.exit(0);
    }

    private static void z4() {
        //4
        Mat szare = Imgcodecs.imread("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab1\\src\\lab1\\baza.jpg",Imgcodecs.IMREAD_GRAYSCALE);
        HighGui.imshow("Skala Szarości", szare);
    }

    private static Mat z2() {
        //2
        Mat zdj = Imgcodecs.imread("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab1\\src\\lab1\\baza.jpg");
        Imgcodecs.imwrite("C:/Users/Debil/Documents/NetBeansProjects/Lab1/src/lab1/zapis.jpg", zdj);
        return zdj;
    }

    private static void z1() {
        //1
        Mat img = new Mat(3,3,CvType.CV_8U);
        int row=0,col=0;
        img.put(row,col,1,2,3,3,5,6,6,7,8);
        System.out.println(img.dump());
    }
    
}
