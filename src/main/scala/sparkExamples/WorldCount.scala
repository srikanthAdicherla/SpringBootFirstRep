package sparkExamples

import org.apache.spark.sql.SparkSession

object WorldCount {

  def main(args: Array[String]): Unit = {


    val sparkSession = SparkSession.builder()
      .master("local")
      .appName("worldCount")
      .getOrCreate()

    sparkSession.sparkContext.setLogLevel("ERROR")

    import sparkSession.implicits._


    val df = sparkSession
      .read.textFile("D:\\FragmaProjects\\SparkExamples\\src\\main\\scala\\sparkExamples\\wordCount")
      .flatMap(
        line => line.split(" ")
      ).map(world => (world, +1)).show()
  }
}