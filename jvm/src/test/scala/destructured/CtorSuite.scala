package destructured

import munit.FunSuite

class CtorSuite extends FunSuite:

  test("Some[A]"):
    val a: Some[Int] = Some(1)
    def f[A](a: A)(using C: CtorOf[Int, A]): CtorOf[Int, A] = C
    val obtained: Some[Int] = f(a).apply(2)
    val expected: Some[Int] = Some(2)
    assertEquals(obtained, expected)

  test("Right[A, B]"):
    val a: Right[Boolean, Int] = Right(1)
    def f[A](a: A)(using C: CtorOf[Int, A]): CtorOf[Int, A] = C
    val obtained: Right[Boolean, Int] = f(a).apply(2)
    val expected: Right[Boolean, Int] = Right(2)
    assertEquals(obtained, expected)

  test("Left[A, B]"):
    val a: Left[Boolean, Int] = Left(true)
    def f[A](a: A)(using C: CtorOf[Boolean, A]): CtorOf[Boolean, A] = C
    val obtained: Left[Boolean, Int] = f(a).apply(false)
    val expected: Left[Boolean, Int] = Left(false)
    assertEquals(obtained, expected)