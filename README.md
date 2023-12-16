# destructured

Common typeclasses and constructors, but parameterized by `A` instead of `F[_]`

### Why?

This might be useful if your model uses subtypes in its definition.

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

## Libraries for Scala 3 (JS, JVM, and Native platforms)
 - [`destructured-cats`](#destructured-cats): typeclasses that provide common cats typeclasses, e.g., `Applicative[Option]`
 - [`destructured-scala`](#destructured-scala): typeclasses that provide the underlying data constructors of a type `A`, e.g., `Some[T]`

### `destructured-cats`

```scala
"com.julianpeeters" %% "destructured-cats" % "0.0.0"
```

```scala
import destructured.cats.{ApplicativeOf, given}

def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
val a: Some[Int] = Some(1)
// a: Some[Int] = Some(value = 1)
val b: Option[String] = f(a).pure("foo")
// b: Option[String] = Some(value = "foo")
```

### `destructured-scala`

```scala
"com.julianpeeters" %% "destructured-scala" % "0.0.0"
```

```scala
import destructured.scala.{CtorOf, given}

def f[A](a: A)(using A: CtorOf[A]): CtorOf[A] = A
val a: Some[Int] = Some(1)
// a: Some[Int] = Some(value = 1)
val b: Option[String] = f(a)("foo")
// b: Option[String] = Some(value = "foo")
```