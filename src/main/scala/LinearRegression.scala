import scala.xml._
import java.io.FileNotFoundException

object LinearRegression extends Predict {

    private var t0:BigDecimal = 0
    private var t1:BigDecimal = 0
    loadFromFile();

    def loadFromFile(fileName: String = "save.xml") {
        try {
            val saved = XML.loadFile(fileName) \\ "root" \\ "val"
            val l = for (x <- saved) yield BigDecimal(x.text)
            updateThetas(l(0), l(1))
        }
        catch {
            case fnf:FileNotFoundException  => println(s"$fileName not found.")
            case x: Throwable               => println(s"An unknown error happened while loading $fileName : $x")
        }
    }

    def theta0 = t0
    def theta1 = t1
    def updateThetas(x:BigDecimal, y:BigDecimal) = {t0 = x; t1 = y}
    def taught = t0 != 0 || t1 != 0
    def commitToFile(fileName: String = "save.xml") = XML.save(fileName, <root><val name="theta0">{theta0}</val><val name="theta1">{theta1}</val></root>)
}
