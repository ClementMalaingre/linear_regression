import scala.language.postfixOps

trait Predict {
	def theta0(): Double
	def theta1(): Double

	def predictPrice(mil: Double): Double = {
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
