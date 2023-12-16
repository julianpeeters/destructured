package destructured.cats

import cats.Applicative

trait ApplicativeOf[FT]:
  def ap[A, B](ff: Option[A => B])(fa: Option[A]): Ret[FT, B]
  def pure[A](x: A): Ret[FT, A]

type Ret[X, A] = X match
  case Option[a] => Option[A]

given [T](using A: Applicative[Option]): ApplicativeOf[Option[T]] =
  new ApplicativeOf[Option[T]]:
    def ap[A, B](ff: Option[A => B])(fa: Option[A]): Ret[Option[T], B] =
      A.ap(ff)(fa)
    def pure[A](x: A): Ret[Option[T], A] =
      A.pure(x)

given [T](using A: Applicative[Option]): ApplicativeOf[Some[T]] =
  new ApplicativeOf[Some[T]]:
    def ap[A, B](ff: Option[A => B])(fa: Option[A]): Ret[Some[T], B] =
      A.ap(ff)(fa)
    def pure[A](x: A): Ret[Some[T], A] =
      A.pure(x)

given (using A: Applicative[Option]): ApplicativeOf[None.type] =
  new ApplicativeOf[None.type]:
    def ap[A, B](ff: Option[A => B])(fa: Option[A]): Ret[None.type, B] =
      A.ap(ff)(fa)
    def pure[A](x: A): Ret[None.type, A] =
      A.pure(x)