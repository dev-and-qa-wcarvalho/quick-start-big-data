

class Produto {
  private var _code: String = ""
  private var _description: String = ""
  def setCode(code: String): Produto = {
    _code = code
    return this
  }
  def setDescription(description: String): Produto = {
    _description = description
    return this
  }
  def getCode(): String = {
    return _code
  }
  def getDescription(): String = {
    return _description
  }
}
