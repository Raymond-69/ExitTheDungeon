package etf;
import java.util.Random;
public class powerup {
	static Random random = new Random();
	public static int powerupDuration = 10;
	
	
	//Positive Buffs
	public static int bulletBuff= 2;
	public static int playerSpeedBuff= 1;
	public static int enemySpeedDebuff = -1;
	public static int enemyAttackDebuff = -2;
	
	//Negative Buffs
	public static int enemySpeedBuff = 1;
	public static int bulletDebuff = -2;
	public static int playerSpeedDebuff = -1;
	public static int enemyAttackBuff = 2;
	
	//Powerup coords
	public static int powerupX = random.nextInt(borderSpecs.RIGHT - borderSpecs.LEFT) + borderSpecs.LEFT;
	public static int powerupY = random.nextInt(borderSpecs.BOTTOM - borderSpecs.TOP) + borderSpecs.TOP;
	
	//Apply Powerups
	public static boolean drawPowerup = true;
	public static int playerSpeedEffect = 0;
	public static int enemySpeedEffect = 0;
	public static int enemyAttackPowerup = 0;

	//PowerUp Array
	static String [] powerupArray = {"enemyAttackDebuff","playerSpeedBuff","enemySpeedDebuff","enemySpeedBuff","playerSpeedDebuff","enemyAttackBuff"};
	
	public static void generatePowerup() {
		System.out.println("Powerup");
		int index = random.nextInt(powerupArray.length-1);
		String powerup = powerupArray[index];
		if (powerup == "playerSpeedBuff") {
			playerSpeedEffect += playerSpeedBuff;
		}
		else if (powerup == "enemySpeedDebuff") {
			enemySpeedEffect += playerSpeedDebuff;
		}
		else if (powerup == "enemySpeedBuff") {
			enemySpeedEffect += enemySpeedBuff;
		}
		else if(powerup == "playerSpeedDebuff" && playerSpeedDebuff >2) {
			playerSpeedEffect += playerSpeedDebuff;
		}
		else if (powerup == "enemyAttackBuff") {
			enemyAttackPowerup += enemyAttackBuff;
		}
		else if (powerup == "enemyAttackDebuff") {
			enemyAttackPowerup += enemyAttackDebuff;
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
