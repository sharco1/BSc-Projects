import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
/**
 * this panel showcases some interesting statistics generated from data
 *
 * @authors (Amanjit Somal, Anjali Raveendran, Nabaa Al-Alawi, Farzaneh Javid)
 * @version (03/25/2018)
 */
public class StatisticsPanel extends JPanel
{
    //panels that display 1 statistic each at any point in time
    private JPanel  panel1;
    private JPanel  panel2;
    private JPanel  panel3;
    private JPanel  panel4;
    private AirbnbDataLoader loader = new AirbnbDataLoader();
    private ArrayList<AirbnbListing> allData = new ArrayList<>(); 
    private JButton back;
    private JButton forward;
    //panels that hold 1 statistic
    private JPanel statPanel1;
    private JPanel statPanel2;
    private JPanel statPanel3;
    private JPanel statPanel4;
    private JPanel statPanel5;
    private JPanel statPanel6;
    private JPanel statPanel7;
    private JPanel statPanel8;
    private Random rand = new Random();
    
    public StatisticsPanel () {
        allData = loader.load();
        makePanel();
    }
    
    /**
     * creates this panel
     */
    private void makePanel()
    {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        
        //create title of panel
        JLabel title = new JLabel("STATISTICS", SwingConstants.CENTER);
        setLabelFont(title, 70f);
        title.setForeground(Color.WHITE);
        this.add(title, BorderLayout.NORTH);
        
        //container holds the 4 panels on screen
        JPanel container = new JPanel();
        container.setBackground(Color.WHITE);
        container.setLayout(new GridLayout(2,2));
        this.add(container, BorderLayout.CENTER);
        
        //makes the panels and add them to the container
        makePanel1(container);
        makePanel2(container);
        makePanel3(container);
        makePanel4(container);
    }
    
    /**
     * each panel can only diplay 2 stats, uses cardlayout
     * @param container hold this panel
     */
    private void makePanel1(Container container)
    {
      JPanel panel = new JPanel(new BorderLayout()); //panel that holds all components (e.g. button, stat etc)
      JPanel cards = new JPanel(); //panel that holds statistic
      CardLayout cardLayout = new CardLayout();
      cards.setLayout(cardLayout);
      makeStatPanel1();
      cards.add(statPanel1, "Stat 1");
      makeStatPanel2();
      cards.add(statPanel2, "Stat 2");
      panel.add(cards, BorderLayout.CENTER);
          
      makeBackButton(cards, cardLayout); //create back button for panel
      panel.add(back, BorderLayout.WEST);
      
      makeForwardButton(cards, cardLayout); //create forward button for panel
      panel.add(forward, BorderLayout.EAST);
      
      panel.setBackground(Color.WHITE);
      panel.setBorder(BorderFactory.createLineBorder(Color.white));
      container.add(panel); //add panel to container  
    }
    
    /**
     * each panel can only diplay 2 stats, uses cardlayout
     * @param container hold this panel
     */
    private void makePanel2(Container container)
    {
      JPanel panel = new JPanel(new BorderLayout()); //panel that holds all components
      JPanel cards = new JPanel(); //panel that holds statistic
      CardLayout cardLayout = new CardLayout();
      cards.setLayout(cardLayout);
      makeStatPanel3();
      cards.add(statPanel3, "Stat 3");
      makeStatPanel4();
      cards.add(statPanel4, "Stat 4");
      panel.add(cards, BorderLayout.CENTER);
      
      makeBackButton(cards, cardLayout); //create back button for panel
      panel.add(back, BorderLayout.WEST);
      
      makeForwardButton(cards, cardLayout); //create forward button for panel
      panel.add(forward, BorderLayout.EAST);
      
      panel.setBackground(Color.WHITE);
      panel.setBorder(BorderFactory.createLineBorder(Color.white));
      container.add(panel); //add panel to container 
    }
    
    /**
     * each panel can only diplay 2 stats, uses cardlayout
     * @param container hold this panel
     */
    private void makePanel3(Container container)
    {
      JPanel panel = new JPanel(new BorderLayout()); //panel that holds all components
      JPanel cards = new JPanel(); //panel that holds statistic
      CardLayout cardLayout = new CardLayout();
      cards.setLayout(cardLayout);
      makeStatPanel5();
      cards.add(statPanel5, "Stat 5");
      makeStatPanel6();
      cards.add(statPanel6, "Stat 6");
      panel.add(cards, BorderLayout.CENTER);
      
      makeBackButton(cards, cardLayout); //create back button for panel
      panel.add(back, BorderLayout.WEST);
      
      makeForwardButton(cards, cardLayout); //create forward button for panel
      panel.add(forward, BorderLayout.EAST);
      
      panel.setBackground(Color.WHITE);
      panel.setBorder(BorderFactory.createLineBorder(Color.white));
      container.add(panel); //add panel to container 
    }
    
    /**
     * each panel can only diplay 2 stats, uses cardlayout
     * @param container hold this panel
     */
    private void makePanel4(Container container)
    {
      JPanel panel = new JPanel(new BorderLayout()); //panel that holds all components
      JPanel cards = new JPanel(); //panel that holds statistic
      CardLayout cardLayout = new CardLayout();
      cards.setLayout(cardLayout);
      makeStatPanel7();
      cards.add(statPanel7, "Stat 7");
      makeStatPanel8();
      cards.add(statPanel8, "Stat 8");
      panel.add(cards, BorderLayout.CENTER);
      
      makeBackButton(cards, cardLayout); //create back button for panel
      panel.add(back, BorderLayout.WEST);
      
      makeForwardButton(cards, cardLayout); //create forward button for panel
      panel.add(forward, BorderLayout.EAST);
      
      panel.setBackground(Color.WHITE);
      panel.setBorder(BorderFactory.createLineBorder(Color.white));
      container.add(panel); //add panel to container 
    }
    
    /**
     * makes a panel that holds 1 statistic
     */
    private void makeStatPanel1()
    {
        statPanel1 = new JPanel(new BorderLayout());
        JLabel title = new JLabel("AVERAGE NUMBER OF REVIEWS PER PROPERTY", SwingConstants.CENTER);
        setLabelFont(title, 12f);
        title.setPreferredSize(new Dimension(380,200));
        
        int avgReview = (int) getAverageReview(allData);
        JLabel stat = new JLabel(String.valueOf(avgReview), SwingConstants.CENTER);
        setLabelFont(stat, 20f);
        statPanel1.add(title,BorderLayout.NORTH);
        statPanel1.add(stat,BorderLayout.CENTER);
    }
    
    /**
     * makes a panel that holds 1 statistic
     */
    private void makeStatPanel2()
    {
        statPanel2 = new JPanel(new BorderLayout());
        JLabel title = new JLabel("NUMBER OF PROPERTIES", SwingConstants.CENTER);
        setLabelFont(title, 12f);
        title.setPreferredSize(new Dimension(380,200));
        
        int totalNoOfProperties = allData.size();
        JLabel stat = new JLabel(String.valueOf(totalNoOfProperties), SwingConstants.CENTER);
        setLabelFont(stat, 20f);
        statPanel2.add(title,BorderLayout.NORTH);
        statPanel2.add(stat,BorderLayout.CENTER);
    }
    
    /**
     * makes a panel that holds 1 statistic
     */
    private void makeStatPanel3()
    {
        statPanel3 = new JPanel(new BorderLayout());
        JLabel title = new JLabel("ENTIRE HOMES/APPARTMENTS AVAILABLE", SwingConstants.CENTER);
        setLabelFont(title, 12f);
        title.setPreferredSize(new Dimension(380,200));
        
        int entireHomeAptCounts = getEntireHomeAptCounts(allData);
        JLabel stat = new JLabel(String.valueOf(entireHomeAptCounts), SwingConstants.CENTER);
        setLabelFont(stat, 20f);
        statPanel3.add(title,BorderLayout.NORTH);
        statPanel3.add(stat,BorderLayout.CENTER);
    }
    
    /**
     * makes a panel that holds 1 statistic
     */
    private void makeStatPanel4()
    {
        statPanel4 = new JPanel(new BorderLayout());
        JLabel title = new JLabel("MOST LUXURIOUS NEIGHBOURHOOD", SwingConstants.CENTER);
        setLabelFont(title, 12f);
        title.setPreferredSize(new Dimension(380,200));
        
        Map.Entry<String, Float> priciestNeighbourhood = getPriciestNeighbourhood( allData );
        JLabel stat = new JLabel(priciestNeighbourhood.getKey(), SwingConstants.CENTER);
        setLabelFont(stat, 12f);
        statPanel4.add(title,BorderLayout.NORTH);
        statPanel4.add(stat,BorderLayout.CENTER);
    }
    
    /**
     * makes a panel that holds 1 statistic
     */
    private void makeStatPanel5()
    {
        statPanel5 = new JPanel(new BorderLayout());
        JLabel title = new JLabel("HOST WITH MOST REVIEWS IN CITY OF LONDON", SwingConstants.CENTER);
        setLabelFont(title, 12f);
        title.setPreferredSize(new Dimension(380,200));
        
        String mostReviewedHost = getMostReviewedHost( allData );
        JLabel stat = new JLabel(mostReviewedHost, SwingConstants.CENTER);
        setLabelFont(stat, 20f);
        statPanel5.add(title,BorderLayout.NORTH);
        statPanel5.add(stat,BorderLayout.CENTER);
    }
    
    /**
     * makes a panel that holds 1 statistic
     */
    private void makeStatPanel6()
    {
        statPanel6 = new JPanel(new BorderLayout());
        JLabel title = new JLabel("HOST WITH CHEAPEST HOME", SwingConstants.CENTER);
        setLabelFont(title, 12f);
        title.setPreferredSize(new Dimension(380,200));
        
        Map.Entry<String, Float> cheapestHost = getCheapestHostName(allData);
        JLabel stat = new JLabel(cheapestHost.getKey(), SwingConstants.CENTER);
        setLabelFont(stat, 20f);
        statPanel6.add(title,BorderLayout.NORTH);
        statPanel6.add(stat,BorderLayout.CENTER);
    }
    
    /**
     * makes a panel that holds 1 statistic
     */
    private void makeStatPanel7()
    {
        statPanel7 = new JPanel(new BorderLayout());
        JLabel title = new JLabel("BOROUGH WITH MOST AVAILABLE HOMES PER YEAR", SwingConstants.CENTER);
        setLabelFont(title, 10f);
        title.setPreferredSize(new Dimension(380,200));
        Map.Entry<String, Float> mostAvailable = getMostAvailable( allData);
        JLabel stat = new JLabel(mostAvailable.getKey(), SwingConstants.CENTER);
        setLabelFont(stat, 20f);
        statPanel7.add(title,BorderLayout.NORTH);
        statPanel7.add(stat,BorderLayout.CENTER);
    }
    
    /**
     * makes a panel that holds 1 statistic
     */
    private void makeStatPanel8()
    {
        statPanel8 = new JPanel(new BorderLayout());
        JLabel title = new JLabel("CHEAPEST HOME PRICE IN CITY OF LONDON", SwingConstants.CENTER);
        setLabelFont(title, 12f);
        title.setPreferredSize(new Dimension(380,200));
        Map.Entry<String, Float> cheapest = getCheapestCentralLondonProperties(allData);
        JLabel stat = new JLabel("£" + (cheapest.getValue()), SwingConstants.CENTER);
        setLabelFont(stat, 20f);
        statPanel8.add(title,BorderLayout.NORTH);
        statPanel8.add(stat,BorderLayout.CENTER);
    }
    
    /**
     * makes a back button for each panel in grid
     */
    private void makeBackButton(JPanel cards, CardLayout cardLayout)
    {
        back = new JButton("<");
        back.addActionListener(e -> changeStatistic(cards, cardLayout)); 
    }
    
    /**
     * makes a forward button for each panel in grid
     */
    private void makeForwardButton(JPanel cards, CardLayout cardLayout)
    {
        forward = new JButton(">");
        forward.addActionListener(e -> changeStatistic(cards, cardLayout)); 
    }
    
    /**
     * changes statistic when forward or backward is pressed
     */
    private void changeStatistic(JPanel cards, CardLayout cardLayout) {
           cardLayout.next(cards);
    }
    
    /**
     * returns average review
     */
    private float getAverageReview(ArrayList<AirbnbListing> allData){
        float sum = 0;

        for(int i=0;i<allData.size();i++){
            sum = sum + allData.get(i).numberOfReviews;
        }

        float averageReview = sum/allData.size();      
        return averageReview;
    }
    
    /**
     * return number of homes that are renting the whole house out
     */
    private int getEntireHomeAptCounts(ArrayList<AirbnbListing> allData){

        int count = 0;

        for(int i=0;i<allData.size();i++){
            if(allData.get(i).room_type.equals("Entire home/apt")){
                count = count + 1;
            }
        }
        int entireHomeAptCounts = count;
        return entireHomeAptCounts;
    }
    
    /**
     * finds the most expsive borough to live in
     */
    private Map.Entry<String, Float> getPriciestNeighbourhood( ArrayList<AirbnbListing> allData ){
        HashMap<String, Float> neighbourhoodToAveragePrice = new HashMap<String, Float>();
        //find out all the neighbourhoods in the dataset and store to an arraylist
        ArrayList<String> neighbourhoods = new ArrayList<String>();
        for(int i=0;i<allData.size();i++){
            if (!neighbourhoods.contains( allData.get(i).neighbourhood ) ){
                neighbourhoods.add( allData.get(i).neighbourhood );
            }
        }

        //for each neighbourhood in the arraylist find the average price, number of minNights, countOfListings in that neighbourhood
        for( int j=0; j<neighbourhoods.size(); j++ ){
            float sum = 0;
            int count = 0;
            float sumOfMinNights = 0 ;
            for(int i=0;i<allData.size();i++){
                if ( allData.get(i).neighbourhood.equals( neighbourhoods.get( j ) ) ){
                    sum = sum + allData.get( i ).price;     // running sum for this neighbuorhood
                    count = count + 1;                      //increment the counter for this neightbourhood
                    sumOfMinNights = sumOfMinNights + allData.get( i ).minimumNights;
                }             
            }

            //save the data for this neighbourhood onto a Hashmap
            float avg = sum/count;          
            neighbourhoodToAveragePrice.put( neighbourhoods.get( j ), avg );

        }

        Map.Entry<String, Float> maxEntry = null;
        for (Map.Entry<String, Float> entry : neighbourhoodToAveragePrice.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }

        return maxEntry;
    }

    /**
     * returns name of host who has most reviews
     */
    private String getMostReviewedHost( ArrayList<AirbnbListing> allData )
    {
        int maxReview =0;
        String hostId = "";
        for(int i =0; i < allData.size(); i++)
        {
            if(allData.get(i).getNeighbourhood().equals("City of London"))
            {
                if(maxReview < allData.get(i).getNumberOfReviews())
                {
                    maxReview = allData.get(i).getNumberOfReviews();
                    hostId= allData.get(i).getHost_name();
                }
            }
        }

        return hostId;
    }

    /**
     * returns name of host who has cheapest home on rent
     */
    private Map.Entry<String, Float> getCheapestHostName( ArrayList<AirbnbListing> allData ){
        HashMap<String, Float> cheapestHostName = new HashMap<String, Float>();

        //finds host name and adds them to an arraylist
        ArrayList<String> hostName = new ArrayList<String>();
        for(int i=0;i<allData.size();i++){
            if (!hostName.contains( allData.get(i).host_name) && allData.get(i).neighbourhood.equals("City of London") ){               
                hostName.add( allData.get(i).host_name  );
            }
        }

        //for each host name in the arraylist find the cheapest one
        for( int j=0; j<hostName.size(); j++ ){
            float sum = 0;
            int count = 0;
            float sumOfMinNights = 0 ;
            for(int i=0;i<allData.size();i++){
                if ( allData.get(i).host_name.equals( hostName.get( j ) ) ){
                    sum = sum + allData.get( i ).price;     // running sum for this neighbuorhood
                    count = count + 1;                      //increment the counter for this neightbourhood
                    sumOfMinNights = sumOfMinNights + allData.get( i ).minimumNights;
                }             
            }

            //save the data for this name onto a Hashmap
            float avg = sum/count;          
            cheapestHostName.put( hostName.get( j ), avg );

        }

        Map.Entry<String, Float> minEntry = null;
        for (Map.Entry<String, Float> entry : cheapestHostName.entrySet())
        {
            if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) > 0)
            {
                minEntry = entry;
            }
        }

        return minEntry;
    }  
    
    /**
     * returns name of bourhg that is avilable the most throughout the year
     */
    private Map.Entry<String, Float> getMostAvailable(ArrayList<AirbnbListing> allData){
        
        HashMap<String, Float> mostAvailable = new HashMap<String, Float>();
        
        //finds the name of boroughs and adds them to an arrayList
        ArrayList<String> neighbourhood = new ArrayList<String>();
        for(int i=0;i<allData.size();i++){
            if (!neighbourhood.contains( allData.get(i).neighbourhood)  ){               
                neighbourhood.add( allData.get(i).neighbourhood );
            }
        }
                
                
        //for each neighbourhood in the arraylist find the total number of properties available 
        for( int j=0; j<neighbourhood.size(); j++ ){
            float sum = 0;
            for(int i=0;i<allData.size();i++){
                if ( allData.get(i).neighbourhood.equals( neighbourhood.get( j ) ) ){
                    sum = sum + allData.get( i ).getAvailability365();     // running sum for this reviews
                }             
            }

            mostAvailable.put( neighbourhood.get( j ), sum );

        }

        Map.Entry<String, Float> maxEntry = null;
        for (Map.Entry<String, Float> entry : mostAvailable.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        
        
        return maxEntry;
    }
    
    /**
     * sets label font
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
    
        /**
         * finds the cheapest home in central
         */
        public Map.Entry<String, Float> getCheapestCentralLondonProperties( ArrayList<AirbnbListing> allData ){
        HashMap<String, Float> cheapestCentralLondonProperties = new HashMap<String, Float>();
        //find neighbourhoods in zone 1 and add them to an arraylist

        ArrayList<String> neighbourhoods = new ArrayList<String>();
        for(int i=0;i<allData.size();i++){
            if (!neighbourhoods.contains( allData.get(i).neighbourhood) && allData.get(i).neighbourhood.equals("Westminster")){               
                neighbourhoods.add( allData.get(i).neighbourhood  );
            }
        }

        //for each neighbourhood in the arraylist chepeast price
        for( int j=0; j<neighbourhoods.size(); j++ ){
            float sum = 0;
            int count = 0;
            float sumOfMinNights = 0 ;
            for(int i=0;i<allData.size();i++){
                if ( allData.get(i).neighbourhood.equals( neighbourhoods.get( j ) ) ){
                    sum = sum + allData.get( i ).price;     // running sum for this neighbuorhood
                    count = count + 1;                      //increment the counter for this neightbourhood
                    sumOfMinNights = sumOfMinNights + allData.get( i ).minimumNights;
                }             
            }

            //save the data for this name onto a Hashmap
            float avg = sum/count;          
            cheapestCentralLondonProperties.put(neighbourhoods.get( j ), avg );

        }
    

        Map.Entry<String, Float> minEntry = null;
        for (Map.Entry<String, Float> entry : cheapestCentralLondonProperties.entrySet())
        {
            if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) > 0)
            {
                minEntry = entry;
            }
        }
        
        return minEntry;
    } 
}
