package interactor

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import model.CityEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FindTheCheapestTenCitiesApartmentsTest {

    private lateinit var cheapestApartments: FindTheCheapestTenCitiesApartments

    @MockK
    private var dataSource: CostOfLivingDataSource
    init {
        MockKAnnotations.init(this)
        dataSource = mockk()
    }

    @Test
    fun should_ReturnLeastCostAppartemnts_WhenNoNullOrNegativeInAllValues(){
        // given valid cities list
        val list= mutableListOf<CityEntity>()

        for (i in 0 until 100){
            val city= mockk<CityEntity>()
            with(city){
                every {
                    realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre
                } returns i *1f
                every {
                    averageMonthlyNetSalaryAfterTax
                } returns 100f
                every {
                    dataQuality
                } returns true
                every {
                    cityName
                } returns "$i"
            }
            list.add(city)
        }
        every {
            dataSource.getAllCitiesData()
        } returns list
        cheapestApartments=FindTheCheapestTenCitiesApartments(dataSource)

        val expectedMap= HashMap<String,Double>()

        for (i in 0 until 10){
            val city=list[i]
            val costOf100mAppartment=city.realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre!!.times(100)
            val yearSalary=city.averageMonthlyNetSalaryAfterTax!!*12
            val numberOfYears=costOf100mAppartment/yearSalary.toDouble()
            expectedMap["$i"] = numberOfYears
        }

        //when find the least cost cities for appartments
        val listOfCheapestTenCititesAppartements=cheapestApartments.execute(10)

        // then return the expected map
        assertEquals(expectedMap,listOfCheapestTenCititesAppartements)
    }
    @Test
    fun should_ReturnEmptyMap_WhenListIsEmpty() {
        // given empty list
        every {
            dataSource.getAllCitiesData()
        } returns emptyList()

        cheapestApartments=FindTheCheapestTenCitiesApartments(dataSource)

        //when find the least cost cities for appartments
        val mapOfCheapestTenCititesAppartements=cheapestApartments.execute(10)

        // then returns empty map
        assertTrue(mapOfCheapestTenCititesAppartements.isEmpty())
    }

    @Test
    fun should_ReturnEmptyMap_WhenAllPricesPerSquareMeterToBuyApartmentOutsideOfCentreIsNegative() {

        // given negative price of apartments list
        val list= mutableListOf<CityEntity>()

        for (i in 0 until 10){
            val city= mockk<CityEntity>()

            with(city) {
                every {
                    realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre
                } returns -1f
                every {
                    averageMonthlyNetSalaryAfterTax
                } returns 1f
                every {
                    cityName
                } returns ""
            }
            list.add(city)
        }

        every {
            dataSource.getAllCitiesData()
        } returns list

        cheapestApartments=FindTheCheapestTenCitiesApartments(dataSource)

        //when find the least cost cities for appartments
        val mapOfCheapestTenCititesAppartements=cheapestApartments.execute(10)

        // then returns empty map
        assertTrue(mapOfCheapestTenCititesAppartements.isEmpty())
    }

    @Test
    fun should_ReturnEmptyMap_WhenAllSalariesIsNegative() {

        // given negative salaries cities list
        val list= mutableListOf<CityEntity>()

        for (i in 0 until 10){
            val city= mockk<CityEntity>()
            with(city) {
                every {
                    realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre
                } returns 1f
                every {
                    averageMonthlyNetSalaryAfterTax
                } returns -1f
                every {
                    cityName
                } returns ""
            }
            list.add(city)
        }

        every {
            dataSource.getAllCitiesData()
        } returns list

        cheapestApartments=FindTheCheapestTenCitiesApartments(dataSource)

        //when find the least cost cities for appartments
        val mapOfCheapestTenCititesAppartements=cheapestApartments.execute(10)

        // then return empty map
        assertTrue(mapOfCheapestTenCititesAppartements.isEmpty())
    }

    @Test
    fun should_ReturnEmptyMap_WhenAllPricesPerSquareMeterToBuyApartmentOutsideOfCentreIsNull() {

        // given null price of apartments list

        val list= mutableListOf<CityEntity>()

        for (i in 0 until 10){
            val city= mockk<CityEntity>()
            with(city) {
                every {
                    realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre
                } returns null
                every {
                    averageMonthlyNetSalaryAfterTax
                } returns 1f
                every {
                    cityName
                } returns ""
            }
            list.add(city)
        }

        every {
            dataSource.getAllCitiesData()
        } returns list

        cheapestApartments=FindTheCheapestTenCitiesApartments(dataSource)

        //when find the least cost cities for appartments
        val mapOfCheapestTenCititesAppartements=cheapestApartments.execute(10)

        // then return empty map
        assertTrue(mapOfCheapestTenCititesAppartements.isEmpty())
    }

    @Test
    fun should_ReturnEmptyMap_WhenAllSalariesIsNull() {

        // given null salaries list
        val list= mutableListOf<CityEntity>()

        for (i in 0 until 10){
            val city= mockk<CityEntity>()
            with(city) {
                every {
                    realEstatesPrices.pricePerSquareMeterToBuyApartmentOutsideOfCentre
                } returns 1f
                every {
                    averageMonthlyNetSalaryAfterTax
                } returns null
                every {
                    cityName
                } returns ""
            }
            list.add(city)
        }

        every {
            dataSource.getAllCitiesData()
        } returns list

        cheapestApartments=FindTheCheapestTenCitiesApartments(dataSource)

        //when find the least cost cities for appartments
        val mapOfCheapestTenCititesAppartements=cheapestApartments.execute(10)

        // then return empty map
        assertTrue(mapOfCheapestTenCititesAppartements.isEmpty())
    }


}