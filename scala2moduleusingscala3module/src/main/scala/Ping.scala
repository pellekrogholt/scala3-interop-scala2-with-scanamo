import example.*

object Ping {
  def ping: String = "ping scala 2.13 module"
  def main(args: Array[String]): Unit = {
    println(ping)
    println(Foobar.pong)
    println(s"helloEnum: ${Foobar.helloEnum}")
  }
}
