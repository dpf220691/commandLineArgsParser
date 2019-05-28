package com.dpf.CommandLineArgsParser.util;

import com.dpf.commandLineArgsParser.annotation.Arg;
import com.dpf.commandLineArgsParser.annotation.IgnoreArg;

public class ModelDummy {
	
	private int intArg;
	
	public Integer integerArg;
	
	@IgnoreArg
	private double doubleArg;
	
	private Float floatArg;
	
	private boolean booleanArg;
	
	@Arg("Str.Arg")
	private String stringArg;

	@Arg("dummy")
	private ClassDummy classDummyArg;

	public ModelDummy() {
		super();
	}

	public int getIntArg() {
		return intArg;
	}

	public void setIntArg(int intArg) {
		this.intArg = intArg;
	}

	public Integer getIntegerArg() {
		return integerArg;
	}

	public void setIntegerArg(Integer integerArg) {
		this.integerArg = integerArg;
	}

	public double getDoubleArg() {
		return doubleArg;
	}

	public void setDoubleArg(double doubleArg) {
		this.doubleArg = doubleArg;
	}

	public Float getFloatArg() {
		return floatArg;
	}

	public void setFloatArg(Float floatArg) {
		this.floatArg = floatArg;
	}

	public boolean isBooleanArg() {
		return booleanArg;
	}

	public void setBooleanArg(boolean booleanArg) {
		this.booleanArg = booleanArg;
	}

	public String getStringArg() {
		return stringArg;
	}

	public void setStringArg(String stringArg) {
		this.stringArg = stringArg;
	}

	public ClassDummy getClassDummyArg() {
		return classDummyArg;
	}

	public void setClassDummyArg(ClassDummy classDummyArg) {
		this.classDummyArg = classDummyArg;
	}
	
}
