package SkiptraceIncrementalFlow

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object TestCustomerData {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .appName("skiptrace test")
      .master("local")
      .getOrCreate()

    sparkSession.sparkContext.setLogLevel("ERROR")

    import sparkSession.implicits._

    val customerDataFrame = Seq(
      ("1", "12236812736", "knr,hyd", "bnr", "8985136602"),
      ("2", "176181381", "knr,hyd", "bnr", "898513660"),
      ("2", "16238738726", "delhi", "kerala", "898513660"),
      ("3", "12236812736", "rajasthan", "sri", "8985136602"),
      ("1", "12236812736", "knr,hyd", "bnr", "8985136602"),
      ("3", "922368127", "knr,hyd", "nalamala", "985136602"),
      ("3", "92236812736", "knr,hyd", "nalamala", "985136602")
    ).toDF("customerId", "panNumber", "address1", "address2", "mobileNumber")

    //customerDataFrame.write.format("delta").save("D:\\customer")

  //  val df=sparkSession.read.format("delta").load("D:\\customer").show()
//   val ss= customerDataFrame.createOrReplaceTempView("sd")
//    customerDataFrame.printSchema()
//customerDataFrame.foreachPartition(a=>a.foreach(s=>println(s)))
   val ss= customerDataFrame.groupBy("customerId").agg(
     first("panNumber",true).as("panNumber"),
     collect_set("panNumber").as("PanNumber"),
     collect_set("address1").as("address1"),
      collect_set("address2").as("address2"),
      collect_set("mobileNumber").as("mobileNumber"))
    ss.printSchema()
   // ss.show()
    ss.createOrReplaceTempView("ss")
    //ss.dropDuplicates().show()
    //sparkSession.sql("select customerId,String_Agg(panNumber) from ss group by customerId ").show()

  }
}
