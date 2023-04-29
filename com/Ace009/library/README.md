# personal `library` package
`package com.Ace009.library`

0. [CoordinatSystem(see it's ReadMe)](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library/CoordinateSystem)
1. [RNG](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library#rng)
2. [JavaConsole](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library#javaconsole)
3. [CClass](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library/CClass)

## RNG

extends `java.util.Random`.

### limited random methods

`public T limited<T>Random(T min, T max)` returns a number of Type T that is between `min` and `max`


### rerollSeed

`public long rerollRandom(int length)` uses the current `RNG` to generate a `String` of up to 18 random digits, which is the parsed into a `long` and set as new `this.setSeed` and then returned.


## JavaConsole

**an _attempt_ at creating a console that exepts java method/commands via commandline. _unfinished_**

