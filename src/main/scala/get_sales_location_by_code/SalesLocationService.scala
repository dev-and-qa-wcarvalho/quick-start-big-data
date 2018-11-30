package get_sales_location_by_code

import scala.util.parsing.json.JSON

class SalesLocationService(private var salesLocationRepository: SalesLocationRepository) {
  def get(sale: String): SalesLocation = {
    val salesLocationCode = getSalesLocationCodeFromSaleJSON(sale)
    return salesLocationRepository.get(salesLocationCode)
  }
  def getSalesLocationCodeFromSaleJSON(sale: String): String = {
    return JSON.parseFull(sale).get
    .asInstanceOf[Map[String, String]]
    .get("salesLocationCode").get
  }

}
