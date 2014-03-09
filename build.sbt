name := "qutils-scala"

organization := "com.quartz"

version := "1.0"

scalaVersion := "2.10.3"

resolvers += "Local Maven Repository" at "file://cygdrive/c/Users/Christian/.m2/repository/"

libraryDependencies ++= Seq(
    "org.scalatest" % "scalatest_2.10" % "2.0" % "test",
    "org.joda" % "joda-convert" % "1.6",
    "joda-time" % "joda-time" % "2.3",
    "log4j" % "log4j" % "1.2.17",
    "mockobjects" % "mockobjects-core" % "0.09",
    "junit" % "junit" % "4.11",
    "org.apache.commons" % "commons-collections4" % "4.0",
    "commons-beanutils" % "commons-beanutils-core" % "1.8.3",
    "commons-cli" % "commons-cli" % "1.2",
    "commons-dbutils" % "commons-dbutils" % "1.5",
    "commons-digester" % "commons-digester" % "2.1",
    "commons-lang" % "commons-lang" % "2.3",
    "org.springframework" % "spring-context" % "4.0.2.RELEASE",
    "org.springframework" % "spring-tx" % "3.2.4.RELEASE",
    "org.springframework" % "spring-jdbc" % "3.2.4.RELEASE"
)

