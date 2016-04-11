import scala.language.postfixOps

trait Predict {
	def theta0(): BigDecimal
	def theta1(): BigDecimal

	def predictPrice(mil: BigDecimal): BigDecimal = {
		return (theta0 + theta1 * mil)
	}

	def predict: Unit = {
		val mil = try { Some(ConsoleProxy.readLine("Please enter the mileage: ").toDouble)} catch {case _:Throwable => None}
		mil match {
			case Some(mil) => ConsoleProxy.println(s"For a mileage of $mil, I think your car is worth ${predictPrice(mil)} dollars.")
			case None => ConsoleProxy.println("This is not a correct number.") ; predict
		}
	}
}
