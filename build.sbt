lazy val commonSettings = Seq (
    version := "1.0",
    scalaVersion := "2.11.7",
    libraryDependencies += "jline" % "jline" % "2.12",
    libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.5",
    libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.7.2" % "test"),
    scalacOptions in Test ++= Seq("-Yrangepos")
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "linear_regression",
    libraryDependencies  ++= Seq(
        "org.scalanlp" %% "breeze" % "0.12",
        "org.scalanlp" %% "breeze-natives" % "0.12",
        "org.scalanlp" %% "breeze-viz" % "0.12"
    ),
    resolvers ++= Seq(
        "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
        "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
    )
  )
