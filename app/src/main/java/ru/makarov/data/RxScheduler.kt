package ru.makarov.data

import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxScheduler {
    fun <T> apply() = ObservableTransformer<T, T> { upstream: Observable<T> ->
        upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun applyComp() = CompletableTransformer { upstream: Completable ->
        upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}
