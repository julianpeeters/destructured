package destructured

trait CtorOf[A, FA]:
  def apply(x: A): FA

// Option
given [T]: CtorOf[T, Some[T]] =
  new CtorOf[T, Some[T]]:
    def apply(x: T): Some[T] =
      Some(x)

given [T]: CtorOf[T, None.type] =
  new CtorOf[T, None.type]:
    def apply(x: T): None.type =
      None

// Either
given [L, R]: CtorOf[R, Right[L, R]] =
  new CtorOf[R, Right[L, R]]:
    def apply(x: R): Right[L, R] =
      Right(x)

given [L, R]: CtorOf[L, Left[L, R]] =
  new CtorOf[L, Left[L, R]]:
    def apply(x: L): Left[L, R] =
      Left(x)