// https://www.scala-lang.org/download/all.html
// 3.3.* is LTS
lazy val scala3Version = "3.3.4"
lazy val scala2Version = "2.13.14"

lazy val catsCoreVersion = "2.12.0"
lazy val catsEffectVersion = "3.5.5"
lazy val testScalaTestVersion = "3.2.17"
lazy val testScalaMockVersion = "6.0.0"
lazy val scanamoVersion = "2.0.0" // Note: supports scala 3
lazy val scalaJava8Compat = "1.0.2"

lazy val scala3CrossDeps = Seq(
  ("org.scala-lang.modules" %% "scala-java8-compat" % scalaJava8Compat)
    .cross(CrossVersion.for2_13Use3),
  ("org.typelevel" %% "cats-free" % catsCoreVersion)
    .cross(CrossVersion.for2_13Use3),
  ("org.typelevel" %% "cats-kernel" % catsCoreVersion)
    .cross(CrossVersion.for2_13Use3),
  ("org.typelevel" %% "cats-core" % catsCoreVersion)
    .cross(CrossVersion.for2_13Use3),
  ("org.scanamo" %% "scanamo" % scanamoVersion).cross(CrossVersion.for2_13Use3)
)

lazy val scala3module = (project in file("scala3module"))
  .settings(
    scalaVersion := scala3Version,
    libraryDependencies ++= scala3CrossDeps
  )

lazy val scala2moduleusingscala3module =
  (project in file("scala2moduleusingscala3module"))
    .settings(
      scalaVersion := scala2Version,
      libraryDependencies ++= scala3CrossDeps,
      scalacOptions ++= Seq("-Ytasty-reader", "-Xsource:3-cross")
    )
    .dependsOn(scala3module)
    .aggregate(scala3module)
