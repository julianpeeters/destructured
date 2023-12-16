package destructured.cats
import munit.FunSuite

class ApplicativeSuite extends FunSuite:
  
  test("Option[A]"):
    val a: Option[Int] = Option(1)
    def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
    val obtained: Option[String] = f(a).pure("foo")
    val expected: Option[String] = Some("foo")
    assertEquals(obtained, expected)

  test("Some[A]"):
    val a: Some[Int] = Some(1)
    def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
    val obtained: Option[String] = f(a).pure("foo")
    val expected: Option[String] = Some("foo")
    assertEquals(obtained, expected)

  test("None.type"):
    val a: None.type = None
    def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
    val obtained: Option[String] = f(a).pure("foo")
    val expected: Option[String] = Some("foo")
    assertEquals(obtained, expected)