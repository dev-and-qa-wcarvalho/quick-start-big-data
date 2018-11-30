package get_sales_location_by_code

class SalesLocation(private var code: String, private var description: String) {
  def getCode(): String = {
    return code
  }
  def getDescription(): String = {
    return description
  }
}
