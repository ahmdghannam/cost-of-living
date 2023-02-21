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
    println(GetAvgFruitNVeggieInteractor.execute(10))
    printSeparationLine()

    //3
    val getSalariesOfCountryCitiesInteractor = GetSalariesOfCountryCitiesInteractor(dataSource)
    println(getSalariesOfCountryCitiesInteractor.execute("Iraq"))
    printSeparationLine()

    // 4 getHighestDifferanceBetweenCityCenterAndOutside
    val getHighestDifferanceBetweenCityCenterAndOutside=GetHighestDifferanceBetweenCityCenterAndOutside(dataSource)
    println(getHighestDifferanceBetweenCityCenterAndOutside.execute(TheTypeOfApartments.ONE_BED_ROOM))
    printSeparationLine()

    //5
    val gitTopFiveFashionCitiesInteractor=GetTopFiveFashionCitiesInteractor(dataSource)
    println(gitTopFiveFashionCitiesInteractor.execute(5))
    printSeparationLine()

    //6
    val getCheapestTenCitiesApartments = FindTheCheapestTenCitiesApartments(dataSource)
    println(getCheapestTenCitiesApartments.execute(10))
    printSeparationLine()

    //7
    val getCitiesHasTheCheapestBananaPricesInteractor = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
    println(getCitiesHasTheCheapestBananaPricesInteractor.execute("Las Tunas", "Latakia", "Hamah", "Damascus", "Uyo",
        "Tamale", "Kasese", "Aleppo", "Saddiqabad"))
    printSeparationLine()

    //8
    val getCityThatHasAverageMealsPricesInteractor = GetCityThatHasAverageMealsPricesInteractor(dataSource)
    println(getCityThatHasAverageMealsPricesInteractor.execute(listOf("united states", "canada", "mexico")))
    printSeparationLine()


    //9
    val getTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractor = GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractor(dataSource)
    println(getTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractor.execute(10))
    printSeparationLine()

    //10
    val getCityHasMoreSavingsPerMonthInteractor = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
    println(getCityHasMoreSavingsPerMonthInteractor.execute())
    printSeparationLine()

    // new features for this week

    // 1
    val getMostExpensiveCityForFruitTradeInteractor = GetMostExpensiveCityForFruitTradeInteractor(dataSource)
    println(getMostExpensiveCityForFruitTradeInteractor.execute("apple"))
    printSeparationLine()

    // 2
    val getTheCheapestCityForTheSalaryInItAndTheCheapestCarPrice = GetTheCheapestCityForTheSalaryInItAndTheCheapestCarPrice(dataSource)
    println(getTheCheapestCityForTheSalaryInItAndTheCheapestCarPrice.execute(1))
}
private fun printSeparationLine(){
    print("\n_______________________________\n")

}

