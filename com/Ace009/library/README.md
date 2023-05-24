# personal `library` package
`package com.Ace009.library`

packages (redirecting to their page):

1. [CoordinateSystem](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library/CoordinateSystem)
2. [CClass](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library/CClass)

content:

1. [RNG](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library#rng)
2. [Range](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library#range)
3. [Arguments](https://github.com/Zapdos333/Java-Playground/tree/main/com/Ace009/library#arguments)

## RNG

extends `java.util.Random`

### limited random methods

`public T limited<T>Random(T min, T max)` returns a number of Type T that is between `min` and `max` (inclusive for `int` and `long`, exclusive for `float` and `double`)

### rerollSeed

`public long rerollRandom(int length)` uses the current `RNG` to generate a `String` of up to 18 random digits, which is the parsed into a `long` and set as new `this.setSeed` and then returned.

## Range

extends `java.util.ArrayList<Integer>`

made to imitate pythons `for i in range` as `for (int i : new Range)` or `for (int i : Range.arrayRange)`

### Range Constructor
`public Range(int start=0, int stop, int steps=1)`

creates an `ArrayList<Integer>` containing `(stop-start)/steps` numbers starting from `start`
incrementing by `steps` and ending before `stop`.

### arrayRange
`public static int[] arrayRange(int start=0, int stop, int steps=1)`

creates an `int[]` with the same content as the `constructor`

## Arguments

### Args properties
|property|type|description|
|----------|----------|----------|
|createdType|String|type given in constructor|
|Sargs|String[]|args given in constructor|
|output|String[]|output given by System.in|
|outputInt|int[]|output parsed as int|
|outputLong|long[]|output parsed as long|
|outputFloat|float[]|output parsed as float|
|outputDouble|double[]|output parsed as double|

### Args constructor
`public Args(String type, String...args)`

For every `String` in `args`, it asks for an `System.in` line.
That `input` is saved in `output[i]` and if `type` is an number,
`input` is parsed as that `Number` and added to the corresponding `number[]`

### Args.parseAsType
`public void parseAsType(String type)`

parses `Args.output` as `Number` given as `type`

identical to the constructors parsing but allows to fill more than one `outputNumber[]`
