package interactor

import model.CityEntity


class GetMostExpensiveCityForFruitTradeInteractor(
    private val dataSource: CostOfLivingDataSource,
) {
    fun execute(nameFruit: String): String {
        val listOfCities =  dataSource
                     .getAllCitiesData()
                     .filter(::excludeNullFruitPrices)

        return getCheapestAndExpensiveCities(nameFruit,listOfCities)
    }
    private fun excludeNullFruitPrices(city: CityEntity): Boolean {
        return city.fruitAndVegetablesPrices.let {
                    it.apples1kg != null &&
                    it.banana1kg != null &&
                    it.oranges1kg != null
        }

    }
    private fun getCheapestAndExpensiveCities(nameFruit: String, cities: List<CityEntity>) : String {
        val (cheapestCity, expensiveCity) = when (nameFruit.lowercase().trim()) {
            "apple" -> cities.getCheapestAndExpensiveFruitCost { it.fruitAndVegetablesPrices.apples1kg!! }
            "banana" -> cities.getCheapestAndExpensiveFruitCost { it.fruitAndVegetablesPrices.banana1kg!! }
            "orange" -> cities.getCheapestAndExpensiveFruitCost { it.fruitAndVegetablesPrices.oranges1kg!! }
            else ->  return "Please write the name of the fruit correctly, such as an apple, banana, or orange"
        }

        return "Trade ${nameFruit.replaceFirstChar { it.uppercase()}} from ${cheapestCity?.cityName} city to ${expensiveCity?.cityName} city"
    }

    private fun <T> List<T>.getCheapestAndExpensiveFruitCost(function: (T) -> Float): Pair<T?, T?> {
        val cheapestCity = this.minByOrNull(function)

        val expensiveCity = this.maxByOrNull(function)

        return Pair(cheapestCity, expensiveCity)
    }
}

