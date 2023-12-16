inThisBuild(List(
  crossScalaVersions := Seq(scalaVersion.value),
  description := "Typeclasses of a lower kind.",
  organization := "com.julianpeeters",
  homepage := Some(url("https://github.com/julianpeeters/destructured")),
  licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  developers := List(
    Developer(
      "julianpeeters",
      "Julian Peeters",
      "julianpeeters@gmail.com",
      url("http://github.com/julianpeeters")
    )
  ),
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-source:future",
    "-Werror",
    "-Wunused:all",
    "-Wvalue-discard",
    "-Ykind-projector:underscores"
  ),
  scalaVersion := "3.3.1",
  versionScheme := Some("semver-spec"),
))

lazy val root = project.in(file(".")).aggregate(tests)

lazy val cats = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .in(file("modules/cats"))
  .settings(
    name := "destructured-cats",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % "2.10.0"
    )
  )

lazy val scala = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .in(file("modules/scala"))
  .settings(
    name := "destructured-scala",
  )

lazy val tests = project.in(file("modules/tests"))
  .settings(
    name := "destructured-tests",
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "0.7.29" % Test
    )
  )
  .dependsOn(cats.jvm, scala.jvm)
  .enablePlugins(NoPublishPlugin)

lazy val docs = project.in(file("docs/gitignored"))
  .settings(
    mdocOut := file("."),
    mdocVariables := Map(
      "SCALA" -> crossScalaVersions.value.map(e => e.takeWhile(_ != '.')).mkString(", "),
      "VERSION" -> version.value.takeWhile(_ != '+'),
    )
  )
  .dependsOn(cats.jvm, scala.jvm)
  .enablePlugins(MdocPlugin)
  .enablePlugins(NoPublishPlugin)