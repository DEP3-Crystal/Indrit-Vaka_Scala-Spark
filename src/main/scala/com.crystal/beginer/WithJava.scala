import java.util
import scala.jdk.CollectionConverters._
object WithJava extends App{

        //https://www.scala-lang.org/api/2.13.3/scala/jdk/CollectionConverters$.html
        val numsInJava:util.List[Int]=util.Arrays.asList(1,2,3,4)
        val numsScala:scala.collection.mutable.Buffer[Int]=numsInJava.asScala
        numsScala.foreach(num=>println(num))
}
