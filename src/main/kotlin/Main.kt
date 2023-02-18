import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import interactor.CostOfLivingDataSource
import interactor.GetCitiesHasTheCheapestBananaPricesInteractor

fun main() {
    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser) // CSV : comma separated values
//    val getHighestSalaryAverageCities = GetHighestSalaryAverageCititesNamesInteractor(dataSource)
//    println(getHighestSalaryAverageCities.execute(limit = 10))
//    printSeparationLine()

//    val getCityHasCheapestInternetConnectionInteractor = GetCityHasCheapestInternetConnectionInteractor(dataSource)
//    println(getCityHasCheapestInternetConnectionInteractor.execute())
//    printSeparationLine()

//    val getCityThatHasAverageMealsPricesInteractor = GetCityThatHasAverageMealsPricesInteractor(dataSource)
//    println(getCityThatHasAverageMealsPricesInteractor.execute())
//    printSeparationLine()


    val listOfCities = listOf("Santiago de Cuba", "Sancti Spiritus", "Santa Clara", "Jaramana", "Havana",
                              "Moratuwa", "Las Tunas", "Latakia", "Hamah", "Damascus", "Uyo",
                              "Tamale", "Kasese", "Aleppo", "Saddiqabad")

    val getCitiesHasTheCheapestBananaPricesInteractor = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
    println(getCitiesHasTheCheapestBananaPricesInteractor.execute(listOfCities))
    printSeparationLine()


}
private fun printSeparationLine(){
    print("\n_______________________________\n")

}

