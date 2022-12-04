import org.apache.spark._

val conf = new SparkConf()
  .setMaster("local[2]")
  .setAppName("Word Counter")


val sc: SparkContext = SparkContext.getOrCreate(conf)

val textFile = sc.textFile("C:\\Spark\\README.md")
println("Lines in file: " + textFile.count())
textFile.first()

textFile.flatMap(_.split(" ")).count()





