name := "qutils-scala"

version := "1.0"

scalaVersion := "2.10.3"

resolvers += "Local Maven Repository" at "file://cygdrive/c/Users/Christian/.m2/repository/"

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.7.0-SNAPSHOT")

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"