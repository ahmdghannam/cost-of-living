package interactor
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import model.CityEntity
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractorTest {
    private lateinit var countries:GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractor
    @MockK
    private lateinit var dataSource: CostOfLivingDataSource

    @BeforeEach
    fun setup(){
        MockKAnnotations.init(this)
    }

    @Test
    fun should_ReturnNull_When_TheListIsEmpty() {

        every { dataSource.getAllCitiesData() }returns emptyList()

        // given empty list
        countries = GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractor(dataSource)
        // when the list is empty
        val country = countries.execute(0)
        // then
        assertNull(country)
    }
    @Test
    fun should_ReturnAllCountries_When_ProvidedLessThan10Countries() {
        val city1 = mockk<CityEntity>()
        val city2 = mockk<CityEntity>()

        every { city1.country }returns "egypt"
        every { city1.dataQuality }returns true
        every { city1.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  5f

        every { city2.country }returns "pakistan"
        every { city2.dataQuality }returns true
        every { city2.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  5f


        every {
            dataSource.getAllCitiesData()
        }returns listOf(city1 , city2)

        // given list has more than 10 countries
        countries = GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractor(dataSource)
        val items = listOf<String>()
        // when return top 10 countries
        val allCountries = countries.execute(10)


        // then
        assertEquals(listOf(Pair("egypt" , 5f) , Pair("pakistan" , 5f)) , allCountries)
    }
    @Test
    fun should_ReturnTop10CountriesEnforceHighTaxesOnCarbonatedDrinks_When_ProvidedMoreThan10Countries() {
        val city1 = mockk<CityEntity>()
        val city2 = mockk<CityEntity>()
        val city3 = mockk<CityEntity>()
        val city4 = mockk<CityEntity>()
        val city5 = mockk<CityEntity>()
        val city6 = mockk<CityEntity>()
        val city7 = mockk<CityEntity>()
        val city8 = mockk<CityEntity>()
        val city9 = mockk<CityEntity>()
        val city10 = mockk<CityEntity>()
        val city11 = mockk<CityEntity>()
        val city12 = mockk<CityEntity>()

        every { city1.country }returns "Palestine"
        every { city1.dataQuality }returns true
        every { city1.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  12f

        every { city2.country }returns "Pakistan"
        every { city2.dataQuality }returns true
        every { city2.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  11f

        every { city3.country }returns "Egypt"
        every { city3.dataQuality }returns true
        every { city3.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  10f

        every { city4.country }returns "Iraq"
        every { city4.dataQuality }returns true
        every { city4.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  9f

        every { city5.country }returns "Morocco"
        every { city5.dataQuality }returns true
        every { city5.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  8f

        every { city6.country }returns "USA"
        every { city6.dataQuality }returns true
        every { city6.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  7f

        every { city7.country }returns "Jordan"
        every { city7.dataQuality }returns true
        every { city7.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  6f

        every { city8.country }returns "France"
        every { city8.dataQuality }returns true
        every { city8.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  5f

        every { city9.country }returns "Lebanon"
        every { city9.dataQuality }returns true
        every { city9.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  4f

        every { city10.country }returns "Algeria"
        every { city10.dataQuality }returns true
        every { city10.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  3f

        every { city11.country }returns "Tunisia"
        every { city11.dataQuality }returns true
        every { city11.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  2f

        every { city12.country }returns "Saudi Arabia"
        every { city12.dataQuality }returns true
        every { city12.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants }returns  1f


        every {
            dataSource.getAllCitiesData()
        }returns listOf(city1 , city2 , city3,city4,city5,city6,city7,city8,city9,city10,city11,city12)

        // given list has less than 10 countries
        countries = GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractor(dataSource)
        val items = listOf<String>()
        // when return all countries
        val allCountries = countries.execute(10)


        // then
        assertEquals(listOf(
            Pair("Palestine" , 12f) ,
            Pair("Pakistan" , 11f),
            Pair("Egypt" , 10f) ,
            Pair("Iraq" , 9f),
            Pair("Morocco" , 8f) ,
            Pair("USA" , 7f),
            Pair("Jordan" , 6f) ,
            Pair("France" , 5f),
            Pair("Lebanon" , 4f) ,
            Pair("Algeria" , 3f),

        ), allCountries)
    }

}

