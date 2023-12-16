package destructured.cats

import cats.Functor

trait FunctorOf[FT]:
  def map[B](fa: FA[FT])(f: Func[FT, B]): FB[FT, B]

type FA[X] = X match
  case Option[a] => Option[a]

type FB[X, B] = X match
  case Option[a] => Option[B]

type Func[X, B] = X match
  case Option[a] => a => B

given [A] (using F: Functor[Option]): FunctorOf[Option[A]] =
  new FunctorOf[Option[A]]:
      def map[B](fa: FA[Option[A]])(f: Func[Option[A], B]): FB[Option[A], B] =
        F.map(fa)(f)

given [A] (using F: Functor[Option]): FunctorOf[Some[A]] =
  new FunctorOf[Some[A]]:
      def map[B](fa: FA[Some[A]])(f:  Func[Some[A], B]): FB[Some[A], B] =
        F.map(fa)(f)

given (using F: Functor[Option]): FunctorOf[None.type] =
  new FunctorOf[None.type]:
      def map[B](fa: FA[None.type])(f:  Func[None.type, B]): FB[None.type, B] =
        F.map(fa)(f)