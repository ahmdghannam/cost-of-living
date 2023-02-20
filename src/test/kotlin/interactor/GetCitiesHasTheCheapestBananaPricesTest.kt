package interactor

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import model.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

class GetCitiesHasTheCheapestBananaPricesTest{

    private lateinit var cheapestBanana: GetCitiesHasTheCheapestBananaPricesInteractor

    @MockK
    lateinit var dataSource: CostOfLivingDataSource
    @MockK
    private lateinit var oneCityWithValidBananaPrice: CityEntity
    @MockK
    private lateinit var cityWithZeroPrice: CityEntity
    @MockK
    private lateinit var cityWithNanPrice: CityEntity
    @MockK
    private lateinit var cityWithNegativePrice: CityEntity
    @MockK
    private lateinit var theAveragePriceCityGaza : CityEntity
    @MockK
    private lateinit var theCheapestCityBaghdad: CityEntity
    @MockK
    private lateinit var theMostExpensiveCityCairo: CityEntity

    @BeforeEach
    fun setup(){
        MockKAnnotations.init(this)
    }

    @Test
    fun should_ReturnSortedListByCheapestBananaPrices_When_TheListIsNotEmptyAndPricesIsNotZeroOrInMinus() {

        // given list of cities with valid prices
        val citiesList = mutableListOf<CityEntity>()

        every { theAveragePriceCityGaza.cityName } returns "Gaza"
        every { theAveragePriceCityGaza.fruitAndVegetablesPrices.banana1kg } returns 20f
        citiesList.add(theAveragePriceCityGaza)

        every { theMostExpensiveCityCairo.cityName } returns "Cairo"
        every { theMostExpensiveCityCairo.fruitAndVegetablesPrices.banana1kg } returns 25f
        citiesList.add(theMostExpensiveCityCairo)

        every { theCheapestCityBaghdad.cityName } returns "Baghdad"
        every { theCheapestCityBaghdad.fruitAndVegetablesPrices.banana1kg } returns 15f
        citiesList.add(theCheapestCityBaghdad)

        every { dataSource.getAllCitiesData() }returns citiesList

        // when the cities have a valid prices its sorting by banana prices
        cheapestBanana = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
        val actual = cheapestBanana.execute( listOf(", ${theAveragePriceCityGaza.cityName}, " +
                                                      "${theMostExpensiveCityCairo.cityName}, " +
                                                      "${theCheapestCityBaghdad.cityName}, "))

        // then it returns sorted list of cities names by cheapest banana prices
        assertEquals(listOf("Baghdad", "Gaza", "Cairo"), actual)
    }

    @Test
    fun should_ReturnSortedListByCheapestBananaPrices_When_TheListIsNotEmptyAndPricesIsValidOrInvalid() {

        // given list of cities with valid prices
        val citiesList = mutableListOf<CityEntity>()

        every { theAveragePriceCityGaza.cityName } returns "Gaza"
        every { theAveragePriceCityGaza.fruitAndVegetablesPrices.banana1kg } returns 20f
        citiesList.add(theAveragePriceCityGaza)

        every { theMostExpensiveCityCairo.cityName } returns "Cairo"
        every { theMostExpensiveCityCairo.fruitAndVegetablesPrices.banana1kg } returns 25f
        citiesList.add(theMostExpensiveCityCairo)

        every { cityWithNanPrice.cityName } returns "Rafah"
        every { cityWithNanPrice.fruitAndVegetablesPrices.banana1kg } returns Float.NaN
        citiesList.add(cityWithNanPrice)

        every { cityWithNegativePrice.cityName } returns "Hebron"
        every { cityWithNegativePrice.fruitAndVegetablesPrices.banana1kg } returns -10f
        citiesList.add(cityWithNegativePrice)

        every { theCheapestCityBaghdad.cityName } returns "Baghdad"
        every { theCheapestCityBaghdad.fruitAndVegetablesPrices.banana1kg } returns 15f
        citiesList.add(theCheapestCityBaghdad)

        every { dataSource.getAllCitiesData() }returns citiesList

        // when the cities have a valid prices its sorting by banana prices
        cheapestBanana = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
        val actual = cheapestBanana.execute( listOf(", ${theAveragePriceCityGaza.cityName}, " +
                                                      "${theMostExpensiveCityCairo.cityName}, " +
                                                      "${cityWithNanPrice.cityName}, " +
                                                      "${cityWithNegativePrice.cityName}, " +
                                                      "${theCheapestCityBaghdad.cityName}, "))

        // then it returns sorted list of cities names by cheapest banana prices
        assertEquals(listOf("Baghdad", "Gaza", "Cairo"), actual)
    }

    @Test
    fun should_ReturnOneCityWithValidPrice_When_TheListHasOneCity(){

        // given one city with valid price
        val citiesList = mutableListOf<CityEntity>()

        every { oneCityWithValidBananaPrice.cityName } returns "gaza"

        every { oneCityWithValidBananaPrice.fruitAndVegetablesPrices.banana1kg } returns 30f

        citiesList.add(oneCityWithValidBananaPrice)

        every { dataSource.getAllCitiesData() }returns citiesList

        // when the city has a valid price its sorting by banana prices
        cheapestBanana = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
        val actual = cheapestBanana.execute( listOf(", ${oneCityWithValidBananaPrice.cityName}, "))

        // then it returns the name of this city
        assertEquals(listOf("gaza"), actual)
    }

    @Test
    fun should_ReturnEmptyList_When_TheListIsEmpty() {

        // given an empty list
        every {
            dataSource.getAllCitiesData()
        }returns emptyList()

        // when the list haven't any city
        cheapestBanana = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
        val actual = cheapestBanana.execute(listOf())

        // then it returns an empty list
        assertEquals(listOf<String>(),actual)
    }

    @Test
    fun should_ReturnEmptyList_When_ThePriceIsZeroOrInMinus(){

        // given a list with Invalid prices
        every { cityWithZeroPrice.cityName } returns "Rafah"
        every { cityWithZeroPrice.fruitAndVegetablesPrices.banana1kg } returns 0f

        every { cityWithNegativePrice.cityName } returns "KhanYunis"
        every { cityWithNegativePrice.fruitAndVegetablesPrices.banana1kg } returns -3f

        every {
            dataSource.getAllCitiesData()
        }returns listOf(cityWithZeroPrice,cityWithNegativePrice)

        // when the cities have an Invalid prices It will be excluded from the list
        cheapestBanana = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
        val actual = cheapestBanana.execute(listOf(cityWithZeroPrice.cityName,
                                                   cityWithNegativePrice.cityName))

        // then it returns an empty list
        assertEquals(listOf<String>() ,actual)
    }
}