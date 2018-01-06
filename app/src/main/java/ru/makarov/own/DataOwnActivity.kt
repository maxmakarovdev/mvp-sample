package ru.makarov.own

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.data_activity.*
import ru.makarov.*
import ru.makarov.ext.gone
import ru.makarov.ext.onClick
import ru.makarov.ext.toast
import ru.makarov.ext.visible
import ru.makarov.own.framework.PresenterManager

class DataOwnActivity : Activity(), IDataOwnView {

    private lateinit var presenter: DataOwnPresenter

    companion object {
        val PRESENTER_ID = DataOwnPresenter::class.java.name.hashCode()
        fun start(context: Context) = context.startActivity(Intent(context, DataOwnActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_activity)
        presenter = PresenterManager.getPresenter(PRESENTER_ID, { DataOwnPresenter() })
        button.onClick { presenter.loadData() }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView(this)
        super.onStop()
    }

    override fun showLoading() {
        progress.visible()
        text.gone()
        button.gone()
    }

    override fun showError(message: String) {
        progress.gone()
        text.gone()
        button.visible()
        toast(message)
    }

    override fun showData(data: String) {
        progress.gone()
        text.visible()
        button.gone()
        text.text = data
    }
}