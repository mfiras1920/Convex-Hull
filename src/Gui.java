import java.awt.*;
import javax.swing.JFrame;

public class Gui extends Canvas {

    public void paint(Graphics g) {
        g.setColor(new Color(192,192,192));
        g.drawLine(0, 175, 350, 175);
        g.drawLine(175, 0, 175, 350);
        g.setColor(new Color(0, 0, 255));
        for (Point p : ConvexHull.ArrP) {
            g.fillOval((p.X)-4, (-p.Y+300)-4, 8, 8);
        }
        g.setColor(new Color(250,0,0));
        for (Garis l : ConvexHull.ArrConvexHull){
            g.drawLine(l.P1.X,-(l.P1.Y)+300,l.P2.X,-(l.P2.Y)+300);
        }
    }
}
