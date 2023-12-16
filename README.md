# destructured

Common typeclasses and constructors, but parameterized by `A` instead of `F[_]`

### Libraries for Scala 3 (JS, JVM, and Native platforms)
 - [`destructured-cats`](#destructured-cats): typeclasses of the underlying functor, e.g., `Applicative[Option]`
 - [`destructured-scala`](#destructured-scala): typeclass-based constructors of underlying data type, e.g., `Some[T]`

#### Why?

This library can be useful if your model uses subtypes in its definition.

For example, if the compiler knows that a type `A`, is, at a call site, a
`Some[T]` or a `None.type`, then `destructured` typeclasses can be used to
summon `cats` `Applicative` typeclass for the underlying `Option`:

```scala
import destructured.cats.{ApplicativeOf, given}

def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
val a: Some[Int] = Some(1)
// a: Some[Int] = Some(value = 1)
val b: Option[String] = f(a).pure("foo")
// b: Option[String] = Some(value = "foo")
```

<small>(for a more realistic example, see the [dynamical](https://github.com/julianpeeters/dynamical) library)</small>


## `destructured-cats`

```scala
"com.julianpeeters" %% "destructured-cats" % "0.1.0"
```

Supported types: `Applicative[Option]`, `Functor[Option]`, `// TODO`

##### Examples:

##### `ApplicativeOf`


```scala
import destructured.cats.{ApplicativeOf, given}

def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
val a: Some[Int] = Some(1)
// a: Some[Int] = Some(value = 1)
val b: Option[String] = f(a).pure("foo")
// b: Option[String] = Some(value = "foo")
```


##### `FunctorOf`

```scala
import destructured.cats.{FunctorOf, given}

val fa: Option[Int] = Option(1)
// fa: Option[Int] = Some(value = 1)
def f[A](a: A)(using A: FunctorOf[A]): FunctorOf[A] = A
val fb: Option[Int] = f(fa).map(fa)(_ + 1)
// fb: Option[Int] = Some(value = 2)
```


## `destructured-scala`

```scala
"com.julianpeeters" %% "destructured-scala" % "0.1.0"
```

The following constructors are supported:

| Option | Either   | 
| :---:  |  :---:   | 
| Some   | Left     | 
| None   | Right    | 

##### Examples:

##### `Some[T]`

```scala
import destructured.scala.{CtorOf, given}

val a: Some[Int] = Some(1)
// a: Some[Int] = Some(value = 1)
def f[A](a: A)(using A: CtorOf[A]): CtorOf[A] = A
val b: Some[String] = f(a).apply("foo")
// b: Some[String] = Some(value = "foo")
```

##### `Either[L, R]`

```scala
import destructured.scala.{CtorOf, given}

val a: Right[Boolean, Int] = Right(1)
// a: Right[Boolean, Int] = Right(value = 1)
def f[A](a: A)(using A: CtorOf[A]): CtorOf[A] = A
val b: Right[Boolean, String] = f(a).apply("foo")
// b: Right[Boolean, String] = Right(value = "foo")
```