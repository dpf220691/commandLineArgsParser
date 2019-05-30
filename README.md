# CommandLineArgsParser

A simple and lightweight way to inject your command line arguments to your attributes.

## How to use

#### 1. Add dependency to your pom.xml

You can use jitpack.io to add git repositories to your project.

```xml
        <dependency>
		<groupId>com.github.dpf220691</groupId>
		<artifactId>commandLineArgsParser</artifactId>
		<version>0.1</version>
	</dependency>
```

#### 2. Create a model class

```java
import com.dpf.commandLineArgsParser.annotation.Arg;
import com.dpf.commandLineArgsParser.annotation.IgnoreArg;

public class Model {
	
	@Arg("short.name")
	private int thisArgumentHasAVeryLongName;
	
	private Integer withoutAnnotationAlsoWorks;
	
	@IgnoreArg
	private float thisWillBeIgnored;
	
	@Arg("str")
	public String onlyAcceptsIntsFloatsDoublesAndStrings;

    	(...)

}
```

#### 3. Call CommandLineArgsParser whenever you need it
```java
import com.dpf.commandLineArgsParser.CommandLineArgsParser;

public class YourApp {

	public static void main(String[] args) {
		
		Model model = CommandLineArgsParser.inject(Model.class, args);
		
		System.out.println(model.getThisArgumentHasAVeryLongName());
		System.out.println(model.getWithoutAnnotationAlsoWorks());
		System.out.println(model.getThisWillBeIgnored());
		System.out.println(model.getOnlyAcceptsIntsFloatsDoublesAndStrings());

	}

}
```

#### 4. Run it
```sh
java -jar yourApp.jar -short.name=1 -withoutAnnotationAlsoWorks=3 -thisWillBeIgnored=4.5 -str="Hello world!"
```
And get this output:
```sh
1
3
0.0
Hello world!
```
# Enjoy!
