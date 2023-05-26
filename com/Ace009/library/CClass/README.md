# custom library-styled additions to classes
`package com.Ace009.library.CClass`

1. [CMath](#cmath)
2. [CList](#clist)
3. [CString](#cstring)
4. [CNumber](#cnumber)
5. [CObject](#cobject)

## CMath

### limitedRandom
`public static int limitedRandom(int min, int max)` returns a random `integer` between `min`(inclusive) and `max`(inclusive).

## CList

### getRandom
`public static <Type> Type getRandom(ArrayList<Type> list)` returns a random entry of `list`.

### merge
`public static <T> ArrayList<T> merge(Collection<T> ... allLists)` merges `allLists` into a new `ArrayList`<br>
by adding the entries of each entry of `allLists` to the new list, if the entry isn't already in the new list.

### deduplicate
`public static <T> ArrayList<T> deduplicate(ArrayList<T> list)` returns new `ArrayList<T>` with no duplicate elements.

## CString

### formatToLength
`public static String formatToLength(String input, int length)` returns a new `String`, which has at least the `length()` of `int length`.

## CNumber

***requires testing/refining***
### format
turns a `number` into a `String` with dots seperating blocks of 3 digits

### form(Number)
formats and parses the input `String` as `Number`

## CObject

**any member of this class that accesses `CObject.values` throws `IllegalAccessException`**

### entries/keys/values

returns an array of `String`(keys), `Object`(values) or `Object[2]`(entries)
which contain the property keys, values or a tuple containing both

argument `boolean accessPrivate` asks if private properties should be used, defaults to `false`

### equals
`public static boolean equals(Object a, Object b, boolean checkPrivate)`

compares two objects by their properties, by using `CObject.entries`

`checkPrivate` sets `accessPrivate` for `CObject.entries` and defaults to `true`
