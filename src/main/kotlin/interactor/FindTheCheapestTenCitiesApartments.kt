package interactor

import model.CityEntity

class FindTheCheapestTenCitiesApartments(
    private val dataSource: CostOfLivingDataSource
) {

    fun execute(limit:Int): Map<String, Double> {
        return dataSource
            .getAllCitiesData()
            .filter(::excludeNullAndNegativeSalariesAndApartmentsCostsAndLowQualityData)
            .sortedBy {
                it.realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre!!
                    .div(it.averageMonthlyNetSalaryAfterTax!!)
            }
            .take(limit)
            .associate {city ->
                val costOf100mApartment=city.realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre!!.times(100)
                val yearSalary=city.averageMonthlyNetSalaryAfterTax!!*12
                val numberOfYears=costOf100mApartment/yearSalary.toDouble()
                city.cityName to numberOfYears
            }
    }



    private fun excludeNullAndNegativeSalariesAndApartmentsCostsAndLowQualityData(city: CityEntity):Boolean{
        return excludeNullSalariesOrNullApartmentsCosts(city)&&
                excludeNegativeSalariesOrNegativeInternetCosts(city)&&
                excludeLowQualityData(city)
    }
    private fun excludeLowQualityData(city: CityEntity):Boolean{
        return city.dataQuality
    }
    private fun excludeNullSalariesOrNullApartmentsCosts(city: CityEntity): Boolean {
        return city.realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre != null
                && city.averageMonthlyNetSalaryAfterTax != null
    }

    private fun excludeNegativeSalariesOrNegativeInternetCosts(city: CityEntity): Boolean {

        return city.averageMonthlyNetSalaryAfterTax!! >= 0
                && city.realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre!! >= 0
    }

}