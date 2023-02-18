import FakeData.FakeData
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GetHighestDifferanceBetween_CityCenterAndOutsideTest {

    private lateinit var highestDifferentRent: GetHighestDifferanceBetweenCityCenterAndOutside
    private lateinit var fakedata: FakeData

    @BeforeAll
    fun setUp() {
        fakedata = FakeData()
        highestDifferentRent = GetHighestDifferanceBetweenCityCenterAndOutside(fakedata)
    }

    @Test
    fun should_ReturnCorrectCity_When_TypeOne() {
        val type = TheTypeOfApartments.ONE_BED_ROOM
        val city = highestDifferentRent.execute(type)
        assertEquals(fakedata.getAllCitiesData()[2], city)
    }

    @Test
    fun should_ReturnCorrectCity_When_TypeTwo() {
        val type = TheTypeOfApartments.THREE_BED_ROOMS
        val city = highestDifferentRent.execute(type)
        assertEquals(fakedata.getAllCitiesData()[5], city)
    }


}