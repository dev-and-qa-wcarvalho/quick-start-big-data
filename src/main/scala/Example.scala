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
    new SalesQueueListenerService(
      new SalesQueueListenerRepository()
    ).listen()
  }
}
