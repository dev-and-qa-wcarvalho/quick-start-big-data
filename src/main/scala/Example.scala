import scala.util.parsing.json._
import scalaj.http.Http
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import kafka.server.KafkaConfig
import org.apache.spark.streaming.kafka010.LocationStrategies
import org.apache.spark.streaming.kafka010.ConsumerStrategies
import kafka.serializer.Decoder
import org.apache.spark.streaming.kafka010.KafkaUtils
import java.lang.Boolean
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import get_sales_location_by_code.SalesLocationService
import get_sales_location_by_code.SalesLocationRepository


object Example {
  def main(args: Array[String]): Unit = {
    /*
    val sparkConf = new SparkConf().setAppName("first-application").setMaster("local")
    val ssc = new StreamingContext(sparkConf, Seconds(10))
    val kafkaParams = Map[String, Object](ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "localhost:9092", ConsumerConfig.GROUP_ID_CONFIG -> "teste", ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer], ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer])
    val messages = KafkaUtils.createDirectStream[String, String](ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](Set[String]("teste"), kafkaParams)).map(record => record.value())
    messages.foreachRDD(rdd => rdd.foreach(line => processSale(line)))
    ssc.start()
    ssc.awaitTermination()
    * 
    */
    processSale("""{"salesLocationCode": "sales-location-1234"}""")
  }
  def processSale(sale: String): Unit = {
    val salesLocation = new SalesLocationService(new SalesLocationRepository()).get(sale)
    println(salesLocation.getCode())
    println(salesLocation.getDescription())
  }
}
