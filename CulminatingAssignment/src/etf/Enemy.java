package etf;
import java.util.Arrays;
import java.util.Random;
public class Enemy {
	// Fields
	// Position
	  public static int enemyCount = 6;
	  public static int enemyAlive = enemyCount;
	  public static double[] enemyX = new double[enemyCount];
	  public static double[] enemyY = new double[enemyCount];
	  public static int[] enemyVel = new int[enemyCount];
	  public static double[] enemyVelX = new double[enemyCount];
	  public static double[] enemyVelY = new double[enemyCount];
	  public static int[] enemyWalkingDistance = new int[enemyCount];
	  public static double[] enemyWalkedDistance = new double[enemyCount];
	  public static int enemyFire;
	  public static boolean enemyDrawn[] = new boolean[enemyCount];
	  public static int enemyW = 30;
	  public static int enemyH = 30;
	  static int[] enemyAngle = new int[enemyCount];
	  public static int angleUpperbound = 361;
	  public static int distanceUpperbound = 100;
	  static int enemySpeed = 3 + powerup.enemySpeedEffect;
	
	  public Enemy(double enemyX[], double enemyY[]){
			
			Enemy.enemyX = enemyX;
			Enemy.enemyY = enemyY;
			
			
		}	
	 public static void moveEnemy(double[] enemyvX, double[] enemyvY, int[] enemyAngle, int[] enemyWalkingDistance, int angleUpperbound, int distanceUpperbound, int enemySpeed) {
		   Random random = new Random();

		   for(int i = 0; i < enemyCount; i++){
		     int randomAngle = random.nextInt(angleUpperbound);
		     enemyAngle[i] = randomAngle;
		     int randomDistance = random.nextInt(distanceUpperbound) + 50;
		     enemyWalkingDistance[i] = randomDistance;
		     enemyvX[i] = Math.cos(enemyAngle[i])*enemySpeed;
		     enemyvY[i] = Math.sin(enemyAngle[i])*enemySpeed;
		     
		   }

		 }
	
	 public static void generateEnemy() {
		 Random random = new Random();
		 
		 for(int i = 0; i < enemyCount; ++i){
			 int x =random.nextInt(borderSpecs.RIGHT/2 - borderSpecs.LEFT - enemyW) + borderSpecs.LEFT + borderSpecs.RIGHT/2;
			 int y =random.nextInt(borderSpecs.BOTTOM - borderSpecs.TOP - enemyH)+ borderSpecs.TOP;
			 
			 enemyX[i] = x;
			 enemyY[i] = y;
		

		 } 
	 }
	
	public static void main(String[] args) {
	 Arrays.fill(enemyDrawn, true);
	 Arrays.fill(enemyVelX,0);
	 Arrays.fill(enemyVelY,0);
	}

}
