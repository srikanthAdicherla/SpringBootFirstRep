package customer

import org.apache.spark.sql.Column
import org.apache.spark.sql.functions.{col, expr, lit}

import scala.collection.mutable.ListBuffer

object ExplExpr {
  def main(args: Array[String]): Unit = {
    val listBuffer = new ListBuffer[Column]
    listBuffer += col("customerId")
    listBuffer += col("customerName")
    for (index <- 0 until 2) {
      val addressIndex = index + 1
      listBuffer += expr(s"customerAddresses[$index]").cast("string").as(s"Address$addressIndex")

    }
    println(listBuffer)



  }
}

