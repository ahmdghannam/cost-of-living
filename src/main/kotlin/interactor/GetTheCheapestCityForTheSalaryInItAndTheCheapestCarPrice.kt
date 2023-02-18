package interactor

import model.CityEntity

class GetTheCheapestCityForTheSalaryInItAndTheCheapestCarPrice(
     private val  dataSource: CostOfLivingDataSource) {

    fun execute(limit:Int):List<CityEntity>{
      return  dataSource.getAllCitiesData()
          .filter(::excludeNullFoodPricesAndNullGasoLineAndNullSalaryAverageAndCheapestPriceOfGasoLine)
          .sortedBy(::TheCheapestCarsPrices)
          .take(limit)
    }


    private fun excludeNullCarsPrices(city: CityEntity):Boolean{
        return  city.carsPrices.let {
            it.volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar !=null
            && it.toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar !=null
        }

    }

    private fun excludeNullGasoLine(city:CityEntity):Boolean{
          return  city.transportationsPrices.gasolineOneLiter != null
    }

    private fun excludeNullSalaryAverageMonthly(city: CityEntity): Boolean {
        return city.averageMonthlyNetSalaryAfterTax != null

    }

    fun  excludeNullFoodPricesAndNullGasoLineAndNullSalaryAverageAndCheapestPriceOfGasoLine(city: CityEntity):Boolean{
        return  excludeNullCarsPrices(city)
                && excludeNullGasoLine(city)
                && excludeNullSalaryAverageMonthly(city)
                && TheCheapestPriceOfGasoLineInRelationToTheSalary(city)
    }



    private  fun TheCheapestPriceOfGasoLineInRelationToTheSalary(city: CityEntity):Boolean{
        val PercentOfTheSalary= 0.15f
        val NumberOfLitersPerMonth= 50
        return    city.transportationsPrices.gasolineOneLiter!! * NumberOfLitersPerMonth /
                  city.averageMonthlyNetSalaryAfterTax!! <= PercentOfTheSalary
    }

    private  fun TheCheapestCarsPrices(city: CityEntity):Float{
     return  minOf(city.carsPrices.toyotaCorollaSedan_1_6l_97kwComfortOrEquivalentNewCar!!,
            city.carsPrices.volkswagenGolf_1_4_90kwTrendLineOrEquivalentNewCar!!)
    }


}