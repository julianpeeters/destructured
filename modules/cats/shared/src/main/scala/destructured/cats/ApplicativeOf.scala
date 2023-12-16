package destructured.cats

import cats.Applicative
import destructured.Destructured

trait ApplicativeOf[FT]:
  def pure[A](x: A): Destructured[FT, A]

given [T](using A: Applicative[Option]): ApplicativeOf[Option[T]] =
  new ApplicativeOf[Option[T]]:
    def pure[A](x: A): Destructured[Option[T], A] =
      A.pure(x)