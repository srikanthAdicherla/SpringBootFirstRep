package demo

import config.HikariTeat
import org.apache.spark.sql.functions.lit
import org.apache.spark.sql.{Dataset, SaveMode, SparkSession}

case class InterMediateCustomer(customerId:String)
case class InterMediateCustomer1(customerId:String,name:String)

object CreateCSVFileTest {

  def createCSVFile(sparkSession: SparkSession): Unit = {

    import sparkSession.implicits._
    val customerIdDataFrame = Seq("1", "2", "2018", "4").toDF("customerId")
    val InterMediateCustomerDataSet: Dataset[InterMediateCustomer] = customerIdDataFrame.as[InterMediateCustomer]
    InterMediateCustomerDataSet.show

    val cosmosCsvCustomerValue = InterMediateCustomerDataSet.mapPartitions(interMediateCustomers => {
      interMediateCustomers.map(interMediateCustomer => {
        //Here u need to create only MySqlDabaseUtil datasource
        val mysqlDataSource = HikariTeat.getDatasource
        try {
          SkipTraceCosmosUtil.
            getValuesFromCosmosDbForMapPartition(interMediateCustomer, mysqlDataSource)
        } catch {
          case _: Throwable =>
            SkipTraceCosmosUtil.
              getValuesFromCosmosDbForMapPartition(interMediateCustomer, mysqlDataSource)
        }
      })
    }).toDF
    cosmosCsvCustomerValue.show

    val columnsList=SkipTraceCosmosUtil.getColumnExpression(2,2)
    cosmosCsvCustomerValue.select(columnsList:_*).show

  }
  }