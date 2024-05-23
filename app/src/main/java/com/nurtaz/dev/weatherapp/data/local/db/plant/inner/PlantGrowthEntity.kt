package com.nurtaz.dev.weatherapp.data.local.db.plant.inner

data class PlantGrowthEntity( var phMaximum: Float?,
                              var phMinimum: Float?,
                              var light: Int?,
                              var atmHumidity: Float?,
                              var soilHumidity: Float?,
                              var bloomMonths: List<String>,
                              var growthMonths: List<String>,
                              var fruitMonths: List<String>,
                              var soilNutriments: Int?,
                              var soilSalinity: Int?) {
}