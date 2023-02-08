package interactor

import model.CityEntity

class GetCityHasCheapestInternetConnectionInteractor(
    private val dataSource: CostOfLivingDataSource,
) {

    fun execute(): CityEntity? {
        val list = dataSource.getAllCitiesData()
        if (list.isEmpty()) return null
        return dataSource
            .getAllCitiesData()
            .filter(::excludeNullSalaries)
            .sortedBy { it.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl?.div(it.averageMonthlyNetSalaryAfterTax!!) }
            .first()
    }

    private fun excludeNullSalaries(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null
    }


}