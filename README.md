# Welcome to my Personal `Java-Playground`

here I keep all personal code experimenting I do in my free time

documentation for the  `java source-code` is written in `javadocs`

all development happens in the [`dev-branch`](https://github.com/Zapdos333/Java-Playground/tree/dev)

releases are on [`main`](https://github.com/Zapdos333/Java-Playground/tree/main)

everything released is documented via javadocs or comments

## Gradle Configuration

gradle is configured with many modules:

|module name|package|internal dependencies|
|-----------|-------|------------|
|full build|`com.Ace009.**`|
|library|`com.Ace009.library.**`|
|library-general|`com.Ace009.library.*`|
|library-CClass|`com.Ace009.library.CClass.*`|library-general|
|library-CoordinateSystem|`com.Ace009.library.CoordinateSystem.*`|library-CClass|
|nonLibrary|`com.Ace009.nonLibrary.**`|
|nonLibrary-tests|`com.Ace009.nonLibrary.*`;`com.Ace009.nonLibrary.tests.*`|library-general;library-CClass;library-CoordinateSystem;nonLibrary-school|
|nonLibrary-HelloWorld|`com.Ace009.nonLibrary.HelloWorld.*`|
|nonLibrary-school|`com.Ace009.nonLibrary.school.**`|library-general;library-CClass|
