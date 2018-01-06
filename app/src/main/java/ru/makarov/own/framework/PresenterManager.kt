package ru.makarov.own.framework

object PresenterManager {

    private val MAX_PRESENTERS = 40
    private val presenters = HashMap<Int, Presenter<*>>()

    @Suppress("UNCHECKED_CAST")
    fun <T : Presenter<*>> getPresenter(presenterId: Int, factory: () -> T): T {

        var presenter: Presenter<*>? = presenters[presenterId]

        if (presenter == null) {
            presenter = factory.invoke()
            presenter.onCreate()

            if (presenters.size > MAX_PRESENTERS) removeObsoletePresenters()

            presenters.put(presenterId, presenter)
        }
        return presenter as T
    }

    fun hasPresenterWithId(presenterId: Int): Boolean = presenters.containsKey(presenterId)

    private fun removeObsoletePresenters() {
        while (presenters.size > MAX_PRESENTERS) {

            val oldestDetachTimeId = presenters.keys.minBy { presenters[it]?.detachedTime ?: Long.MAX_VALUE }
            presenters.keys.filter { presenters[it]?.isAttached() == false }
                    .first { presenters[it]?.detachedTime == presenters[oldestDetachTimeId]?.detachedTime }
                    .let { removePresenter(it) }
        }
    }

    fun removeAllPresenters() {
        val it = presenters.keys.iterator()
        while (it.hasNext()) {
            val id = it.next()
            presenters[id]?.onDestroy()
            it.remove()
        }
    }

    fun removePresenter(id: Int) {
        if (presenters.containsKey(id)) {
            presenters[id]?.onDestroy()
            presenters.remove(id)
        }
    }
}
