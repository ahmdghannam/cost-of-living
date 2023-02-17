package interactor

import model.CityEntity

class GetMostExpensiveCityForFruitTradeInteractor(
    private val dataSource: CostOfLivingDataSource,
) {
    fun execute(nameFruit: String): String {
        val listOfCities =  dataSource
                     .getAllCitiesData()
                     .filter(::excludeNullFruitPrices)

        return getMinMaxCities(nameFruit,listOfCities)
    }
    private fun excludeNullFruitPrices(city: CityEntity): Boolean {
        return city.fruitAndVegetablesPrices.let {
                    it.apples1kg != null &&
                    it.banana1kg != null &&
                    it.oranges1kg != null
        }

    }
    private fun getMinMaxCities(nameFruit: String, cities: List<CityEntity>) : String {
        return when (nameFruit.lowercase().trim()) {
                   "apple" -> "Apple from ${cities.minByOrNull { it.fruitAndVegetablesPrices.apples1kg!! }!!.cityName} " +
                      "to ${cities.maxByOrNull { it.fruitAndVegetablesPrices.apples1kg!! }!!.cityName}"

                   "banana" -> "Banana from ${cities.minByOrNull { it.fruitAndVegetablesPrices.banana1kg!! }!!.cityName} " +
                      "to ${cities.maxByOrNull { it.fruitAndVegetablesPrices.banana1kg!! }!!.cityName}"

                   "orange" -> "Orange from ${cities.minByOrNull { it.fruitAndVegetablesPrices.oranges1kg!! }!!.cityName} " +
                      "to ${cities.maxByOrNull { it.fruitAndVegetablesPrices.oranges1kg!! }!!.cityName}"

            else -> "Please write the name of the fruit correctly, such as an apple, banana, or orange"
        }
    }
}

