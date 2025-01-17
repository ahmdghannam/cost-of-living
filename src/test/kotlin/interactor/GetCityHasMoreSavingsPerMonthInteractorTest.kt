package interactor

import org.junit.jupiter.api.Test
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import model.CityEntity
import model.FoodPrices
import model.RealEstatesPrices
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.function.Executable


internal class GetCityHasMoreSavingsPerMonthInteractorTest {
    private lateinit var citySavings: GetCityHasMoreSavingsPerMonthInteractor

    @MockK
    private lateinit var dataSource: CostOfLivingDataSource

    @MockK
    private lateinit var cityEntityPrices: CityEntity

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        every {
            cityEntityPrices.cityName
        }returns "Gaza"

    }

    @Test
    fun should_ReturnCity_When_ApartmentAndCostFoodNotNullAndTransportationsIsNull() {

        // given city with apartment and cost food not null
        every {
            cityEntityPrices.foodPrices
        }returns FoodPrices(10f,20f,30f,40f,10f,50f)

        every {
            cityEntityPrices.realEstatesPrices
        }returns RealEstatesPrices(null,20f,30f,40f,10f,50f)

        every {
            cityEntityPrices.averageMonthlyNetSalaryAfterTax
        }returns 200f

        every {
            dataSource.getAllCitiesData()
        } returns listOf(cityEntityPrices)


        // when find the most  city in the world have more savings per month.
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
        val getCity = citySavings.execute()


        // then
        assertEquals(cityEntityPrices, getCity)


    }
    @Test
    fun should_ThrowException_When_FoodPriceNull() {

        // given city with food price is null
        every {
            cityEntityPrices.foodPrices
        } returns FoodPrices(null,20f,null,40f,10f,50f)

        every {
            dataSource.getAllCitiesData()
        } returns listOf(cityEntityPrices)


        // when find the most  city in the world have more savings per month.
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
        val savingCityExecutable = Executable { citySavings.execute()}



        // then it throws an exception
        assertThrows(Exception::class.java,savingCityExecutable)


    }
    @Test
    fun should_ThrowException_When_ApartmentNull() {

        // given city with apartment is null
        every {
            cityEntityPrices.foodPrices
        }returns  FoodPrices(10f,20f,30f,15f,25f,40f)

        every {
            cityEntityPrices.realEstatesPrices
        } returns RealEstatesPrices(10f,20f,null,null,10f,15f)

        every {
            cityEntityPrices.averageMonthlyNetSalaryAfterTax
        }returns 20f

        every {
            dataSource.getAllCitiesData()
        } returns listOf( cityEntityPrices)


        // when find the most  city in the world have more savings per month.
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
        val savingCityExecutable = Executable { citySavings.execute()}


        //  then it throws an exception
        assertThrows(Exception::class.java,savingCityExecutable)


    }

    @Test
    fun should_ThrowException_When_AverageSalaryNull() {

        // given city with average salary  null
        every {
            cityEntityPrices.foodPrices
        }returns  FoodPrices(10f,20f,30f,15f,25f,40f)

        every {
            cityEntityPrices.realEstatesPrices
        } returns RealEstatesPrices(10f,20f,10f,20f,10f,15f)

        every {
            cityEntityPrices.averageMonthlyNetSalaryAfterTax
        } returns null

        every {
            dataSource.getAllCitiesData()
        } returns listOf( cityEntityPrices)


        // when find the most  city in the world have more savings per month.
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
        val savingCityExecutable = Executable { citySavings.execute()}


        // then it throws an exception
        assertThrows(Exception::class.java,savingCityExecutable)


    }


    @Test
    fun should_ThrowException_When_FoodPriceAndApartmentAndAverageSalaryNull() {

        // given city and food price And apartment is null
        every {
            cityEntityPrices.foodPrices
        }returns  FoodPrices(null,null,null,15f,25f,40f)

        every {
            cityEntityPrices.realEstatesPrices
        } returns RealEstatesPrices(10f,20f,null,null,10f,15f)

        every {
            cityEntityPrices.averageMonthlyNetSalaryAfterTax
        } returns null


        every {
            dataSource.getAllCitiesData()
        } returns listOf( cityEntityPrices)


        // when find the most  city in the world have more savings per month.
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
        val savingCityExecutable = Executable { citySavings.execute()}


        // then it throws an exception
        assertThrows(Exception::class.java,savingCityExecutable)



    }



}



