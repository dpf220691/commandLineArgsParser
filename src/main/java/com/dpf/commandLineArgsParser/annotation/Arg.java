package com.dpf.commandLineArgsParser.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dpf.commandLineArgsParser.CommandLineArgsParser;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Arg {
	
	String value() default CommandLineArgsParser.DEFAULT_VALUE;
	
}
