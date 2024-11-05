package example

import org.scanamo.Table
import org.scanamo.DynamoFormat
import org.scanamo.generic.semiauto.*
final case class Item(id: String)
object Item {
  implicit val dynamoFormatFoo: DynamoFormat[Item] =
    deriveDynamoFormat[Item]
}

object DatabaseOp {
  private val exampleTable = Table[Item]("example-table-name")
}
