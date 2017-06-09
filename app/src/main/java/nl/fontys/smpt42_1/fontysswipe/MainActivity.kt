package nl.fontys.smpt42_1.fontysswipe

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.daprlabs.aaron.swipedeck.SwipeDeck
import kotlinx.android.synthetic.main.activity_main.*
import nl.fontys.smpt42_1.fontysswipe.adapter.SwipeDeckAdapter
import nl.fontys.smpt42_1.fontysswipe.controller.SwipeController
import nl.fontys.smpt42_1.fontysswipe.controller.SwipeControllerListener

/**
 * @author SMPT42-1
 */
class MainActivity : AppCompatActivity(), SwipeControllerListener {

    val controller: SwipeController = SwipeController.createInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showViewElements(false)
    }

    override fun onSwipeControllerInitialized() {
        swipe_deck.setAdapter(SwipeDeckAdapter(controller.questions))
        swipe_deck.setLeftImage(R.id.image_view_left)
        swipe_deck.setRightImage(R.id.image_view_right)
        swipe_deck.setCallback(object : SwipeDeck.SwipeDeckCallback {
            override fun cardSwipedLeft(stableId: Long) {
                controller.processQuestion(false); number_progress_bar.progress = getProgressPercentage()
            }

            override fun cardSwipedRight(stableId: Long) {
                controller.processQuestion(true); number_progress_bar.progress = getProgressPercentage()
            }
        })

        number_progress_bar.max = 100
        image_button_swipe_left.setOnClickListener({ swipe_deck.swipeTopCardLeft(0); number_progress_bar.progress = getProgressPercentage() })
        image_button_swipe_right.setOnClickListener({ swipe_deck.swipeTopCardRight(0); number_progress_bar.progress = getProgressPercentage() })

        showViewElements(true)
    }

    private fun getProgressPercentage(): Int {
        val totalQuestionCount = SwipeController.getInstance().totalQuestionsCount.toDouble()
        val currentQuestionCount = SwipeController.getInstance().currentQuestionCount.toDouble()
        val percentage = ((currentQuestionCount / totalQuestionCount) * 100).toInt()
        return percentage
    }

    override fun onAllQuestionsProcessed() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP or FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun showViewElements(show: Boolean) {
        progress_bar.visibility = if (show) INVISIBLE else VISIBLE
        image_button_swipe_left.visibility = if (show) VISIBLE else INVISIBLE
        image_button_swipe_right.visibility = if (show) VISIBLE else INVISIBLE
        number_progress_bar.visibility = if (show) VISIBLE else INVISIBLE
    }

}
