import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
/**
 * The main menu panel.
 * 
 * JupiterInn
 * Team Jupiter Group Project
 * @authors (Amanjit Somal, Anjali Raveendran, Nabaa Al-Alawi, Farzaneh Javid)
 * @version 1.00 2018/03/22
 */
public class InitialPanel extends JPanel {

    /**
     * Constructs the initial welcome panel.
     * 
     *  @authors (Amanjit Somal, Anjali Raveendran, Nabaa Al-Alawi, Farzaneh Javid)
     *  @version (03/25/2018)
     */
    public InitialPanel() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.setOpaque(false);

        creatTitle(welcomePanel);
        makeInfoLabel(welcomePanel);
        makeGetStartedButton(welcomePanel);

        this.add(welcomePanel);
        this.setOpaque(false);
    } 

    /**
     * create title for panel
     * @param panel the panel in question
     */
    private void creatTitle(JPanel panel)
    {
        JLabel title = new JLabel("<html><br><br>WELCOME!", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(200,370));
        setLabelFont(title, 100f);
        title.setForeground(Color.WHITE);
        panel.add(title, BorderLayout.NORTH); 
    }

    /**
     * create label which contains information about program
     * @param panel the panel which this info will be added to
     */
    private void makeInfoLabel(JPanel panel)
    {
        String text = "<html>The best platform for finding your<br>" +
            "next home. Pick your price range<br>" +
            "to get started<br>";
        JLabel aboutUs = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>");
        aboutUs.setAlignmentY(20f); //this is important as it helps houses in mapPanel stay in the correct place
        setLabelFont(aboutUs, 40f);
        aboutUs.setForeground(Color.WHITE);
        panel.add(aboutUs, BorderLayout.CENTER); 
    }

    /**
     * creates a JLabel that looks like a button on screen
     */
    private void makeGetStartedButton(JPanel panel)
    {
        JLabel getStarted = new JLabel(new ImageIcon("startbutton.png"));
        getStarted.setPreferredSize(new Dimension(150,250));
        panel.add(getStarted, BorderLayout.SOUTH);
    }

    /**
     * sets the font of a JLabel
     * @label the label to be changed
     * @size how big you want the font to be
     */
    private void setLabelFont(JLabel label, float size)
    {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Orbitron-Regular.ttf"));
            label.setFont(font.deriveFont(Font.BOLD, size));
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }
    }
}