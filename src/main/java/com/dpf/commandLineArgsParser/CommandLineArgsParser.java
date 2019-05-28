package com.dpf.commandLineArgsParser;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.dpf.commandLineArgsParser.annotation.Arg;
import com.dpf.commandLineArgsParser.annotation.IgnoreArg;
import com.dpf.commandLineArgsParser.exception.ModelInstantiationException;
import com.dpf.commandLineArgsParser.exception.UnsupportedClassException;

public class CommandLineArgsParser {

	public final static String DEFAULT_VALUE = "&&%%$$>><<!!==ººªª\\||##//";

	private final static String ARG_PREFIX_TOKEN = "-";
	private final static String ARG_ASSIGN_TOKEN = "=";

	public static <T> T inject(Class<T> model, String[] args) {
		T instance = instantiate(model);

		Map<String, String> arguments = generateArgsMap(args);

		injectAttributes(instance, arguments);

		return instance;
	}

	private static <T> T instantiate(Class<T> model) {
		T argsClassInstance = null;
		try {
			argsClassInstance = model.newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new ModelInstantiationException(model);
		}
		return argsClassInstance;
	}

	private static <T> void injectAttributes(T instance, Map<String, String> args) {
		for (Field field : Arrays.stream(instance.getClass().getDeclaredFields())
				.filter(field -> !field.isAnnotationPresent(IgnoreArg.class))
				.filter(field -> args.containsKey(getArgName(field))).collect(Collectors.toList())) {
			boolean isPrivate = !field.isAccessible();
			try {
				if (isPrivate) {
					field.setAccessible(true);
				}
				field.set(instance, parse(field.getType(), args.get(getArgName(field))));
			} catch (IllegalAccessException ex) {
				throw new RuntimeException(ex);
			} finally {
				if (isPrivate) {
					field.setAccessible(false);
				}
			}
		}
	}

	private static String getArgName(Field field) {
		return (field.isAnnotationPresent(Arg.class) && !field.getAnnotation(Arg.class).value().equals(DEFAULT_VALUE))
				? field.getAnnotation(Arg.class).value()
				: field.getName();
	}

	private static Map<String, String> generateArgsMap(String[] args) {
		return Arrays.stream(args).peek(CommandLineArgsParser::validateArg)
				.collect(Collectors.toMap(CommandLineArgsParser::generateKey, CommandLineArgsParser::generateValue));
	}

	private static String generateKey(String arg) {
		return arg.contains(ARG_ASSIGN_TOKEN) ? arg.substring(1, arg.indexOf(ARG_ASSIGN_TOKEN)) : arg.substring(1);
	}

	private static String generateValue(String arg) {
		return (arg.contains(ARG_ASSIGN_TOKEN) ? arg.substring(arg.indexOf(ARG_ASSIGN_TOKEN) + 1)
				: Boolean.TRUE.toString());
	}

	private static void validateArg(String arg) {
		if (!arg.startsWith(ARG_PREFIX_TOKEN)) {
			throw new IllegalArgumentException(
					"Argument \"" + arg + "\" does not start with \"" + ARG_PREFIX_TOKEN + "\"");
		}
	}

	private static Object parse(Class<?> parseToClass, String value) {
		if (parseToClass.equals(int.class) || parseToClass.equals(Integer.class)) {
			return Integer.valueOf(value);
		} else if (parseToClass.equals(float.class) || parseToClass.equals(Float.class)) {
			return Float.valueOf(value);
		} else if (parseToClass.equals(double.class) || parseToClass.equals(Double.class)) {
			return Double.valueOf(value);
		} else if (parseToClass.equals(boolean.class) || parseToClass.equals(Boolean.class)) {
			return Boolean.valueOf(value);
		} else if (parseToClass.equals(String.class)) {
			return value;
		} else {
			throw new UnsupportedClassException(parseToClass);
		}
	}

}
