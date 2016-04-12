import scala.xml._
import java.io.FileNotFoundException
import breeze.linalg._
import breeze.plot._

object LinearRegression extends Predict {

    private var t0:Double = 0
    private var t1:Double = 0
    loadFromFile();

    def loadFromFile(fileName: String = "save.xml") {
        try {
            val saved = XML.loadFile(fileName) \\ "root" \\ "val"
            val l = for (x <- saved) yield x.text.toDouble
            updateThetas(l(0), l(1))
        }
        catch {
            case fnf:FileNotFoundException  => println(s"$fileName not found.")
            case x: Throwable               => println(s"An unknown error happened while loading $fileName : $x")
        }
    }

    def theta0 = t0
    def theta1 = t1
    def updateThetas(x:Double, y:Double) = {t0 = x; t1 = y}
    def taught = t0 != 0 || t1 != 0
    def commitToFile(fileName: String = "save.xml") = XML.save(fileName, <root><val name="theta0">{theta0}</val><val name="theta1">{theta1}</val></root>)

    def represent = {
        def f = Figure()
        def p = f.subplot(0)
        val data = Learn.parseData.get
        p += plot(data map(_(0)), data map(_(1)), '.')
        val x = linspace(0, data.foldLeft(data(0)(0))(_ max _(0)))
        p += plot(x, x map(predictPrice(_)))
        f.saveas("regression.png")
    }
}
