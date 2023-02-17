package interactor

import model.CityEntity

class GetMostExpensiveCityForFruitTradeInteractor(
    private val dataSource: CostOfLivingDataSource,
) {
    fun execute(nameFruit: String?): String {
        val cities = dataSource
            .getAllCitiesData()
            .filter(::excludeNullFoodPrices)
        return when (nameFruit!!.toLowerCase().trim()) {
            "apple" -> "Apple from ${cities.minByOrNull { it.fruitAndVegetablesPrices.apples1kg!! }!!.cityName} " +
                    "to ${cities.maxByOrNull { it.fruitAndVegetablesPrices.apples1kg!! }!!.cityName}"

            "banana" -> "Banana from ${cities.minByOrNull { it.fruitAndVegetablesPrices.banana1kg!! }!!.cityName} " +
                    "to ${cities.maxByOrNull { it.fruitAndVegetablesPrices.banana1kg!! }!!.cityName}"

            "orange" -> "Orange from ${cities.minByOrNull { it.fruitAndVegetablesPrices.oranges1kg!! }!!.cityName} " +
                    "to ${cities.maxByOrNull { it.fruitAndVegetablesPrices.oranges1kg!! }!!.cityName}"

            else -> "Please write the name of the fruit correctly, such as an apple, banana, or orange"
        }
    }

    private fun excludeNullFoodPrices(city: CityEntity): Boolean {
        return city.fruitAndVegetablesPrices.let {
                    it.apples1kg != null &&
                    it.banana1kg != null &&
                    it.oranges1kg != null
        }

    }
}

