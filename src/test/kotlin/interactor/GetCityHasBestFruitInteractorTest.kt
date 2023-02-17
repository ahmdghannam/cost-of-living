package interactor

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import model.CityEntity
import model.FruitAndVegetablesPrices
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

class GetCityHasBestFruitInteractorTest{

    private lateinit var cityFruit: GetCityHasBestFruitInteractor

    @MockK
    private lateinit var dataSource: CostOfLivingDataSource

    @MockK
    private lateinit var cityEntityPrices: CityEntity

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        every {
            cityEntityPrices.cityName
        } returns "Gaza"

    }
    @Test
    fun should_ThrowException_When_FruitNameEmpty(){
        //given name fruit empty value
        val nameFruit = ""

        every {
            cityEntityPrices.fruitAndVegetablesPrices
        }returns FruitAndVegetablesPrices(10f,20f,30f,40f,50f,60f,70f)

        every {
            dataSource.getAllCitiesData()
        } returns listOf(cityEntityPrices)

        //when get city has best fruit
        cityFruit = GetCityHasBestFruitInteractor(dataSource)
        val cityFruitExecutable = Executable { cityFruit.execute(nameFruit)}



        // then
        assertThrows(Exception::class.java,cityFruitExecutable)




    }
    @Test
    fun should_ThrowException_When_FruitNameNull(){
        //given name fruit null value
        val nameFruit : String? = null

        every {
            cityEntityPrices.fruitAndVegetablesPrices
        }returns FruitAndVegetablesPrices(10f,20f,30f,40f,50f,60f,70f)

        every {
            dataSource.getAllCitiesData()
        } returns listOf(cityEntityPrices)

        //when get city has best fruit
        cityFruit = GetCityHasBestFruitInteractor(dataSource)
        val cityFruitExecutable = Executable { cityFruit.execute(nameFruit)}



        // then
        assertThrows(Exception::class.java,cityFruitExecutable)




    }
    @Test
    fun should_ThrowException_When_ApplePriceNull(){
        //given name fruit null value
        val nameFruit = "Apple"

        every {
            cityEntityPrices.fruitAndVegetablesPrices
        }returns FruitAndVegetablesPrices(null,20f,30f,40f,50f,60f,70f)

        every {
            dataSource.getAllCitiesData()
        } returns listOf(cityEntityPrices)

        //when get city has best fruit
        cityFruit = GetCityHasBestFruitInteractor(dataSource)
        val cityFruitExecutable = Executable { cityFruit.execute(nameFruit)}



        // then
        assertThrows(Exception::class.java,cityFruitExecutable)




    }    @Test
    fun should_ThrowException_When_BananaPriceNull(){
        //given name fruit null value
        val nameFruit = "banana"

        every {
            cityEntityPrices.fruitAndVegetablesPrices
        }returns FruitAndVegetablesPrices(50f,null,30f,40f,50f,60f,70f)

        every {
            dataSource.getAllCitiesData()
        } returns listOf(cityEntityPrices)

        //when get city has best fruit
        cityFruit = GetCityHasBestFruitInteractor(dataSource)
        val cityFruitExecutable = Executable { cityFruit.execute(nameFruit)}



        // then
        assertThrows(Exception::class.java,cityFruitExecutable)




    }    @Test
    fun should_ThrowException_When_OrangesPriceNull(){
        //given name fruit null value
        val nameFruit = "orange"

        every {
            cityEntityPrices.fruitAndVegetablesPrices
        }returns FruitAndVegetablesPrices(10f,20f,null,40f,50f,60f,70f)

        every {
            dataSource.getAllCitiesData()
        } returns listOf(cityEntityPrices)

        //when get city has best fruit
        cityFruit = GetCityHasBestFruitInteractor(dataSource)
        val cityFruitExecutable = Executable { cityFruit.execute(nameFruit)}



        // then
        assertThrows(Exception::class.java,cityFruitExecutable)




    }
}