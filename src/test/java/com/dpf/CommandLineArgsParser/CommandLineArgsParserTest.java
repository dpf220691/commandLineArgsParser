package com.dpf.CommandLineArgsParser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dpf.CommandLineArgsParser.util.ModelDummy;
import com.dpf.commandLineArgsParser.CommandLineArgsParser;
import com.dpf.commandLineArgsParser.exception.ModelInstantiationException;
import com.dpf.commandLineArgsParser.exception.UnsupportedClassException;

public class CommandLineArgsParserTest {

	@Test
	public void injectAllTypes() {
		ModelDummy modelDummy = test("-intArg=1 -floatArg=1.2 -booleanArg -Str.Arg=asdf");
		assertTrue(modelDummy.getIntArg() == 1);
		assertTrue(modelDummy.getFloatArg() == 1.2f);
		assertTrue(modelDummy.isBooleanArg());
		assertTrue(modelDummy.getStringArg().equals("asdf"));
	}
	
	@Test(expected = UnsupportedClassException.class)
	public void injectUnsupportedType() {
		test("-dummy=asdf");
	}

	@Test
	public void injectToPrivateArg() {
		ModelDummy modelDummy = test("-intArg=2");
		assertTrue(modelDummy.getIntArg() == 2);
	}

	@Test
	public void injectToPublicArg() {
		ModelDummy modelDummy = test("-integerArg=2");
		assertTrue(modelDummy.getIntegerArg() == 2);
	}

	@Test
	public void injectToIgnoredArg() {
		ModelDummy modelDummy = test("-doubleArg=2");
		assertTrue(modelDummy.getDoubleArg() != 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badArgumentSyntax() {
		test("asdf");
	}

	private ModelDummy test(String args) {
		return CommandLineArgsParser.inject(ModelDummy.class, args.split(" "));
	}

}
