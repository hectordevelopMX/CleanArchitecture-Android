package com.cobeisfresh.template.ui.weather.presentation

import com.cobeisfresh.template.common.DEFAULT_CITY_NAME
import com.cobeisfresh.template.ui.base.*
import com.cobeisfresh.template.ui.weather.view.WeatherViewEffects
import com.example.domain.interaction.weather.GetWeatherUseCase
import com.example.domain.model.WeatherInfo
import com.example.domain.model.onFailure
import com.example.domain.model.onSuccess

class WeatherViewModel(private val getWeather: GetWeatherUseCase) : BaseViewModel<WeatherInfo, WeatherViewEffects>() {
  
  fun getWeatherForLocation(location: String = DEFAULT_CITY_NAME) = executeUseCase(
          action = {
              getWeather(location)
                  .onSuccess { _viewState.value = Success(it) }
                  .onFailure { _viewState.value = Error(it.throwable) }
          },
          noInternetAction = {
              _viewState.value = NoInternetState()
          }
      )
}
