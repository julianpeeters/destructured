package destructured.scala

import destructured.Destructured

trait CtorOf[FT]:
  def apply[A](x: A): Destructured[FT, A]

given [T]: CtorOf[Option[T]] =
  new CtorOf[Option[T]]:
    def apply[A](x: A): Destructured[Option[T], A] =
      Some(x)