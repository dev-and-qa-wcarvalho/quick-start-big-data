package get_sales_location_by_code

object SalesLocationBuilder {
  def apply(salesLocationCode: String, salesLocationDescription: String): SalesLocation = {
    return new SalesLocation(salesLocationCode, salesLocationDescription)
  }
}
