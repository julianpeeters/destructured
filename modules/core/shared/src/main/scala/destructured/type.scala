package destructured

type Pure[X, A] = X match
  case Option[a] => Option[A]

type Ctor[X, A] = X match
  case Some[a] => Some[A]
  case None.type => None.type