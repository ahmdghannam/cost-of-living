package interactor

import model.CityEntity

class FindTheCheapestTenCitiesApartments(
    private val dataSource: CostOfLivingDataSource
) {

    fun execute(limit: Int): Map<String, Double> {
        return dataSource
            .getAllCitiesData()
            .filter(::excludeNullAndNegativeSalariesAndApartmentsCostsAndLowQualityData)
            .sortedBy(::sortCondition)
            .take(limit)
            .associate(::pairOfCityNameAndNumberOfYearsNeededToBuyAnApartment)
    }


    private fun sortCondition(city: CityEntity): Float {
        with(city) {
            return realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre!!
                .div(averageMonthlyNetSalaryAfterTax!!)
        }
    }

    private fun pairOfCityNameAndNumberOfYearsNeededToBuyAnApartment(city: CityEntity): Pair<String, Double> {
        val numberOfYears: Double
        city.apply {
            val costOf100mApartment = realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre!! * 100
            val yearSalary = averageMonthlyNetSalaryAfterTax!! * 12
            numberOfYears = costOf100mApartment / yearSalary.toDouble()
        }

        return city.cityName to numberOfYears
    }

    private fun excludeNullAndNegativeSalariesAndApartmentsCostsAndLowQualityData(city: CityEntity): Boolean {
        return excludeNullSalariesOrNullApartmentsCosts(city) &&
                excludeNegativeSalariesOrNegativeInternetCosts(city) &&
                excludeLowQualityData(city)
    }

    private fun excludeLowQualityData(city: CityEntity): Boolean {
        return city.dataQuality
    }

    private fun excludeNullSalariesOrNullApartmentsCosts(city: CityEntity): Boolean {
        with(city) {
            return realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre != null
                    && averageMonthlyNetSalaryAfterTax != null
        }
    }

    private fun excludeNegativeSalariesOrNegativeInternetCosts(city: CityEntity): Boolean {
        with(city) {
            return averageMonthlyNetSalaryAfterTax!! >= 0
                    && realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre!! >= 0
        }
    }

}