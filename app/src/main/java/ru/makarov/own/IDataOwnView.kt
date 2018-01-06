package ru.makarov.own

import ru.makarov.own.framework.IView

interface IDataOwnView : IView {
    fun showLoading()
    fun showError(message: String)
    fun showData(data: String)
}