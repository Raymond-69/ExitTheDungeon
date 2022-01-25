package etf;

import java.util.Arrays;

public class Boss{
	   //Fields
	   //Position
	   public static double bossX;
	   public static double bossY;
	   public int bossW;
	   public int bossH;
	   static int bossDifficulty = 1;
	   static public int bossHealth = 50* bossDifficulty;
	   static public int healthLeft = bossHealth;
	   public int bossFire;
	   public static int bossBulletW = 10;
	   public static int bossBulletH = 10;
	   public static int bossBulletCount = 22;
	   public static double bossBulletSpeed = 8;
	   public static boolean[] bossBulletDrawn = new boolean[bossBulletCount];
	   public static double[] bossBulletX = new double[bossBulletCount];
	   public static double[] bossBulletY = new double[bossBulletCount];
	   public static double[] bossBulletvX = new double[bossBulletCount];
	   public static double[] bossBulletvY = new double[bossBulletCount];
	   
	   public static void main(String[] args) {
			Arrays.fill(Boss.bossBulletX, borderSpecs.RIGHT/2 + dionysus.width/2);
			Arrays.fill(Boss.bossBulletY, borderSpecs.TOP + dionysus.height/2);
	   }
	   public Boss(){
	   
	   }
	  
	   

	}