package destructured.cats
import munit.FunSuite

class ApplicativeSuite extends FunSuite:
  
  test("ap Option[A]"):
    val a: Option[Int] = Option(1)
    def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
    val obtained: Option[String] = f(a).ap[Int, String](Some(_ => "foo"))(a)
    val expected: Option[String] = Some("foo")
    assertEquals(obtained, expected)

  test("ap Some[A]"):
    val a: Some[Int] = Some(1)
    def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
    val obtained: Option[String] = f(a).ap[Int, String](Some(_ => "foo"))(a)
    val expected: Option[String] = Some("foo")
    assertEquals(obtained, expected)

  test("ap None.type"):
    val a: None.type = None
    def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
    val obtained: Option[String] = f(a).ap[Int, String](Some(_ => "foo"))(a)
    val expected: Option[String] = None
    assertEquals(obtained, expected)

  test("pure Option[A]"):
    val a: Option[Int] = Option(1)
    def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
    val obtained: Option[String] = f(a).pure("foo")
    val expected: Option[String] = Some("foo")
    assertEquals(obtained, expected)

  test("pure Some[A]"):
    val a: Some[Int] = Some(1)
    def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
    val obtained: Option[String] = f(a).pure("foo")
    val expected: Option[String] = Some("foo")
    assertEquals(obtained, expected)

  test("pure None.type"):
    val a: None.type = None
    def f[A](a: A)(using A: ApplicativeOf[A]): ApplicativeOf[A] = A
    val obtained: Option[String] = f(a).pure("foo")
    val expected: Option[String] = Some("foo")
    assertEquals(obtained, expected)