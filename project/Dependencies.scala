import sbt.librarymanagement.ModuleID
import sbt._

object Dependencies {
  object Cats {
    private val effectVer = "3.3.6"
    private val coreVer   = "2.7.0"
    private val org       = "org.typelevel"

    val core   = org %% "cats-core"   % coreVer
    val effect = org %% "cats-effect" % effectVer
  }

  object Streams {
    val fs2Core = "co.fs2" %% "fs2-core" % "3.2.7"
  }
}
