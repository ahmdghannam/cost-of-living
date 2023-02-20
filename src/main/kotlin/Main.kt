import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import dataSource.utils.TheTypeOfApartments
import interactor.CostOfLivingDataSource
import interactor.GetCityHasCheapestInternetConnectionInteractor
import interactor.GetCityThatHasAverageMealsPricesInteractor
import interactor.GetHighestSalaryAverageCititesNamesInteractor

import interactor.*


fun main() {

    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser) // CSV : comma separated values

    // bareq
    val getHighestSalaryAverageCities = GetHighestSalaryAverageCititesNamesInteractor(dataSource)
    println(getHighestSalaryAverageCities.execute(limit = 10))
    printSeparationLine()


    //1
    val getCityHasCheapestInternetConnectionInteractor = GetCityHasCheapestInternetConnectionInteractor(dataSource)
    println(getCityHasCheapestInternetConnectionInteractor.execute())
    printSeparationLine()

    //2
    val GetAvgFruitNVeggieInteractor = GetAvgFruitNVeggieInteractor(dataSource)
    println(GetAvgFruitNVeggieInteractor.execute())
    printSeparationLine()

    //3
    val getSalariesOfCountryCitiesInteractor = GetSalariesOfCountryCitiesInteractor(dataSource)
    println(getSalariesOfCountryCitiesInteractor.execute("Iraq"))
    printSeparationLine()

    // 4  getHighestDifferanceBetweenCityCenterAndOutside
    val getHighestDifferanceBetweenCityCenterAndOutside=GetHighestDifferanceBetweenCityCenterAndOutside(dataSource)
    println(getHighestDifferanceBetweenCityCenterAndOutside.execute(TheTypeOfApartments.ONE_BED_ROOM))
    printSeparationLine()

    //5
    val gitTopFiveFashionCitiesInteractor=GetTopFiveFashionCitiesInteractor(dataSource)
    println(gitTopFiveFashionCitiesInteractor.execute(5))
    printSeparationLine()

    //6
    val getCheapestTenCitiesApartments = FindTheCheapestTenCitiesApartments(dataSource)
    println(getCheapestTenCitiesApartments.execute())
    printSeparationLine()

    //7

    val listOfCities = listOf("Santiago de Cuba", "Sancti Spiritus", "Santa Clara", "Jaramana", "Havana",
        "Moratuwa", "Las Tunas", "Latakia", "Hamah", "Damascus", "Uyo",
        "Tamale", "Kasese", "Aleppo", "Saddiqabad")
    val getCitiesHasTheCheapestBananaPricesInteractor = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
    println(getCitiesHasTheCheapestBananaPricesInteractor.execute(listOfCities))
    printSeparationLine()

    //8
    val getCityThatHasAverageMealsPricesInteractor = GetCityThatHasAverageMealsPricesInteractor(dataSource)
    println(getCityThatHasAverageMealsPricesInteractor.execute())
    printSeparationLine()


    //9
    val getTop10CountriesEnforceHighTaxesOnCarbonatedDrinks = GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinks(dataSource)
    println(getTop10CountriesEnforceHighTaxesOnCarbonatedDrinks.execute())
    printSeparationLine()

    //10
    val getCityHasMoreSavingsPerMonthInteractor = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
    println(getCityHasMoreSavingsPerMonthInteractor.execute())
    printSeparationLine()

    // new features for this week

    // 1- get most expensive city for fruit trade
    val getMostExpensiveCityForFruitTradeInteractor = GetMostExpensiveCityForFruitTradeInteractor(dataSource)
    println(getMostExpensiveCityForFruitTradeInteractor.execute("apple"))
    printSeparationLine()

  
}
private fun printSeparationLine(){
    print("\n_______________________________\n")

}

