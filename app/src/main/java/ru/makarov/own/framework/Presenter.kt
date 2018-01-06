package ru.makarov.own.framework

import io.reactivex.disposables.CompositeDisposable
import java.util.*

abstract class Presenter<V : IView> {

    protected var disposables = CompositeDisposable()

    private val mViews = ArrayList<V>()
    var detachedTime: Long? = null
        private set

    val views: List<V> = mViews

    fun isAttached(): Boolean = mViews.size > 0

    fun attachView(view: V) {
        mViews.add(view)
        detachedTime = null
        onViewAttached(view)
    }

    fun detachView(view: V) {
        onViewDetached(view)
        detachedTime = System.currentTimeMillis()
        mViews.remove(view)
    }

    fun detachAllViews() {
        val it = mViews.iterator()
        while (it.hasNext()) {
            val view = it.next()
            onViewDetached(view)
            detachedTime = System.currentTimeMillis()
            it.remove()
        }
    }

    fun view(action: (V) -> Unit) = views.forEach(action)

    open fun onCreate() {}

    open fun onViewAttached(view: V) {}

    open fun onViewDetached(view: V) {}

    open fun onDestroy() = disposables.dispose()
}
