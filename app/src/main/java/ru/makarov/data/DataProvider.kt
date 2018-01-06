package ru.makarov.data

object DataProvider { //todo toothpick
    private val api = DataApiBuilder.createAPI() //todo toothpick

    fun getData() = api.getData().map { it.data }.compose(RxScheduler.apply())
}