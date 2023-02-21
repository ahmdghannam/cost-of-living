package interactor

import model.CityEntity
import java.lang.Exception

class GetSalariesOfCountryCitiesInteractor(
    private val dataSource: CostOfLivingDataSource,
) {
    fun execute(countryName: String): Map<String, Float> {
        return dataSource.getAllCitiesData()
            .filter {
                it.country.equals(countryName, ignoreCase = true)
                        && excludeNullSalariesAndLowQualityData(it)
            }
            .associate { it.cityName to it.averageMonthlyNetSalaryAfterTax!! }
            .takeIf { it.isNotEmpty() } ?: throw IllegalArgumentException("Wrong Country Name")
    }

    private fun excludeNullSalariesAndLowQualityData(city: CityEntity): Boolean {
        return city.dataQuality && city.averageMonthlyNetSalaryAfterTax != null
    }
}