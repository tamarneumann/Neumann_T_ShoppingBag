package Exceptions;

public class NotFoundException extends RuntimeException {
	public NotFoundException(){
		super("Not found");
	}

}
