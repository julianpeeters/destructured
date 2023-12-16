package destructured.cats
import munit.FunSuite

class FunctorSuite extends FunSuite:
  
  test("Option[A]"):
    val a: Option[Int] = Option(1)
    def f[A](a: A)(using A: FunctorOf[A]): FunctorOf[A] = A
    val obtained: Option[Int] = f(a).map(a)(_ + 1)
    val expected: Option[Int] = Some(2)
    assertEquals(obtained, expected)

  test("Some[A]"):
    val a: Some[Int] = Some(1)
    def f[A](a: A)(using A: FunctorOf[A]): FunctorOf[A] = A
    val obtained: Option[Int] = f(a).map(a)(_ + 1)
    val expected: Option[Int] = Some(2)
    assertEquals(obtained, expected)

  test("None.type"):
    val a: None.type = None
    def f[A](a: A)(using A: FunctorOf[A]): FunctorOf[A] = A
    val obtained: Option[Int] = f(a).map(a)(_  => 2)
    val expected: Option[Int] = None
    assertEquals(obtained, expected)