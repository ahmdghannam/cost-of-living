package interactor

import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeAll
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GetCityHasCheapestInternetConnectionInteractorTest {
    private lateinit var cheapestInternet:GetCityHasCheapestInternetConnectionInteractor

    @Test
    fun should_ReturnLeastInterNetCost_When_TheListNotEmptyAndNotNullAndTheAttributesNotNull() {
        // given list of cities
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, SUB_FILE_NAME) // CSV : comma separated values
        cheapestInternet=GetCityHasCheapestInternetConnectionInteractor(dataSource)
        val list=dataSource.getAllCitiesData()
        // when find the minimum internet coast of this list
        val cheapestCityInInternet=cheapestInternet.execute()
        // then
        assertEquals(list[1],cheapestCityInInternet)

    }

    @Test
    fun should_ReturnNull_When_TheFileIsEmpty() {
        // given empty csv file
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, EMPTY_FILE) // CSV : comma separated values
        cheapestInternet=GetCityHasCheapestInternetConnectionInteractor(dataSource)
        val list=dataSource.getAllCitiesData()
        // when find the minimum internet coast of this list
        val cheapestCityInInternet=cheapestInternet.execute()
        // then
        assertNull(cheapestCityInInternet)

    }
    @Test
    fun should_ReturnNull_When_TheSalaryIsNull() {
//        // given a null salary cities list
//        val csvParser = CsvParser()
//        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, EMPTY_FILE) // CSV : comma separated values
//        cheapestInternet=GetCityHasCheapestInternetConnectionInteractor(dataSource)
//        val list=dataSource.getAllCitiesData()
//        // when find the minimum internet coast of this list
//        val cheapestCityInInternet=cheapestInternet.execute()
//        // then
//        assertNull(cheapestCityInInternet)

    }



    companion object{
        private const val SUB_FILE_NAME = "subListOfCostOfLiving.csv"
        private const val EMPTY_FILE = "emptyFile.csv"
    }
}