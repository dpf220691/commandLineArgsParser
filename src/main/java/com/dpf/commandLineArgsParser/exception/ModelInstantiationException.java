package com.dpf.commandLineArgsParser.exception;

public class ModelInstantiationException extends RuntimeException {

	private static final long serialVersionUID = 6490290564236689711L;

	public ModelInstantiationException(Class<?> intantiatingClass) {
		super("Error during instantiation of class \"" + intantiatingClass.getSimpleName() +"\"");
	}	
	
}
