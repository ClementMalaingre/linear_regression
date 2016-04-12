import jline.console.ConsoleReader
object ConsoleProxy extends ConsoleReader
object Main {
    def main(args: Array[String]): Unit = {
        ConsoleProxy.println("Welcome in this tool of evolutive mileage / price prediction.")
        if (!LinearRegression.taught)
            ConsoleProxy.println("I am not yet taught, so any calculation will return 0 !")
        ConsoleProxy.println("What would you want to do ? (learn / predict / exit)")
        ConsoleProxy.flush()
        var cont = true
        while (cont) {
            ConsoleProxy.readLine("cmd? ").trim match {
                case "learn"        => Learn.learn
                case "predict"      => LinearRegression.predict
                case "state"        => ConsoleProxy.println(s"Theta0 = ${LinearRegression.theta0} ; theta1 = ${LinearRegression.theta1}")
                case "represent"    => LinearRegression.represent
                case ""             => {}
                case null | "exit"  => ConsoleProxy.println("Bye !") ; cont = false
                case _              => ConsoleProxy.println("This is not a valid command")
            }
            ConsoleProxy.flush()
        }
        LinearRegression.commitToFile()
    }
}
