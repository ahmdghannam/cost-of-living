package interactor

import model.CityEntity


class GetCitiesHasTheCheapestBananaPricesInteractor (
    private val dataSource: CostOfLivingDataSource ){


    fun execute(vararg cities: String): List<String> {

        return dataSource
                .getAllCitiesData()
                .filter {excludeNullAndNegativeBananaPrices(it)
                        && cities.any{ cityName -> it.cityName.equals(cityName, true) }}
                .sortedBy { it.fruitAndVegetablesPrices.banana1kg }
                .map { it.cityName }
    }

    private fun excludeNullAndNegativeBananaPrices(city: CityEntity): Boolean {
        return with(city) {
                       fruitAndVegetablesPrices.banana1kg != null
                    && fruitAndVegetablesPrices.banana1kg > 0f
        }
    }
}