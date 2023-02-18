package interactor

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import model.CarsPrices
import model.CityEntity
import model.TransportationsPrices
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class GetTheCheapestCityForTheSalaryInItAndTheCheapestCarPriceTest {

    private lateinit var citySavings: GetTheCheapestCityForTheSalaryInItAndTheCheapestCarPrice

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
    fun should_ReturnCity_When_CarsPrices_GasoLine_And_SalaryAverageMonthlyNotNull() {

        // given city with carsPrices , transportationsPrices and averageMonthlyNetSalary not null
            every {
            cityEntityPrices.carsPrices
        }returns CarsPrices(100f,200f)

        every {
            cityEntityPrices.transportationsPrices
        }returns TransportationsPrices(null,10f,30f,40f,10f,10f)

        every {
            cityEntityPrices.averageMonthlyNetSalaryAfterTax
        }returns 3900f

        every {
            cityEntityPrices.transportationsPrices
        }returns TransportationsPrices(null,null,null,null,null,5f)

        every {
            dataSource.getAllCitiesData()
        } returns listOf(cityEntityPrices)

        // When you find the largest city in the world, it has cheaper cars and gasoline.
        citySavings = GetTheCheapestCityForTheSalaryInItAndTheCheapestCarPrice(dataSource)
        val getCity = citySavings.execute(1)

        // done
        assertEquals(cityEntityPrices, getCity[0])
    }







}