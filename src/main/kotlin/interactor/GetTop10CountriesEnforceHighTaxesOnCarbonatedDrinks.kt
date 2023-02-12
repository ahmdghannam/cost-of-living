package interactor

import model.CityEntity

class GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinks (
    private val dataSource: CostOfLivingDataSource,
){
    fun excute():List<String>?{
        val list = dataSource.getAllCitiesData()
        if (list.isEmpty()) return null

        return dataSource.getAllCitiesData()
            .filter(::excludeNullPricesAndLowQualityData)
            .sortedByDescending{it.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants}
            .take(10).map { it.cityName}

    }
    private fun excludeNullPricesAndLowQualityData(city: CityEntity): Boolean {
        return city.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants != null
                && city.dataQuality
    }

}