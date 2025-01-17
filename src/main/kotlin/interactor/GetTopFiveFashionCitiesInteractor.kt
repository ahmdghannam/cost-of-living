package interactor

import model.CityEntity
import model.ClothesPrices
import kotlin.math.max
import kotlin.math.min

class GetTopFiveFashionCitiesInteractor(
    private val dataSource: CostOfLivingDataSource,
) {
/**
 * @author: Bilal Alkhatib
 * @see: isValidData()
         is a boolean function that takes a CityEntity object as an argument and checks whether the object's
         clothesPrices property has valid values for certain items of clothing.
 * @see: getClothesPrice()
         is a float function that takes a ClothesPrices object as an argument and calculates the total
         price of four items of clothing.
 */

    fun execute(limit: Int): List<String> {
        val allCitiesData = dataSource.getAllCitiesData()
        return allCitiesData
            .ifEmpty { throw Exception("the cities list is empty") }
            .filter (::isValidData ) //before: it.clothesPrices.onePairOfJeansLevis50oneOrSimilar != null | after fixed: I used isValidData function
            .sortedByDescending (::getClothesPrice)//before: it.clothesPrices.onePairOfJeansLevis50oneOrSimilar!! | after fixed: I used getClothesPrice function
            .take(min(max(limit,0), allCitiesData.size))
            .map { it.cityName }

    }

    private fun isValidData(city: CityEntity): Boolean {
        return city.clothesPrices.onePairOfJeansLevis50oneOrSimilar != null &&
                city.clothesPrices.onePairOfMenLeatherBusinessShoes != null &&
                city.clothesPrices.onePairOfNikeRunningShoesMidRange != null &&
                city.clothesPrices.oneSummerDressInAChainStoreZaraHAndM != null
    }

    private fun getClothesPrice(city: CityEntity):Float{
        return city.clothesPrices.onePairOfJeansLevis50oneOrSimilar!! +
               city.clothesPrices.onePairOfMenLeatherBusinessShoes!! +
               city.clothesPrices.oneSummerDressInAChainStoreZaraHAndM!! +
               city.clothesPrices.onePairOfNikeRunningShoesMidRange!!
    }
}