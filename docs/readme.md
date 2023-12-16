# destructured

Common typeclasses and constructors, but parameterized by `A` instead of `F[_]`

### Libraries for Scala @SCALA@ (JS, JVM, and Native platforms)
 - [`destructured-cats`](#destructured-cats): typeclasses of the underlying functor, e.g., `Applicative[Option]`
 - [`destructured-scala`](#destructured-scala): typeclass-based constructors of underlying data type, e.g., `Some[T]`

#### Why?

This library can be useful if your model uses subtypes in its definition.

For example, if the compiler knows that a type `A`, is, at a call site, a
`Some[T]` or a `None.type`, then `destructured` typeclasses can be used to
summon `cats` `Applicative` typeclass for the underlying `Option`:

```scala mdoc:reset
import destructured.cats.{ApplicativeOf, given}

def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
val a: Some[Int] = Some(1)
val b: Option[String] = f(a).pure("foo")
```

<small>(for a more realistic example, see the [dynamical](https://github.com/julianpeeters/dynamical) library)</small>


## `destructured-cats`

```scala
"com.julianpeeters" %% "destructured-cats" % "@VERSION@"
```

Supported types: `Applicative[Option]`, `Functor[Option]`, `// TODO`

##### Examples:

##### `ApplicativeOf`


```scala mdoc:reset
import destructured.cats.{ApplicativeOf, given}

def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
val a: Some[Int] = Some(1)
val b: Option[String] = f(a).pure("foo")
```


##### `FunctorOf`

```scala mdoc:reset
import destructured.cats.{FunctorOf, given}

val fa: Option[Int] = Option(1)
def f[A](a: A)(using A: FunctorOf[A]): FunctorOf[A] = A
val fb: Option[Int] = f(fa).map(fa)(_ + 1)
```


## `destructured-scala`

```scala
"com.julianpeeters" %% "destructured-scala" % "@VERSION@"
```

The following constructors are supported:

| Option | Either   | 
| :---:  |  :---:   | 
| Some   | Left     | 
| None   | Right    | 

##### Examples:

##### `Some[T]`

```scala mdoc:reset
import destructured.scala.{CtorOf, given}

val a: Some[Int] = Some(1)
def f[A](a: A)(using A: CtorOf[A]): CtorOf[A] = A
val b: Some[String] = f(a).apply("foo")
```

##### `Either[L, R]`

```scala mdoc:reset
import destructured.scala.{CtorOf, given}

val a: Right[Boolean, Int] = Right(1)
def f[A](a: A)(using A: CtorOf[A]): CtorOf[A] = A
val b: Right[Boolean, String] = f(a).apply("foo")
```