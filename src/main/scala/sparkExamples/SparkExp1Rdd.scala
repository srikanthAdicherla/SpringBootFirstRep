package sparkExamples

import org.apache.spark.sql.SparkSession

object SparkExp1Rdd {


  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder()
      .appName("create rdd")
      .master("local")
      .getOrCreate()

    sparkSession.sparkContext.setLogLevel("ERROR")

    import sparkSession.implicits._

    val data1 = sparkSession.read.textFile("D:\\sri.txt.txt")
    val moviesDataSet = sparkSession.read.textFile("D:\\fragmapProblems\\movieProblem\\movies.dat")
      .map(a => {
        val split = a.split("::")
        (split(0), split(1), split(2))
      })
      .toDF("id", "name", "data")
    //writing the file into csv
    moviesDataSet.write.format("csv")
      .mode("overwrite").save("D:\\fragmapProblems\\movieProblem\\sai.parquest.txt")


    val std = sparkSession.read.format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/sri")
      .option("user", "root")
    .option("password", "root")
    //transformations spark
    //1 map ===>return the one dataset
    val addNewElement = data1.map(add => (add, "Srikanth"))
    addNewElement.show()

    //2 flatmap====> return the list of datasets
    val flatmapDs = data1.flatMap(reptation => (List(reptation, reptation)))
    flatmapDs.show()
    moviesDataSet.flatMap(a=>(List(a.length))).write.save("D:\\srik.txt.txt")

    val data = Seq(1, 2, 3, 3, 4, 4, 6, 7, 8, 84, 234, 2346, 678, 23, 45, 67, 77)
    val distDs1 = sparkSession.sparkContext.parallelize(1 to 100, 10)
    val distDs2 = sparkSession.sparkContext.parallelize(90 to 200)
    //3 using the filter function
    val flatmap = distDs1.filter(_ % 2 == 0).toDF()
    flatmap.show()
    //4. using the intersection function
    val intersecctionDS = distDs1.intersection(distDs2).toDS()
    intersecctionDS.show()
    //  5.filter
    val filter = data1.filter(m => m.contains("ram")).show()
    //6 mapReduce

    //spark action functions
    //1 reduce
    val reduce = distDs1.reduce(_ + _)
    println("sum of the 1 to 100=" + reduce)
    //2 first
    val first = distDs1.first()
    println("first number is==" + first)
    //3 foreachpartion
    distDs1.foreachPartition(parts => println(parts.reduce(_ + _)))
    //


  }
}