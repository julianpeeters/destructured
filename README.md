# destructured

Common typeclasses, but parameterized by `A` instead of `F[_]`.

### Why?

For example, if the compiler knows that a type `A`, is, at a call site, a
subtype of `Option[?]`, then `destructured`'s typeclasses can be used to summon
cats typeclasses for the underlying `Option`:

```
A.pure("hello, world")
```