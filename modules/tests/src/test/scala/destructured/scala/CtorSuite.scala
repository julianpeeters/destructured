package destructured.scala

import munit.FunSuite

class CtorSuite extends FunSuite:
    
    test("Option[A]"):
      val a: Option[Int] = Option(1)
      def f[A](a: A)(using A: CtorOf[A]): CtorOf[A] = A
      val obtained: Option[String] = f(a)("foo")
      val expected: Option[String] = Some("foo")
      assertEquals(obtained, expected)