package interactor
import dataSource.*
import dataSource.utils.CsvParser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinksTest {
    private lateinit var cities:GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinks
    @Test
    fun should_ReturnNull_When_TheFileIsEmpty() {
        // given empty csv file
        val csvParser = CsvParser()
        val dataSource : CostOfLivingDataSource = CsvDataSource(csvParser , EMPTY_FILE )
        cities = GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinks(dataSource)
        // when the file is empty
        val city = cities.excute()
        // then
        assertNull(city)
    }
    @Test
    fun should_ReturnAllCityWithoutCitiesIsContainsOfNullPricesToCarbonatedDrinks_When_ProvidedLessThan10Cities() {
       // given list has less than 10 cities
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser,
            SUB_FILE_NAME)
        cities = GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinks(dataSource)
        val items = listOf<CsvDataSource>()
        // when return all cities
        val allCities = cities.excute()
        // then
        assertEquals(items , allCities)
    }
    @Test
    fun should_ReturnNull_When_AllPricesCarbonatedDrinksIsNegative() {
        // given list contains negative prices
        val csvParser = CsvParser()
        val dataSource : CostOfLivingDataSource = CsvDataSource(csvParser , NEGATIVE_PRICE)
        cities = GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinks(dataSource)
        // when the list contains negative prices carbonated drinks
        val city = cities.excute()
        // then
        assertNull(city)
    }
    @Test
    fun should_ReturnNull_When_AllPricesCarbonatedDrinksIsNull() {
        // given list contains negative prices
        val csvParser = CsvParser()
        val dataSource : CostOfLivingDataSource = CsvDataSource(csvParser , NULL_PRICE)
        cities = GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinks(dataSource)
        // when the list contains negative prices carbonated drinks
        val city = cities.excute()
        // then
        assertNull(city)
    }
    companion object{
        private const val SUB_FILE_NAME = "csvFiles/subListOfCostOfLivingContainsNullPricesOfCarbonatedDrinks.csv"
        private const val EMPTY_FILE = "csvFiles/emptyFile.csv"
        private const val NEGATIVE_PRICE = "csvFiles/negativePriceCarbonatedDrinks.csv"
        private const val NULL_PRICE = "csvFiles/nullPriceCarbonatedDrinks.csv"


    }
}

