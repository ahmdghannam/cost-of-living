package interactor

import org.junit.jupiter.api.Test
import dataSource.utils.CsvParser
import dataSource.CsvDataSource
import org.junit.jupiter.api.Assertions.*


internal class GetCityHasMoreSavingsPerMonthInteractorTest {
    private lateinit var citySavings: GetCityHasMoreSavingsPerMonthInteractor



    @Test
    fun should_ReturnCity_When_CityFoodCostIsNotNull(){

        // given city food cost not null
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, CITY_COST_FOOD_NOT_NULL_FILE)
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
        val listCityData=dataSource.getAllCitiesData()


        // when find the most  city in the world have more savings per month.
        val getCity = citySavings.execute()


        // done
        assertEquals(listCityData[0],getCity)


    }


    @Test
    fun should_ReturnCity_When_ApartmentIsNotNull(){

        // given city apartment not null
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, CITY_APARTMENT_NOT_NULL_FILE)
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
        val listCityData=dataSource.getAllCitiesData()


        // when find the most  city in the world have more savings per month.
        val getCity = citySavings.execute()


        // done
        assertEquals(listCityData[0],getCity)


    }


    @Test
    fun should_ReturnCity_When_TransportationsPricesNull(){

        // given city with transportations Prices null
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, CITY_WITH_TRANSPORTATIONS_PRICES_NULL_FILE)
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
        val listCityData=dataSource.getAllCitiesData()


        // when find the most  city in the world have more savings per month.
        val getCity = citySavings.execute()


        // done
        assertEquals(listCityData[0],getCity)


    }


    @Test
    fun should_ReturnCity_When_AverageSalaryNotNull(){

        // given city with average salary not null
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, CITY_WITH_AVERAGE_SALARY_NOY_NULL_FILE)
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
        val listCityName=dataSource.getAllCitiesData()


        // when find the most  city in the world have more savings per month.
        val getCity = citySavings.execute()


        // done
        assertEquals(listCityName[0],getCity)


    }


    @Test
    fun should_ReturnCity_When_ApartmentAndCostFoodNotNullAndTransportationsIsNull(){

        // given city with apartment and cost food not null and transportations is null
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, CITY_WITH_TRANSPORTATIONS_PRICES_NULL_AND_APARTMENT_AND_COST_FOOD_NOT_NULL_FILE)
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
        val listCityData=dataSource.getAllCitiesData()


        // when find the most  city in the world have more savings per month.
        val getCity = citySavings.execute()


        // done
        assertEquals(listCityData[0],getCity)


    }




    @Test
    fun should_ReturnNull_When_FoodPriceNull(){

        // given city with food price is null
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, NULL_CITY_FILE)
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)


        // when find the most  city in the world have more savings per month.
        val getNull = citySavings.execute()


        // done
        assertNull(getNull)


    }
    @Test
    fun should_ReturnNull_When_AverageSalaryNull(){

        // given city with average salary  null
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, CITY_WITH_AVERAGE_SALARY_NULL_FILE)
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)



        // when find the most  city in the world have more savings per month.
        val getNull = citySavings.execute()


        // done
        assertNull(getNull)


    }



    @Test
    fun should_ReturnNull_When_ApartmentNull(){

        // given city with apartment is null
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, NULL_CITY_FILE)
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)


        // when find the most  city in the world have more savings per month.
        val getNull = citySavings.execute()


        // done
        assertNull(getNull)


    }


    @Test
    fun should_ReturnNull_When_TransportationsNotNull(){

        // given city transportations not null
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, NULL_CITY_FILE)
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)


        // when find the most  city in the world have more savings per month.
        val getNull = citySavings.execute()


        // done
        assertNull(getNull)


    }


    @Test
    fun should_ReturnNull_When_TransportationsNotNullAndFoodPriceAndApartmentNull(){

        // given city with transportations not null and food price And apartment is null
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, NULL_CITY_FILE)
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)


        // when find the most  city in the world have more savings per month.
        val getNull = citySavings.execute()


        //done
        assertNull(getNull)

    }


    @Test
    fun should_ReturnCity_When_countSavingPerMonthNotNull(){

        // given city saving permonth not null
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, FIND_COUNT_SAVING_PER_MONTH_NOT_NULL_FILE)
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)
        val listCityData=dataSource.getAllCitiesData()


        // when find the most  city in the world have more savings per month.
        val getCity = citySavings.execute()


        // done
        assertEquals(listCityData[0],getCity)

    }


    @Test
    fun  should_ReturnNull_When_countSavingPerMonthNull(){

        // given city with count Saving PerMonth null
        val csvParser = CsvParser()
        val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser, NULL_CITY_FILE)
        citySavings = GetCityHasMoreSavingsPerMonthInteractor(dataSource)


        // when find the most  city in the world have more savings per month.
        val getNull = citySavings.execute()


        //done
        assertNull(getNull)

    }

    companion object{

        private const val NULL_CITY_FILE = "csvFiles/nullCity.csv"
        private const val CITY_COST_FOOD_NOT_NULL_FILE = "csvFiles/findCityWithCostFoodNotNull.csv"
        private const val CITY_APARTMENT_NOT_NULL_FILE = "csvFiles/findCityWithApartmentNotNull.csv"
        private const val CITY_WITH_TRANSPORTATIONS_PRICES_NULL_FILE= "csvFiles/findCityWithTransportationsPricesIsNull.csv"
        private const val CITY_WITH_TRANSPORTATIONS_PRICES_NULL_AND_APARTMENT_AND_COST_FOOD_NOT_NULL_FILE= "csvFiles/findCityWithTransportationsPricesIsNullAndFoodPriceAndApartmentNotNull.csv"
        private const val CITY_WITH_AVERAGE_SALARY_NOY_NULL_FILE= "csvFiles/findCityWithAverageNotNull.csv"
        private const val CITY_WITH_AVERAGE_SALARY_NULL_FILE= "csvFiles/findCityWithAverageNull.csv"
        private const val FIND_COUNT_SAVING_PER_MONTH_NOT_NULL_FILE = "csvFiles/findCountSavingPerMonthNotNull.csv"

    }
}



