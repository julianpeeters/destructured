# destructured

Common typeclasses and constructors, but parameterized by `A` instead of `F[_]`

### Why?

This might be useful if your model uses subtypes in its definition.

For example, if the compiler knows that a type `A`, is, at a call site, a
`Some[T]` or a `None.type`, then `destructured` typeclasses can be used to
summon `cats` `Applicative` typeclass for the underlying `Option`:

```scala mdoc
import destructured.cats.{ApplicativeOf, given}

def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
val a: Some[Int] = Some(1)
val b: Option[String] = f(a).pure("foo")
```

## Libraries for Scala @SCALA@ (JS, JVM, and Native platforms)
 - [`destructured-cats`](#destructured-cats): typeclasses that provide common cats typeclasses, e.g., `Applicative[Option]`
 - [`destructured-scala`](#destructured-scala): typeclasses that provide the underlying data constructors of a type `A`, e.g., `Some[T]`

### `destructured-cats`

| ApplicativeOf| Supported data types |
| ------------- | ------------- |
| Option        | Content Cell  |

```scala
"com.julianpeeters" %% "destructured-cats" % "@VERSION@"
```

```scala mdoc
import destructured.cats.{ApplicativeOf, given}

def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
val a: Some[Int] = Some(1)
val b: Option[String] = f(a).pure("foo")
```

### `destructured-scala`

```scala
"com.julianpeeters" %% "destructured-scala" % "@VERSION@"
```