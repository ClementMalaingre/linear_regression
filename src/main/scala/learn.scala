object Learn extends Predict{
	def parseData = try {
			Some(scala.io.Source.fromFile("data.csv").getLines.toList map(_ split ',' toList) drop(1) map (_ map (_ toDouble)))
		} catch {
			case _: Throwable	=> None
		}

	val learningRate: Double = 1

    var t0 = LinearRegression.theta0
    var t1 = LinearRegression.theta1

    def theta0 = t0
    def theta1 = t1

	def learn = {
		ConsoleProxy.println("learning...")

		def iterate(data: List[List[Double]], scaling: Double => Double): Unit = {
			val tmp0:Double = (learningRate / data.size) * (0.0 /: data)((acc, x) => acc + predictPrice(scaling(x(0))) - x(1))
			val tmp1:Double = (learningRate / data.size) * (0.0 /: data)((acc, x) => acc + (predictPrice(scaling(x(0))) - x(1)) * scaling(x(0)))
			t0 -= tmp0
			t1 -= tmp1
		}
        parseData match {
            case Some(x)    => {
                val km_min = (x(0)(0) /: x)(_ min _(0))
                val km_max = (x(0)(0) /: x)(_ max _(0))
                def scaling(y: Double): Double = (y - km_min) / (km_max - km_min)
                for (_ <- (1 to 100)) iterate(x, scaling)
                t0 = t0 + t1 * km_min / (km_min - km_max)
                t1 = t1 / (km_max - km_min)
                ConsoleProxy.println("Done learning.")}
            case None       => ConsoleProxy.println("An error occured while parsing data.csv. Please check existence or formatting.")
        }
		LinearRegression.updateThetas(t0, t1)
	}
}

