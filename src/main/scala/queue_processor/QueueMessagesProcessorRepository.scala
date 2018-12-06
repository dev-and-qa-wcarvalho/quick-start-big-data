package queue_processor;
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.streaming.kafka010.ConsumerStrategies
import org.apache.spark.streaming.kafka010.LocationStrategies
import org.apache.spark.streaming.kafka010.KafkaUtils
import get_sales_location_by_code.SalesLocationService
import get_sales_location_by_code.SalesLocationRepository


class QueueMessagesProcessorRepository {
  private var streamingContext = None: Option[StreamingContext]
  def listenQueue() = {
    setUpStreamingContext()
    setUpDirectStream()
    startListener()
  }

  def setUpStreamingContext() = {
    val appName: String = "first-application"
    val sparkConf = new SparkConf().setAppName(appName).setMaster("local")
    streamingContext = Some(new StreamingContext(sparkConf, Seconds(10)))
  }

  def setUpDirectStream() = {
    val kafkaParams = Map[String, Object](ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "localhost:9092", ConsumerConfig.GROUP_ID_CONFIG -> "teste", ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer], ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer])
    val messages = KafkaUtils.createDirectStream[String, String](streamingContext.get, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](Set[String]("teste"), kafkaParams)).map(record => record.value())
    val processMessage = (message: String) => {
      val salesLocation = new SalesLocationService(new SalesLocationRepository()).get(message)
      println(salesLocation.getDescription())
    }
    messages.foreachRDD(rdd => rdd.foreach(line => processMessage(line)))
  }

  def startListener() = {
    streamingContext.get.start()
    streamingContext.get.awaitTermination()
  }

}
