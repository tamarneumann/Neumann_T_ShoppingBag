package shoppingbag;
import java.io.Serializable;
import java.util.Iterator;

import Exceptions.NotFoundException;
import shoppingbag.LinkedList.LinkedListIterator;

public class FancyShoppingBag implements Serializable{

	private int numItems;		//the number of items the bag contains
	private double totalCost;	//the total cost of items in the shopping bag
	private LinkedList<Item>bag;
	
	public FancyShoppingBag(){
		numItems=0;
		totalCost=0;
		bag=new LinkedList<Item>();
	}
	public int getNumItems(){
		return numItems;
	}
	
	public double getTotalCost(){
		return totalCost;
	}
	

	public void addItem(Item newItem){
		bag.add(newItem);
		totalCost+=newItem.getPrice();
		numItems++;
	}
	
	public Item removeItem(String description){
		Iterator<Item> iter= bag.iterator();
		Item item;
		while(iter.hasNext())
		{
			item=(Item)iter.next();
			if(item.getDescription().equalsIgnoreCase(description))
			{
				bag.remove(item);
				numItems--;
				totalCost-=item.getPrice();
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
			for(Item item:bag)
			{
				sb.append(item+"\n");
				
			}
		sb.append(String.format("\n------------------------\nBalance : $%,.2f",totalCost));
		}
		else
			sb.append("There are no items in your shopping bag.");
		return sb.toString();
	}
	
}
