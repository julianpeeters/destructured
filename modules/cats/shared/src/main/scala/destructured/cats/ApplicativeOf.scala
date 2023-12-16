package destructured.cats

import cats.Applicative

trait ApplicativeOf[FT]:
  def pure[A](x: A): Pure[FT, A]

type Pure[X, A] = X match
  case Option[a] => Option[A]

given [T](using A: Applicative[Option]): ApplicativeOf[Option[T]] =
  new ApplicativeOf[Option[T]]:
    def pure[A](x: A): Pure[Option[T], A] =
      A.pure(x)

given [T](using A: Applicative[Option]): ApplicativeOf[Some[T]] =
  new ApplicativeOf[Some[T]]:
    def pure[A](x: A): Pure[Some[T], A] =
      A.pure(x)

given (using A: Applicative[Option]): ApplicativeOf[None.type] =
  new ApplicativeOf[None.type]:
    def pure[A](x: A): Pure[None.type, A] =
      A.pure(x)