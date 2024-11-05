package example

object Foobar {

  def pong: String = "ping scala 3 module"

  enum Color:
    case Red, Green, Blue

  def helloEnum: Color = Color.Red

}
