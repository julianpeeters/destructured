package destructured.scala

import munit.FunSuite

class CtorSuite extends FunSuite:

  test("Some[A]"):
    val a: Some[Int] = Some(1)
    def f[A](a: A)(using A: CtorOf[A]): CtorOf[A] = A
    val obtained: Some[String] = f(a).apply("foo")
    val expected: Some[String] = Some("foo")
    assertEquals(obtained, expected)

  test("None.type"):
    val a: None.type = None
    def f[A](a: A)(using A: CtorOf[A]): CtorOf[A] = A
    val obtained: None.type = f(a)("foo")
    val expected: None.type = None
    assertEquals(obtained, expected)

  test("Right[A, B]"):
    val a: Right[Boolean, Int] = Right(1)
    def f[A](a: A)(using A: CtorOf[A]): CtorOf[A] = A
    val obtained: Right[Boolean, String] = f(a).apply("foo")
    val expected: Right[Boolean, String] = Right("foo")
    assertEquals(obtained, expected)

  test("Left[A, B]"):
    val a: Left[Boolean, Int] = Left(true)
    def f[A](a: A)(using A: CtorOf[A]): CtorOf[A] = A
    val obtained: Left[String, Int] = f(a).apply("foo")
    val expected: Left[String, Int] = Left("foo")
    assertEquals(obtained, expected)