package destructured

type Destructured[X, A] = X match
  case Option[a] => Option[A]
