package etf;
import javax.imageio.ImageIO;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
public class DungeonGame{ 
	// Game Window properties
	static JFrame gameWindow;
	static GraphicsPanel canvas;

	// key listener
	static MyKeyListener keyListener = new MyKeyListener();    
	// mouse listeners
	static MyMouseListener mouseListener = new MyMouseListener();
	// button listeners
	static JButton playButton;
	static JButton settingsButton;
	static JButton buyButton;
	static JButton backButton;
	static JButton exitButton;
	static JButton nextPageButton;
	static JButton difficultyButton;
	static JButton storeAmmo;
	static JButton storeArmor;
	static JButton storeSpeed;
	static JButton noobButton;
	static JButton mediumButton;
	static JButton hardishButton;
	static JButton hardButton;
	static JButton CHADButton;

	//--------------------------------------------------------------------------
	// declare the properties of all game objects here
	//--------------------------------------------------------------------------
	//Main Menu Options
	static int selection = 0;
	static int currentPage = 0;

	// Window properties
	public final static int WIDTH = 1170;
	public final static int HEIGHT = 1300;
	static BufferedImage settingsBackground;
	static BufferedImage background;
	// map properties
	static int mapX = 100;
	static int mapY = 50;
	static int mapW = 60;
	static int mapH = 30;

	//bullet properties
	static int numBullets = 3;
	static boolean [] showBullet = new boolean[numBullets];
	static bullet [] bullets = new bullet[numBullets];
	static int bulletVel = 6;
	//Diffuculty Settings 	
	static int difficultySettings = 1;
	static String difficultyChoice = "";
	//Ammo Drawing
	static BufferedImage ammo;	
	static int drawAmmo [] = new int[numBullets];
	//Shop item drawing
	static BufferedImage shield;
	static BufferedImage speed;
	//NPC shop
	static BufferedImage shopBuilding;
	static BufferedImage indicator;
	static Font menuFont = new Font("Arial", Font.PLAIN,12);
	static boolean drawIndicator = false;
	static int shopMenuX = borderSpecs.RIGHT/2;
	static int shopMenuY = borderSpecs.TOP;
	static int shopMenuW = 400;
	static int shopMenuH = 400;
	static int shopSpeedEffect = 0;
	static boolean drawShop = true;
	static boolean interact = false;
	static int buyItem = -1;
	static boolean touching = false;
	static boolean shopMenu = false;
	//Boss 
	static boolean bossRoom = true;
	static boolean bossAlive = true;
	static int currentBoss = 1;
	static BufferedImage trapdoor;
	//Coins
	static BufferedImage coin; // image
	static int coins = 0; // purse
	static int numCoins = 8; // number coins
	static int currentCoinGen = 0;//counter
	static boolean[] drawCoin = new boolean[numCoins];//draw 
	static Coin coinXY[] = new Coin[numCoins];//object

	// Flag for end of play
	static boolean inPlay = true;

	// Font for game over
	static Font largeFont = new Font("Serif", Font.BOLD, 24);   
	static Font settingFont = new Font("Serif", Font.PLAIN,20);
	static Font difficultyFont = new Font("Serif", Font.BOLD,60);
	static Font smallFont = new Font("Serif", Font.BOLD,40);
	static Font descriptionFont = new Font("Serif", Font.BOLD,20);

	static Color purple = new Color(179, 61, 252);
	static Color buttonColor = new Color(191, 191, 191);
	static Color gameBackground = new Color(109, 67, 56);
	// hitboxes
	static Rectangle powerupBox = new Rectangle(borderSpecs.RIGHT/2 + 20, borderSpecs.BOTTOM/2 + 20, 20, 20);
	static Rectangle shopBox = new Rectangle(borderSpecs.RIGHT - borderSpecs.LEFT, borderSpecs.BOTTOM - borderSpecs.TOP,80,80);
	static Rectangle interactShopBox = new Rectangle(borderSpecs.RIGHT-borderSpecs.LEFT -20, borderSpecs.BOTTOM - borderSpecs.TOP-20, 100,110);
	static Rectangle gameBox = new Rectangle(borderSpecs.LEFT,borderSpecs.TOP, borderSpecs.RIGHT,borderSpecs.BOTTOM);
	static long start = System.currentTimeMillis();
	static long elapsedTime = 0;

	//To do list:
	//Maybe add story
	//Settings menu graphics
	//Main Screen Graphics
	//------------------------------------------------------------------------------    
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, InterruptedException{
		gameWindow = new JFrame("Exit The Dungeon");
		gameWindow.setSize(WIDTH,HEIGHT);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new GraphicsPanel();
		playButton = new JButton("Play");
		playButton.setFont(new Font("Serif",Font.BOLD,30));
		settingsButton = new JButton("Settings");
		settingsButton.setFont(new Font("Serif",Font.BOLD,30));
		backButton = new JButton("Back");
		backButton.setFont(new Font("Serif",Font.BOLD,30));
		exitButton = new JButton("X");
		buyButton = new JButton("Buy");
		nextPageButton = new JButton("Next Page");
		nextPageButton.setFont(new Font("Serif",Font.BOLD,30));
		//Difficulty Buttons
		difficultyButton = new JButton("Difficulty");
		difficultyButton.setFont(new Font("Serif",Font.BOLD,30));
		noobButton = new JButton("Noob smh");
		noobButton.setBackground(buttonColor);
		noobButton.setFont(new Font("Serif",Font.BOLD,30));
		mediumButton = new JButton("Medium");
		mediumButton.setBackground(buttonColor);
		mediumButton.setFont(new Font("Serif",Font.BOLD,30));
		hardishButton = new JButton("Hardish");
		hardishButton.setBackground(buttonColor);
		hardishButton.setFont(new Font("Serif",Font.BOLD,30));
		hardButton = new JButton("Hard");
		hardButton.setBackground(buttonColor);
		hardButton.setFont(new Font("Serif",Font.BOLD,30));
		CHADButton = new JButton("CHAD");
		CHADButton.setBackground(buttonColor);
		CHADButton.setFont(new Font("Serif",Font.BOLD,30));
		//Icons
		canvas.setLayout(null);
		Icon shopShield = new ImageIcon("src/etf/shield.png");
		Icon shopSpeed = new ImageIcon("src/etf/speed.png");            
		Icon shopAmmo= new ImageIcon("src/etf/ammo.png");
		//Reading images
		try{
			settingsBackground = ImageIO.read(new File("src/etf/settingsBackground.png"));
			background = ImageIO.read(new File("src/etf/background.png"));
			ammo = ImageIO.read(new File("src/etf/ammo.png"));
			coin = ImageIO.read(new File("src/etf/coin.png"));
			shopBuilding = ImageIO.read(new File("src/etf/shop.png"));
			indicator = ImageIO.read(new File("src/etf/indicator.png"));
			shield = ImageIO.read(new File("src/etf/shield.png"));
			speed = ImageIO.read(new File("src/etf/speed.png"));
			trapdoor = ImageIO.read(new File("src/etf/trapdoor.png"));
		}catch(IOException ex){}

		//Store buttons
		storeAmmo = new JButton(shopAmmo);
		storeArmor = new JButton(shopShield);
		storeSpeed = new JButton(shopSpeed);
		//Button Properties
		//Main Menu
		playButton.setBounds(borderSpecs.RIGHT/2,borderSpecs.BOTTOM/2 +50, 200, 50);
		settingsButton.setBounds(borderSpecs.RIGHT/2, borderSpecs.BOTTOM/2 + 100, 200,50);
		difficultyButton.setBounds(borderSpecs.RIGHT/2, borderSpecs.BOTTOM/2 + 150,  200, 50);
		backButton.setBounds(borderSpecs.LEFT, borderSpecs.BOTTOM + 60, 200,40);


		exitButton.setBounds(shopMenuX-45, shopMenuY, 45, 45);
		buyButton.setBounds(shopMenuX + shopMenuW - 60, shopMenuY + shopMenuH - 45, 60,45);
		storeAmmo.setBounds(shopMenuX + 20,shopMenuY + 50, 60, 60);
		storeArmor.setBounds(shopMenuX + 80, shopMenuY + 50, 60,60);
		storeSpeed.setBounds(shopMenuX + 140, shopMenuY + 50,60,60);
		nextPageButton.setBounds(borderSpecs.RIGHT - 100, borderSpecs.BOTTOM + 60, 200, 40);
		noobButton.setBounds(borderSpecs.LEFT + 30, borderSpecs.BOTTOM/2 +10,200,40);
		mediumButton.setBounds(borderSpecs.LEFT + 30, borderSpecs.BOTTOM/2 + 50,200,40);
		hardishButton.setBounds(borderSpecs.LEFT + 30, borderSpecs.BOTTOM/2 + 90, 200, 40);
		hardButton.setBounds(borderSpecs.LEFT + 30, borderSpecs.BOTTOM/2 + 130, 200,40);
		CHADButton.setBounds(borderSpecs.LEFT + 30, borderSpecs.BOTTOM/2 + 170, 200,40);
		nextPageButton.setOpaque(true);
		backButton.setOpaque(true);
		nextPageButton.setForeground(Color.white);
		nextPageButton.setContentAreaFilled(false);
		backButton.setContentAreaFilled(false);
		backButton.setForeground(Color.white);
		noobButton.setForeground(Color.white);
		mediumButton.setForeground(Color.white);
		hardishButton.setForeground(Color.white);
		hardButton.setForeground(Color.white);
		CHADButton.setForeground(Color.white);
		playButton.setForeground(Color.white);
		settingsButton.setForeground(Color.white);
		difficultyButton.setForeground(Color.white);
		//Adding Buttons
		canvas.add(noobButton);
		canvas.add(mediumButton);
		canvas.add(hardishButton);
		canvas.add(hardButton);
		canvas.add(CHADButton);
		canvas.add(playButton);
		canvas.add(settingsButton);
		canvas.add(backButton);
		canvas.add(buyButton);
		canvas.add(exitButton);
		canvas.add(storeAmmo);
		canvas.add(storeArmor);
		canvas.add(storeSpeed);
		canvas.add(nextPageButton);
		canvas.add(difficultyButton);
		canvas.addMouseListener(mouseListener);
		canvas.addKeyListener(keyListener);
		gameWindow.add(canvas); 
		playButton.addActionListener(new playButtonListener());
		playButton.setFocusable(false);
		settingsButton.addActionListener(new settingsButtonListener());
		settingsButton.setFocusable(false);
		backButton.addActionListener(new backButtonListener());
		backButton.setFocusable(false);
		exitButton.addActionListener(new exitButtonListener());
		exitButton.setFocusable(false);
		buyButton.addActionListener(new buyButtonListener());
		buyButton.setFocusable(false);
		storeAmmo.addActionListener(new storeAmmoListener());
		storeAmmo.setFocusable(false);
		storeArmor.addActionListener(new storeArmorListener());
		storeAmmo.setFocusable(false);
		storeSpeed.addActionListener(new storeSpeedListener());
		storeSpeed.setFocusable(false);
		nextPageButton.addActionListener(new nextPageListener());
		nextPageButton.setFocusable(false);
		difficultyButton.addActionListener(new difficultyListener());
		difficultyButton.setFocusable(false);
		noobButton.addActionListener(new noobListener());
		noobButton.setFocusable(false);
		mediumButton.addActionListener(new mediumListener());
		mediumButton.setFocusable(false);
		hardishButton.addActionListener(new hardishListener());
		hardishButton.setFocusable(false);
		hardButton.addActionListener(new hardListener());
		hardButton.setFocusable(false);
		CHADButton.addActionListener(new CHADListener());
		CHADButton.setFocusable(false);

		for(int r = 0; r < rooms.floors; r++) {
			Arrays.fill(rooms.finishedRoom[r], false);
		}
		rooms.finishedRoom[0][0] = true;
		Arrays.fill(Enemy.enemyDrawn, false);
		Arrays.fill(showBullet, false);
		Arrays.fill(player.playerShield, false);
		Arrays.fill(bullets,new bullet(0,0,0,0));
		backButton.setVisible(false);
		exitButton.setVisible(false);
		buyButton.setVisible(false);
		storeAmmo.setVisible(false);
		storeArmor.setVisible(false);
		storeSpeed.setVisible(false);
		noobButton.setVisible(false);
		mediumButton.setVisible(false);
		hardishButton.setVisible(false);
		hardButton.setVisible(false);
		CHADButton.setVisible(false);
		nextPageButton.setVisible(false);
		storeAmmo.setOpaque(false);
		storeArmor.setOpaque(false);
		storeSpeed.setOpaque(false);
		storeAmmo.setContentAreaFilled(false);
		storeArmor.setContentAreaFilled(false);
		storeSpeed.setContentAreaFilled(false);
		playButton.setBackground(buttonColor);
		settingsButton.setBackground(buttonColor);
		difficultyButton.setBackground(buttonColor);
		gameWindow.setVisible(true);

		runGameLoop();

	} // main method end

	//------------------------------------------------------------------------------   
	public static void runGameLoop() throws InterruptedException{
		while (true) {
			gameWindow.repaint();
			try  {Thread.sleep(10);} catch(Exception e){}
			//------------------------------------------------------------------
			// implement the game functionality here
			//------------------------------------------------------------------
			//Player collision with shop
			Rectangle playerBox = new Rectangle(player.playerX, player.playerY, player.playerW, player.playerH);
			if(drawShop) {
				if(playerBox.intersects(shopBox) && player.playerStepX >0){
					player.playerStepX = 0;
					touching = true;
				}
				if(playerBox.intersects(shopBox) && player.playerStepY > 0){
					player.playerStepY = 0;
					touching =true;
				}
			}
			//Shop interact box
			if(playerBox.intersects(interactShopBox)) {
				shopMenu = true;
				drawIndicator = true;
			}
			else {drawIndicator = false;}

			//Player movement and collision with border
			if(player.playerX > borderSpecs.LEFT && player.playerX + player.playerW < borderSpecs.RIGHT || player.playerX <= borderSpecs.LEFT && player.playerStepX > 0 || player.playerX + player.playerW >= borderSpecs.RIGHT && player.playerStepX < 0) {
				player.playerX += player.playerStepX;
			}
			if(player.playerY + player.playerH > borderSpecs.BOTTOM && player.playerY < borderSpecs.TOP|| player.playerY + player.playerH <= borderSpecs.BOTTOM && player.playerStepY > 0 || player.playerY >= borderSpecs.TOP && player.playerStepY < 0) {
				player.playerY += player.playerStepY;
			}

			//Enemy movement + collision with bullet
			for(int counter = 0; counter < Enemy.enemyCount; counter++ ){
				Enemy.enemyWalkedDistance[counter] += Math.sqrt(Math.pow(Enemy.enemyVelY[counter],2)+ Math.pow(Enemy.enemyVelX[counter],2));
				for(int anotherCounter = 0; anotherCounter < bullets.length; anotherCounter++){
					double distance = Math.sqrt(Math.pow(Enemy.enemyX[counter] + Enemy.enemyW/2 - bullets[anotherCounter].bulletX + bullet.bulletW/2,2) + Math.pow(Enemy.enemyY[counter] + Enemy.enemyH/2 - bullets[anotherCounter].bulletY + bullet.bulletH/2,2));   
					if(distance< Enemy.enemyW/2 + bullet.bulletW && Enemy.enemyDrawn[counter]){
						coinXY[counter] = new Coin(Enemy.enemyX[counter], Enemy.enemyY[counter]);
						drawCoin[counter] = true;
						Enemy.enemyDrawn[counter] = false;
						showBullet[anotherCounter] = false;
						Enemy.enemyVelX[counter] = 0;
						Enemy.enemyVelY[counter] = 0;
						Enemy.enemyWalkedDistance[counter] = 0;
						Enemy.enemyWalkingDistance[counter] = 0;
						Enemy.enemyAlive -= 1;
					}				    
				}
				if(Enemy.enemyWalkedDistance[counter] < Enemy.enemyWalkingDistance[counter]){
					if(Enemy.enemyX[counter] <= borderSpecs.LEFT + 10){
						Enemy.enemyVelX[counter] = -Enemy.enemyVelX[counter];         
					}else if(Enemy.enemyX[counter] >= borderSpecs.RIGHT - Enemy.enemyW - 10){
						Enemy.enemyVelX[counter] = -Enemy.enemyVelX[counter];  
					}
					if(Enemy.enemyY[counter] <= borderSpecs.TOP + 10){
						Enemy.enemyVelY[counter] = -Enemy.enemyVelY[counter];           
					}else if(Enemy.enemyY[counter] >= borderSpecs.BOTTOM - Enemy.enemyH - 10){
						Enemy.enemyVelY[counter] = -Enemy.enemyVelY[counter];           
					}
					Enemy.enemyX[counter] +=  Enemy.enemyVelX[counter];
					Enemy.enemyY[counter] +=  Enemy.enemyVelY[counter];
				}else{
					Arrays.fill(Enemy.enemyVelX, 0);
					Arrays.fill(Enemy.enemyVelY, 0);
					Arrays.fill(Enemy.enemyWalkedDistance, 0);
					Arrays.fill(Enemy.enemyWalkingDistance, 0);
					if(Enemy.enemyDrawn[counter] == true){
						Enemy.moveEnemy(Enemy.enemyVelX, Enemy.enemyVelY, Enemy.enemyAngle, Enemy.enemyWalkingDistance, Enemy.angleUpperbound, Enemy.distanceUpperbound, Enemy.enemySpeed);
					}
				}
			}
			//Enemy pew pew
			for (int i=0; i<Enemy.enemyCount; i++){

				if(rooms.currentRoom !=4 && bullet.enemyBulletY[i] <= borderSpecs.TOP || bullet.enemyBulletY[i]>= borderSpecs.BOTTOM|| bullet.enemyBulletX[i] < borderSpecs.LEFT ||bullet.enemyBulletX[i] > borderSpecs.RIGHT && Enemy.enemyDrawn[i]) {
					bullet.enemyBulletDrawn[i] = false;
					bullet.enemyBulletX[i] = Enemy.enemyX[i] + Enemy.enemyW/2;
					bullet.enemyBulletY[i] = Enemy.enemyY[i] + Enemy.enemyH/2;
					bullet.enemyBulletvY[i] = bullet.enemyBulletSpeedY(Enemy.enemyX[i], Enemy.enemyY[i], player.playerX, player.playerY, bullet.enemyBulletSpeed);
					bullet.enemyBulletvX[i] = bullet.enemyBulletSpeedX(Enemy.enemyX[i], Enemy.enemyY[i], player.playerX, player.playerY, bullet.enemyBulletSpeed);

				}else if(Enemy.enemyDrawn[i] && bullet.enemyBulletDrawn[i] == false){
					bullet.enemyBulletDrawn[i] = true;
				}
				else if(bullet.enemyBulletDrawn[i]) {
					bullet.enemyBulletY[i] += bullet.enemyBulletvY[i];
					bullet.enemyBulletX[i] += bullet.enemyBulletvX[i];
				}
			}

			//Bullet Movement and drawing condition
			for(int i =0 ; i< bullets.length; i++) {
				bullets[i].updateBullet();
				if(bullets[i].bulletY <= borderSpecs.TOP || bullets[i].bulletY>= borderSpecs.BOTTOM|| bullets[i].bulletX < borderSpecs.LEFT || bullets[i].bulletX > borderSpecs.RIGHT) {
					showBullet[i] = false;
				}
			}			
			//Bullet Shot
			for(int i = 0; i < numBullets; i++) {
				if(showBullet[i]) {
					drawAmmo[i] = 1;
				}
				else {
					drawAmmo[i] = 2;
				}
			}

			//Powerup collision with player
			if(rooms.finishedRoom[rooms.currentFloor][rooms.currentRoom] && powerup.drawPowerup) {
				if(playerBox.intersects(powerupBox)) {
					powerup.generatePowerup();
					powerup.drawPowerup = false;
				}			
			}
			//Enemy bullets and player collision
			for(int j = 0; j < player.playerShield.length; j++) {
				for(int i = 0; i < Enemy.enemyCount; i++) {
					Rectangle bulletBox = new Rectangle((int)bullet.enemyBulletX[i], (int)bullet.enemyBulletY[i], bullet.enemyBulletW,bullet.enemyBulletH);
					if(bullet.enemyBulletDrawn[i] && bulletBox.intersects(playerBox) && player.shieldCount ==0) {
						inPlay = false;
					}
					if(bullet.enemyBulletDrawn[i] && playerBox.intersects(bulletBox)) {
						bullet.enemyBulletDrawn[i] = false;
						player.playerShield[j] = false;
						player.shieldCount -=1;
						bullet.enemyBulletX[i] = Enemy.enemyX[i];
						bullet.enemyBulletY[i] = Enemy.enemyY[i];
					}
					if(player.shieldCount <0)
						player.shieldCount =0;
				}
			}
			//Coin collision with player

			for(int i = 0; i < Enemy.enemyCount; i++) {
				if(drawCoin[i]) {
					Rectangle coinBox = new Rectangle(coinXY[i].getX(), coinXY[i].getY(),20, 30);
					if(playerBox.intersects(coinBox) || playerBox.intersects(shopBox)) {
						Random random = new Random();
						coins += random.nextInt(4) + 1;
						drawCoin[i] = false;
					}
				}
			}
			//Room finished
			if(Enemy.enemyAlive ==0) {
				rooms.finishedRoom[rooms.currentFloor][rooms.currentRoom] = true;
				powerup.drawPowerup = true;
				if(shop.generateShop() ==2 && difficultyChoice != "CHAD") {
					drawShop = true;
				}
				Enemy.enemyAlive = -1;
			}
			if(!rooms.finishedRoom[rooms.currentFloor][rooms.currentRoom]) {
				powerup.drawPowerup = false;
				drawShop = false;
			}
			//Boss Rooms

			if(bossRoom && currentBoss ==1 && bossAlive) {
				Boss.bossX =borderSpecs.RIGHT/2 + 20;
				Boss.bossY = borderSpecs.TOP - dionysus.width;
				
				for(int i = 0; i < numBullets; i++) {
					Rectangle bulletBox = new Rectangle(bullets[i].getX(), bullets[i].getY(),bullet.bulletW,bullet.bulletW);
					Rectangle dionysusBox= new Rectangle(borderSpecs.RIGHT/2 +20,borderSpecs.TOP,dionysus.width,dionysus.height);
					if(bulletBox.intersects(dionysusBox) && showBullet[i] && Boss.healthLeft >0) {
						Boss.healthLeft --;
						showBullet[i] = false;
					}
					if(playerBox.intersects(dionysusBox)) {
						for(int j = 0; j < player.playerShield.length; j++) {
							if(player.shieldCount ==0) {
								inPlay = false;
							}
							else{
								player.playerShield[j] = false;
								player.shieldCount -=1;
							}
						}
					}
				}
				if(Boss.healthLeft ==0) {
					bossAlive = false;
					dionysus.dionysusDeath(Boss.bossBulletvX, Boss.bossBulletvY, Boss.bossBulletCount);
				}
				//Boss pew pew 0,1,2
				//Attack will reset the velocity of the bullets
				long currentTime = System.currentTimeMillis();
				elapsedTime = (currentTime - start)/1000;
				Random random = new Random();
				if(elapsedTime >=1) {
					start = currentTime;
					int attack = random.nextInt(3)+1;
					Arrays.fill(Boss.bossBulletDrawn, true);
					if(attack ==1) {
						dionysus.dionysusBulletWave(Boss.bossBulletvX, Boss.bossBulletvY, Boss.bossBulletSpeed);
					}
					else if(attack ==2) {
						dionysus.dionysusTargetedBullet(player.playerX, player.playerY,Boss.bossX,Boss.bossY,Boss.bossBulletvX,Boss.bossBulletvY);
					}
					else if(attack ==3) {
						dionysus.dionysusBulletAnotherVer(Boss.bossBulletvX, Boss.bossBulletvY, Boss.bossBulletSpeed);
					}
				}
				for(int i = 0; i < 22; i++) {
					Boss.bossBulletX[i] += Boss.bossBulletvX[i];
					Boss.bossBulletY[i] += Boss.bossBulletvY[i];
				}
				for(int i = 0; i < 22; i++) {
					if(Boss.bossBulletDrawn[i]) {						
						Rectangle bossBulletBox = new Rectangle((int)Boss.bossBulletX[i], (int)Boss.bossBulletY[i],Boss.bossBulletW, Boss.bossBulletH);					
						if(playerBox.intersects(bossBulletBox)) {
							for(int j = 0; j< player.playerShield.length; j++) {
								if(player.playerShield[j]) {
									player.playerShield[j] = false;
									player.shieldCount--;
									Boss.bossBulletDrawn[i] = false;
								}
								if(player.shieldCount ==0) {
									inPlay = false;
								}
								if(player.shieldCount <0) {
									player.shieldCount =0;
								}
							}
						}
						
							if(Boss.bossBulletX[i] < borderSpecs.LEFT || Boss.bossBulletX[i] > borderSpecs.RIGHT && Boss.bossBulletY[i] <borderSpecs.TOP || Boss.bossBulletY[i] > borderSpecs.BOTTOM) {
							Boss.bossBulletX[i] = borderSpecs.RIGHT/2 + dionysus.width/2;
							Boss.bossBulletY[i] = borderSpecs.TOP + dionysus.height/2;
							Boss.bossBulletvX[i] = 0;
							Boss.bossBulletvY[i] = 0;
							
						}
						if(Boss.bossBulletY[i] < borderSpecs.TOP || Boss.bossBulletX[i] >borderSpecs.RIGHT) {
							Boss.bossBulletX[i] = borderSpecs.RIGHT/2 + dionysus.width/2;
							Boss.bossBulletY[i] = borderSpecs.TOP + dionysus.height/2;
							Boss.bossBulletvX[i] = 0;
							Boss.bossBulletvY[i] = 0;
						}
					}
				}
			}


			//Door Collision
			if (rooms.finishedRoom[rooms.currentFloor][rooms.currentRoom]){
				if(player.playerX + player.playerW >= borderSpecs.RIGHT && player.playerY > borderSpecs.BOTTOM/2 && player.playerY < borderSpecs.BOTTOM/2 + 80) {
					if(rooms.currentRoom < rooms.rooms){
						rooms.currentRoom +=1;
						Enemy.enemyAlive = Enemy.enemyCount;
						player.playerX = borderSpecs.LEFT+20;
						Enemy.generateEnemy();
						Arrays.fill(drawCoin, false);
						Arrays.fill(showBullet, false);
						Arrays.fill(Enemy.enemyDrawn, true);
					}
					if(rooms.currentRoom ==4){
						player.playerX = borderSpecs.RIGHT/2;
						player.playerY = borderSpecs.BOTTOM - player.playerH;
						drawShop = false;
						Enemy.enemyAlive = -1;
						Arrays.fill(Enemy.enemyDrawn,true);
						Arrays.fill(bullet.enemyBulletDrawn,false);
						Arrays.fill(Enemy.enemyY, 0);
						Arrays.fill(Enemy.enemyX,0);
						Arrays.fill(Enemy.enemyVelX, 0);
						Arrays.fill(Enemy.enemyVelY, 0);
						Arrays.fill(Enemy.enemyWalkedDistance, 0);
						Arrays.fill(Enemy.enemyWalkingDistance, 0);
						bossRoom = true;
						Boss.bossDifficulty +=1;
						if(rooms.currentFloor ==0) {
							currentBoss =1;
						}
					}
				}
			}
		}
	} // runGameLoop method end

	//------------------------------------------------------------------------------  
	static class GraphicsPanel extends JPanel{
		public GraphicsPanel(){
			setFocusable(true);
			requestFocusInWindow();
		}			
		public void paintComponent(Graphics g){ 
			super.paintComponent(g); //required
			//------------------------------------------------------------------
			// do all your drawings here 
			//------------------------------------------------------------------
			if (inPlay){

				if(selection == 0) {
					Image bg = background.getScaledInstance(borderSpecs.RIGHT,borderSpecs.BOTTOM, Image.SCALE_SMOOTH);
					g.drawImage(bg, borderSpecs.TOP, borderSpecs.LEFT,this);
					g.setColor(Color.black);
					g.setFont(new Font("Serif",Font.PLAIN, 70));
					for(int i = 0; i < 65; ++i) {
						g.drawString("Exit The Dungeon", borderSpecs.RIGHT/2 - 170 +(int)(i*0.1), borderSpecs.TOP + borderSpecs.TOP + (int)(i*0.1));
					}

				}	

				if(selection == 1) {
					//Fill background
					g.setColor(Color.black);
					g.fillRect(1,1,1169,1299);

					//Draw Floor and Room
					g.setColor(Color.white);
					g.setFont(new Font("SansSerif",Font.PLAIN,25));
					g.drawString(rooms.currentFloor +" : " + rooms.currentRoom, mapX, mapY -5);

					//Draw Minimap Frame
					g.setColor(Color.white);
					g.drawRect(mapX, mapY, rooms.rooms*mapW, mapH);
					//Draw Minimap Rooms
					g.drawRect(mapX, mapY, mapW, mapH);
					g.drawRect(mapX + mapW, mapY, mapW, mapH);
					g.drawRect(mapX + mapW*2, mapY, mapW, mapH);
					g.drawRect(mapX + mapW*3, mapY, mapW, mapH);
					g.drawRect(mapX + mapW*4, mapY, mapW, mapH);
					g.setColor(Color.GREEN);
					if(rooms.currentRoom == 0) {
						g.fillOval(mapX + mapW/2 - 5, mapY + mapH/2 - 5,10, 10);
					}
					else if(rooms.currentRoom == 1) {
						g.fillOval(mapX + mapW +25, mapY + mapH/2 - 5, 10, 10);
					}
					else if(rooms.currentRoom == 2) {
						g.fillOval(mapX + mapW*2 +25, mapY + mapH/2 - 5, 10, 10);
					}
					else if(rooms.currentRoom == 3) {
						g.fillOval(mapX + mapW*3 + 25, mapY + mapH/2 - 5, 10, 10);
					}else if(rooms.currentRoom == 4) {
						g.fillOval(mapX + mapW*4 +25, mapY + mapH/2 - 5, 10, 10);
					}else if(rooms.currentRoom == 5) {
						g.fillOval(mapX + mapW*5 +25, mapY + mapH/2 - 5, 10, 10);
					}


					//Draw border
					g.setColor(gameBackground);
					g.fillRect(borderSpecs.TOP,borderSpecs.LEFT, borderSpecs.RIGHT - borderSpecs.LEFT, borderSpecs.BOTTOM - borderSpecs.TOP);
					g.fillRect(borderSpecs.TOP-1, borderSpecs.LEFT-1, borderSpecs.RIGHT- borderSpecs.LEFT+1,borderSpecs.BOTTOM- borderSpecs.TOP-1);
					//Draw player
					g.setColor(Color.black);
					g.fillOval(player.playerX, player.playerY, player.playerW, player.playerH);

					//Doors
					g.setColor(Color.blue);
					if(rooms.finishedRoom[rooms.currentFloor][rooms.currentRoom] && rooms.currentRoom !=4) {
						//Draw right door
						g.fillRect(borderSpecs.RIGHT -20, borderSpecs.BOTTOM/2, 20, 80);
					}

					//Draw Enemy Bullet
					g.setColor(Color.BLUE);
					for(int i = 0; i < Enemy.enemyCount; i ++) {
						if(bullet.enemyBulletDrawn[i] && Enemy.enemyDrawn[i] && rooms.currentRoom <4) {
							g.fillOval((int)bullet.enemyBulletX[i],(int)bullet.enemyBulletY[i], bullet.enemyBulletW, bullet.enemyBulletH);
						}
					}

					g.setColor(Color.cyan);
					//Drawing Enemies
					for(int i =0; i< Enemy.enemyCount; i++) {
						if(rooms.currentRoom != 4)
							if(Enemy.enemyDrawn[i]) {
								g.fillOval((int)Enemy.enemyX[i], (int)Enemy.enemyY[i], Enemy.enemyW, Enemy.enemyH);
							}
					}
					//Draw bullet
					g.setColor(Color.red);
					for (int i=0; i<showBullet.length; i++){
						if (showBullet[i]) {
							g.fillOval(bullets[i].getX(),bullets[i].getY(),bullet.bulletW,bullet.bulletH);
						}
					}
					//Draw Boss Bullet
					g.setColor(Color.BLUE);
					for(int i = 0; i < Boss.bossBulletCount; i ++) {
						if(Boss.bossBulletDrawn[i]) {
							g.fillOval((int)Boss.bossBulletX[i],(int)Boss.bossBulletY[i], Boss.bossBulletW, Boss.bossBulletH);
						}
					}
					//Draw powerup
					if(powerup.drawPowerup) {
						g.setColor(purple);
						g.fillRect(borderSpecs.RIGHT/2 + 20, borderSpecs.BOTTOM/2 + 20, 20, 20);
					}
					//Draw Ammo
					Image rAmmo = ammo.getScaledInstance(20, 30, Image.SCALE_SMOOTH);
					Arrays.sort(drawAmmo);
					for(int i =0; i < numBullets; i++) {
						if(drawAmmo[i] == 2) {
							g.drawImage(rAmmo, borderSpecs.RIGHT-rAmmo.getWidth(this) - i*10, borderSpecs.TOP - rAmmo.getHeight(this), this);
						}
					}
					//Draw shop
					if(drawShop) {
						Image NPCshop= shopBuilding.getScaledInstance(80, 90, Image.SCALE_SMOOTH);
						g.drawImage(NPCshop,borderSpecs.RIGHT - borderSpecs.LEFT, borderSpecs.BOTTOM - borderSpecs.TOP, this );
						//Draw shop indicator
						Image shopIndicator = indicator.getScaledInstance(30, 10, Image.SCALE_SMOOTH);
						if(drawIndicator) {
							g.drawImage(shopIndicator,borderSpecs.RIGHT - borderSpecs.LEFT + 25, borderSpecs.BOTTOM-borderSpecs.TOP- 30,this );
						}
					}

					//Draw shop menu
					Image imageShield = shield.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
					Image imageSpeed = speed.getScaledInstance(40,40, Image.SCALE_SMOOTH);
					Image imageAmmo = ammo.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

					if(shopMenu && interact) {
						g.setColor(Color.BLACK);
						g.fillRect(shopMenuX, shopMenuY, shopMenuW, shopMenuH);
						g.setColor(Color.white);
						g.setFont(menuFont);
						g.drawString("Welcome Traveller, what would you like to purchase?", shopMenuX + 10, shopMenuY + 25);
						storeAmmo.setVisible(true);
						storeArmor.setVisible(true);
						storeSpeed.setVisible(true);
						if(buyItem == 1) {
							g.setColor(Color.white);
							g.drawRect(shopMenuX, shopMenuY +shopMenuH-60, shopMenuW-4, 58);
							g.drawImage(imageAmmo, shopMenuX,shopMenuY + shopMenuH - 50,this);
							g.drawString("Cost: 20 coins",shopMenuX + 50, shopMenuY + shopMenuH - 40);
							g.drawString("Gain an extra bullet!",shopMenuX + 50, shopMenuY + shopMenuH - 20);

						}
						if(buyItem == 2) {
							g.setColor(Color.white);
							g.drawRect(shopMenuX, shopMenuY +shopMenuH-60, shopMenuW-4, 58);
							g.drawImage(imageShield,shopMenuX,shopMenuY +shopMenuH - 50,this);
							g.drawString("Cost: 15 coins",shopMenuX + 50, shopMenuY + shopMenuH - 40);
							g.drawString("Refill 1 armor!",shopMenuX + 50, shopMenuY + shopMenuH - 20);
						}
						if(buyItem ==3) {
							g.setColor(Color.white);
							g.drawRect(shopMenuX, shopMenuY +shopMenuH-60, shopMenuW-4, 58);
							g.drawImage(imageSpeed,shopMenuX,shopMenuY +shopMenuH - 50,this);
							g.drawString("Cost: 30 coins",shopMenuX + 50, shopMenuY + shopMenuH - 40);
							g.drawString("Move slightly faster!",shopMenuX + 50, shopMenuY + shopMenuH - 20);
						}



					}//End of shop menu

					//Draw player armor
					for(int i =0; i < player.shieldCount; i++) {
						g.drawImage(imageShield,borderSpecs.RIGHT-35 + i*20 - i* imageShield.getWidth(this),borderSpecs.BOTTOM,this);
					}
					Image coinV = coin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
					//Draw Coin upon killing enemy
					for(int i = 0; i < Enemy.enemyCount; i++) {
						if(drawCoin[i]) {
							g.drawImage(coinV, coinXY[i].getX(), coinXY[i].getY(),this);
						}
					}
					//Draw Coin Count
					g.setColor(Color.WHITE);
					g.setFont(new Font("SansSerif",Font.PLAIN,25));
					g.drawImage(coinV, borderSpecs.LEFT, borderSpecs.BOTTOM, this);
					g.drawString(""+coins, borderSpecs.LEFT + coinV.getWidth(this), borderSpecs.BOTTOM + coinV.getHeight(this)/2 + 8);


					//Boss drawing
					if(bossRoom && rooms.currentFloor==0) {
						g.setColor(Color.GREEN);
						g.fillOval(borderSpecs.RIGHT/2 + 20, borderSpecs.TOP, dionysus.width,dionysus.height);
						g.drawString("" + Boss.healthLeft + " / " + Boss.bossHealth, borderSpecs.RIGHT/2 + 10, borderSpecs.TOP - 50);
					}




				}//End of selection 1

				//Settings Page
				else if (selection == 2){
					if(currentPage == 0) {
						canvas.add(backButton);
						Image settingBackground = settingsBackground.getScaledInstance(borderSpecs.RIGHT, borderSpecs.BOTTOM, Image.SCALE_REPLICATE);
						g.drawImage(settingBackground,borderSpecs.TOP,borderSpecs.LEFT,this);
						//Draw control boxes
						g.setColor(Color.white);
						g.setFont(largeFont);
						//Player
						g.setColor(Color.black);
						g.fillOval(borderSpecs.LEFT + 30, borderSpecs.TOP + 70, player.playerW, player.playerH);
						g.setColor(Color.white);
						g.drawString("Player: Controlled by you", borderSpecs.LEFT +player.playerW + 40 , borderSpecs.TOP + 95);
						//Player Bullet
						g.setColor(Color.red);
						g.fillOval(borderSpecs.LEFT + 35, borderSpecs.TOP + 123, bullet.bulletH, bullet.bulletW);
						g.setColor(Color.white);
						g.drawString("Player Bullet: Bullet player shoots", borderSpecs.LEFT + 70, borderSpecs.TOP + 140);

						//Enemy 
						g.setColor(Color.cyan);
						g.fillOval(borderSpecs.LEFT + 30, borderSpecs.TOP+ 160, Enemy.enemyW, Enemy.enemyH);
						g.setColor(Color.white);
						g.drawString("Enemy: Will shoot bullets toward player", borderSpecs.LEFT + 70, borderSpecs.TOP +185);

						//Enemy Bullet
						g.setColor(Color.blue);
						g.fillOval(borderSpecs.LEFT + 40 , borderSpecs.TOP + 218, 10, 10);
						g.setColor(Color.white);
						g.drawString("Enemy Bullet: Bullet that will kill you", borderSpecs.LEFT + 70, borderSpecs.TOP + 230);

						//Powerup intro
						g.setColor(purple);
						g.fillRect(borderSpecs.LEFT + 35, borderSpecs.TOP + 250, 20, 20);
						g.setColor(Color.white);
						g.drawString("Powerup: Gives you a random buff or enemy a debuff", borderSpecs.LEFT + 70, borderSpecs.TOP+ 268);

						//Shop
						Image NPCshop= shopBuilding.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
						g.drawImage(NPCshop, borderSpecs.LEFT + 30, borderSpecs.TOP + 280, this);
						g.drawString("Shop: Buy more ammo, armor, and speeeeeeeeeeed", borderSpecs.LEFT + 70, borderSpecs.TOP + 305);
						//Ammo
						Image imageAmmo = ammo.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
						g.drawImage(imageAmmo,borderSpecs.LEFT + 30, borderSpecs.TOP + 328,this);
						g.drawString("Ammo: Visual cue of the amount of ammo", borderSpecs.LEFT + 70, borderSpecs.TOP + 350);
						//Shield
						Image imageShield = shield.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
						g.drawImage(imageShield,borderSpecs.LEFT + 30, borderSpecs.TOP + 370, this);
						g.drawString("Armor: Blocks one bullet for you", borderSpecs.LEFT + 70, borderSpecs.TOP + 395);
						//Speed
						Image imageSpeed = speed.getScaledInstance(30,30, Image.SCALE_SMOOTH);
						g.drawImage(imageSpeed,borderSpecs.LEFT + 30, borderSpecs.TOP + 410, this);
						g.drawString("Speed: Only seen in shop, it affects your speed", borderSpecs.LEFT + 70, borderSpecs.TOP + 433);


					}
					if(currentPage == 1) {
						Image settingBackground = settingsBackground.getScaledInstance(borderSpecs.RIGHT, borderSpecs.BOTTOM, Image.SCALE_REPLICATE);
						g.drawImage(settingBackground,borderSpecs.TOP,borderSpecs.LEFT,this);
						//Controls
						g.setColor(Color.white);
						g.setFont(largeFont);
						g.drawString("Game Controls:", borderSpecs.LEFT + 30, borderSpecs.TOP + 45);
						g.setFont(settingFont);
						//Left Boxes
						int corner = 10;
						g.drawRoundRect(borderSpecs.LEFT + 30, borderSpecs.TOP + 70,  105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 30, borderSpecs.TOP + 100, 105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 30, borderSpecs.TOP + 130, 105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 30, borderSpecs.TOP + 160, 105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 30, borderSpecs.TOP + 190, 105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 30, borderSpecs.TOP + 220, 105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 30, borderSpecs.TOP + 250, 105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 30, borderSpecs.TOP + 280, 105, 30,corner,corner);

						//Right Boxes
						g.drawRoundRect(borderSpecs.LEFT + 205, borderSpecs.TOP + 70,  105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 205, borderSpecs.TOP + 100, 105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 205, borderSpecs.TOP + 130, 105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 205, borderSpecs.TOP + 160, 105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 205, borderSpecs.TOP + 190, 105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 205, borderSpecs.TOP + 220, 105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 205, borderSpecs.TOP + 250, 105, 30,corner,corner);
						g.drawRoundRect(borderSpecs.LEFT + 205, borderSpecs.TOP + 280, 105, 30,corner,corner);


						//Left Control texts
						g.drawString("Up", borderSpecs.LEFT + 32, borderSpecs.TOP + 95 );
						g.drawString("Left", borderSpecs.LEFT + 32, borderSpecs.TOP +125);
						g.drawString("Down", borderSpecs.LEFT + 32, borderSpecs.TOP +155);
						g.drawString("Right", borderSpecs.LEFT + 32, borderSpecs.TOP +185);
						g.drawString("Respawn", borderSpecs.LEFT + 32, borderSpecs.TOP +215);
						g.drawString("Interact", borderSpecs.LEFT + 32, borderSpecs.TOP + 245);
						g.drawString("Fire", borderSpecs.LEFT + 32, borderSpecs.TOP + 275);
						g.drawString("Aim", borderSpecs.LEFT + 32, borderSpecs.TOP + 305);

						//Right Control texts
						g.drawString("W", borderSpecs.LEFT + 210, borderSpecs.TOP + 95);
						g.drawString("A", borderSpecs.LEFT + 210, borderSpecs.TOP +125);
						g.drawString("S", borderSpecs.LEFT + 210, borderSpecs.TOP +155);
						g.drawString("D", borderSpecs.LEFT + 210, borderSpecs.TOP +185);
						g.drawString("R", borderSpecs.LEFT + 210, borderSpecs.TOP +215);
						g.drawString("E", borderSpecs.LEFT + 210, borderSpecs.TOP + 245);
						g.drawString("Click", borderSpecs.LEFT + 210, borderSpecs.TOP + 275);
						g.drawString("Cursor", borderSpecs.LEFT + 210, borderSpecs.TOP + 305);

						//Graphics Improvement

						//Game Goal
						g.setColor(Color.white);
						g.setFont(largeFont);
						g.drawString("Goal:", borderSpecs.LEFT + 30, borderSpecs.TOP + borderSpecs.BOTTOM/2 + 40);
						g.setFont(settingFont);
						g.drawString("Escape the dungeon!", borderSpecs.LEFT + 30, borderSpecs.BOTTOM/2 + borderSpecs.TOP + 70);
						g.drawString("Clear all 3 floors and defeat 3 bosses.", borderSpecs.LEFT + 30, borderSpecs.BOTTOM/2 + borderSpecs.TOP + 90);
						g.drawString("During your journey, there are travelling merchants that can help you.", borderSpecs.LEFT + 30, borderSpecs.BOTTOM/2 + borderSpecs.TOP + 110);
						g.drawString("There will be powerups along the way that can give debuffs/buffs to you or enemies.", borderSpecs.LEFT + 30, borderSpecs.BOTTOM/2 + borderSpecs.TOP + 130);

					}
				}
				else if(selection == 3) {
					Image bg = background.getScaledInstance(borderSpecs.RIGHT,borderSpecs.BOTTOM, Image.SCALE_SMOOTH);
					g.drawImage(bg, borderSpecs.TOP, borderSpecs.LEFT,this);
					canvas.add(backButton);
					g.setColor(Color.white);
					g.setFont(difficultyFont);
					g.drawString("Select your difficulty:", borderSpecs.LEFT + 30, borderSpecs.TOP +60);
					g.setFont(settingFont);
					//Noob
					if(difficultyChoice.equals("noob")) {
						g.drawString("NOOBB, can't believe your that bad", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2-20);
						g.setFont(smallFont);
						g.drawString("Difficulty Changes:", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 + 40);
						g.setFont(descriptionFont);
						g.drawString("Speed of enemy is slower", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 +60);
						g.drawString("Speed of enemy bullet is slower", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 + 75);


					}
					//Medium
					else if(difficultyChoice.equals("medium")){
						g.setFont(smallFont);
						g.drawString("Difficulty Changes:", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 + 40);
						g.setFont(descriptionFont);
						g.drawString("Speed of enemy is increased a little", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 +60);
						g.drawString("Speed of enemy bullet is increased a little", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 + 75);
					}
					//Hardish
					else if(difficultyChoice.equals("hardish")){
						g.setFont(smallFont);
						g.drawString("Difficulty Changes:", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 + 40);
						g.setFont(descriptionFont);
						g.drawString("Speed of enemy is increased a bit more", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 +60);
						g.drawString("Speed of enemy bullet is increased a bit more", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 + 75);
					}
					//Hard
					else if(difficultyChoice.equals("hard")){
						g.setFont(smallFont);
						g.drawString("Difficulty Changes:", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 + 40);
						g.setFont(descriptionFont);
						g.drawString("Speed of enemy is increased a lot", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 +60);
						g.drawString("Speed of enemy bullet is increased a lot", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 + 75);
					}
					//CHAD
					else if(difficultyChoice.equals("CHAD")){
						g.setFont(smallFont);
						g.drawString("Difficulty Changes:", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 + 40);
						g.setFont(descriptionFont);
						g.drawString("Speed of enemy is normal", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 +60);
						g.drawString("Speed of enemy bullet is INSANE", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 + 75);
						g.drawString("No more shopping for you", borderSpecs.RIGHT/2 , borderSpecs.BOTTOM/2 + 90);

					}

				}

			}

			else {
				g.setColor(Color.black);
				g.fillRect(borderSpecs.TOP, borderSpecs.LEFT, borderSpecs.RIGHT,borderSpecs.BOTTOM);
				g.setColor(Color.white);
				g.setFont(new Font("Arial",Font.PLAIN, 20));
				g.drawString("YOU DIED, GET GOOD", borderSpecs.RIGHT/2, borderSpecs.BOTTOM/2);
				g.setFont(new Font("Arial",Font.PLAIN, 10));
				g.drawString("Press R to restart", borderSpecs.RIGHT/2, borderSpecs.BOTTOM/2 +20);
			}

		} // paintComponent method end
	} // GraphicsPanel class end   

	//------------------------------------------------------------------------------     
	static class MyKeyListener implements KeyListener{   
		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_W ){
				player.playerStepY = -(4 + powerup.playerSpeedEffect + shopSpeedEffect);
			} else if (key == KeyEvent.VK_A ){
				player.playerStepX = -(4 + powerup.playerSpeedEffect + shopSpeedEffect);
			} else if (key == KeyEvent.VK_S ){
				player.playerStepY = 4 + powerup.playerSpeedEffect + shopSpeedEffect;
			} else if(key == KeyEvent.VK_D ) {
				player.playerStepX = 4 + powerup.playerSpeedEffect + shopSpeedEffect;
			} else if(key == KeyEvent.VK_E && touching) {
				interact = true;
				exitButton.setVisible(true);
			}else if (key == KeyEvent.VK_R) {
				inPlay = true;	
				selection = 0;
				coins =0;
				numBullets = 3;
				player.shieldCount = 0;
				rooms.currentRoom = 0;
				rooms.currentFloor = 0;
				Boss.healthLeft = Boss.bossHealth;
				for(int i = 0; i < 3;i++) {
					Arrays.fill(rooms.finishedRoom[i],false);
				}
				for(int i = 0 ;i < Enemy.enemyCount; i++) {

					Arrays.fill(drawCoin, false);
					bullet.enemyBulletX[i] = Enemy.enemyX[i];
					bullet.enemyBulletY[i] = Enemy.enemyY[i];

				}
				Arrays.fill(Enemy.enemyDrawn, false);	
				Arrays.fill(bullet.enemyBulletDrawn, false);
				Arrays.fill(showBullet,false);
				Enemy.enemyAlive = 0;
				rooms.finishedRoom[0][0] = true;
				player.playerX = borderSpecs.LEFT + 30;
				player.playerY = borderSpecs.BOTTOM/2;
				canvas.add(playButton);
				canvas.add(settingsButton);
				canvas.add(difficultyButton);
			}

		}
		public void keyReleased(KeyEvent e){ 
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ESCAPE){
				gameWindow.dispose();
			}
			else if (key == KeyEvent.VK_W){
				player.playerStepY = 0;
			}
			else if (key == KeyEvent.VK_A){
				player.playerStepX = 0;
			}
			else if (key == KeyEvent.VK_S){
				player.playerStepY = 0;
			}
			else if (key == KeyEvent.VK_D){
				player.playerStepX = 0;
			}

		}   
		public void keyTyped(KeyEvent e){
		}           
	} // MyKeyListener class end

	//------------------------------------------------------------------------------ 
	static class MyMouseListener implements MouseListener{
		public void mouseClicked(MouseEvent e){

		}
		public void mousePressed(MouseEvent e){
			for(int currentBullet = 0; currentBullet< showBullet.length; currentBullet++) {
				if(!showBullet[currentBullet]) {
					//Get mouse position
					double mouseX = e.getX();
					double mouseY = -1*e.getY();
					double slope = (mouseY + player.playerY + player.playerH/2)/(mouseX - player.playerX - player.playerW/2);
					double theta = Math.abs(Math.atan(slope));
					double vX = Math.abs(Math.cos(theta)*bulletVel);
					double vY = Math.abs(Math.sin(theta)*bulletVel);
					if(mouseX <= player.playerX + player.playerW/2 ){
						vX*=-1;
					}
					if(mouseY*-1 <= player.playerY + player.playerH/2){
						vY*=-1;
					}
					bullets[currentBullet] = new bullet(player.playerX, player.playerY,vX,vY);
					showBullet[currentBullet] = true;
					break;
				}
			}


		}
		public void mouseReleased(MouseEvent e){
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e){

		}
	} // MyMouseListener class end
	//------------------------------------------------------------------------------    
	/* clickButtonListener class
	 * Desc: An internal class to respond to the button events. Each listener requires it.
	 *       The name of this class must match the listener that was added to the JComponent.
	 */
	static class playButtonListener implements ActionListener{ 
		public void actionPerformed(ActionEvent event){     
			selection = 1;
			canvas.remove(playButton);
			canvas.remove(settingsButton);
			canvas.remove(difficultyButton);
		}
	} // playButtonListener class end
	static class settingsButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){         
			// The code below will run automatically when the button is activated
			selection = 2;
			canvas.remove(playButton);
			canvas.remove(settingsButton);
			backButton.setVisible(true);
			nextPageButton.setVisible(true);
			difficultyButton.setVisible(false);

		}
	} // settingsButtonListener class end
	static class backButtonListener implements ActionListener{  
		public void actionPerformed(ActionEvent event){
			if(currentPage ==0 && selection ==2) {
				selection = 0;
				canvas.add(playButton);
				canvas.add(settingsButton);
				difficultyButton.setVisible(true);
				canvas.remove(backButton);
				nextPageButton.setVisible(false);
			}
			if(currentPage == 1 && selection ==2) {
				currentPage--;
				nextPageButton.setVisible(true);
			}
			if(selection == 3) {
				noobButton.setVisible(false);
				mediumButton.setVisible(false);
				hardishButton.setVisible(false);
				hardButton.setVisible(false);
				CHADButton.setVisible(false);
				playButton.setVisible(true);
				settingsButton.setVisible(true);
				difficultyButton.setVisible(true);
				backButton.setVisible(false);
				selection = 0;

			}
		}
	} // clickButtonListener class end
	static class exitButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			shopMenu = false;
			interact = false;
			buyButton.setVisible(false);
			exitButton.setVisible(false);
			storeAmmo.setVisible(false);
			storeArmor.setVisible(false);
			storeSpeed.setVisible(false);
		}
	}
	static class difficultyListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			selection = 3;
			backButton.setVisible(true);
			difficultyButton.setVisible(false);
			playButton.setVisible(false);
			settingsButton.setVisible(false);
			noobButton.setVisible(true);
			mediumButton.setVisible(true);
			hardishButton.setVisible(true);
			hardButton.setVisible(true);
			CHADButton.setVisible(true);

		}
	}
	static class buyButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(buyItem == 1) {
				if(coins >=20) {
					numBullets +=1;
					showBullet = new boolean[numBullets];
					drawAmmo = new int[numBullets];
					bullets = new bullet[numBullets];
					Arrays.fill(bullets, new bullet(0,0,0,0));
					coins -=20;
					buyButton.setVisible(false);

				}

			}else if(buyItem == 2) {
				if(coins >=15) {
					for(int i = 0; i < player.playerShield.length; i++) {
						if(!player.playerShield[i] && player.shieldCount <3) {
							coins -=15;
							player.shieldCount +=1;
							player.playerShield[i] = true;
							break;
						}
					}
					buyButton.setVisible(false);

				}
			}else if(buyItem == 3) {
				if(coins >= 30) {
					coins -= 30;
					shopSpeedEffect +=1;
					buyButton.setVisible(false);
				}
			}
		}
	}
	static class storeAmmoListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			buyItem = 1;
			buyButton.setVisible(true);
		}
	}
	static class storeArmorListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			buyItem = 2;
			buyButton.setVisible(true);

		}
	}
	static class storeSpeedListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			buyItem = 3;
			buyButton.setVisible(true);

		}
	}
	static class nextPageListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(currentPage <1) {
				currentPage++;
				nextPageButton.setVisible(false);
			}
		}
	}
	static class noobListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			difficultyChoice = "noob";
			difficultySettings = 0;
		}
	}
	static class mediumListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			difficultyChoice = "medium";
			difficultySettings = 1;
		}
	}
	static class hardishListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			difficultyChoice = "hardish";
			difficultySettings = 2;
		}
	}
	static class hardListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			difficultyChoice = "hard";
			difficultySettings = 3;
		}
	}
	static class CHADListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			difficultyChoice = "CHAD";
			difficultySettings = 5;
		}
	}


	//-------------------------------------------------------------------------------
	//------------------------------------------------------------------------------         
} // DungeonGame class end