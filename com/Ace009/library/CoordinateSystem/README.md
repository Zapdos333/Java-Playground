# Package for Coordinate Systems
`package com.Ace009.library.CoordinateSystem`

1. [DataType `Coordinate`](https://github.com/Zapdos333/Java-Playground#datatype-coordinate)
2. [Creating `"Circles"`](https://github.com/Zapdos333/Java-Playground#circles)

## Datatype Coordinate

### Constructor

the `Coordinate` Constructor `Coordinate(double x, double y)` simply assigns the values to the `x` and `y` properties of `Coordinate`.


### Distance

the `public static double Distance(Coordinate pointA, Coordinate pointB)` takes the difference of the two points x and y values and returns the hypothenuse. Simply it gives you the shortest distance between the two points.


### Overrides

the `equals(Object o)` method has been overridden to make different Instances with the same x and y values equal.
(this also required overriding the `hashCode()` method)

the `toString()` method has been overridden to give a readable output,


## Circles

### Constructor

the `Circle` Constructor `Circle(Coordinate center, double radius)` simply stores these values in the `center` and `radius` properties.


### Circle Position

the method `public Coordinate positionDegree(double degrees)` returns the Coordinate of a Point on the Circle at `degrees` degrees from up clockwise.


### Circle/Polygon Construction

the method `public ArrayList<Coordinate> costruct(double interval)` returns an ArrayList of Coordinates evenly distributed over the circle with a distance of `interval` degrees.

the method `public ArrayList<Coordinate> costructPoly(int corners)` returns the result `construct(360/corners)`, thus constructing a polygon with `corners` corners. the returned `ArrayList.size()` is thus always equal to corners.
