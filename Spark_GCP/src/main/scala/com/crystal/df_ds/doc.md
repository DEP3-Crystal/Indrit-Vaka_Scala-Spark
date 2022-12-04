# Dataframes and Datasets

- Dataframes is really just a DataSet of Row objects (DataSet[Row])
- DataSet can explicitly wrap a given struct or type (DataSet[Person], DataSet[(string,Double)])
    - it knows what it is a columns are from the get-go
- DataFrames schema is inferred at runtime, but dataSet can be inferred at compile time
    - Faster detection of errors, and better optimization
    - DataSets can only be used in compiled languages (Java,Scala,Python)
- RDD's can be converted to DataSets with .toDS()

The trend is to Use Datasets more than RDD less

- DataSets are more efficient
    - They can be serialized very efficiently - even better than Kryo
    - Optimal execution plants can be determined at compile time
- DataSets allow for better interoperability
    - MMLib and Spark Streaming are moving toward using DataSets instead of RDD's for their primary API
- DataSets simplify development
    - You can preform most SQL operations on a dataset with one line

# Using Spark SQL in Scala

- Create a SparkSession object instead of a SparkContext when using Spark SQL/DataSet
    - You can get a SparkContext from this session, and use to issue SQL queries on your DataSets!
    - Stop the session when you're done.

# Shell access

Spark can talk to a JDBC or ODBC, so it is possible to actually open up a shell to Spark SQL
and deal with it just like it is a database

- Spark SQL exposes a JDBC/ODBC server (if you built it Spark with hive support)
- Start it with sbt/start-thriftserver.sh
- listens on port 10_000 by default
- Connect using bin/beeline -u jdbc:hive2://hocalhost:10000
- Viola, you have a SQL shell to spark SQL
- You can create tables, or query existion ones that were cached using hiveCtx.cacheTable("tableName")

# User Defined functions (UDF'S)

```scala worksheet
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.udf

val sparkSession = SparkSession.builder()
  .master("local[*]")
  .appName("UDF")
  .getOrCreate()
val dataFrame = sparkSession.read.csv("")
val square = udf { x => x * x }; // declaring the UDF
val squareDF = dataFrame.withColumn("square", square("value")) // Using UDF
```

## Dataset work best with structured data

# Word Count with DataFrames

- Using datasets with this unstructured text data isn't a great fit
- Our initial DataFrame will just have row objects with a column named
  "value" for each line of text
- This is a case where RDD's would be more straightforward

# Using SQL Functions

- explode - similar to flatmap, explodes columns into rows
- split()
- lower()
- passing column names as parameters
    - split( $"value", "\\W++")
    - filter($"colName" =!= "")
        - note that we can use =!= for not equal and === for equal
