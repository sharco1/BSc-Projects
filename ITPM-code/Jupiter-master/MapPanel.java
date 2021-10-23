import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Comparator;
/**
 * This class creates a panel which displays a map
 * Houses on the map present properties in a borough
 * When you click on a house, a dialog pops up showing all the homes in that borough
 * When you choose a particular house, another dialog appears giving you more information
 * on the home
 * 
 * @authors (Amanjit Somal, Anjali Raveendran, Nabaa Al-Alawi, Farzaneh Javid)
 * @version (03/27/2018)
 */
public class MapPanel extends JPanel
{
    //private JFrame frame = new JFrame();
    private AirbnbDataLoader data = new AirbnbDataLoader();
    //stores the listings that match search cireria (i.e. price range)
    private ArrayList<AirbnbListing> list = new ArrayList<>(); 
    ///stores borough name and number pf houses in that borough pairs
    private HashMap<String,Integer> housesInBorough = new HashMap<>();
    //stores borough name and borough location pairs
    private HashMap<String,String> boroughLocation = new HashMap<>();
    //label contains image of map
    private JLabel map = new JLabel(new ImageIcon("map.png"));
    //pops up when user clicks on a house
    private JDialog dialog;

    public MapPanel (int minPrice, int maxPrice) {
        makePanel(minPrice,maxPrice);
        this.setOpaque(false);
    }
    
    /**
     * Makes the panel that is inside frame
     */
    private void makePanel(int minPrice, int maxPrice)
    {
        //create a layout manager for mapPanel
        this.setLayout(new BorderLayout());
        
        //create title of panel
        JLabel title = new JLabel("LONDON MAP", SwingConstants.CENTER);
        setLabelFont(title, 70f);
        title.setForeground(Color.WHITE);

        //position components inside panel
        this.add(map, BorderLayout.CENTER);
        this.add(title, BorderLayout.NORTH);

        map.setLayout(null); //this allows for houses to be placed anywhere on the map
        searchForHouses(minPrice, maxPrice); //finds houses that meet search criteria
        makeHouses(map); //adds houses that are within price range to map
    }
    
    /**
     * finds all houses which meet the price range criteria
     * @param minPrice must be more than maxPrice
     * @param maxPrice must be less than minPrice
     */
    private void searchForHouses(double minPrice, double maxPrice) throws IllegalArgumentException
    {
        if(minPrice > maxPrice)
        {
            throw new IllegalArgumentException("minPrice is more than maxPrice"); 
        }
        ArrayList<AirbnbListing> dataList = data.load();// gets all data from CSV file
        //stores the houses that match search cireria
        for(int i = 0; i < dataList.size(); i++)
        {
            //checks that house is within price range
          if(dataList.get(i).getPrice() <= maxPrice && dataList.get(i).getPrice() >= minPrice)
            {
                list.add(dataList.get(i));
            }  
        }
        boroughCount(); //counts number of houses in each borough
    }
    
    /**
     * counts number of times a borough appears in list
     */
    private void boroughCount()
    {
       for(int i = 0; i < list.size(); i++)
       {
           //if this borough is not part of hashmap, add it
           String borough = list.get(i).getNeighbourhood();
           if(!housesInBorough.containsKey(borough)) 
           {
               housesInBorough.put(borough, 0);
           }
           //add 1 to value
           housesInBorough.put(borough, housesInBorough.get(borough) + 1);
       }
    }

    /**
     * Creates houses that go on the map
     * @param container will hold the houses
     */
    private void makeHouses(Container container)
    {
        ImageIcon houseIcon = new ImageIcon("house2.png"); //image used to illustrate houses
        Image image = houseIcon.getImage();
        int width = houseIcon.getIconWidth(); //width of icon
        int height = houseIcon.getIconHeight(); //height of icon
        
        boroughLocationMap(); //fills location map. this is used to place houses in correct location
        for(String key : housesInBorough.keySet())
        {
            String[] coordinates = boroughLocation.get(key).split(",");
            int xAxis = Integer.parseInt(coordinates[0]); //get x position
            int yAxis = Integer.parseInt(coordinates[1]); //get y position
            
            //size of house icon size is dependent on how many houses this borough has,
            //relative to borough with most houses
            float sizeProportion = (float) housesInBorough.get(key)/findBoroughWithMostHouses();
            int newWidth = (int) (width*(sizeProportion)); 
            int newHeight = (int) (height*(sizeProportion)); 
            
            double threshold = 0.25; //houses below this threshold will all have same size
             
            JLabel house;
            if(sizeProportion < threshold) //default size for boroughs with ony a few houses
            {
                newWidth = (int) (width*threshold);
                newHeight = (int) (height*threshold);
            }
            else if(sizeProportion >= 0.70 && sizeProportion != 1) //we don't want too many big houses
            {
                newWidth = (int) (width*0.7);
                newHeight = (int) (height*0.7);
            }
            
            //sets the size of house icon
            Image newimg = image.getScaledInstance(newWidth,  newHeight,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon houseIcon2 = new ImageIcon(newimg);
            house = new JLabel(houseIcon2);
            house.setBounds(xAxis, yAxis, newWidth,  newHeight);

            container.add(house);
            //add a mouse listener to each house icon
            house.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                //get the houses that have been added so far to the map
                Component[] stack = container.getComponents();
                container.removeAll();
                //add this house first so that when you click on it
                //it comes to the front of the screen
                container.add(house); 
                
                //change colour of house icon to indicate that user has selected it previously
                ImageIcon originalIcon = (ImageIcon) house.getIcon();
                ImageIcon icon = new ImageIcon("blueHouse.png");
                Image image = icon.getImage();
                Image newimg = image.getScaledInstance(originalIcon.getIconWidth(),  
                               originalIcon.getIconHeight(),  java.awt.Image.SCALE_SMOOTH);  
                ImageIcon newIcon = new ImageIcon(newimg); 
                
                house.setIcon(newIcon); //set this as new icon
                for(Component comp : stack) //add remainder of house icons back into map
                {
                   if(comp != house)
                   {
                       container.add(comp);
                   }
                }
                container.repaint(); //update map on screen
                handleMousePressed(e,key,house);//add a method that displays a pop up                   
            }
            });
        }
    }
        
    /**
     *Creates a dialog when user click on a house
     *@param e mouse event
     *@param key name of borough
     *@param house the icon that the user clicked on
     */
    private void handleMousePressed(MouseEvent e, String key, Component house)
    {
        //holds all house listing panels
        JPanel pane = new JPanel(new GridLayout(0,1));
        for(int i = 0; i < list.size(); i++)
        {
            if(key.equals(list.get(i).getNeighbourhood())) //we are looking at the right borough
            {
                makeHouseInfoList(list.get(i), pane);
            }
        }
        JScrollPane scrollPane = new JScrollPane(pane);
        JPanel sortPane = makeSortPane(key, pane);
        
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(sortPane, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        
        Object[] options = {"Confirm Choice"};
        scrollPane.setPreferredSize( new Dimension( (int) pane.getMinimumSize().getWidth(), 200 ));
        makeDialog(house, container, key); //create dialog when panel is selected
    }
    
    /**
     * creates a panel which holds information about a house
     * @param listing the house in question
     * @param panel holds panels that contain information about houses
     */
    private void makeHouseInfoList(AirbnbListing listing, JPanel panel)
    {
        JPanel houseInfo = new JPanel(); //holds information about house 
        houseInfo.setLayout(new BoxLayout(houseInfo, BoxLayout.PAGE_AXIS));
        panel.add(houseInfo);
    
        Border blackline = BorderFactory.createLineBorder(new Color(210,80,48));
        houseInfo.setBorder(blackline);
        
        //creates a label that stores name of host
        JLabel name = new JLabel(listing.getHost_name());
        name.setForeground(new Color(210,80,48));
        setLabelFont(name, 12f);
        
        //creates a label that stores price of house
        JLabel price = new JLabel("  • £" + listing.getPrice() + " per day");
        price.setForeground(new Color(210,80,48));
        setLabelFont(price, 10f);
        
        //creates a label that stores total reviews of house
        JLabel reviews = new JLabel("  • " + listing.getNumberOfReviews() + " reviews");
        reviews.setForeground(new Color(210,80,48));
        setLabelFont(reviews, 10f);
        
        //creates a label that stores minimum stay
        JLabel minStay = new JLabel("  • Min " + listing.getMinimumNights() + " nights stay");
        minStay.setForeground(new Color(210,80,48));
        setLabelFont(minStay, 10f);
        
        houseInfo.add(name);
        houseInfo.add(price);
        houseInfo.add(reviews);
        houseInfo.add(minStay);
        
        houseInfo.setBackground(new Color(254,251,255));
        
        
        houseInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                Component[] stack = panel.getComponents();
                for(Component comp : stack) //make houseInfo panel standard colour first
                {
                    //changes text back to right colour
                    JPanel panel = (JPanel) comp;
                    for(Component innerComp : panel.getComponents())
                    {
                        innerComp.setForeground(new Color(210,80,48)); 
                    }
                    //changes background of panel to right colour
                    comp.setBackground(new Color(254,251,255));
                }
                
                //change the color of this houseInfo panel
                houseInfo.setBackground(new Color(210,80,48));
                for(Component innerComp : houseInfo.getComponents())
                {
                    innerComp.setForeground(new Color(254,251,255)); 
                }
                
                //creates a pop up when this HouseInfo panel is selected
                moreInfoPopup(houseInfo, listing.getName());
            }
            });
    }
    
    /**
     * creates a dialog which gives more information on house
     * @param comp the component selected
     * @param information house information
     */
    private void moreInfoPopup(Component comp, String information)
    {
        JLabel label = new JLabel(information);
        setLabelFont(label, 12f);
        JOptionPane.showMessageDialog(comp, label, "More information on home", JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * creates a sort bar which has 4 menu items: number of reviews (highest
     * to lowest), price (lowest to highest), price (highest to lowest), host name
     */
    private JPanel makeSortPane(String borough, JPanel panel)
    {
        JPanel sortPane = new JPanel();
        sortPane.setLayout(new FlowLayout((FlowLayout.RIGHT)));
        
        JLabel sort = new JLabel("sort by");
        setLabelFont(sort, 10f);
        sortPane.add(sort);
        
        String[] sortOptions = {"Reviews (Highest to Lowest)", "Price (Lowest to Highest)",
                                "Price (Highest to Lowest)", "Host name (alphabetically)"};
        
        JComboBox sortBox = new JComboBox(sortOptions);
        sortBox.addActionListener(e -> sortList(e, borough, panel));
        sortPane.add(sortBox);
        return sortPane;
    }
    
    /**
     * creates a dialog that shows all houses in a borough
     * @param house the house icon that was clicked
     * @param container holds panels which represent listings of houses
     * @param borough where the house is located in london
     */
    private void makeDialog(Component house, JPanel container, String borough)
    {
        dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.setTitle(borough);
        dialog.add(container, BorderLayout.CENTER);
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dialog.setLocationRelativeTo(house);
        dialog.setModal(true);
        dialog.pack();
        dialog.setVisible(true);
    }
       
    /**
     * Sorts listings in dialogue according to price, name of host or number of reviews
     * @param e the action event
     * @param borough where the listings are located
     * @param panel holds all house listing panels
     */
    private void sortList(ActionEvent e, String borough, JPanel panel)
    {
        JComboBox cb = (JComboBox)e.getSource();
        String option = (String)cb.getSelectedItem();
        //gets all listings in this borough
        ArrayList<AirbnbListing> boroughList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            if(borough.equals(list.get(i).getNeighbourhood())) //we are looking at the right borough
            {
                boroughList.add(list.get(i));
            }
        }
        
        //sort list
        ArrayList<AirbnbListing> sortedList = new ArrayList<>();
        if(option.equals("Reviews (Highest to Lowest)"))
        {
            sortedList = sortByReviews(boroughList);
        }
        else if(option.equals("Price (Lowest to Highest)"))
        {
            sortedList = sortByLowestPrice(boroughList);
        }
        else if(option.equals("Price (Highest to Lowest)"))
        {
            sortedList = sortByHighestPrice(boroughList);
        }
        else if(option.equals("Host name (alphabetically)"))
        {
            sortedList = sortByHostName(boroughList);
        }
        
        panel.removeAll(); //remove components so they can be reinserted in correct order
        for(int i = 0; i < sortedList.size(); i++)
        {
            makeHouseInfoList(sortedList.get(i), panel);
        }
        
        dialog.setVisible(true); //makes dialog reload so that it shows sorted entries
    }
    
    /**
     * Sorts listings according to number of reviews it has.
     * houses with more reviews appear at the top of list
     * @param borough the location of listings in london
     */
    private ArrayList<AirbnbListing> sortByReviews(ArrayList<AirbnbListing> boroughList)
    {
        boroughList.sort(Comparator.comparing(AirbnbListing::getNumberOfReviews).reversed());
        return boroughList;
    }
    
    /**
     * sort houses from lowest to highest price
     * @param borough the location of listings in london
     */
    private ArrayList<AirbnbListing> sortByLowestPrice(ArrayList<AirbnbListing> boroughList)
    {
        boroughList.sort(Comparator.comparing(AirbnbListing::getPrice));
        return boroughList;
    }
    
    /**
     * sort houses from highest to lowest price
     * @param borough the location of listings in london
     */
    private ArrayList<AirbnbListing> sortByHighestPrice(ArrayList<AirbnbListing> boroughList)
    {
        boroughList.sort(Comparator.comparing(AirbnbListing::getPrice).reversed());
        return boroughList;
    }
    
    /**
     * sort houses alphabetically by host name
     * @param borough the location of listings in london
     */
    private ArrayList<AirbnbListing> sortByHostName(ArrayList<AirbnbListing> boroughList)
    {
        boroughList.sort(Comparator.comparing(AirbnbListing::getHost_name));
        return boroughList;
    }
    
    /**
     * finds borough with most houses
     * @return number of houses in this borough
     */
    private int findBoroughWithMostHouses()
    {
        int totalHouses = 0;
        for(String key : housesInBorough.keySet())
        {
            int houses = housesInBorough.get(key);
            if(totalHouses < houses)
            {
                totalHouses = houses;
            }
        }
        return totalHouses;
    }
    
    /**
     * fills boroughLocation map with co-ordinates of boroughs
     */
    private void boroughLocationMap()
    {
        boroughLocation.put("Kingston upon Thames","230,514");
        boroughLocation.put("Croydon","471,584");
        boroughLocation.put("Bromley","617,563");
        boroughLocation.put("Hounslow","123,393");
        boroughLocation.put("Ealing","175,308");
        boroughLocation.put("Hillingdon","47,247");
        boroughLocation.put("Harrow","152,173");
        boroughLocation.put("Brent","241,237");
        boroughLocation.put("Barnet","298,137");
        boroughLocation.put("Enfield","441,72");
        boroughLocation.put("Waltham Forest","526,173");
        boroughLocation.put("Redbridge","618,185");
        boroughLocation.put("Sutton","352,585");
        boroughLocation.put("Lambeth","420,407");
        boroughLocation.put("Southwark","468,368");
        boroughLocation.put("Lewisham","525,440");
        boroughLocation.put("Enfield","441,72");
        boroughLocation.put("Westminster","369,315");
        boroughLocation.put("Kensington and Chelsea","351,353");
        boroughLocation.put("Hammersmith and Fulham","289,334");
        boroughLocation.put("Wandsworth","323,424");
        boroughLocation.put("Tower Hamlets","502,298");
        boroughLocation.put("Hackney","484,248");
        boroughLocation.put("Islington","422,249");
        boroughLocation.put("Camden","361,250");
        boroughLocation.put("Richmond upon Thames","179,440");
        boroughLocation.put("Merton","325,504");
        boroughLocation.put("Greenwich","607,376");
        boroughLocation.put("Bexley","696,412");
        boroughLocation.put("Havering","806,207");
        boroughLocation.put("Barking and Dagenham","685,265");
        boroughLocation.put("Newham","585,283");
        boroughLocation.put("Haringey","423,170");
        boroughLocation.put("City of London","443,309");
    }
    
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