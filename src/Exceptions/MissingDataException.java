package Exceptions;

public class MissingDataException extends RuntimeException{
	public MissingDataException (){
		super("missing data");
	}

}
