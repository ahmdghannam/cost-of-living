






package interactor

import model.CityEntity
import model.FruitAndVegetablesPrices


class GetAvgFruitNVeggieInteractor(
    private val dataSource: CostOfLivingDataSource,
) {

    fun execute(limit: Int): List<CityEntity> {
        return dataSource.getAllCitiesData()
            .filter(::nullAndNegativeExclution)
            .sortedBy(::avgPricesOverAvgSalary)
            .take(limit)
    }

    private fun avgPricesOverAvgSalary(city: CityEntity): Float{
        return fruitsAndVegetablesPrice(city.fruitAndVegetablesPrices
            , city.averageMonthlyNetSalaryAfterTax!!)
    }
    private fun nullAndNegativeExclution(city: CityEntity):Boolean{
        return excludeNullSalariesAndFruitsVegPrices(city)
                && excludeNegativeSalariesAndFruitsVegPrices(city)
    }
    private fun excludeNullSalariesAndFruitsVegPrices(city: CityEntity): Boolean {
        with(city.fruitAndVegetablesPrices) {
            return apples1kg != null &&
                    banana1kg != null &&
                    oranges1kg != null &&
                    tomato1kg != null &&
                    potato1kg != null &&
                    onion1kg != null &&
                    lettuceOneHead != null &&
                    city.averageMonthlyNetSalaryAfterTax != null
        }
    }
    private fun excludeNegativeSalariesAndFruitsVegPrices(city: CityEntity): Boolean {
        with(city.fruitAndVegetablesPrices) {
            return apples1kg!! >= 0 &&
                    banana1kg!! >= 0 &&
                    oranges1kg!! >= 0 &&
                    tomato1kg!! >= 0 &&
                    potato1kg!! >= 0 &&
                    onion1kg!! >= 0 &&
                    lettuceOneHead!! >= 0 &&
                    city.averageMonthlyNetSalaryAfterTax!! > 0

        }
    }

    private fun fruitsAndVegetablesPrice(
        fruitAndVegetablesPrices: FruitAndVegetablesPrices,
        averageMonthlyNetSalaryAfterTax: Float?): Float {
        val count= 7.0f
        fruitAndVegetablesPrices.run{
            return ((apples1kg!! + banana1kg!! +  oranges1kg!! + tomato1kg!!
                    + potato1kg!! + onion1kg!! + lettuceOneHead!!)/count)/averageMonthlyNetSalaryAfterTax!!
        }
    }
}


