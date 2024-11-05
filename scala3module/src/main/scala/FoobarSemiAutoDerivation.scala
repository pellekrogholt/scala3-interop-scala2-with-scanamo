package example

import org.scanamo.*
import org.scanamo.generic.semiauto.*

// Note: example file from the scanamo library to verify the scala 3 module is working
// source https://github.com/scanamo/scanamo/blob/main/scanamo/src/test/scala/org/scanamo/generic/FoobarSemiAutoDerivation.scala

case class FoobarSemiAutoDerivation(value: Option[String])
object FoobarSemiAutoDerivation {
  implicit val dynamoFormatFoo: DynamoFormat[FoobarSemiAutoDerivation] =
    deriveDynamoFormat[FoobarSemiAutoDerivation]
}
