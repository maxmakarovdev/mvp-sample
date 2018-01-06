package ru.makarov

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.main_activity.*
import ru.makarov.ext.onClick
import ru.makarov.own.DataOwnActivity

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        own.onClick { DataOwnActivity.start(this) }
        mosby.onClick { }
        moxy.onClick { }
    }
}
