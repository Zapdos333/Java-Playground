# projects based on school-lessons/material
`package com.Ace009.non_library.school.*`

## math

### AbstractNumberSequence

an `abstract class` that `extends ArrayList<Double>`, defining the skeleton
for a mathematical sequence of numbers a<sub>n</sub>

`constructor`**:**<br/>
calls the `super()` constructor and adds the value in `static protected double a1`

`calculateNextAmount(int amount, boolean next)`/`calculateNextTo(int to, boolean next)`**:**<br/>
calculates the next `amount` numbers in the sequence or to a sequence length of `to`.<br/>
`next` sets if the numbers are calculated using the `calculateNext`(`true`) or the `calculateAt`(`false`) method.
