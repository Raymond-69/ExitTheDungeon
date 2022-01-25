package etf;
import java.util.Arrays;
public class dionysus {
	public static int width = 60;
	public static int height = 60;


	public static void dionysusBulletWave(double[] bossBulletvX, double[] bossBulletvY, double bossBulletSpeed){
		int[] bulletAngle = new int[11];
		Arrays.fill(bulletAngle, 0);
		int value = 0;
		for(int i = 0; i < bulletAngle.length; i++){
			value += 15;
			bulletAngle[i] = value;

			bossBulletvX[i] = Math.cos(bulletAngle[i])*bossBulletSpeed;
			bossBulletvY[i] = Math.sin(bulletAngle[i])*bossBulletSpeed;
			if(bossBulletvY[i] < 0) {
				bossBulletvY[i] = bossBulletvY[i]*-1;
			}

		}}
	 public static void dionysusTargetedBullet(int playerX, int playerY, double bossX, double bossY, double[] bossBulletvX, double[] bossBulletvY){
		   double[] bossBulletSpeed = new double[15];
		   double slope = (playerY - bossY + 37.5)/(bossX - playerX -  37.5);
		   double theta = Math.abs(Math.atan(slope));
		   for(int i = 0; i < bossBulletSpeed.length; i++){
		   bossBulletSpeed[i] = i + 7;
		   bossBulletvX[i] = Math.cos(theta)*bossBulletSpeed[i];
		   bossBulletvY[i] = Math.sin(theta)*bossBulletSpeed[i];        
		   if(bossX > playerX){
		   bossBulletvX[i] = -bossBulletvX[i];
		}
		   }}
	public static void dionysusBulletAnotherVer(double[] bossBulletvX, double[] bossBulletvY, double bossBulletSpeed){
		int[] bulletAngle = new int[15];
		Arrays.fill(bulletAngle, 0);
		int value = 0;
		for(int i = 0; i < bulletAngle.length; i++){
			value += 20;
			if(value == 180){
				value = 20;  
			}
			bulletAngle[i] = value;
			bossBulletvX[i] = Math.cos(bulletAngle[i])*bossBulletSpeed;
			bossBulletvY[i] = Math.sin(bulletAngle[i])*bossBulletSpeed;  
			if(bossBulletvY[i] <0) {
				bossBulletvY[i] *= -1;
			}
		}}


	public static void dionysusDeath(double[] bossBulletvX, double[] bossBulletvY, double bossBulletSpeed){
		double[] bulletAngle = new double[22];
		Arrays.fill(bulletAngle, 0);
		double value = 0;
		for(int i = 0; i < bulletAngle.length; i++){
			value += 7.5;
			bulletAngle[i] = value;
			bossBulletvX[i] = Math.cos(bulletAngle[i])*bossBulletSpeed;
			bossBulletvY[i] = Math.sin(bulletAngle[i])*bossBulletSpeed;

		}}

}