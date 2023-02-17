package interactor

import model.CityEntity
import model.MealsPrices
import java.lang.Exception

class GetCityThatHasAverageMealsPricesInteractor(private val dataSource: CostOfLivingDataSource) {
    fun execute(selectedCountryNames: List<String>): CityEntity {
        require(selectedCountryNames.isNotEmpty()) { "selected country names must not be empty" } // add tests for this
        return dataSource.getAllCitiesData()
            .filter { excludeCitiesNotInTheSelectedCountriesAndCitiesWithInvalidMealsPrices(it, selectedCountryNames) }
            .ifEmpty { emptyList() }
            .run {
                minByOrNull { calculateAverageMealsPrices(it) - getAverageMealPriceExactlyBetweenMinAndMax() }
                    ?: throw Exception("couldn't find a city that matches the manager expectations")
            }
    }

    private fun excludeCitiesNotInTheSelectedCountriesAndCitiesWithInvalidMealsPrices(
        city: CityEntity,
        selectedCountryNames: List<String>,
    ): Boolean {
        return isCityInSelectedCountries(city, selectedCountryNames) && city.mealsPrices.isValid()
    }

    private fun isCityInSelectedCountries(city: CityEntity, selectedCountryNames: List<String>): Boolean {
        return selectedCountryNames.any { it.lowercase() == city.country.lowercase() }
    }

    private fun calculateAverageMealsPrices(city: CityEntity): Float {
        with(city.mealsPrices) {
            return (mealInexpensiveRestaurant!! + mealFor2PeopleMidRangeRestaurant!! + mealAtMcDonaldSOrEquivalent!!) / 3f
        }
    }

    private fun MealsPrices.isValid(): Boolean {
        return mealInexpensiveRestaurant != null && mealInexpensiveRestaurant > 0f
                && mealFor2PeopleMidRangeRestaurant != null && mealFor2PeopleMidRangeRestaurant > 0f
                && mealAtMcDonaldSOrEquivalent != null && mealAtMcDonaldSOrEquivalent > 0f
    }

    private fun List<CityEntity>.getAverageMealPriceExactlyBetweenMinAndMax(): Float {
        val minPrice = minOf { calculateAverageMealsPrices(it) }
        val maxPrice = maxOf { calculateAverageMealsPrices(it) }
        return (maxPrice + minPrice) / 2
    }
}