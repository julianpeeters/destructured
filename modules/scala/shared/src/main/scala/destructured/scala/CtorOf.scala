package destructured.scala

trait CtorOf[FT]:
  def apply[A](x: A): ctor[FT, A]

type ctor[X, A] = (X, A) match

  // Option
  case (Some[x], a) => Some[a]
  case (None.type, a) => None.type

  // Either
  case (Left[l, r], a) => Left[a, r]
  case (Right[l, r], a) => Right[l, a]


// Option
given [T]: CtorOf[Some[T]] =
  new CtorOf[Some[T]]:
    def apply[A](x: A): ctor[Some[T], A] =
      Some(x)

given [T]: CtorOf[None.type] =
  new CtorOf[None.type]:
    def apply[A](x: A): ctor[None.type, A] =
      None

// Either
given [L, R]: CtorOf[Right[L, R]] =
  new CtorOf[Right[L, R]]:
    def apply[A](x: A): ctor[Right[L, R], A] =
      Right(x)

given [L, R]: CtorOf[Left[L, R]] =
  new CtorOf[Left[L, R]]:
    def apply[A](x: A): ctor[Left[L, R], A] =
      Left(x)