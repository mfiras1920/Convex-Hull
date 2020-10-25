import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConvexHull {
    //Atribut
    static int N;
    static Point[] ArrP;
    static ArrayList<Garis> ArrConvexHull;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        BuatTitik();
        CariConvexHull();
        GUI();
    }


    private static void BuatTitik() {
        do {
            System.out.print("Banyak titik yang akan dibuat : ");
            N = in.nextInt();
            if (N < 2){
                System.out.println("Masukan harus N > 1");
                System.out.println();
            }
        } while (N < 2);
        ArrP = new Point[N];


        Random r = new Random();
        for (int i=0; i<N; i++){        //menentukan koordinat titik secara acak dari 0 sampai 300 sebanyak N
            ArrP[i] = new Point(r.nextInt(301), r.nextInt(301));
        }
        ArrConvexHull = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            System.out.printf("(%d,%d)\n", ArrP[i].X, ArrP[i].Y);
        }
        System.out.println();
    }

    private static void CariConvexHull() {
        int Persamaan1;
        int Persamaan2;
        int Count1;
        int Count2;
        Garis temp;

        long StartTime = System.nanoTime(); //menghitung waktu pencarian convex hull dengan brute force

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if ((ArrP[i].X != ArrP[j].X) && (ArrP[i].Y != ArrP[j].Y)) {
                    Persamaan2 = (ArrP[i].X * ArrP[j].Y) - (ArrP[i].Y * ArrP[j].X);
                    Count1 = 0;
                    Count2 = 0;
                    for (int k = 0; k < N; k++) {
                        Persamaan1 = (ArrP[j].Y - ArrP[i].Y) * ArrP[k].X + (ArrP[i].X - ArrP[j].X) * ArrP[k].Y;
                        if (Persamaan1 > Persamaan2){
                            Count1 = Count1 + 1;
                        }
                        if (Persamaan1 < Persamaan2){
                            Count2 = Count2 + 1;
                        }
                    }
                    if ((Count1 == 0) || (Count2 == 0)){
                        temp = new Garis(ArrP[i],ArrP[j]);
                        ArrConvexHull.add(temp);
                    }
                }
            }
        }

        long EndTime = System.nanoTime();

        System.out.println("Kumpulan titik-titik yang merupakan garis bagian dari Convex Hull adalah:");
        for (int i = 0; i < ArrConvexHull.size(); i++) {
            System.out.printf("((%d,%d),(%d,%d))\n", ArrConvexHull.get(i).P1.X, ArrConvexHull.get(i).P1.Y, ArrConvexHull.get(i).P2.X, ArrConvexHull.get(i).P2.Y);
        }
        System.out.println();
        System.out.println("Lama waktu yang diperlukan untuk menemukan convex hull adalah: " + (EndTime-StartTime) + " nanosecond");
        System.out.println();

    }

    private static void GUI() {
        JFrame frame = new JFrame("Convex Hull");
        Canvas canvas = new Gui();
        canvas.setSize(350, 350);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }
}
