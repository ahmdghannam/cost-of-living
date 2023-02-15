package interactor

import model.CityEntity


class GetCityHasMoreSavingsPerMonthInteractor(
    private val dataSource: CostOfLivingDataSource,
) {


    fun execute(): CityEntity? {
        return dataSource.getAllCitiesData()
            .filter(::excludeNullFoodPricesAndApartmentAndSalaryAndTransportationsPricesWithNotNull)
            .maxByOrNull(::countSavingsPerMonth)
    }


}


private fun excludeNullFoodPricesAndApartmentAndSalaryAndTransportationsPricesWithNotNull(city: CityEntity): Boolean {
    return     city.foodPrices.loafOfFreshWhiteBread500g != null
            && city.foodPrices.localCheese1kg != null
            && city.foodPrices.beefRound1kgOrEquivalentBackLegRedMeat != null
            && city.foodPrices.chickenFillets1kg != null
            && city.foodPrices.riceWhite1kg != null
            && city.realEstatesPrices.apartment3BedroomsInCityCentre != null
            && city.realEstatesPrices.apartment3BedroomsOutsideOfCentre != null
            && city.transportationsPrices.gasolineOneLiter == null
            && city.transportationsPrices.monthlyPassRegularPrice == null
            && city.transportationsPrices.taxi1kmNormalTariff == null
            && city.transportationsPrices.taxi1hourWaitingNormalTariff == null
            && city.transportationsPrices.oneWayTicketLocalTransport == null
            && city.transportationsPrices.taxiStartNormalTariff == null
            && city.averageMonthlyNetSalaryAfterTax != null


}


private fun countSavingsPerMonth(city: CityEntity): Float {
    val salaryMonth: Float = city.averageMonthlyNetSalaryAfterTax!! * 2

    val foodPricesAndApartment : Float =
        city.foodPrices.loafOfFreshWhiteBread500g!! * 30 +
        city.foodPrices.localCheese1kg!! +
        city.foodPrices.beefRound1kgOrEquivalentBackLegRedMeat!! * 4 +
        city.foodPrices.chickenFillets1kg!! * 10 +
        city.foodPrices.riceWhite1kg!! * 2 +
        city.realEstatesPrices.apartment3BedroomsInCityCentre!!

    val otherNeeds : Float = 250f

    return salaryMonth - foodPricesAndApartment - otherNeeds

}











