package FancyShoppingBag;
import java.io.Serializable;

import Exceptions.*;

public class Item implements Serializable, Comparable<Item>{

	private String description;
	private double price;
	
	public Item(String description, double price){
		if(price<=0)
			throw new InvalidDataException();
		if(description==null)
			throw new MissingDataException();
		
		this.description=description;
		this.price=price;
	}

	public String getDescription() {
		return description;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public int compareTo(Item item){
		return item.getDescription().compareTo(description);
	}
	
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		
		sb.append(String.format("%s\t\t$%,.2f",description,price));
		
		return sb.toString();
	}
}
