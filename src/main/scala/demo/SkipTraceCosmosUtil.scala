package demo

import config.MySqlDabaseUtil
import javax.sql.DataSource
import org.apache.spark.sql.Column
import org.apache.spark.sql.functions.{col, expr}

import scala.collection.mutable.ListBuffer

case class CsvCustomer(
customerId:String,
customerAddresses:Seq[String],
customerMobileNumber:Seq[Integer]
  )


object SkipTraceCosmosUtil {
  def getValuesFromCosmosDbForMapPartition(
                                           interMediateCustomer: InterMediateCustomer, mysqlDataSource: DataSource): CsvCustomer = {
    val inputCustomerId = interMediateCustomer.customerId
    val interMediateCosmosCustomer = MySqlDabaseUtil.getValuesFromMysql(mysqlDataSource, inputCustomerId)

    val interMediateCustomerAddresses = interMediateCosmosCustomer.getAddress
    val interMediateCustomerMobileNumbers = interMediateCosmosCustomer.getMobileNo

    var customerAddresses: List[String] = List.empty
    var customerMobileNumbers: List[Integer] = List.empty
    import scala.collection.JavaConverters._
    if (interMediateCustomerAddresses != null) {
      customerAddresses = interMediateCustomerAddresses.asScala.toList
    }
    if (interMediateCustomerMobileNumbers != null) {
      customerMobileNumbers = interMediateCustomerMobileNumbers.asScala.toList
    }
    CsvCustomer.apply(inputCustomerId, customerAddresses, customerMobileNumbers)
  }

  def getColumnExpression(customerAddressColumns: Int, customerMobileNumber: Int): List[Column] = {
    val listBuffer = new ListBuffer[Column]
    listBuffer += col("customerId")
    //listBuffer += col("customerName")
    for (index <- 0 until customerAddressColumns) {
      val addressIndex = index + 1
      listBuffer += expr(s"customerAddresses[$index]").cast("string").as(s"Address$addressIndex")
    }
    for (index <- 0 until customerMobileNumber) {
      val mobileNumberIndex = index + 1
      listBuffer += expr(s"customerMobileNumber[$index]").cast("string").as(s"MobileNumber$mobileNumberIndex")
    }
    listBuffer.toList
  }
}
