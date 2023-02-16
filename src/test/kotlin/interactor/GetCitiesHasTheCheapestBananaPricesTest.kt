package interactor

import io.mockk.*
import io.mockk.impl.annotations.MockK
import model.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.function.Executable

class GetCitiesHasTheCheapestBananaPricesTest{

    @MockK
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
    private lateinit var theAveragePriceCity_gaza : CityEntity
    @MockK
    private lateinit var theMostCheapestCity_baghdad: CityEntity
    @MockK
    private lateinit var theMostExpensiveCity_cairo: CityEntity

    @BeforeEach
    fun setup(){
        MockKAnnotations.init(this)
    }

    @Test
    fun should_ReturnSortedListByCheapestBananaPrices_When_TheListIsNotEmptyAndPricesIsNotZeroOrInMinus() {
        // given
        val resultList = mutableListOf<CityEntity>()
        val listOfCities = listOf("Baghdad", "Gaza", "Cairo")

        every { theAveragePriceCity_gaza.cityName } returns "Gaza"
        every { theAveragePriceCity_gaza.fruitAndVegetablesPrices.banana1kg } returns 17f
        resultList.add(theAveragePriceCity_gaza)

        every { theMostExpensiveCity_cairo.cityName } returns "Cairo"
        every { theMostExpensiveCity_cairo.fruitAndVegetablesPrices.banana1kg } returns 25f
        resultList.add(theMostExpensiveCity_cairo)

        every { theMostCheapestCity_baghdad.cityName } returns "Baghdad"
        every { theMostCheapestCity_baghdad.fruitAndVegetablesPrices.banana1kg } returns 15f
        resultList.add(theMostCheapestCity_baghdad)

        every { dataSource.getAllCitiesData() }returns resultList
        // when
        val actual = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource).execute(theAveragePriceCity_gaza,theMostExpensiveCity_cairo,theMostCheapestCity_baghdad)

        // then
        assertEquals(listOfCities, actual)
    }

    @Test
    fun should_ReturnOneCityWithValidPrice_When_TheListHasOneCity(){
        // given
        val resultList = mutableListOf<String>()

        every { oneCityWithValidBananaPrice.cityName } returns "gaza"

        every { oneCityWithValidBananaPrice.fruitAndVegetablesPrices.banana1kg } returns 30f

        resultList.add(oneCityWithValidBananaPrice.cityName)

        every { dataSource.getAllCitiesData() }returns listOf(oneCityWithValidBananaPrice)

        // when
        cheapestBanana = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
        val actual = cheapestBanana.execute(oneCityWithValidBananaPrice)

        // then
        assertEquals(resultList, actual)
    }

    @Test
    fun should_ThrowException_When_TheListIsEmpty() {

        // given
        every {
            dataSource.getAllCitiesData()
        }returns emptyList()

        // when
        cheapestBanana = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
        val actual = Executable{cheapestBanana.execute()}


        // then
        assertThrows(Exception :: class.java ,actual)
    }

    @Test
    fun should_ThrowException_When_ThePriceIsZeroOrInMinus(){
        // given

        every { cityWithZeroPrice.fruitAndVegetablesPrices.banana1kg } returns 0f
        every { cityWithNegativePrice.fruitAndVegetablesPrices.banana1kg } returns -3f

        every {
            dataSource.getAllCitiesData()
        }returns listOf(cityWithZeroPrice,cityWithNegativePrice)

        // when
        cheapestBanana = GetCitiesHasTheCheapestBananaPricesInteractor(dataSource)
        val actual = Executable{cheapestBanana.execute()}

        // then
        assertThrows(Exception :: class.java ,actual)
    }

}