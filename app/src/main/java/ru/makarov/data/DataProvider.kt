package ru.makarov.data

class DataProvider {
    private val api = DataApiBuilder.createAPI() //todo toothpick

    fun getData() =
            api.getData().map { it.data }
}