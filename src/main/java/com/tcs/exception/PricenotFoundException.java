package com.tcs.exception;

public class PricenotFoundException  extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;

	public PricenotFoundException(String msg) {
		super(msg);
	}

}
