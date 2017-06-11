package nl.fontys.smpt42_1.fontysswipe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_result.*
import nl.fontys.smpt42_1.fontysswipe.adapter.ResultAdapter
import nl.fontys.smpt42_1.fontysswipe.controller.SwipeController
import nl.fontys.smpt42_1.fontysswipe.controller.SwipeControllerResultListener

/**
 * @author SMPT42-1
 */
class ResultActivity : AppCompatActivity(), SwipeControllerResultListener {

    val controller = SwipeController.getInstance()
    val adapter = ResultAdapter(controller.results)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        controller.setSwipeControllerResultListener(this)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    override fun onResultsUpdated() {
        adapter.notifyDataSetChanged()
    }

}
