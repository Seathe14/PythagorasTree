import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class PythagorasTree extends JPanel {
    final int depthLimit = 15;
    int width = 1280;
    int height = 800;
    public static int count = 0;
    public static long time = 0;
    public PythagorasTree() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.white);
    }
    private void drawTreeAngle(Graphics2D g, float x1, float y1,int size, double angle,
                          int depth) {
        count++;
        if (depth == depthLimit)
            return;

        Graphics2D g1 = (Graphics2D) g.create();
        g1.translate(x1,y1);
        g1.rotate(angle);
        Rectangle2D rect = new Rectangle(0, -size, size, size);
        g1.setColor(Color.getHSBColor(count*0.02f, 1, 1));
        g1.draw(rect);
        g1.fill(rect);
        float x2 =0,y2 = -size;
        int size2 = (int)Math.abs(Math.cos(-Math.PI/4) * size);
        double angle2 = -Math.PI/4;
        Graphics2D g2 = (Graphics2D) g1.create();
        if(depth>0)
        {
            drawTreeAngle(g1,x2,y2,size2,angle2,depth-1);
        }
        else
        {
            g1.translate(x2,y2);
            g1.rotate(angle2);
            rect = new Rectangle(0, -size2, size2, size2);
            g1.draw(rect);
            g1.fill(rect);
        }

        Graphics2D gg = (Graphics2D) g2.create();
        float x3 = x2 + (float)Math.cos(angle2) * size2;
        float y3 = y2 + (float)Math.sin(angle2) * size2;
        int size3 = (int)Math.abs(Math.cos(-Math.PI/4) * size);
        double angle3 = angle2 + Math.PI/2;
        if(depth>0)
        {

            drawTreeAngle(g1,x3,y3,size3,angle3,depth-1);
        }
        else
        {
            g2.translate(x3,y3);
            g2.rotate(angle3);
            rect = new Rectangle(0, -size3, size3, size3);
            g2.draw(rect);
            g2.fill(rect);
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g1 = (Graphics2D)g.create();
        try {
            long start = System.currentTimeMillis();
            drawTreeAngle(g1, width/2-75, height, 150, 0, 14);
            long finish = System.currentTimeMillis();
            time = finish - start;
        } finally {

            g1.dispose();
            System.out.println("Time it took: " + time);
            System.out.println(count);
        }


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Pythagoras Tree");
            f.setResizable(false);
            f.add(new PythagorasTree(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);

        });
    }
}