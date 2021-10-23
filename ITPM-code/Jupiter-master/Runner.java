/**
 * JupiterInn
 * Team Jupiter Group Project
 * @authors (Amanjit Somal, Anjali Raveendran, Nabaa Al-Alawi, Farzaneh Javid)
 * @version 1.00 2018/03/22
 */

public class Runner {

	public static void main(String[] args) {
		AirbnbDataLoader airbnbDataLoader = new AirbnbDataLoader();
		JupiterInn jupiterInn = new JupiterInn(airbnbDataLoader);
	}
}
