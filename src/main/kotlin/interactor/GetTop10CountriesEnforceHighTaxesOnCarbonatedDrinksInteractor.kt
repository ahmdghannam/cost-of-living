package interactor

import model.CityEntity

class GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractor (
    private val dataSource: CostOfLivingDataSource,
){

    fun execute(limit:Int):List<Pair<String , Float?>>?{
        val list = dataSource.getAllCitiesData()

        if (list.isEmpty()) return null

        return dataSource.getAllCitiesData()
            .asSequence()
            .filter(::excludeNullPricesAndNegativePriceAndLowQualityData)
            .distinctBy { it.country }
            .sortedByDescending{it.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants}
            .take(limit)
            .map { Pair(it.country , it.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants) }
            .toList()
    }


    private fun excludeNullPricesAndNegativePriceAndLowQualityData(city: CityEntity): Boolean {
        return city.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants != null
                &&city.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants>0
                && city.dataQuality
    }



}