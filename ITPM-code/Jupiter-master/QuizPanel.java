import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/**
 * Write a description of class QuizPanel here.
 * layout:
 * this panel will use borderlayout
 * north is title
 * center is sliders
 * south is generate results button
 * actually, i've jsut had a rethink:
 * how about, 2 panels in borderlayout
 * panel1 is in center
 * panel2 is south, which holds generate result
 * do i want it to be a pop up?
 * no, i think i would prefer it if panel itself changed
 * panel1 will hold all the sliders
 *
 * @authors (Farzaneh Javid)
 * @version (03/25/2018)
 */
public class QuizPanel extends JPanel
{
   //holds all JSliders in one panel
   private JPanel sliderPanel;
   //a panel which contains the quiz screen
   private JPanel quizPanel;
   //a panel which contains the results from the quiz
   private JPanel resultPanel = new JPanel(); 
   private AirbnbDataLoader loader = new AirbnbDataLoader();
   private ArrayList<AirbnbListing> data = new ArrayList<>();
   //the layout used for cards panel
   private CardLayout cardLayout = new CardLayout();
   //holds the result and quiz panel
   private JPanel cards = new JPanel(cardLayout);
   //the sliders used to form quizPanel
   private JSlider slider1;
   private JSlider slider2;
   private JSlider slider3;
   private JSlider slider4;
   //checks the inner state of showResults button
   private boolean hasBeenClicked = false;
   private Random rand = new Random();
   
   /**
    * constrcuts QuizPanel instance
    */ 
   public QuizPanel()
   {
       data = loader.load();
       makePanel();
    }
   
   /**
    * makes panel
    */ 
   private void makePanel()
   {
       this.setLayout(new BorderLayout());
       this.setOpaque(false);
       
       //create title of panel
       JLabel title = new JLabel("DREAM HOME FINDER", SwingConstants.CENTER);
       setLabelFont(title, 70f);
       title.setForeground(Color.WHITE);
       this.add(title, BorderLayout.NORTH);
       
       makeQuizPanel();
       cards.add(quizPanel, "Slider Panel");
       this.add(cards, BorderLayout.CENTER);
       cards.setOpaque(false);
       
       makeResultsButtonPanel(this);
   }
   
   private void makeQuizPanel()
   {
       quizPanel = new JPanel(new BorderLayout());
       quizPanel.setOpaque(false);
       
       //creates label letting user knwo what this panel is about
       JLabel addInstructions = new JLabel("<html>Let us find you a home for your future holiday<br>"
                + "based on our quick personality test.<br>"
                + "How much you agree to the following statements?<br>", SwingConstants.CENTER);
       setLabelFont(addInstructions, 25f);
       addInstructions.setForeground(Color.WHITE);
       addInstructions.setPreferredSize(new Dimension(500,200));
       quizPanel.add(addInstructions, BorderLayout.NORTH);
       
       //create panel for sliders
       sliderPanel = new JPanel();
       sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
       sliderPanel.setOpaque(false);
       quizPanel.add(sliderPanel, BorderLayout.CENTER);
       
       // add first question
       slider1 = new JSlider();
       makeSlider(slider1,"I am an outgoing person.");
       slider1.setPreferredSize(new Dimension(500,60));
        
       // add second question
       slider2 = new JSlider();
       makeSlider(slider2, "I enjoy shopping.");
       slider2.setPreferredSize(new Dimension(500,60));

       // add third question
       slider3 = new JSlider();
       makeSlider(slider3, "Number of nights you would like to stay");
       slider3.setPreferredSize(new Dimension(500,60));

       // add fourth question
       slider4 = new JSlider();
       makeSlider(slider4, "I like quiet neighborhoods.");
       slider4.setPreferredSize(new Dimension(500,60));
   }
   
   /**
    * creates the results panel
    * @param dreamHome is the house chosen for the user based on quizPanel
    */
   private void makeResultPanel(AirbnbListing dreamHome)
   {
       resultPanel = new JPanel(new BorderLayout());
       resultPanel.setOpaque(false);
       
       //top label lets user know which borough best suits their personality
       JLabel borough = new JLabel("You're dream home is in " + dreamHome.getNeighbourhood(), SwingConstants.CENTER);
       setLabelFont(borough, 30f);
       borough.setForeground(Color.WHITE);
       borough.setPreferredSize(new Dimension(500,300));
       resultPanel.add(borough, BorderLayout.NORTH);
       
       //more information about home 
       JPanel textField = new JPanel();
       textField.setLayout(new BoxLayout(textField, BoxLayout.Y_AXIS));
       textField.setOpaque(true);
       resultPanel.add(textField, BorderLayout.CENTER);
       
       JLabel jupiterInfo = new JLabel("<html>Here at JupiterInn, we utlise state of the art alogirthms<br>" +
       "that are able to uniquely identify a suitable home for you<br>" +
       "<br>Below is a summary of the home we think best suits you." + "<br>", SwingConstants.CENTER);
       setLabelFont(jupiterInfo, 20f);
       jupiterInfo.setForeground(Color.WHITE);
       jupiterInfo.setPreferredSize(new Dimension(500,200));
       
       JLabel houseInfo = new JLabel("<html>Description: " + dreamHome.getName() + "<br>" +
       "<br>Price: " + dreamHome.getPrice() + "<br>" +
       "<br>Reviews: " + dreamHome.getNumberOfReviews(), SwingConstants.CENTER);
       setLabelFont(houseInfo, 20f);
       houseInfo.setForeground(Color.WHITE);
       houseInfo.setPreferredSize(new Dimension(500,300));
       
       textField.add(jupiterInfo);
       textField.add(houseInfo);
       textField.setOpaque(false);
   }
   
   /**
    * button that allows user to see their results
    */
   private void makeResultsButtonPanel(JPanel panel)
   {
       JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
       buttonPanel.setOpaque(false);
       JButton showResultButton = new JButton("Show result");
       buttonPanel.add(showResultButton);
       panel.add(buttonPanel, BorderLayout.SOUTH);
       showResultButton.addActionListener(e -> showResult(slider1, slider2, slider3, slider4));
   }
   
    /**
    * makes a slider
    * @param s the slider
    * @param question the slider question
    */ 
   private void makeSlider(JSlider slider, String question){
       slider.setPaintTicks(true);
       slider.setPaintLabels(true);
       slider.setMajorTickSpacing(20);
       slider.setMinorTickSpacing(5);
       
       JPanel panelForSlider = new JPanel(new BorderLayout());
       panelForSlider.setOpaque(false);
       JLabel questionLabel = new JLabel(question);
       setLabelFont(questionLabel, 18f);
       questionLabel.setForeground(Color.WHITE);
       panelForSlider.add(questionLabel, BorderLayout.NORTH);
       panelForSlider.add(slider, BorderLayout.CENTER);
       
       slider.setOpaque(false);
       sliderPanel.add(panelForSlider);
   } 
    
   /**
    * Show the result of the quiz
    * @param s1,s2,s3,s4 the sliders which were answered
    */ 
   private void showResult(JSlider s1,JSlider s2,JSlider s3,JSlider s4){
       // getting the answer of each slider
       if(!hasBeenClicked)
       {
        int q1 = s1.getValue();
       int q2 = s2.getValue();
       int q3 = s3.getValue();
       int q4 = s4.getValue();
       
       AirbnbListing dreamHome = getDreamHome(q1, q2, q3, q4);
       
       makeResultPanel(dreamHome);
       cards.removeAll();
       cards.add(quizPanel, "Slider Panel");
       cards.add(resultPanel, "Results");
       cardLayout.show(cards, "Results"); 
       hasBeenClicked = true;
       
        }
        else
        {
            String st = "We have shown your results";
                        JOptionPane.showMessageDialog(this, st);
        }
   } 
   
   /**
    * choose a dream home for user
    * @param answers provided from sliders
    */
   private AirbnbListing getDreamHome(int answer1, int answer2, int answer3, int answer4)
   {
      //stores homes that could potentially be suitable
       ArrayList<AirbnbListing> potentialHomes = new ArrayList<>();
       //checking answer1
       if(answer1 < 30){
            for(int i=0; i < data.size(); i++){
                if(data.get(i).getNeighbourhood().equals("Havering") || data.get(i).getNeighbourhood().equals("Hillingdon") ||
                data.get(i).getNeighbourhood().equals("Enfield") || data.get(i).getNeighbourhood().equals("Barking and Dagenham")){
                    potentialHomes.add(data.get(i));
                } 
            }  
        }
        else if(answer1 >= 50){
            for(int i=0; i < data.size(); i++){
                if(data.get(i).getNeighbourhood().equals("City of London")){
                    potentialHomes.add(data.get(i));
                }
            }   
        } 
        else{
            for(int i=0; i < data.size(); i++){
                    potentialHomes.add(data.get(i));
            }
        }
      //checking answer 2  
        if(answer2 >= 50){
            for(int i=0; i < potentialHomes.size(); i++){
                if(!(potentialHomes.get(i).getPrice() > 200)){
                    potentialHomes.remove(i);
                }
            }
        }
        else //you dont enjoy shopping, so you get a better deal
            {
               for(int i=0; i < potentialHomes.size(); i++){
                    if(!(potentialHomes.get(i).getPrice() <= 20)){
                        potentialHomes.remove(i);
                } 
            }
        }
        
        //checking answer3
        for(int i=0; i < potentialHomes.size(); i++){
            if(!(potentialHomes.get(i).getMinimumNights() < answer3)){
                    potentialHomes.remove(i);
            }
        }  
             
        //checking answer 4
        if(answer4 < 50){
            for(int i=0; i < potentialHomes.size(); i++){
                if(!(potentialHomes.get(i).getNeighbourhood().equals("Sutton") ||
                 potentialHomes.get(i).getNeighbourhood().equals("Lambeth") ||
                 potentialHomes.get(i).getNeighbourhood().equals("Southwark")||
                 potentialHomes.get(i).getNeighbourhood().equals("Lewisham")||
                 potentialHomes.get(i).getNeighbourhood().equals("Greenwich") ||
                 potentialHomes.get(i).getNeighbourhood().equals("Bexley") ||
                 potentialHomes.get(i).getNeighbourhood().equals("Richmond upon Thames") ||
                 potentialHomes.get(i).getNeighbourhood().equals("Merton") ||
                 potentialHomes.get(i).getNeighbourhood().equals("Wandsworth"))){
                   potentialHomes.remove(i);
                }
            }
        }
        
        int maxReview=0;
        int homeIndex=0;
        // find the house with most reviews
        for(int i=0; i < potentialHomes.size(); i++){
            if(potentialHomes.get(i).getNumberOfReviews() > maxReview){
                homeIndex = i;
            }
        }
        
        //if no home found, generate a random home
        if(potentialHomes.size() == 0)
        {
            return data.get(rand.nextInt(data.size()));
        }
        return potentialHomes.get(homeIndex); 
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
