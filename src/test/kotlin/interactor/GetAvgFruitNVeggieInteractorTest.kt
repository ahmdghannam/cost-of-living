package interactor

import org.junit.jupiter.api.Test
import kotlin.test.*
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlin.test.assertEquals
import model.CityEntity
import model.FruitAndVegetablesPrices

class GetAvgFruitNVeggieInteractorTest {
    private lateinit var cheapestFruitNVeggie: GetAvgFruitNVeggieInteractor
    @MockK
    private lateinit var dataSource: CostOfLivingDataSource


    init {
        MockKAnnotations.init(this)
        dataSource = mockk()
    }
    @Test
    fun should_ReturnLowest10CityPricesComparedToSalaries_When_TheListNotEmptyAndNotNullAndTheAttributesNotNull(){
        //given list of cities prices of fruit and vegetables and salaries
        val list= mutableListOf<CityEntity>()
        for (i in 0.. 20) {
            val city = mockk<CityEntity>()
            every {
                city.averageMonthlyNetSalaryAfterTax
            } returns 10f
            every {
                city.fruitAndVegetablesPrices
            } returns FruitAndVegetablesPrices(
                1.0f,
                2.0f,
                3.0f,
                4.0f,
                1.0f,
                5.0f,
                1.0f)
            list.add(city)
        }
        every {
            dataSource.getAllCitiesData()
        } returns list
        cheapestFruitNVeggie=GetAvgFruitNVeggieInteractor(dataSource)
        //when average prices of fruits and vegetables are the lowest compared to the city salaries
        val cheapestCityInPrices = cheapestFruitNVeggie.execute(10)
        //then find the lowest 10 cities fruits and vegetables compared to salaries
        assertEquals(list.subList(0, 10), cheapestCityInPrices)

    }
    @Test
    fun should_ReturnAllCity_When_LessThan10CityProvided(){
        //given list of cities prices of fruit and vegetables and salaries
        val list= mutableListOf<CityEntity>()
        for (i in 0.. 5) {
            val city = mockk<CityEntity>()
            every {
                city.averageMonthlyNetSalaryAfterTax
            } returns 10f
            every {
                city.fruitAndVegetablesPrices
            } returns FruitAndVegetablesPrices(
                1.0f,
                2.0f,
                3.0f,
                4.0f,
                1.0f,
                5.0f,
                1.0f)
            list.add(city)
        }
        every {
            dataSource.getAllCitiesData()
        } returns list
        cheapestFruitNVeggie=GetAvgFruitNVeggieInteractor(dataSource)
        //when average prices of fruits and vegetables are the lowest compared to the city salaries
        val cheapestCityInPrices = cheapestFruitNVeggie.execute(10)
        //then find the lowest 10 cities fruits and vegetables compared to salaries
        assertEquals(list.take(10), cheapestCityInPrices)
    }

    @Test
    fun should_Return10City_When_just10CityProvided(){
        //given list of cities prices of fruit and vegetables and salaries
        val list= mutableListOf<CityEntity>()
        for (i in 0.. 9) {
            val city = mockk<CityEntity>()
            every {
                city.averageMonthlyNetSalaryAfterTax
            } returns 10f
            every {
                city.fruitAndVegetablesPrices
            } returns FruitAndVegetablesPrices(
                1.0f,
                2.0f,
                3.0f,
                4.0f,
                1.0f,
                5.0f,
                1.0f)
            list.add(city)
        }
        every {
            dataSource.getAllCitiesData()
        } returns list
        cheapestFruitNVeggie=GetAvgFruitNVeggieInteractor(dataSource)
        //when average prices of fruits and vegetables are the lowest compared to the city salaries
        val cheapestCityInPrices = cheapestFruitNVeggie.execute(10)
        //then find the lowest 10 cities fruits and vegetables compared to salaries
        assertEquals(list.subList(0, 10), cheapestCityInPrices)

    }
    @Test
    fun should_ReturnEmptyList_When_TheFileIsEmpty(){
        //given an empty file
        every {
            dataSource.getAllCitiesData()
        } returns listOf()
        cheapestFruitNVeggie=GetAvgFruitNVeggieInteractor(dataSource)
        // when
        val cheapestCityInPrices = cheapestFruitNVeggie.execute(10)
        //then it should return empty list
        assertTrue(cheapestCityInPrices.isEmpty())
    }

    @Test
    fun should_ReturnEmptyList_When_AllTheSalariesAreNull(){
        //given null salaries
        val list= mutableListOf<CityEntity>()
        for (i in 0 until 10){
            val city = mockk<CityEntity>()
            every {
                city.averageMonthlyNetSalaryAfterTax
            } returns null
            every {
                city.fruitAndVegetablesPrices
            } returns FruitAndVegetablesPrices(
                1.0f,
                2.0f,
                3.0f,
                4.0f,
                1.0f,
                5.0f,
                1.0f)
            list.add(city)
        }
        every {
            dataSource.getAllCitiesData()
        } returns list
        cheapestFruitNVeggie=GetAvgFruitNVeggieInteractor(dataSource)
        // when
        val cheapestCityInPrices =cheapestFruitNVeggie.execute(10)
        //then it should return empty list
        assertTrue(cheapestCityInPrices.isEmpty())
    }

    @Test

    fun should_ReturnEmptyList_When_AllTheFruitsAndVegetablesPricesAreNull(){
        //given null fruits and vegetables prices
        val list= mutableListOf<CityEntity>()
        for (i in 0 until 10){
            val city = mockk<CityEntity>()
            every {
                city.averageMonthlyNetSalaryAfterTax
            } returns 10f
            every {
                city.fruitAndVegetablesPrices
            } returns FruitAndVegetablesPrices(
                null,
                null,
                null,
                null,
                null,
                null,
                null)
            list.add(city)
        }
        every {
            dataSource.getAllCitiesData()
        } returns list
        cheapestFruitNVeggie=GetAvgFruitNVeggieInteractor(dataSource)
        // when
        val cheapestCityInPrices =cheapestFruitNVeggie.execute(10)
        //then it should return empty list
        assertTrue(cheapestCityInPrices.isEmpty())
    }
    @Test
    fun should_ReturnEmptyList_When_AllTheSalariesAreNegative() {
        //given null fruits and vegetables prices
        val list= mutableListOf<CityEntity>()
        for (i in 0 until 10){
            val city = mockk<CityEntity>()
            every {
                city.averageMonthlyNetSalaryAfterTax
            } returns -10f
            every {
                city.fruitAndVegetablesPrices
            } returns FruitAndVegetablesPrices(
                1.0f,
                2.0f,
                3.0f,
                4.0f,
                1.0f,
                5.0f,
                1.0f)
            list.add(city)
        }
        every {
            dataSource.getAllCitiesData()
        } returns list
        cheapestFruitNVeggie=GetAvgFruitNVeggieInteractor(dataSource)
        // when
        val cheapestCityInPrices =cheapestFruitNVeggie.execute(10)
        //then it should return empty list
        assertTrue(cheapestCityInPrices.isEmpty())

    }
    @Test
    fun should_ReturnEmptyList_When_AllTheFruitsAndVegetablesPricesAreNegative(){
        //given null fruits and vegetables prices
        val list= mutableListOf<CityEntity>()
        for (i in 0 until 10){
            val city = mockk<CityEntity>()
            every {
                city.averageMonthlyNetSalaryAfterTax
            } returns 10f
            every {
                city.fruitAndVegetablesPrices
            } returns FruitAndVegetablesPrices(
                -1.0f,
                -2.0f,
                -3.0f,
                -4.0f,
                -1.0f,
                -5.0f,
                -1.0f)
            list.add(city)
        }
        every {
            dataSource.getAllCitiesData()
        } returns list
        cheapestFruitNVeggie=GetAvgFruitNVeggieInteractor(dataSource)
        // when
        val cheapestCityInPrices =cheapestFruitNVeggie.execute(10)
        //then it should return empty list
        assertTrue(cheapestCityInPrices.isEmpty())
    }
    @Test
    fun should_returnEmptyList_when_salaryIsZero(){
        //given null fruits and vegetables prices
        val list= mutableListOf<CityEntity>()
        for (i in 0 until 10){
            val city = mockk<CityEntity>()
            every {
                city.averageMonthlyNetSalaryAfterTax
            } returns 0f
            every {
                city.fruitAndVegetablesPrices
            } returns FruitAndVegetablesPrices(
                1.0f,
                2.0f,
                3.0f,
                4.0f,
                1.0f,
                5.0f,
                1.0f)
            list.add(city)
        }
        every {
            dataSource.getAllCitiesData()
        } returns list
        cheapestFruitNVeggie=GetAvgFruitNVeggieInteractor(dataSource)
        // when
        val cheapestCityInPrices = cheapestFruitNVeggie.execute(10)
        // then
        assertTrue(cheapestCityInPrices.isEmpty())
    }
}

