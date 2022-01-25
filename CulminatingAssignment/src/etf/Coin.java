package etf;
import java.util.Random;
public class Coin {
	public double coinX;
	public double coinY;
	
	public Coin(double x, double y) {
		this.coinX = x;
		this.coinY = y;
	}
	public int getX() {
		return (int) coinX;
	}
	public int getY() {
		return (int) coinY;
	}
	public static int generateCoin() {
		int generate = 1;
		Random random = new Random();
		generate = random.nextInt(3);
		return generate;
	}
}
