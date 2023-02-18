package interactor

import model.CityEntity


class GetCitiesHasTheCheapestBananaPricesInteractor ( private val dataSource: CostOfLivingDataSource ){


    fun execute(vararg cities: List<String>): List<String> {

        return dataSource
                .getAllCitiesData()
                .filter {excludeNullAndNegativeBananaPrices(it) && it.cityName in cities.toList().toString()}
                .sortedBy { it.fruitAndVegetablesPrices.banana1kg }
                .map { it.cityName }
    }

    private fun excludeNullAndNegativeBananaPrices(city: CityEntity): Boolean {
        return city.fruitAndVegetablesPrices.banana1kg != null
                && city.fruitAndVegetablesPrices.banana1kg > 0f
    }
}