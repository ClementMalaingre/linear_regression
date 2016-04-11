object Learn with Predict{
	def parseData = try {
			Some(scala.io.Source.fromFile("data.csv").getLines map(_ split ',' toList) drop(1) map (_ map (_ toDouble)))
		} catch {
			case _: Throwable	=> None
		}

	val learningRate: Double = 1.0

	def learn = {
		ConsoleProxy.println("learning...")
		var t0 = LinearRegression.theta0
		var t1 = LinearRegression.theta1
		val data: Iterator[List[Double]] = parseData;
		def iterate = {
			val tmp0 = learningRate * (0.0 /: data)((acc, x) => acc + predictPrice(t0, t1, x(0) - x(1)) / data.size.toDouble
			val tmp1 = learningRate * (0.0 /: data)((acc, x) => acc + (predictPrice(t0, t1, x(0)) - x(1)) * x(1)) / data.size.toDouble
			t0 = tmp0
			t1 = tmp1
		}
		for (_ <- (1 to 1000)) iterate
		LinearRegression.updateThetas(t0, t1)
	}
}
