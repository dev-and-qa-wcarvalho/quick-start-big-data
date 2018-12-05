import get_sales_location_by_code.SalesLocationService
import get_sales_location_by_code.SalesLocationRepository
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.streaming.kafka010.ConsumerStrategies
import org.apache.spark.streaming.kafka010.LocationStrategies
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka010.KafkaUtils


class SalesQueueListenerService(private var salesQueueListenerRepository: SalesQueueListenerRepository) {
  def listen() = {
    salesQueueListenerRepository.listen()
  }
  
}
