package interactor

import model.CityEntity


class GetCitiesHasTheCheapestBananaPricesInteractor (
    private val dataSource: CostOfLivingDataSource,
    ){

    fun execute(vararg cities: CityEntity): List<String>? {
        return if (cities.isNotEmpty()){
            cities
                .filter (:: excludeNullAndNegativeBananaPrices)
                .sortedBy { it.fruitAndVegetablesPrices.banana1kg }
                .map { it.cityName }
        }else
            null
    }

    private fun excludeNullAndNegativeBananaPrices(city: CityEntity): Boolean {
        return city.fruitAndVegetablesPrices.banana1kg != null && city.fruitAndVegetablesPrices.banana1kg > 0f
    }
}