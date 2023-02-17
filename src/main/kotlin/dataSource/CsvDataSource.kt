package dataSource

import dataSource.utils.CsvParser
import interactor.CostOfLivingDataSource
import model.CityEntity
import java.io.File
import java.io.IOException

class CsvDataSource(private val parser: CsvParser,private val file :String= FILE_NAME): CostOfLivingDataSource {

    override fun getAllCitiesData(): List<CityEntity> {
        return getCsvFile().readLines().map { csvLine ->
            parser.parseLine(csvLine)
        }
    }

    private fun getCsvFile(): File {
        val costOfLivingCsvFile = File(file)
        if (costOfLivingCsvFile.exists()) {
            return costOfLivingCsvFile
        }
        throw IOException("File Not Found")
    }

    companion object {
        private const val FILE_NAME = "csvFiles/costOfLiving.csv"
        private const val SUB_FILE_NAME = "csvFiles/subListOfCostOfLiving.csv"
        private const val NULL_PRICES_FILE_NAME = "csvFiles/nullSalaries.csv"
        private const val ONE_CITY_FILE_NAME = "csvFiles/oneCity.csv"
        private const val EMPTY_FILE_NAME = "csvFiles/emptyFile.csv"
    }

}