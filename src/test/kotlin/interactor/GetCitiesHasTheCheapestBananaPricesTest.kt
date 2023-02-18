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
        val resultList = mutableListOf<CityEntity>()
        val listOfCities = listOf("Baghdad", "Gaza", "Cairo")

        every { theAveragePriceCityGaza.cityName } returns "Gaza"
        every { theAveragePriceCityGaza.fruitAndVegetablesPrices.banana1kg } returns 17f
        resultList.add(theAveragePriceCityGaza)

        every { theMostExpensiveCityCairo.cityName } returns "Cairo"
        every { theMostExpensiveCityCairo.fruitAndVegetablesPrices.banana1kg } returns 25f
        resultList.add(theMostExpensiveCityCairo)

        every { theCheapestCityBaghdad.cityName } returns "Baghdad"
        every { theCheapestCityBaghdad.fruitAndVegetablesPrices.banana1kg } returns 15f
        resultList.add(theCheapestCityBaghdad)

        every { dataSource.getAllCitiesData() }returns resultList

        // when the cities have a valid prices its sorting by banana prices
        cheapestBanana = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
        val actual = cheapestBanana.execute( listOf(theAveragePriceCityGaza.cityName,
                                                    theMostExpensiveCityCairo.cityName,
                                                    theCheapestCityBaghdad.cityName,))

        // then it returns sorted list of cities names by cheapest banana prices
        assertEquals(listOfCities, actual)
    }

    @Test
    fun should_ReturnOneCityWithValidPrice_When_TheListHasOneCity(){

        // given one city with valid price
        val resultList = mutableListOf<String>()

        every { oneCityWithValidBananaPrice.cityName } returns "gaza"

        every { oneCityWithValidBananaPrice.fruitAndVegetablesPrices.banana1kg } returns 30f

        resultList.add(oneCityWithValidBananaPrice.cityName)

        every { dataSource.getAllCitiesData() }returns listOf(oneCityWithValidBananaPrice)

        // when the city has a valid price its sorting by banana prices
        cheapestBanana = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
        val actual = cheapestBanana.execute(listOf(oneCityWithValidBananaPrice.cityName))

        // then it returns the name of this city
        assertEquals(resultList, actual)
    }

    @Test
    fun should_ReturnEmptyList_When_TheListIsEmpty() {

        // given an empty list
        every {
            dataSource.getAllCitiesData()
        }returns emptyList()

        // when the list haven't any city
        cheapestBanana = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
        val actual = cheapestBanana.execute()

        // then it returns an empty list
        assertEquals(listOf<String>(),actual)
    }

    @Test
    fun should_ReturnEmptyList_When_ThePriceIsZeroOrInMinus(){

        // given a list with Invalid prices
        every { cityWithZeroPrice.fruitAndVegetablesPrices.banana1kg } returns 0f
        every { cityWithNegativePrice.fruitAndVegetablesPrices.banana1kg } returns -3f

        every {
            dataSource.getAllCitiesData()
        }returns listOf(cityWithZeroPrice,cityWithNegativePrice)

        // when the cities have an Invalid prices It will be excluded from the list
        cheapestBanana = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
        val actual = cheapestBanana.execute()

        // then it returns an empty list
        assertEquals(listOf<String>() ,actual)
    }
}