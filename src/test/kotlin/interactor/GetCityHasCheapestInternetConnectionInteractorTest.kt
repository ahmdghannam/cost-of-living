package interactor

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import model.CityEntity
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import kotlin.test.assertEquals

class GetCityHasCheapestInternetConnectionInteractorTest {

    private lateinit var cheapestInternet: GetCityHasCheapestInternetConnectionInteractor

    @MockK
    private var dataSource: CostOfLivingDataSource

    init {
        MockKAnnotations.init(this)
        dataSource = mockk()
    }

    @Test
    fun should_ReturnLeastInternetCost_When_TheListNotEmptyAndNotNullAndTheAttributesNotNull() {
        // given list of cities

        val list= mutableListOf<CityEntity>()
        val internetCosts= listOf(10f,90f,60f,40f,50f,20f,71f,120f,45f,42f)

        for (i in 0 until 10){
            val mockkedCityObject= mockk<CityEntity>()
            every {
                mockkedCityObject.averageMonthlyNetSalaryAfterTax
            } returns 100f
            every {
                mockkedCityObject.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl
            } returns internetCosts[i]

            list.add(mockkedCityObject)
        }

        every {
            dataSource.getAllCitiesData()
        } returns list

       // CSV : comma separated values
        cheapestInternet = GetCityHasCheapestInternetConnectionInteractor(dataSource)

        // when find the minimum internet coast of this list
        val cheapestCityInInternet = cheapestInternet.execute()
        // then
        assertEquals(list[0], cheapestCityInInternet)

    }

    @Test
    fun should_ReturnFirstCity_When_JustOneCityProvided() {
        // given
        val city = mockk<CityEntity>()

        every {
            city.averageMonthlyNetSalaryAfterTax
        } returns 1f
        every {
            city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl
        } returns 100f

        val list = listOf(city)



        every {
            dataSource.getAllCitiesData()
        } returns list

        cheapestInternet = GetCityHasCheapestInternetConnectionInteractor(dataSource)

        // when find the minimum internet coast of this list
        val cheapestCityInInternet = cheapestInternet.execute()
        // then
        assertEquals(city, cheapestCityInInternet)

    }

    @Test
    fun should_ThrowException_When_TheListIsEmpty() {
        // given
        every {
            dataSource.getAllCitiesData()
        } returns listOf()

        cheapestInternet = GetCityHasCheapestInternetConnectionInteractor(dataSource)

        // when find the minimum internet coast of this list
        val cheapestCityInInternetExecutable  = Executable { cheapestInternet.execute()}
        // then
        assertThrows(Exception::class.java,cheapestCityInInternetExecutable)

    }

    @Test
    fun should_ThrowException_When_AllTheSalariesIsNull() {
        // given list of cities with null salaries
        val list= mutableListOf<CityEntity>()

        for (i in 0 until 10){
            val city= mockk<CityEntity>()
            every {
                city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl
            }returns 1f

            every {
                city.averageMonthlyNetSalaryAfterTax
            } returns null
            list.add(city)
        }

        every {
            dataSource.getAllCitiesData()
        } returns list

        cheapestInternet =  GetCityHasCheapestInternetConnectionInteractor(dataSource)


        // when find the minimum internet coast of this list
        val cheapestCityInInternetExcecutable = Executable { cheapestInternet.execute() }
        // then
        assertThrows(Exception::class.java,cheapestCityInInternetExcecutable)

    }

    @Test
    fun should_ReturnNull_When_AllTheInternetCostsIsNull() {

        // given list of cities with null internet costs
        val list= mutableListOf<CityEntity>()

        for (i in 0 until 10){
            val city= mockk<CityEntity>()
            every {
                city.averageMonthlyNetSalaryAfterTax
            }returns 1f

            every {
                city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl
            } returns null
            list.add(city)
        }

        every {
            dataSource.getAllCitiesData()
        } returns list

        cheapestInternet = GetCityHasCheapestInternetConnectionInteractor(dataSource)


        // when find the minimum internet coast of this list
        val cheapestCityInInternetExecutable = Executable {cheapestInternet.execute()}
        // then it returns null
        assertThrows(Exception::class.java,cheapestCityInInternetExecutable)

    }

    @Test
    fun should_ReturnNull_When_AllTheSalaryIsNegative() {
        // given list of negative salaries
        val list= mutableListOf<CityEntity>()

        for (i in 0 until 10){
            val city= mockk<CityEntity>()
            every {
                city.averageMonthlyNetSalaryAfterTax
            }returns -1f

            every {
                city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl
            } returns 1f
            list.add(city)
        }

        every {
            dataSource.getAllCitiesData()
        } returns list
        cheapestInternet = GetCityHasCheapestInternetConnectionInteractor(dataSource)


        // when find the minimum internet coast of this list
        val cheapestCityInInternetExecutable = Executable {  cheapestInternet.execute()}
        // then
        assertThrows(Exception::class.java,cheapestCityInInternetExecutable)

    }

    @Test
    fun should_ReturnNull_When_AllTheInternetCostsIsNegative() {
        // given a negative internet costs list

        val list= mutableListOf<CityEntity>()

        for (i in 0 until 10){
            val city= mockk<CityEntity>()
            every {
                city.averageMonthlyNetSalaryAfterTax
            }returns 1f

            every {
                city.servicesPrices.internet60MbpsOrMoreUnlimitedDataCableAdsl
            } returns -1f
            list.add(city)
        }

        every {
            dataSource.getAllCitiesData()
        } returns list
        cheapestInternet = GetCityHasCheapestInternetConnectionInteractor(dataSource)


        // when find the minimum internet coast of this list
        val cheapestCityInInternetExecutable = Executable {   cheapestInternet.execute()}
        // then
        assertThrows(Exception::class.java,cheapestCityInInternetExecutable)

    }

}