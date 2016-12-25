package shoppingbag;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import Exceptions.*;

public class MainShopping {

	public static void main(String[]args){
		
		Scanner input=new Scanner(System.in);
		int option;                  //users option from the menu.
		Item removedItem;			 //an item the user removed from the shopping bag.
		   
	    
		ShoppingBag shopbag = null;
		
		//read in file and throw exception if user doesn't click on restore bag first.
		do{
			
		//receive users option from the menu.
		option=menu(input);
		
		
			switch (option)
			{

			case 1:{
				//create a shopping bag if it was not instantiated yet.
				if(shopbag==null)
				{
					shopbag=createShoppingBag(input);
				}
			
				//enter an item into the shopping bag.
				customerAddItem(input, shopbag);	
				}
			  break;
			  
			case 2:{
				//remove an item from the shopping bag.
				removedItem=customerRemoveItem(input, shopbag);
				
				//display the item that was successfully removed.
				if(removedItem!=null)
				System.out.println("The item "+removedItem +" was removed from your shopping bag");
			}
			  break;
			
			
			case 3:{
				try{
				//display the shopping bag if it contains items, otherwise throw an exception.
				
					System.out.println(shopbag.toString());
				}
				catch(NullPointerException e){
					System.out.println("Your shopping bag is empty");
				}
				
			}
			break;
			
			
			case 4:{
				try{
					//read through a file to restore a shopping bag.
			     ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream("shoppingItems.ser"));
			     
			     shopbag=(ShoppingBag)fileIn.readObject();
			     
			     System.out.println("Your shopping bag was restored.");
			}
			catch(IOException io){
				System.out.println("Could not restore data");
				System.exit(1);
			}
			catch(ClassNotFoundException notfound){
				System.out.println("Shopping bag not found. Could not restore data.");
				System.exit(1);
			}
			}
			break;
			
			case 5:{
				  try{
					  //save shopping bag to file.
			             ObjectOutputStream fileOut = new ObjectOutputStream (new FileOutputStream("shoppingItems.ser") );
			 
			             fileOut.writeObject(shopbag);
			             System.out.println("Your shopping bag is saved in shoppingItems.ser");
			             System.exit(0);  
			    }
			    catch(IOException io){
			    	System.out.println("Your shopping bag could not be saved.");
			    }
			}
		}
			
		}
		while(option!=5);
	}
	
	/**
	 * Menu method that gives user options for the program.
	 * @param input A scanner object to read input.
	 * @return the users answer from the menu.
	 */
	public static int menu(Scanner input){
		int option=0;	
		
		System.out.println("\nEnter an option from the menu:"
			+"\n1.Add an item to your bag\n2.Remove an item from your bag\n3.View your bag\n"
			+ "4.Restore your bag\n5.Save your bag and exit");
		option=input.nextInt();
		
			while(option<1||option>5){
				System.out.println("Enter a valid option from the menu");
				option=input.nextInt();
			}
		
		
		
		return option;
			
	}
	
	/**
	 *A method that creates a shopping bag 
	 * @param input A scanner object to read input.
	 * @return A new shopping bag object.
	 */
	public static ShoppingBag createShoppingBag(Scanner input){
		
		int estimateItems;		//users estimate of items that will be added to shopping car.
		ShoppingBag shopbag;	//a shopping bag object.
		
		
		System.out.println("Enter an estimate number of items you will add to the shopping bag");
		estimateItems=input.nextInt();
		
			while(estimateItems<=0)
			{
				System.out.println("Enter a number greater than 0");
				estimateItems=input.nextInt();
			}
		
	    //create a new shopping bag the size of the users estimate.
		shopbag=new ShoppingBag(estimateItems);
		
		
		
		return shopbag;
	}
	
	/**
	 * Method to add an item to the shopping bag.
	 * @param input A scanner object to read input.
	 * @param shopbag A shopping bag.
	 */
	public static void customerAddItem(Scanner input,  ShoppingBag shopbag){
		String description;	//a description of an item.
		Item item;			//an item for the shopping bag.
		double price;		//the price of the item.
		
		
		System.out.println("Enter the price of the item: ");
		price=input.nextDouble();
		
		input.nextLine();
		
		System.out.println("Enter the description of the item:");
		description=input.nextLine();
		
		try{
		//create a new item with an item description and price.
		item=new Item(description,price);
		
		//add the item to the shopping bag.
		shopbag.addItem(item);
		}
		catch(InvalidDataException e){
			System.out.println("Invalid Price. Item not added.");
		}
		catch(MissingDataException e){
			System.out.println("Missing Item Description. Item not added.1");
		}
		
	}
	
	/**
	 * Method to remove an item from the shopping bag.
	 * @param input A scanner object to read input.
	 * @param shopbag A shopping bag object.
	 * @return The item that was removed if successful, or null.
	 */
	public static Item customerRemoveItem(Scanner input, ShoppingBag shopbag){
		
		String description;	//the description of the item being removed.
		Item item = null;
		
		//consume line.
		input.nextLine();
		
		System.out.println("Enter the description of the item you would like to remove:");
		description=input.nextLine();
		try{
		item=shopbag.removeItem(description);
		}
		catch(NotFoundException e){
			System.out.println("No match found. Item was not removed.");
		}
		catch(NullPointerException n){
			System.out.println("There are no items in your shopping bag.");
		}
		return item;
		
	}
	
}
