package shoppingbag;
import java.io.Serializable;

import Exceptions.*;

public class ShoppingBag implements Serializable {
	
		private int numItems;		//the number of items the bag contains
		private double totalCost;	//the total cost of items in the shopping bag
		private Item[] bag;			//the shopping bag.
		private Item[] expandedBag; //a larger shopping bag if the original one is full.
		
	
	public ShoppingBag(int estimateItem){
		this.numItems=0;
		this.totalCost=0;
		//create an array that represents a cart that can hold a number of items according to the users estimate.
		bag=new Item[estimateItem];
	}                                                                                                                                                                       
	
	public int getNumItems(){
		return numItems;
	}
	
	public double getTotalCost(){
		return totalCost;
	}
	public void addItem(Item newItem){
		
		if(numItems<bag.length)
		{
		//Add an item to the next subscript in the array. 
		bag[numItems]=newItem;
				
		//add the items cost to the totalCost
		totalCost+=newItem.getPrice();
				
		//increment the number of items.
		numItems++;
				
			}
			
			//if the array is full, then copy the array into another with 5 more subscripts.
		   else 
				{
					Item[] expandedBag=new Item[bag.length+5];
					for(int i=0; i<bag.length; i++)
					{
						expandedBag[i]=bag[i];
					}
			    	//have the original cart reference the new expanded cart.
					bag=expandedBag;
				}
		
	
	}
		
	public Item removeItem(String description){
		
		Item item=null;
		
		for(int i=0;i<numItems;i++)
		{
			//if the description of the item matches the description of an item in the cart, remove the item.
			if(bag[i].getDescription().equalsIgnoreCase(description))
			{
				//shallow copy of item.
				item=bag[i];
				
				totalCost-=bag[i].getPrice();
				
				for(int j=i; j<numItems-1;j++)
				{
					bag[j]=bag[j+1];
					
				}
				
				bag[numItems-1]=null;
				numItems--;
				return item;
			}	
			
		}
		
			 throw new NotFoundException();
		
	}
		
	@Override
	public String toString(){
	  
		StringBuilder sb=new StringBuilder();
		
		if(numItems>0)
		{
		sb.append("Number of Items: "+numItems+"\n");
		sb.append("Item: \t\tPrice:\n------------------------\n");
			for(int i=0;i<numItems;i++)
			{
				sb.append(bag[i]+"\n");
				
			}
		sb.append(String.format("\n------------------------\nBalance : $%,.2f",totalCost));
		}
		else
			sb.append("There are no items in your shopping bag.");
		return sb.toString();
	}
	
}
