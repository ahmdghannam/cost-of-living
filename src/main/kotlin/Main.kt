import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import interactor.CostOfLivingDataSource
import interactor.GetCityHasCheapestInternetConnectionInteractor
import interactor.GetHighestSalaryAverageCititesNamesInteractor

fun main() {
//    val csvParser = CsvParser()
//    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser) // CSV : comma separated values
//
//    val getHighestSalaryAverageCities = GetHighestSalaryAverageCititesNamesInteractor(dataSource)
//
//    println(getHighestSalaryAverageCities.execute(limit = 10))
//    printSeparationLine()
//
//    val getCityHasCheapestInternetConnectionInteractor = GetCityHasCheapestInternetConnectionInteractor(dataSource)
//    println(getCityHasCheapestInternetConnectionInteractor.execute())
//    printSeparationLine()
//
//    println()
    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, "subListOfCostOfLiving.csv") // CSV : comma separated values
    val list=dataSource.getAllCitiesData()
    list.forEach {
        println(it)
        printSeparationLine()
    }
}
private fun printSeparationLine(){
    print("\n_______________________________\n")
}

