
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class JupiterInnTest.
 * tests class JupiterInn
 *
 *JupiterInn
 * Team Jupiter Group Project
 * @authors (Amanjit Somal, Anjali Raveendran, Nabaa Al-Alawi, Farzaneh Javid)
 * @version 1.00 2018/03/22
 */
public class JupiterInnTest
{
    private AirbnbDataLoader airbnbDa1;
    private JupiterInn jupiterI1;

    
    /**
     * Default constructor for test class JupiterInnTest
     * used to test JupiterInn Class
     */
    public JupiterInnTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        airbnbDa1 = new AirbnbDataLoader();
        jupiterI1 = new JupiterInn(airbnbDa1);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testSwitchPanel()
    {
        assertEquals(4, jupiterI1.getCards());
    }

    @Test
    public void testBackButton()
    {
        assertEquals(1, jupiterI1.getBackButton());
    }

    @Test
    public void testForwardButton()
    {
        assertEquals(1, jupiterI1.getForwardButton());
    }
}



