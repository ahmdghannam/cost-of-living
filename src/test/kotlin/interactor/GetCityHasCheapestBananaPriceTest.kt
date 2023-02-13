package interactor

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.jupiter.api.Test

class GetCityHasCheapestBananaPriceTest{

    private lateinit var cheapestBanana: GetCityHasTheCheapestBananaPriceInteractor

    @MockK
    private lateinit var dataSource: CostOfLivingDataSource

    init {
        MockKAnnotations.init(this)
        dataSource = mockk()
    }

    @Test
    fun should_ReturnSortedListByCheapestBananaPrices_When_TheListIsNotEmptyAndPricesIsNotZeroOrInMinus(){
        // given

        // when

        // then
    }

    @Test
    fun should_ReturnSingleList_When_TheListHasOneCityAndThePriceIsNotNull(){
        // given

        // when

        // then
    }

    @Test
    fun should_ReturnNull_When_TheListIsEmpty(){
        // given

        // when

        // then
    }

    @Test
    fun should_ReturnNull_When_ThePricesIsZeroOrInMinus(){
        // given

        // when

        // then
    }



    companion object {
        private const val EMPTY_FILE = "csvFiles/emptyFile.csv"
        private const val ONE_CITY_FILE = "csvFiles/oneCity.csv"
        private const val SUB_FILE_NAME = "csvFiles/subListOfCostOfLiving.csv"
        private const val NULL_SALARIES_FILE = "csvFiles/nullSalaries.csv"
        private const val NEGATIVE_SALARIES_FILE = "csvFiles/negativeSalaries.csv"
    }
}