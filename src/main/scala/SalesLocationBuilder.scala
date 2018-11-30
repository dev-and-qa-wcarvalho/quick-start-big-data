

object SalesLocationBuilder {
  def apply(salesLocationCode: String, salesLocationDescription: String): SalesLocation = {
    return new SalesLocation(salesLocationCode, salesLocationDescription)
  }
}
