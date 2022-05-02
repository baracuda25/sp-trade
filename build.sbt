import Artifactory.daResolvers
import Versions._

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion :="2.12.11"

ThisBuild / organization := "com.etaranenko.sp"


lazy val parent = project
  .in(file("."))
  .settings(
    name := "sp-trade",
    Compile / packageDoc / publishArtifact := false,
    Compile / packageSrc / publishArtifact := false
  )
  .aggregate(`scala-codegen`, `application`)

// <doc-ref:modules>
lazy val `scala-codegen` = project
  .in(file("scala-codegen"))
  .settings(
    name := "scala-codegen",
    commonSettings,
    libraryDependencies ++= codeGenDependencies,
  )

lazy val `application` = project
  .in(file("application"))
  .settings(
    name := "application",
    commonSettings,
    libraryDependencies ++= codeGenDependencies ++ applicationDependencies,
  )
  .dependsOn(`scala-codegen`)
// </doc-ref:modules>

lazy val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-feature",
    "-target:jvm-1.8",
    "-deprecation",
    "-Xfatal-warnings",
    "-Xsource:2.13",
    "-unchecked",
    "-Xfuture",
    "-Xlint:_,-unused"
  ),
  resolvers ++= daResolvers,
  classpathTypes += "maven-plugin",
)

// <doc-ref:dependencies>
lazy val codeGenDependencies = Seq(
  "com.daml.scala" %% "bindings" % daSdkVersion,
)

lazy val applicationDependencies = Seq(
  "com.daml.scala" %% "bindings-akka" % daSdkVersion,
  "org.typelevel" %% "cats-effect" % catsEffectVersion
)
// </doc-ref:dependencies>
trapExit := false
