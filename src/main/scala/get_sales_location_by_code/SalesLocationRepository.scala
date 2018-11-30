package get_sales_location_by_code

import scala.util.parsing.json.JSON
import scalaj.http.Http

class SalesLocationRepository {
  def get(salesLocationCode: String): SalesLocation = {
    val salesLocationJSON = getSalesLocationJSON(salesLocationCode)
    return SalesLocationBuilder(
      salesLocationCode = salesLocationJSON.get("code").get,
      salesLocationDescription = salesLocationJSON.get("description").get
      )
  }

  def getSalesLocationJSON(salesLocationCode: String): Map[String, String] = {
    val salesLocationHost: String = "http://localhost:8080/saleslocations/"
    return JSON.parseFull(
      Http(salesLocationHost).asString.body
    ).get
    .asInstanceOf[Map[String, String]]
  }

}
