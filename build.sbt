name := "spark-SkipTraceCustomerDataPull"

version := "0.1"

scalaVersion := "2.11.12"

// https://mvnrepository.com/artifact/mysql/mysql-connector-java
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.12"
libraryDependencies += "com.microsoft.sqlserver" % "mssql-jdbc" % "6.2.1.jre8"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.1" 
// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.1" 


// https://mvnrepository.com/artifact/com.microsoft.azure/azure-documentdb
libraryDependencies += "com.microsoft.azure" % "azure-documentdb" % "2.0.0"
// https://mvnrepository.com/artifact/com.github.mpkorstanje/simmetrics-core
libraryDependencies += "com.github.mpkorstanje" % "simmetrics-core" % "4.1.1"
// https://mvnrepository.com/artifact/com.zaxxer/HikariCP-java6
libraryDependencies += "com.zaxxer" % "HikariCP-java6" % "2.3.5"
// https://mvnrepository.com/artifact/org.apache.commons/commons-csv
libraryDependencies += "org.apache.commons" % "commons-csv" % "1.4"
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-azure
libraryDependencies += "org.apache.hadoop" % "hadoop-azure" % "2.7.2"


dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.8.7"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.7"
dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.8.7"

// https://mvnrepository.com/artifact/org.apache.spark/spark-streaming
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.4" % "provided"
