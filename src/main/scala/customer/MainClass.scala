package customer

import demo.CreateCSVFileTest
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object MainClass {



  def main(args: Array[String]): Unit = {
    val spark = org.apache.spark.sql.SparkSession.builder().
      master("local").
      appName("csv reader").
      getOrCreate

    spark.sparkContext.setLogLevel("ERROR")


CreateCSVFileTest.createCSVFile(sparkSession = spark)

  }
}
