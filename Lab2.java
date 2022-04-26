
package lab2;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.core.Range;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.resize;
public class Lab2 {
    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    public static void main(String[] args) {
        Mat baza= Imgcodecs.imread("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab1\\src\\lab1\\baza.jpg");
        //z1(baza);
        //z2(baza);
        //z3(baza);
        //z4(baza);
        //z5(baza);
        z6(baza);        
        //z7(baza);
        HighGui.waitKey();
        System.exit(0);
    }

    private static void z5(Mat baza) {
        Mat res=baza.clone();
        Mat pyru = baza.clone();
        int skalar=4;
        resize(baza,res,new Size(res.cols()*skalar,res.rows()*skalar),0.5,0.5,
                Imgproc.INTER_LINEAR);
        Imgproc.pyrUp(pyru, pyru, new Size(pyru.cols()*2,pyru.rows()*2));
        Imgproc.pyrUp(pyru, pyru, new Size(pyru.cols()*2,pyru.rows()*2));
        HighGui.imshow("Resize", res);
        HighGui.imshow("PyrUP", pyru);
    }

    private static void z6(Mat baza) {
        int skalar=4;
        Mat res2=baza.clone();
        Mat pyrd = baza.clone();        
        resize(baza,res2,new Size(res2.cols()/skalar,res2.rows()/skalar),
                0.5,0.5,Imgproc.INTER_LINEAR);
        Imgproc.pyrDown(pyrd, pyrd, new Size(pyrd.cols()/2,pyrd.rows()/2));
        Imgproc.pyrDown(pyrd, pyrd, new Size(pyrd.cols()/2,pyrd.rows()/2));
        HighGui.imshow("Resize", res2);
        HighGui.imshow("PyrDown", pyrd);
    }

    private static void z4(Mat baza) {
        Range x = new Range(400,600);
        Range y= new Range(500,700);
        Mat crop = baza.clone();
        Rect rectCrop = new Rect(400,660,300,400);
        Mat crop2=new Mat(crop,rectCrop);
        crop=crop.rowRange(x).colRange(y);
        HighGui.imshow("RectCrop", crop2);
        HighGui.imshow("Crop", crop);
    }

    private static void z3(Mat baza) {
        Mat wyn = baza.clone();
        Point center = new Point (baza.cols()/2,baza.rows()/2);
        Size dsize = new Size(baza.cols(),baza.rows());
        Mat transl=Imgproc.getRotationMatrix2D(center, 45, 1);
        Imgproc.warpAffine(baza, wyn, transl, dsize);
        HighGui.imshow("Obrót", wyn);
    }

    private static void z2(Mat baza) {
        Mat wyn = baza.clone();
        //Core.flip(baza, wyn, 0);
        Core.flip(baza, wyn, -1);
        HighGui.imshow("Odbicie", wyn);
    }

    private static void z1(Mat baza) {
        Mat transl = new Mat(2,3,CvType.CV_32F);
        Mat wyn = baza.clone();
        transl.put(0,0,1,0,100,0,1,300);
        Size dsize = new Size(baza.cols(),baza.rows());
        Imgproc.warpAffine(baza, wyn, transl, dsize,Imgproc.INTER_LINEAR);
        HighGui.imshow("Translacja", wyn);
    }

    private static void z7(Mat baza) {
        Mat resload = baza.clone();
        resize(resload,resload,new Size(resload.cols()*1.5,resload.rows()*1.5),
                0.5,0.5,Imgproc.INTER_LINEAR);
        HighGui.imshow("1.5X", resload);
    }
    
}
