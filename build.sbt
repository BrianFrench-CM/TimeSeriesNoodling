// cargometrics

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")


lazy val root = (project in file("."))
  .settings(
    name := "timeseriesnoodling",
    version := "0.0.1",
    scalaVersion := "2.11.8",
    retrieveManaged := true,
    libraryDependencies ++= Seq(
      "org.scala-lang"               %  "scala-reflect"            % scalaVersion.value,
      "org.apache.spark"             %% "spark-core"               % "2.2.1"    % "provided",
      "org.apache.spark"             %% "spark-sql"                % "2.2.1"    % "provided",
      "org.apache.hadoop"            %  "hadoop-aws"               % "2.8.3"    % "provided",
      "org.apache.spark"             %% "spark-mllib"              % "2.3.1"    % "provided",
      "com.rockymadden.stringmetric" %% "stringmetric-core"        % "0.27.4",
      "com.github.fommil.netlib"     %  "all"                      % "1.1.2" ,
      "org.scalactic"                %% "scalactic"                % "3.0.5",
      "org.apache.spark"             %% "spark-mllib"              % "2.3.1"    % "test",
      "com.amazonaws"                %  "aws-java-sdk-glue"        % "1.11.470" % "test",
      "com.amazonaws"                %  "aws-java-sdk"             % "1.11.470" % "test",
      "org.scalatest"                %% "scalatest"                % "3.0.5"    % "test",
      // https://mvnrepository.com/artifact/com.cloudera.sparkts/sparkts
      "com.cloudera.sparkts"         % "sparkts"                   % "0.4.1",
      "org.scalanlp"                 %% "breeze"                   % "0.12",
      "org.scalanlp"                 %% "breeze-viz"               % "0.12",
      "org.threeten"                 % "threeten-extra"            % "0.9",
      "org.scala-lang.modules"       %% "scala-parser-combinators" % "1.0.4"
    ),
    dependencyOverrides ++= Seq(
      "com.google.code.findbugs"   % "jsr305"                    % "1.3.9",
      "com.google.guava"           % "guava"                     % "11.0.2",
      "junit"                      % "junit"                     % "4.8.2",
      "io.netty"                   % "netty"                     % "3.9.9.Final",
      "commons-net"                % "commons-net"               % "3.1"
    ),
    scalacOptions := Seq(
      "-unchecked",
      "-deprecation",
      "-feature",
      "-Xfatal-warnings")
  )


publishArtifact in (Test, packageSrc) := true

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "native", xs @ _*) => MergeStrategy.singleOrError
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case PathList("com", "amazonaws", xs @ _*) => MergeStrategy.last
  case x => MergeStrategy.first
}
