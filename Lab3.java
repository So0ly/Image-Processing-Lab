
package lab3;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class Lab3 {
static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    private static byte saturate(double val) { //jeśli wartość jest mniejsza niż 0 to wstawia 0; jeśli większa niż 255 to wstawia 255
        int iVal = (int) Math.round(val);
        iVal = iVal > 255 ? 255 : (iVal < 0 ? 0 : iVal);
        return (byte) iVal;
    }
  
    public static void main(String[] args) {
        Mat baza=Imgcodecs.imread("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab1\\src\\lab1\\baza.jpg");
        Mat lena = Imgcodecs.imread("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab3\\ln.png");
        z1(lena);
        z3(baza);
        //z4();
        //z5();
        //z6(baza);
        z7();
        //z8(baza);
        z9(baza);        
        HighGui.waitKey();
        System.exit(0);   
    }

    private static void z7(){
        Mat src = Imgcodecs.imread("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab1\\src\\lab1\\baza.jpg",
                Imgcodecs.IMREAD_GRAYSCALE);
        Imgproc.threshold(src, src, 119, 80, 4);
        HighGui.imshow("Binaryzacja", src);
    }
    private static void z9(Mat baza) {
        List <Mat> hist = new ArrayList<>();
        Core.split(baza, hist);//rozdzielenie na kanały
        int histSize = 256;
        float[] range = {0,histSize}; //zakres kolorów
        MatOfFloat histRange = new MatOfFloat(range);
        Mat bHist = new Mat(), gHist = new Mat(), rHist = new Mat();
        Imgproc.calcHist(hist, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), histRange);
        Imgproc.calcHist(hist, new MatOfInt(1), new Mat(), gHist, new MatOfInt(histSize), histRange);
        Imgproc.calcHist(hist, new MatOfInt(2), new Mat(), rHist, new MatOfInt(histSize), histRange);
        int histW = 512, histH = 400; // wymiary okna
        int binW = (int) Math.round((double) histW / histSize); //rozłożenie wartości na całej szerokości okna
        Mat histImage = new Mat(histH, histW, CvType.CV_8UC3,new Scalar(0,0,0));
        Core.normalize(bHist, bHist, 0, histImage.rows(), Core.NORM_MINMAX);
        Core.normalize(gHist, gHist, 0, histImage.rows(), Core.NORM_MINMAX);
        Core.normalize(rHist, rHist, 0, histImage.rows(), Core.NORM_MINMAX);
        float[] bHistData = new float[(int)(bHist.total() * bHist.channels())];
        bHist.get(0,0,bHistData);
        float[] gHistData = new float[(int)(gHist.total() * gHist.channels())];
        gHist.get(0,0,gHistData);
        float[] rHistData = new float[(int)(rHist.total() * rHist.channels())];
        rHist.get(0,0,rHistData);
        for(int i = 1; i<histSize;i++){
            Imgproc.line(histImage, new Point(binW * (i - 1), histH - Math.round(bHistData[i-1])),
                    new Point(binW * (i), histH - Math.round(bHistData[i])), new Scalar (255,0,0),2);
            Imgproc.line(histImage, new Point(binW * (i - 1), histH - Math.round(gHistData[i-1])),
                    new Point(binW * (i), histH - Math.round(gHistData[i])), new Scalar (0,255,0),2);
            Imgproc.line(histImage, new Point(binW * (i - 1), histH - Math.round(rHistData[i-1])),
                    new Point(binW * (i), histH - Math.round(rHistData[i])), new Scalar (0,0,255),2);
        }
        HighGui.imshow("Histogram", histImage);
    }

    private static void z8(Mat baza) {
        Mat drugi = Imgcodecs.imread(
                "C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab3\\src\\lab3\\flip.jpg");
        Mat add = new Mat();
        Mat amb = new Mat();
        Mat bma = new Mat();
        Mat atb = new Mat();
        Mat adb = new Mat();
        Mat bda = new Mat();
        Core.add(baza, drugi, add);
        HighGui.imshow("Dodawanie", add);
        Core.subtract(baza, drugi, amb);
        HighGui.imshow("Odejmowanie A-B", amb);
        Core.subtract(baza, drugi, bma);
        HighGui.imshow("Odejmowanie B-A", bma);
        Core.multiply(baza, drugi, atb);
        HighGui.imshow("Mnożenie", atb);
        Core.divide(baza, drugi, adb);
        HighGui.imshow("Dzielenie A/B", adb);
        Core.divide(baza, drugi, bda);
        HighGui.imshow("Dzielenie B/A", bda);
    }

    private static void z6(Mat baza) {
        Mat imageHSV = baza.clone();
        Imgproc.cvtColor(imageHSV, imageHSV, Imgproc.COLOR_BGR2HSV);
        HighGui.imshow("HSV", imageHSV);
        HighGui.imshow("Baza", baza);
    }

    private static void z4() {
        Mat lena = Imgcodecs.imread("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab3\\normalizacja.png",0);
        double min = Core.minMaxLoc(lena).minVal;
        double max = Core.minMaxLoc(lena).maxVal;
        System.out.println(min);
        System.out.println(max);
        Core.normalize(lena, lena, 0, 255,Core.NORM_MINMAX);
        min = Core.minMaxLoc(lena).minVal;
        max = Core.minMaxLoc(lena).maxVal;
        System.out.println(min);
        System.out.println(max);
        HighGui.imshow("Normalizacja", lena);
    }

    private static void z5() {
        Mat channel = Imgcodecs.imread("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab3\\rozbicie_na_kanaly.png");
        ArrayList <Mat> dst = new ArrayList<>();
        Core.split(channel, dst);
        HighGui.imshow("B", dst.get(0));
        HighGui.imshow("G", dst.get(1));
        HighGui.imshow("R", dst.get(2));
	Imgcodecs.imwrite("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab3\\b.jpg", dst.get(0));
	Imgcodecs.imwrite("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab3\\g.jpg", dst.get(1));
	Imgcodecs.imwrite("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab3\\r.jpg", dst.get(2));
    }

    private static void z3(Mat image) {
        Mat drugi = Imgcodecs.imread("C:\\Users\\Debil\\Documents\\NetBeansProjects\\Lab3\\src\\lab3\\flip.jpg");
        Core.addWeighted(image, 1, drugi, 1, 0, drugi);
        //wzór do sprawozdania
        HighGui.imshow("Blend", drugi);
    }

    private static void z1(Mat baza) {
        Mat wyn=Mat.zeros(baza.size(), baza.type());
        double kontrast= 2.2;//0.5;
        double jasnosc= 50;//-30;
        baza.convertTo(baza, CvType.CV_8U, kontrast, jasnosc);
        //z1f(baza, wyn, kontrast, jasnosc);
        HighGui.imshow("kontrast", wyn);
    }

    private static void z1f(Mat baza, Mat wyn, double kontrast, int jasnosc) {
        //convertto
        byte[] imageData = new byte[(int) (baza.total()*baza.channels())];//tablica bajtów, pozwala na odczytanie każdego piksela dla każdego kanału
        baza.get(0, 0, imageData);//współrzędne obrazu i tablica bajtów
        byte[] newImageData = new byte[(int) (wyn.total()*wyn.channels())];
        for (int y = 0; y < baza.rows(); y++) {
            for (int x = 0; x < baza.cols(); x++) {
                for (int c = 0; c < baza.channels(); c++) {
                    double pixelValue = imageData[
                            (y * baza.cols() + x) * baza.channels() + c];
                    pixelValue = pixelValue < 0 ? pixelValue + 256 : pixelValue;
                    newImageData[(y * baza.cols() + x) * baza.channels() + c] = 
                            saturate(kontrast * pixelValue + jasnosc);
                }
            }
        }
        wyn.put(0, 0, newImageData);
    }
    
}
