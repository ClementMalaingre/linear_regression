import scala.io.StdIn.readInt
import java.io.{EOFException => EOF}
import java.lang.{NumberFormatException => NFE}
import java.nio.file.{Files, Paths}
import scala.language.postfixOps

trait predict {
	def predictPrice(mil: Int): Double = {
		var thetas: List[Double] = Nil;
		if (Files.exists(Paths.get("save")))
			thetas = scala.io.Source.fromFile("save").getLines.map(_.toDouble) toList
		else {
			thetas = 0.0 :: 0.0 :: Nil
			println("Wow, I'm not well taught. You'll get a 0 !")
		}
		return (thetas(0) + thetas(1) * mil)
	}
}

object Main extends predict {

	def main(args: Array[String]): Unit = {
		println("Hello, I'm happy to see you. What's the mileage ?")
		try {
			println("Predicted price is : " + predictPrice(readInt()))
		} catch {
			case e: EOF			=> println("Unexpected end of input")
			case nfe: NFE		=> println("Please enter an Int")
			case x: Throwable	=> println("An unknown error happened: " + x)
		}
	}
}


