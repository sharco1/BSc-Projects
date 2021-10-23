import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Contains the frame and all different panels
 * Holds the back and forward buttons, as well as price bar
 * 
 * 
 * @authors (Amanjit Somal, Anjali Raveendran, Nabaa Al-Alawi, Farzaneh Javid)
 * @version (03/25/2018)
 */
public class JupiterInn{
    //holds the different panels in frame
    private JPanel cards;
    //the layout for cards JPanel
    private CardLayout cardLayout;
    private JFrame frame;
    //Min price from combo box
    private int minimumPrice = 0;
    //Max price from combo box
    private int maximumPrice = 0;
    //Holds price combo boxes
    private JPanel pricePanel;
    private JButton backButton;
    private JButton forwardButton;
    //tracks panel we are currently on
    private int currentCard = 0;
    //holds back button
    private JPanel backPanel;
    //holds forward button
    private JPanel forwardPanel;
    //holds logo
    private JPanel logoPanel;
    private ArrayList<AirbnbListing> dataList = new ArrayList<>();
    private static final String VERSION = "Version 1.0 ©TeamJupiter";
    //top panel of frame
    private JPanel top;
    //checks the inner state next buttons actionlistener. reutrns true if new cards have been added 
    private boolean isAdded;

    /**
     * Constructs the frame and adds panels to CardLayout.
     * @param airbnbDataLoader used to access data
     */
    public JupiterInn(AirbnbDataLoader airbnbDataLoader) { 
        frame = new JFrame("JUPITER INN");
        makeFrame(frame);
        
        dataList = airbnbDataLoader.load();
        // below are the frame's characteristics
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(d);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * makes frame in which panels will go
     */
    private void makeFrame(Frame frame)
    {
        GradientPanel superPanel = new GradientPanel(); //ce=reates a panel with a gradient
        superPanel.setLayout(new BorderLayout());
        frame.add(superPanel);
        makePriceComboBoxes();
        superPanel.add(top, BorderLayout.NORTH);
        makePanels();
        superPanel.add(cards, BorderLayout.CENTER);
        makeBackButton();
        superPanel.add(backPanel, BorderLayout.WEST);
        makeForwardButton();
        superPanel.add(forwardPanel, BorderLayout.EAST);
        makeLogoPane();
        superPanel.add(logoPanel, BorderLayout.SOUTH);
    }
    
    /**
     * makes the combo boxes used to enable user to pick a suitable price
     */
    private void makePriceComboBoxes()
    {
        //price comboboxes will be stored here
        pricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pricePanel.setOpaque(false);
        
        String[] priceList = {"0", "10", "20", "30", "50", "100", "200"};
            
        JLabel MinLabel = new JLabel("FROM");
        setLabelFont(MinLabel, 12f);
        MinLabel.setForeground(Color.WHITE);
        
        JLabel MaxLabel = new JLabel ("TO");
        setLabelFont(MaxLabel, 12f);
        MaxLabel.setForeground(Color.WHITE);
        
        JComboBox minPrice = new JComboBox(priceList);
        JComboBox maxPrice = new JComboBox(priceList);
        
        pricePanel.add(MinLabel);
        pricePanel.add(minPrice);
        pricePanel.add(MaxLabel);
        pricePanel.add(maxPrice); 
            
        minPrice.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox)e.getSource();
                    String option = (String)cb.getSelectedItem();
                    minimumPrice = Integer.parseInt(option);
                }
            });
                
        maxPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.exit(0);
                //jupiterInn.switchPanel("");
                JComboBox cb = (JComboBox)e.getSource();
                String option = (String)cb.getSelectedItem();
                maximumPrice = Integer.parseInt(option);
                // if(maximumPrice !=0 && !isAdded)
                // {
                   // cards.add(mapPanel, "Map");
                   // cards.add(statisticsPanel, "Statistics");
                   // cards.add(quizPanel, "Quiz"); 
                   // isAdded = true;
                // }
            }
        });
        
        JLabel version = new JLabel(VERSION);
        setLabelFont(version, 12f);
        version.setForeground(Color.WHITE);
        
        top = new JPanel(new BorderLayout());
        top.add(version, BorderLayout.WEST);
        top.add(pricePanel, BorderLayout.EAST);
        top.setOpaque(false);
    }
    
    /**
     * Makes panels that will be interchanging
     */
    private void makePanels()
    {
        //holds all panels
        cards = new JPanel(cardLayout = new CardLayout());

        // add panels to the card layout
        cards.add(new InitialPanel(), "Initial");
        cards.add(new MapPanel(minimumPrice, maximumPrice), "Map");
        cards.add(new StatisticsPanel(), "Statistics");
        cards.add(new QuizPanel(), "Quiz"); 
        cards.setOpaque(false);
    }
    
    /**
     * Makes back button. if on 1st card, will not permit user to go back
     */
    private void makeBackButton()
    {
        backPanel = new JPanel();
        backPanel.setLayout(new BorderLayout());

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //check that we are not in first panel
                    if(!isDisplayingFirstCard())
                    {
                        cardLayout.previous(cards);
                        currentCard--;
                    }
                    //else nothing happens
                }
            });
        backPanel.add(backButton, BorderLayout.SOUTH);
        backPanel.setOpaque(false);
    }
    
    /**
     * Makes back button. if on last card, will not permit user to go forward
     */
    private void makeForwardButton() 
    {
        forwardPanel = new JPanel();
        forwardPanel.setLayout(new BorderLayout());
        
        forwardButton = new JButton("Next");
        forwardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //min price needs to be more than max
                    if(minimumPrice > maximumPrice || maximumPrice == 0)
                    {
                        String st = "Please select a valid price range";
                        JOptionPane.showMessageDialog(frame, st);
                    }
                    else
                    {
                       //check that we are not in last panel
                        if(!isDisplayingLastCard())
                        {
                            cardLayout.next(cards);
                            currentCard++;
                            if(!isAdded)
                            { //update cards, by reinserting them into panel
                            cards.removeAll();
                            cards.add(new InitialPanel(), "Initial"); 
                            cards.add(new MapPanel(minimumPrice, maximumPrice), "Map");
                            cards.add(new StatisticsPanel(), "Statistics");
                            cards.add(new QuizPanel(), "Quiz"); 
                            cards.setOpaque(false); 
                            isAdded = true;
                            cardLayout.next(cards);
                            
                        }
                        }
                        //else do nothing 
                    }
                }
            });
        forwardPanel.add(forwardButton, BorderLayout.SOUTH);
        forwardPanel.setOpaque(false);
    }
    
    /**
     * makes logo pane which holds our logo
     */
    private void makeLogoPane()
    {
        logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout());
        JLabel logo = new JLabel(new ImageIcon("logo2.png"));
        logoPanel.add(logo, BorderLayout.CENTER);
        logoPanel.setOpaque(false);
    }
    
    /**
     * returns true if panel is displaying first card
     */
    private boolean isDisplayingFirstCard() {
        return currentCard == 0;
    }
    
    /**
     * returns true if panel is displaying last card
     */
    private boolean isDisplayingLastCard() {
        return currentCard == 3;
    }

    /**
     * Switches the current panel.
     * @param panelName
     */
    private void switchPanel(String panelName) {
        cardLayout.show(cards, panelName);
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
    
    public int getCards()
    {
        return cards.getComponentCount();
    }

    public int getBackButton()
    {
        return backButton.getActionListeners().length;
    }
    
    public int getForwardButton()
    {
        return forwardButton.getActionListeners().length;
    }
        
}