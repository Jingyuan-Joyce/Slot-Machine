import java.util.Scanner;
import java.io.*;
import java.util.Random;
public class SlotMachine {

	public static void main(String[] args) throws IOException{
		Scanner keyboard = new Scanner(System.in);
		File myfile=new File("SlotMachineMoney.txt");
		Scanner inputFile = new Scanner(myfile);
		double amount=0.0;
		while(inputFile.hasNext()){		
	         amount = inputFile.nextDouble();
		}
		inputFile.close();
		String again = "yes";
		double totalbets=0.0,totalmoneywin=0.0;
		while (again.charAt(0) =='y' && amount>=1000){
			System.out.println("Enter the amount of money to insert:");
			double bets = keyboard.nextInt();
			totalbets+=bets;
			amount+=bets;
			if(!(checkbetsamount(bets))){
				System.out.println("Not allowed!!");
				break;	
			}
			else
				System.out.println("The amount is allowed!");
			
		    String f1,f2,f3,f4;
		    f1 = ranFruit();
		    f2 = ranFruit();
		    f3 = ranFruit();
		    f4 = ranFruit();
		    System.out.println(f1+" "+f2+" "+f3+" "+f4);
		    
		   
		    double moneywin=onerunmoneywin(f1,f2,f3,f4,bets,amount);
		    System.out.println("The money you win is:"+moneywin);
	    	totalmoneywin+=moneywin;
		    amount=amount-moneywin;
		    System.out.println("The amount available now is:"+amount);
		    System.out.println("Do you want to play again?(y/n)");
		    again = keyboard.next();
		}
		System.out.println("The total bet money is:"+totalbets);
		System.out.println("The total amount win is:"+totalmoneywin);
		FileWriter fwriter=new FileWriter("SlotMachineMoney.txt",true);
		PrintWriter outputFile = new PrintWriter(fwriter);
		outputFile.println("+"+totalbets);
		outputFile.println("-"+totalmoneywin);
		outputFile.println(amount);
		outputFile.close();
		
	}
	/**
	 * the checkbetsamount method returns if the bet amount is valid	    			       	
	 * @param the bet amount the player enter
	 * @return boolean, identify if the bet value is true
	 */
    public static boolean checkbetsamount(double bet){
    	boolean value;
    	if (bet>=0.0 && bet<=100.0){
    		value=true;
            return value;
    	}
    	else{
    		value=false;
    		return value;
    	}
    }
    /**
     * ranFruit method returns the fruit string by the random number
     * @return String fruit
     */
    public static String ranFruit(){
	    Random randomNumber = new Random();
	    int nb = randomNumber.nextInt(6);	 
    	String fruit="null";   	
    	switch(nb)
    	{
    	   case 0:
    		   fruit="Cherry";
    		   break;
    	   case 1:
    		   fruit="Lemon";
    		   break;
    	   case 2:
    		   fruit="Orange";
    		   break;
    	   case 3:
    		   fruit="Apple";
    		   break;
    	   case 4:
    		   fruit="Grape";
    		   break;
    	   case 5:
    		   fruit="Banana";
    		   break;
    	}
    	return fruit;    		       	
    }
    
    /**
     * onerunmoneywin returns the amount of money win in one-run of game
     * @param f1 the first fruit string
     * @param f2 the second fruit string
     * @param f3 the third fruit string
     * @param f4 the fourth fruit string
     * @param bbets the bets value
     * @param aamount the total amount left in the machine
     * @return the money wins in one-run game
     */
    public static double onerunmoneywin(String f1,String f2, String f3, String f4,double bbets,double aamount){
    	double moneywin=0.0;
	    if(f1.equals(f2)&&f1.equals(f3)&&f1.equals(f4)){
	    	System.out.println("Jackpot!");
	    	moneywin=aamount;
	    	return moneywin;
	
	    }
	    else if((f2.equals(f3)&&f2.equals(f4))||(f1.equals(f3)&&f1.equals(f4))||(f1.equals(f2)&&f1.equals(f4))||(f1.equals(f2)&&f1.equals(f3))){
	    	System.out.println("3 times your bet!");
	    	moneywin=bbets*3;
	    	return moneywin;
	
	    }
	    else if(f1.equals(f2)==false&&f1.equals(f3)==false&&f1.equals(f4)==false&&f2.equals(f3)==false&&f2.equals(f4)==false&&f3.equals(f4)==false){
	    	System.out.println("you win 0");
	    	moneywin=0;
	    	return moneywin;
	    }
	    else{
	    	System.out.println("2 times your bet!");
	    	moneywin=bbets*2;
	    	return moneywin;
	  
	    }
	    
    }

}
