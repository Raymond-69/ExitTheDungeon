package etf;
public class bullet {
	//bullet properties

	public double bulletX;
	public double bulletY;
	public double bulletSpeedX;
	public double bulletSpeedY;
	public static int bulletW = 20;
	public static int bulletH = 20;
	public static boolean[] enemyBulletDrawn = new boolean[Enemy.enemyCount];
	public static double[] enemyBulletX = new double[Enemy.enemyCount];
	public static double[] enemyBulletY = new double[Enemy.enemyCount];
	public static double[] enemyBulletvX = new double[Enemy.enemyCount];
	public static double[] enemyBulletvY = new double[Enemy.enemyCount];
	public static int[] enemyFireTime = new int[Enemy.enemyCount];
	public static int enemyBulletW = 10;
	public static int enemyBulletH = 10;
	public static int enemyBulletSpeed = 3 + DungeonGame.difficultySettings*2;
	public bullet(double bulletX, double bulletY, double bulletSpeedX, double bulletSpeedY){

		this.bulletX = bulletX;
		this.bulletY = bulletY;
		this.bulletSpeedX = bulletSpeedX;
		this.bulletSpeedY = bulletSpeedY;


	}
	public int getX() {
		return (int) bulletX;
	}
	public int getY() {
		return (int) bulletY;
	}
	public void updateBullet() {
		bulletX+= bulletSpeedX;
		bulletY += bulletSpeedY;
	}


	public static double enemyBulletSpeedY(double enemyX, double enemyY, int playerX, int playerY, int enemyBulletSpeed){
		double slope = (playerY - enemyY + 15)/(enemyX - playerX -  15);
		double theta = Math.abs(Math.atan(slope));
		double enemyBulletSpeedY = Math.sin(theta)*enemyBulletSpeed;
		if(playerY < enemyY){
			enemyBulletSpeedY = -enemyBulletSpeedY;
		}

		return enemyBulletSpeedY;

	}
	public static double enemyBulletSpeedX(double enemyX, double enemyY, int playerX, int playerY, int enemyBulletSpeed){
		double slope = (playerY - enemyY + 15)/(enemyX - playerX -  15);
		double theta = Math.abs(Math.atan(slope));
		double enemyBulletSpeedX = Math.cos(theta)*enemyBulletSpeed;
		if(playerX < enemyX){
			enemyBulletSpeedX = -enemyBulletSpeedX;
		}
		return enemyBulletSpeedX;

	}

	public int getEnemyBulletX(double enemyBulletX){
		return (int) enemyBulletX;
	}
	public int getEnemyBulletY(double enemyBulletY){
		return (int) enemyBulletY;
	}


	public static void main(String[] args) {

	}

}

