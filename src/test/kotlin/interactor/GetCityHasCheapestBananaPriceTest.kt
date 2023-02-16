package interactor

import io.mockk.*
import io.mockk.impl.annotations.MockK
import model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GetCityHasCheapestBananaPriceTest{

    @MockK
    private lateinit var cheapestBanana: GetCityHasTheCheapestBananaPriceInteractor

    @MockK
    lateinit var dataSource: CostOfLivingDataSource

    @MockK
    private lateinit var citiesWithCheapestBananaPrice: CityEntity

    @MockK
    private lateinit var citiesWithNullBananaPrice: CityEntity

    @MockK
    private lateinit var citiesWithNegativeBananaPrice: CityEntity

    @MockK
    private lateinit var oneCityWithValidBananaPrice: CityEntity


    @BeforeEach
    fun setup(){
        MockKAnnotations.init(this)

    }

    @Test
    fun should_ReturnSortedListByCheapestBananaPrices_When_TheListIsNotEmptyAndPricesIsNotZeroOrInMinus() {
        // given

         val resultList = mutableListOf<CityEntity>()

        every { citiesWithCheapestBananaPrice.cityName } returns "Gaza"
        resultList.add(citiesWithCheapestBananaPrice)

        every { citiesWithCheapestBananaPrice.cityName } returns "Cairo"
        resultList.add(citiesWithCheapestBananaPrice)

        every { citiesWithCheapestBananaPrice.cityName } returns "Baghdad"
        resultList.add(citiesWithCheapestBananaPrice)


        every { dataSource.getAllCitiesData() }returns resultList

        // when
        cheapestBanana = GetCityHasTheCheapestBananaPriceInteractor(dataSource)
        val actual = cheapestBanana.execute()

        // then
//        assertEquals(resultList, actual)
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
        cheapestBanana = GetCityHasTheCheapestBananaPriceInteractor(dataSource)
        val actual = cheapestBanana.execute()

        // then
        assertEquals(resultList, actual)
    }

    @Test
    fun should_ReturnNull_When_TheListIsEmpty() {

        // given
        every {
            dataSource.getAllCitiesData()
        }returns emptyList()

        // when
        cheapestBanana = GetCityHasTheCheapestBananaPriceInteractor(dataSource)
        val actual = cheapestBanana.execute()

        // then
        assertNull(actual)
    }

    @Test
    fun should_ReturnThrowException_When_ThePriceIsZeroOrInMinus(){
        // given


    }

}