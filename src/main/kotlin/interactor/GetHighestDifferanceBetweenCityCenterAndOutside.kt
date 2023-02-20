import dataSource.utils.TheTypeOfApartments
import interactor.CostOfLivingDataSource
import model.CityEntity
import java.lang.Math.abs

//A class to get the city with the highest difference between the cost of living in the city center and outside
class GetHighestDifferanceBetweenCityCenterAndOutside(
    private val dataSource: CostOfLivingDataSource
) {

    // A function to execute the logic to get the city with the highest difference
    fun execute(type: TheTypeOfApartments): CityEntity {
        if (type == TheTypeOfApartments.ONE_BED_ROOM) {
            return dataSource.getAllCitiesData()
                .filter(::excludeNullRentOneTypeOneAndLowQualityData)
                .maxByOrNull {
                    abs(
                        it.realEstatesPrices.apartmentOneBedroomInCityCentre!!
                                - it.realEstatesPrices.apartmentOneBedroomOutsideOfCentre!!
                    )
                }!!

        } else {
            return dataSource.getAllCitiesData()
                .filter(::excludeNullRentOneTypeTwoAndLowQualityData)
                .maxByOrNull {
                    abs(
                        it.realEstatesPrices.apartment3BedroomsInCityCentre!! -
                                it.realEstatesPrices.apartment3BedroomsOutsideOfCentre!!
                    )
                }!!

        }
    }
   // A function to filter out cities with null or low-quality data for one-bedroom apartment
    private fun excludeNullRentOneTypeOneAndLowQualityData(city: CityEntity): Boolean {
        return city.dataQuality
                && city.realEstatesPrices.apartmentOneBedroomInCityCentre != null
                && city.realEstatesPrices.apartmentOneBedroomOutsideOfCentre != null
    }

    // A function to filter out cities with null or low-quality data for three-bedroom apartment
    private fun excludeNullRentOneTypeTwoAndLowQualityData(city: CityEntity): Boolean {

        return city.dataQuality
                && city.realEstatesPrices.apartment3BedroomsInCityCentre != null
                && city.realEstatesPrices.apartment3BedroomsOutsideOfCentre != null


    }
}




