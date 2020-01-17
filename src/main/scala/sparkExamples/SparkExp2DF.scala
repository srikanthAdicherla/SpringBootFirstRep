package sparkExamples

import org.apache.spark.sql.SparkSession

object SparkExp2DF {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .master("local")
      .getOrCreate()
    sparkSession.sparkContext.setLogLevel("Error")
    //    val  sc=sparkSession.sparkContext
    import sparkSession.implicits._
    val s=Seq((23,32,32,32,32),(22,2,2,2,3)).toDF()
    // val std=Set((1,"sri",3443),(2,"ram",3463),(4,"kiran",3545))
    sparkSession.read.textFile("D:\\sri.txt.txt").show()
    val data1 = sparkSession.read.textFile("D:\\sri.txt.txt").
      map(a => {
        val array = a.split(",")
       (array(0), array(1))
      })
    data1.toDF("name", "id").show()

    data1.printSchema()
    //movie dataFrame
    val moviesDataSet = sparkSession.read.textFile("D:\\fragmapProblems\\movieProblem\\movies.dat")
      .map(a => {
        val split = a.split("::")
        (split(0), split(1), split(2))
      })
      .toDF("id", "name", "data")
    moviesDataSet.createOrReplaceTempView("movieData")
    //userData
    val userDataSet = sparkSession.read.textFile("D:\\fragmapProblems\\movieProblem\\users.dat")
      .map(a => {
        val split = a.split("::")
        (split(0), split(1), split(2),split(3))
      })
      .toDF("id", "gender", "age","data")
    //1st
    moviesDataSet.groupBy("id").count().show(20)
    moviesDataSet.createOrReplaceTempView("movieData")
    moviesDataSet.createGlobalTempView("moviedata1")

    //2nd
    moviesDataSet.select("id").groupBy("id").count().show()
    //3rd
    moviesDataSet.filter("id < 20")
      .join(userDataSet, moviesDataSet("Id") === userDataSet("id"))
      .groupBy(userDataSet("age"), moviesDataSet("name")).count().show()
    val firstRow=moviesDataSet.first()
    println(firstRow)
    val countOfMovieFile=moviesDataSet.count()
    println(countOfMovieFile)
    moviesDataSet.select("id","name").filter("id<10").show()

  }

}
