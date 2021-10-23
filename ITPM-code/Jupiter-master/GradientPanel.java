import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * this panel overrides a method in JPanel so that we are able to create
 * panels with a gradient
 * 
 * JupiterInn
 * Team Jupiter Group Project
 * @authors (Amanjit Somal, Anjali Raveendran, Nabaa Al-Alawi, Farzaneh Javid)
 * @version 1.00 2018/03/22
 */
public class GradientPanel extends JPanel { //maybe change this to layered pane?

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = new Color(60,106,166);
        Color color2 = new Color (210,80,48);
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}