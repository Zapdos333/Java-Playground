# Welcome to my Personal `Java-Playground`

here I keep all personal code experimenting I do in my free time

documentation for the  `java source-code` is written in `javadocs`

all development happens in the [`dev-branch`](https://github.com/Zapdos333/Java-Playground/tree/dev)<br>
releases are on [`main`](https://github.com/Zapdos333/Java-Playground/tree/main)

everything released is documented via javadocs or comments

## Gradle Configuration

gradle is configured with many modules:

|module name|classes/package|internal dependencies|
|-----------|-------|------------|
|full build|`com.Ace009.**`|all|
|library|`com.Ace009.library.**`|all lower|
|library-general|`com.Ace009.library.*`|**none**|
|library-CClass|`com.Ace009.library.CClass.*`|**none**|
|library-Math|`com.Ace009.library.Math.*`|library-CClass|
|library-CoordinateSystem|`com.Ace009.library.CoordinateSystem.*`|library-CClass,library-Math|
|nonLibrary|`com.Ace009.nonLibrary.**`|all lower|
|nonLibrary-tests|`com.Ace009.nonLibrary.debug.*`|library;nonLibrary-school|
|nonLibrary-HelloWorld|`com.Ace009.nonLibrary.HelloWorld.*`|none|
|nonLibrary-school|`com.Ace009.nonLibrary.school.*`|library-general;library-CClass,library-Math|

also the jar-file for the nonLibrary module has a manifest file which sets the `Main-Class` to `CommandLineWrapper`
