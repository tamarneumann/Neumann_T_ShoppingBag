package shoppingbag;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import Exceptions.NotFoundException;

public class MainFancyShoppingBag {
public static void main(String[]args){
		
		Scanner input=new Scanner(System.in);
		int option;                  //users option from the menu.
		Item removedItem;			 //an item the user removed from the shopping bag.
		   
	    
		FancyShoppingBag fshopbag = null;
		
		//read in file and throw exception if user doesn't click on restore bag first.
		do{
			
		//receive users option from the menu.
		option=menu(input);
		
		
			switch (option)
			{

			case 1:{
				//create a shopping bag if it was not instantiated yet.
				if(fshopbag==null)
				{
					fshopbag=new FancyShoppingBag();
				}
			
				//enter an item into the shopping bag.
				customerAddItem(input, fshopbag);	
				}
			  break;
			  
			case 2:{
				//remove an item from the shopping bag.
				removedItem=customerRemoveItem(input, fshopbag);
				
				//display the item that was successfully removed.
				if(removedItem!=null)
				System.out.println("The item "+removedItem +" was removed from your shopping bag");
			}
			  break;
			
			
			case 3:{
				try{
				//display the shopping bag if it contains items, otherwise throw an exception.
				
					System.out.println(fshopbag.toString());
				}
				catch(NullPointerException e){
					System.out.println("Your shopping bag is empty");
				}
				
			}
			break;
			
			
			case 4:{
				try{
					//read through a file to restore a shopping bag.
			     ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream("fancyShoppingItems.ser"));
			     
			     fshopbag=(FancyShoppingBag)fileIn.readObject();
			     
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
			             ObjectOutputStream fileOut = new ObjectOutputStream (new FileOutputStream("fancyShoppingItems.ser") );
			 
			             fileOut.writeObject(fshopbag);
			             System.out.println("Your shopping bag is saved in fancyShoppingItems.ser");
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
	 * Method to add an item to the shopping bag.
	 * @param input A scanner object to read input.
	 * @param shopbag A shopping bag.
	 */
	public static void customerAddItem(Scanner input,  FancyShoppingBag fshopbag){
		String description;	//a description of an item.
		Item item;			//an item for the shopping bag.
		double price;		//the price of the item.
		
		
		System.out.println("Enter the price of the item: ");
		price=input.nextDouble();
		
		input.nextLine();
		
		System.out.println("Enter the description of the item:");
		description=input.nextLine();
		
		
		//create a new item with an item description and price.
		item=new Item(description,price);
		
		//add the item to the shopping bag.
		fshopbag.addItem(item);
		
		
	}
	
	/**
	 * Method to remove an item from the shopping bag.
	 * @param input A scanner object to read input.
	 * @param shopbag A shopping bag object.
	 * @return The item that was removed if successful, or null.
	 */
	public static Item customerRemoveItem(Scanner input, FancyShoppingBag fshopbag){
		
		String description;	//the description of the item being removed.
		Item item = null;
		
		//consume line.
		input.nextLine();
		
		System.out.println("Enter the description of the item you would like to remove:");
		description=input.nextLine();
		try{
		item=fshopbag.removeItem(description);
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
