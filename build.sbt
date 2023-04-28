ThisBuild / version := "1.0.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "Uebung1",
    scalaVersion := "2.13.9",
    Compile / scalacOptions ++= Seq("-deprecation"),
    Compile / console / scalacOptions --= Seq("-Ywarn-unused", "-Ywarn-unused-import"),
    Test / fork := true,
    libraryDependencies ++=Seq("org.scalactic" %% "scalactic" % "3.2.9" % "test",
	"org.scalatest" %% "scalatest" % "3.2.9" % "test",
        "org.apache.logging.log4j" % "log4j-api-scala_2.13" % "12.0",
        "org.apache.logging.log4j" % "log4j-core" % "2.19.0" % Runtime)

  )
  
