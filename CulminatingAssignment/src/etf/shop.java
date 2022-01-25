package etf;
import java.util.Random;
public class shop {
		public shop() {
			
		}
		public static int generateShop() {
			Random random = new Random();
			int generate;
			generate = random.nextInt(3);
		    if(generate ==0) {
		    	generate = 1;
		    }
			return generate;
		}	
		
}
