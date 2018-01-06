package ru.makarov.own

import io.reactivex.disposables.Disposable
import ru.makarov.data.DataProvider
import ru.makarov.own.framework.Presenter

class DataOwnPresenter : Presenter<IDataOwnView>() {

    private var data: String? = null
    private var dataDisposable: Disposable? = null

    override fun onViewAttached(view: IDataOwnView) {
        super.onViewAttached(view)
        data?.let { d -> view { it.showData(d) } }
    }

    fun loadData() {
        dataDisposable = DataProvider.getData().subscribe(
                { d ->
                    dataDisposable?.let { disposables.remove(it) }
                    data = d
                    view { it.showData(d) }
                },
                { t ->
                    dataDisposable?.let { disposables.remove(it) }
                    view { it.showError(t.localizedMessage ?: "error") }
                },
                { dataDisposable?.let { disposables.remove(it) } })
        dataDisposable?.let { disposables.add(it) }
    }


}