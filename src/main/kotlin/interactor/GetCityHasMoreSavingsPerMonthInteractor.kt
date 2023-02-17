package interactor

import model.CityEntity
import model.FoodPrices
import model.RealEstatesPrices


class GetCityHasMoreSavingsPerMonthInteractor(
    private val dataSource: CostOfLivingDataSource,
) {

    fun execute(): CityEntity {
        return dataSource
            .getAllCitiesData()
            .filter(::excludeNullFoodPricesAndApartmentAndSalary)
            .maxByOrNull(::countSavingsPerMonth)?: throw  Exception("There is no city with the highest savings")
    }
}


private fun excludeNullFoodPricesAndApartmentAndSalary(city: CityEntity): Boolean {
    return excludeNullFoodPrices(city.foodPrices) &&
            excludeNullApartmentPriceAndSalary( city.realEstatesPrices,
                city.averageMonthlyNetSalaryAfterTax )
}

private fun excludeNullFoodPrices(foodPrices: FoodPrices): Boolean {
    return foodPrices.let {
                it.localCheese1kg != null &&
                it.loafOfFreshWhiteBread500g != null &&
                it.beefRound1kgOrEquivalentBackLegRedMeat != null &&
                it.chickenFillets1kg != null &&
                it.riceWhite1kg != null
    }

}

private fun excludeNullApartmentPriceAndSalary(realEstatesPrices: RealEstatesPrices, averageSalary: Float?): Boolean {
    return realEstatesPrices.apartment3BedroomsInCityCentre != null &&
            averageSalary != null
}

private fun countSavingsPerMonth(city: CityEntity): Float {
    val whiteBreadConsumptionByKG = 30
    val beefMeatConsumptionByKG = 4
    val chickenFilletsConsumptionByKG = 10
    val riceWhiteConsumptionByKG = 2
    val doubleSalary = 2

    val salary = (city.averageMonthlyNetSalaryAfterTax ?: 0f) * doubleSalary

    val foodPerMonth = with(city.foodPrices) {
                (loafOfFreshWhiteBread500g ?: 0f) * whiteBreadConsumptionByKG +
                (localCheese1kg ?: 0f) +
                (beefRound1kgOrEquivalentBackLegRedMeat ?: 0f) * beefMeatConsumptionByKG +
                (chickenFillets1kg ?: 0f) * chickenFilletsConsumptionByKG +
                (riceWhite1kg ?: 0f) * riceWhiteConsumptionByKG
    }

    val apartmentPrice = city.realEstatesPrices.apartment3BedroomsInCityCentre ?: 0f
    val otherNeedsPerMonth = 250f

    return (salary - foodPerMonth - apartmentPrice - otherNeedsPerMonth).takeIf { it > 0f } ?: 0f
}












