package interactor

import model.CityEntity

class GetCityHasCheapestInternetConnectionInteractor(
    private val dataSource: CostOfLivingDataSource,
) {

    fun execute(): CityEntity? {
        return dataSource
            .getAllCitiesData()
            .filter(::excludeNullAndNegativeSalariesAndInternetCosts)
            .minByOrNull {
                it.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl!!.div(it.averageMonthlyNetSalaryAfterTax!!)
            }
    }

    private fun excludeNullAndNegativeSalariesAndInternetCosts(city: CityEntity): Boolean {
        return excludeNullSalariesOrNullInternetCosts(city) &&
                excludeNegativeSalariesOrNegativeInternetCosts(city)
    }

    private fun excludeNullSalariesOrNullInternetCosts(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null && city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl != null
    }

    private fun excludeNegativeSalariesOrNegativeInternetCosts(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax!! >= 0 && city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl!! >= 0
    }

}