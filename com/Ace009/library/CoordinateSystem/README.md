# Package for Coordinate Systems
`package com.Ace009.library.CoordinateSystem`

1. [DataType `DoubleCoordinate`](#datatype-doublecoordinate)
2. [Creating `"Circles"`](#circles)
3. [Creating `Coordinate"Map"`](#coordinatemap)

## Datatype DoubleCoordinate

is a final class, supposed to be used as a property to easily store x and y coordinates.

### Constructor

the `DoubleCoordinate` Constructor `DoubleCoordinate(double x, double y)` simply assigns the values to the `x` and `y` properties of `DoubleCoordinate`.

### Distance

the `public static double distance(DoubleCoordinate pointA, DoubleCoordinate pointB)` takes the difference of the two points x and y values and returns the hypotenuse. Simply it gives you the shortest distance between the two points.

the `public double distanceTo(DoubleCoordinate target)` returns the result of the static `distance` with `this` and `target` as `PointA` and `PointB`.

### static CoordinateList Operations

the `public static ArrayList<DoubleCoordinate> roundCoordList(ArrayList<DoubleCoordinate> list, float interval)` returns the as `list` input `ArrayList<DoubleCoordinate>` with the Coordinates x and y values rounded to `interval`.

the `public static double totalDistance(ArrayList<DoubleCoordinate> list,boolean polygon)` returns the total Distance covered by going from one `DoubleCoordinate` in `list` to the next. if `polygon` is true it also adds the distance between the first and last `DoubleCoordinate`, closing the polygon.
**pay attention to the order of the Entries in the `ArrayList<CDoubleoordinate>`**

### Overrides

the `equals(Object o)` method has been overridden to make different Instances with the same x and y values equal.
(this also required overriding the `hashCode()` method).

the `toString()` method has been overridden to give a readable output.

## Circles

### Circle Constructor

the `Circle` Constructor `Circle(DoubleCoordinate center, double radius)` simply stores these values in the `center` and `radius` properties.

### Circle Position

the method `public DoubleCoordinate positionDegree(double degrees)` returns the `DoubleCoordinate` of a Point on the Circle at `degrees` degrees from up clockwise.

### Circumference
the method `public double circumference()` return the circumference of the `Circle` instances radius.

```Java
return Math.PI*radius*2;
```

### Circle/Polygon Construction

the method `public ArrayList<DoubleCoordinate> construct(double interval)` returns an ArrayList of Coordinates evenly distributed over the circle with a distance of `interval` degrees.

the method `public ArrayList<DoubleCoordinate> costructPoly(int corners)` returns the result `construct(360/corners)`, thus constructing a polygon with `corners` corners. the returned `ArrayList.size()` is thus always equal to corners.

### static methods for constructed Polygons

the method `public static String getCircularity(ArrayList<DoubleCoordinate> polygon, double radius)` returns a the ratio of the `polygon`s `totalDistance` over the `circumference` of a `Circle` with the given `radius`.

## CoordinateMap
<details>
<summary>

### ***CoordinateMapping.java should not be used for anything other than as a reference.***

</summary>

### sub-class Position
***this sub-class in particular should only be used for testing purposes***

has two properties, directly set by its constructor `public Position(DoubleCoordinate coords, Object cont)`.

```Java
DoubleCoordinate pos; Object content;
public Position(DoubleCoordinate coords, Object cont) {
 content=cont;pos=coords;
}
```

it also has a `toString` method override returning a readable output using the properties .toString methods.

### Cooridnate-Map Constructor

the constructor `public CoordinateMap (int sizeX, int sizeY)` sets the `ArrayList<ArrayList<Position>> map` to a 2D matrix with `sizeX` columns and `sizeY` rows.

### inputList

the method `public void inputList(ArrayList<DoubleCoordinate> list,Object f_content)` overrides the `content` property of the `Position`s to `f_content` at the by `list` given positions in the 2D matrix.

### toString override

the `toString` method has been overridden to return a readable output with each row on a new line.

</details>