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

class GetMostExpensiveCityForFruitTradeInteractorTest{

    private lateinit var cityFruit: GetMostExpensiveCityForFruitTradeInteractor

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


        //when get most expensive city for fruit trade
        val cityFruitExecutable = Executable { cityFruit.execute(nameFruit)}


        // // then it throws an exception
        assertThrows(Exception::class.java,cityFruitExecutable)

    }
   
    @Test
    fun should_ThrowException_When_ApplePriceNull(){
        //given apple price null value
        val nameFruit = "apple"

        every {
            cityEntityPrices.fruitAndVegetablesPrices
        }returns FruitAndVegetablesPrices(null,20f,30f,40f,50f,60f,70f)

        every {
            dataSource.getAllCitiesData()
        } returns listOf(cityEntityPrices)

        //when get most expensive city for fruit trade
        cityFruit = GetMostExpensiveCityForFruitTradeInteractor(dataSource)
        val cityFruitExecutable = Executable { cityFruit.execute(nameFruit)}



        // // then it throws an exception
        assertThrows(Exception::class.java,cityFruitExecutable)

    }    @Test
    fun should_ThrowException_When_BananaPriceNull(){
        //given banana price null value
        val nameFruit = "banana"

        every {
            cityEntityPrices.fruitAndVegetablesPrices
        }returns FruitAndVegetablesPrices(50f,null,30f,40f,50f,60f,70f)

        every {
            dataSource.getAllCitiesData()
        } returns listOf(cityEntityPrices)

        //when get most expensive city for fruit trade
        cityFruit = GetMostExpensiveCityForFruitTradeInteractor(dataSource)
        val cityFruitExecutable = Executable { cityFruit.execute(nameFruit)}



        // // then it throws an exception
        assertThrows(Exception::class.java,cityFruitExecutable)


    }    @Test
    fun should_ThrowException_When_OrangesPriceNull(){
        //given orange price null value
        val nameFruit = "orange"

        every {
            cityEntityPrices.fruitAndVegetablesPrices
        }returns FruitAndVegetablesPrices(10f,20f,null,40f,50f,60f,70f)

        every {
            dataSource.getAllCitiesData()
        } returns listOf(cityEntityPrices)

        //when get most expensive city for fruit trade
        cityFruit = GetMostExpensiveCityForFruitTradeInteractor(dataSource)
        val cityFruitExecutable = Executable { cityFruit.execute(nameFruit)}


        // // then it throws an exception it throws an exception
        assertThrows(Exception::class.java,cityFruitExecutable)


    }
}