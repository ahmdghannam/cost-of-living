package interactor

import model.CityEntity

class GetTheCheapestCityForTheSalaryInItAndTheCheapestCarPrice(
     private val  dataSource: CostOfLivingDataSource) {

    fun execute(limit:Int):List<CityEntity>{
      return  dataSource.getAllCitiesData()
          .filter(::excludeNullFoodPricesAndNullGasoLineAndNullSalaryAverageAndCheapestPriceOfGasoLine)
          .sortedBy(::theCheapestCarsPrices)
          .take(limit)
    }


    private fun excludeNullCarsPrices(city: CityEntity):Boolean{
        return  city.carsPrices.let {
            it.volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar !=null
            && it.toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar !=null
        }

    }

    private fun excludeNullGasoline(city:CityEntity):Boolean{
          return  city.transportationsPrices.gasolineOneLiter != null
    }

    private fun excludeNullSalaryAverageMonthly(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null

    }

    private fun  excludeNullFoodPricesAndNullGasoLineAndNullSalaryAverageAndCheapestPriceOfGasoLine(city: CityEntity):Boolean{
        return  excludeNullCarsPrices(city)
                && excludeNullGasoline(city)
                && excludeNullSalaryAverageMonthly(city)
                && theCheapestPriceOfGasoLineInRelationToTheSalary(city)
    }



    private  fun theCheapestPriceOfGasoLineInRelationToTheSalary(city: CityEntity):Boolean{
        val percentOfTheSalary= 0.15f
        val numberOfLitersPerMonth= 50
        return    city.transportationsPrices.gasolineOneLiter!! * numberOfLitersPerMonth /
                  city.averageMonthlyNetSalaryAfterTax!! <= percentOfTheSalary
    }

    private  fun theCheapestCarsPrices(city: CityEntity):Float{
     return  minOf(city.carsPrices.toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar!!,
            city.carsPrices.volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar!!)
    }


}