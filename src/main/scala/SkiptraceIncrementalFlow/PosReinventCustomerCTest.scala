package SkiptraceIncrementalFlow

import java.io.FileReader
import java.util
import java.util.{ArrayList, List}

import org.apache.commons.httpclient.util.DateUtil
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{collect_set, first}

object PosReinventCustomerCTest {
  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder()
      .appName("testdata")
      .master("local")
      .getOrCreate()

    sparkSession.sparkContext.setLogLevel("ERROR")

    import sparkSession.implicits._


    val df = sparkSession
      .read.option("header", "true")
      .csv("C:\\Users\\CEPL\\Downloads\\Resulsttop50_New.csv")
      .toDF

    //  df.foreach(v=>println(v))
    val ss = df.groupBy("CUSTOMER_ID").agg(
      //first("CUSTOMER_ID",true).as("customerid"),
      first("CUSTOMER_NAME", true).as("name"),
      collect_set("MOBILE_NUMBERS").as("mobileNumber"),
      first("FULL_ADDRESS1").as("address1"),
      first("FULL_ADDRESS2").as("address2"),
      first("FULL_ADDRESS3").as("address3"),
      first("FULL_ADDRESS4").as("address4"),
      first("FULL_ADDRESS5").as("address5"),
      first("FULL_ADDRESS6").as("address6"),
      first("MODIFIED_DATE").as("modifiedDate")
    )
    ss.printSchema()
    val date = "12-12-1990"
    val ad1 = "knr"
    val ad2 = "hyd"
    val ad3 = "bng"
    val ad4 = "lmd"
    val list = new util.ArrayList[String]
    list.add(ad1)
    list.add(ad2)
    list.add(ad3)
    list.add(ad4)
    println(getCustomerAddress(list, date))
    //println(arrayList)


    def getCustomerAddress(customerAddressList: util.List[String], modifiedDate: String): util.ArrayList[CustomerAddresses] = {
      val customerAddressesList = new util.ArrayList[CustomerAddresses]
      if (!customerAddressList.isEmpty) {
        import scala.collection.JavaConversions._
        for (fullAddress <- customerAddressList) {
          if (fullAddress != null) {
            val customerAddresses = new CustomerAddresses
            customerAddresses.setFullAddress(fullAddress)
            //  if (modifiedDate != null) customerAddresses.setModifiedDate(DateUtil.modifiedDateString(modifiedDate))
            customerAddressesList.add(customerAddresses)
          }
        }
      }
      customerAddressesList
    }

  }
}