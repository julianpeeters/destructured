# destructured

Typeclasses that provide data constructors of `F[_]`, but parameterized by `A`
instead of `F[_]`.

### Add the dependency:
 - libarary for Scala 3 (JS, JVM, and Native platforms)
 
```scala
"com.julianpeeters" %% "destructured" % "0.2.0"
```

### Why?

This library may be useful if your model uses subtypes in its definition.

### How?

The `CtorOf` typeclass is like `ValueOf`, but for constructors:
 - Scala's `ValueOf` provides values like `None` for singleton types,
 - here, `CtorOf` provides constructors like `Some(_)` for parameterized types

### Example:

For example, if the compiler knows that a type `A`, is, at a call site, a
`Some[T]` or a `None.type`, then `destructured` typeclasses can be used to
summon a constructor for the underlying `Some[T]` or `None.type`:

```scala
import destructured.{CtorOf, given}

val a: Some[Int] = Some(1)
// a: Some[Int] = Some(value = 1)
def f[A](a: A)(using C: CtorOf[Int, A]): CtorOf[Int, A] = C
val b: Some[Int] = f(a).apply(2)
// b: Some[Int] = Some(value = 2)
```

<small>(Note: For a more realistic example, see the [dynamical](https://github.com/julianpeeters/dynamical) library)</small>

### SupportedTypes:

| Option | Either   | 
| :---:  |  :---:   | 
| Some   | Left     | 
| None   | Right    | 


##### `Some[T]`

```scala
import destructured.{CtorOf, given}

val a: Some[Int] = Some(1)
// a: Some[Int] = Some(value = 1)
def f[A](a: A)(using C: CtorOf[Int, A]): CtorOf[Int, A] = C
val b: Some[Int] = f(a).apply(2)
// b: Some[Int] = Some(value = 2)
```

##### `None.type`

```scala
import destructured.{CtorOf, given}

val a: None.type = None
// a: None = None
def f[A](a: A)(using C: CtorOf[Int, A]): CtorOf[Int, A] = C
val b: None.type = f(a).apply(2)
// b: None = None
```

##### `Right[L, R]`

```scala
import destructured.{CtorOf, given}

val a: Right[Boolean, Int] = Right(1)
// a: Right[Boolean, Int] = Right(value = 1)
def f[A](a: A)(using C: CtorOf[Int, A]): CtorOf[Int, A] = C
val b: Right[Boolean, Int] = f(a).apply(2)
// b: Right[Boolean, Int] = Right(value = 2)
```

##### `Left[L, R]`

```scala
import destructured.{CtorOf, given}

val a: Left[Boolean, Int] = Left(false)
// a: Left[Boolean, Int] = Left(value = false)
def f[A](a: A)(using C: CtorOf[Boolean, A]): CtorOf[Boolean, A] = C
val b: Left[Boolean, Int] = f(a).apply(true)
// b: Left[Boolean, Int] = Left(value = true)
```