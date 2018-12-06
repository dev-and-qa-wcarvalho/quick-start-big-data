import queue_processor.QueueMessagesProcessorRepository
import queue_processor.QueueMessagesProcessorService

object Example {
  def main(args: Array[String]): Unit = {
    new QueueMessagesProcessorService(
      new QueueMessagesProcessorRepository()
    ).listenQueue()
  }
}
