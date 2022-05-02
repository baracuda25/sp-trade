object Versions {
  lazy val scalaTestVersion = "3.1.0"
  private val daSdkVersionKey = "da.sdk.version"
  lazy val catsEffectVersion = "3.3.9"
  lazy val daSdkVersion: String = sys.props.get(daSdkVersionKey).getOrElse(sdkVersionFromFile())
  println(s"$daSdkVersionKey = ${daSdkVersion: String}")

  private def sdkVersionFromFile(): String =
    "10" + sbt.IO.read(new sbt.File("./SDK_VERSION").getAbsoluteFile).trim
}
