package com.dpf.commandLineArgsParser.exception;

public class UnsupportedClassException extends RuntimeException{

	private static final long serialVersionUID = -1580533448516023554L;

	public UnsupportedClassException(Class<?> unsupportedClass) {
		super("Injection of class \"" + unsupportedClass.getSimpleName() +"\" is not supported");
	}
	
}
