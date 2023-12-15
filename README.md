# destructured

Common typeclasses, but parameterized by `A` instead of `F[_]`.

### Why?

This might be useful if your model uses subtypes in its definition.

For example, if the compiler knows that a type `A`, is, at a call site, a
 `Some[A]` or a `None.type`, then `destructured`'s typeclasses can be used to
summon cats typeclasses for the underlying `Option`:

```
A.pure("hello, world")
```