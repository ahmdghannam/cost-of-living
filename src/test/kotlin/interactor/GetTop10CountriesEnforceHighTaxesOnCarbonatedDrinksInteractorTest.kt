package interactor
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import model.CityEntity
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.function.Executable

class GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractorTest {
    private lateinit var countries:GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractor
    @MockK
    private lateinit var dataSource: CostOfLivingDataSource

    @BeforeEach
    fun setup(){
        MockKAnnotations.init(this)
    }

    @Test
    fun should_ThrowException_When_TheListIsEmpty() {

        every { dataSource.getAllCitiesData() }returns emptyList()

        // given empty list
        countries = GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractor(dataSource)
        // when the list is empty
        val country = Executable{countries.execute(10)}
        // then
        assertThrows(Exception::class.java){
            country.execute()
        }
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

        // resources lists
        val namesOfCountries= listOf("Palestine","Pakistan","Egypt","Iraq","Morocco","USA","Jordan","France","Lebanon","Algeria","Tunisia","Saudi Arabia")
        val carbonatedDrinksPrices= listOf( 12f,11f,10f,9f,8f,7f,6f,5f,4f,3f,2f,1f,)


        val list= mutableListOf<CityEntity>()

        for (i in 0 until 12){
            val city =mockk<CityEntity>()
            every { city.country }returns namesOfCountries[i]
            every { city.dataQuality }returns true
            every { city.drinksPrices.cokePepsiAThirdOfLiterBottleInRestaurants } returns carbonatedDrinksPrices[i]
            list.add(city)
        }
        every {
            dataSource.getAllCitiesData()
        }returns list



        // given list has less than 10 countries
        countries = GetTop10CountriesEnforceHighTaxesOnCarbonatedDrinksInteractor(dataSource)
        val items = listOf<String>()
        // when return all countries
        val allCountries = countries.execute(10)


        // then

        val pairs= listOf(
            Pair("Palestine" , 12f) ,
            Pair("Pakistan" , 11f),
            Pair("Egypt" , 10f) ,
            Pair("Iraq" , 9f),
            Pair("Morocco" , 8f) ,
            Pair("USA" , 7f),
            Pair("Jordan" , 6f) ,
            Pair("France" , 5f),
            Pair("Lebanon" , 4f) ,
            Pair("Algeria" , 3f),)

        assertEquals(pairs, allCountries)
    }

}

