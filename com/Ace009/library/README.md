# personal `library` package
`package com.Ace009.library`

packages (redirecting to their page):
1. [CoordinateSystem](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library/CoordinateSystem)
2. [CClass](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library/CClass)

content:
1. [RNG](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library#rng)
2. [JavaConsole](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library#javaconsole)

## RNG

extends `java.util.Random`

### limited random methods

`public T limited<T>Random(T min, T max)` returns a number of Type T that is between `min` and `max` (inclusive for `int` and `long`, exclusive for `float` and `double`)


### rerollSeed

`public long rerollRandom(int length)` uses the current `RNG` to generate a `String` of up to 18 random digits, which is the parsed into a `long` and set as new `this.setSeed` and then returned.


## JavaConsole

an *attempt* at creating a console that exepts java methods/commands via commandline. ***W.I.P.***
<br/>imports `RhinoScriptEngine`

