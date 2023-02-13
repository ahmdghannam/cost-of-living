package interactor

import model.*

class GetCityHasTheCheapestBananaPriceInteractor (
    private val dataSource: CostOfLivingDataSource,
    ){

    fun sortCitiesByBananaPrice (vararg city: CityEntity): List<String>{
        return emptyList()
    }

}