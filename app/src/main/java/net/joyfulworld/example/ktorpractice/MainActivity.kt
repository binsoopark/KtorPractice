package net.joyfulworld.example.ktorpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.*
import net.joyfulworld.example.ktorpractice.httplogic.HttpRequestHelper
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    lateinit var tvResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        setContentView(R.layout.activity_main)
        tvResult = findViewById(R.id.tv_result_view)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun onSimpleRequest(view: View) {
        launch(Dispatchers.Main) {
            val result = HttpRequestHelper().requestKtorIo()
            tvResult.text = result
        }
    }

    fun onDetailRequest(view: View) {
        launch(Dispatchers.Main) {
            val result = HttpRequestHelper().requestKtorIoInDetail()
            tvResult.text = result
        }
    }
}