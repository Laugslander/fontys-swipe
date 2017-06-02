package nl.fontys.smpt42_1.fontysswipe

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.daprlabs.aaron.swipedeck.SwipeDeck
import kotlinx.android.synthetic.main.activity_main.*
import nl.fontys.smpt42_1.fontysswipe.adapter.SwipeDeckAdapter
import nl.fontys.smpt42_1.fontysswipe.controller.SwipeController
import nl.fontys.smpt42_1.fontysswipe.controller.SwipeControllerDelegate

/**
 * @author SMPT42-1
 */
class MainActivity : AppCompatActivity(), SwipeControllerDelegate {

    val controller: SwipeController = SwipeController.createInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSwipeControllerInitialized() {
        swipe_deck.setAdapter(SwipeDeckAdapter(controller.questions))
        swipe_deck.setLeftImage(R.id.left_image)
        swipe_deck.setRightImage(R.id.right_image)
        swipe_deck.setCallback(object : SwipeDeck.SwipeDeckCallback {
            override fun cardSwipedLeft(stableId: Long) = controller.processQuestion(false)
            override fun cardSwipedRight(stableId: Long) = controller.processQuestion(true)
        })

        image_button_swipe_left.setOnClickListener({ swipe_deck.swipeTopCardLeft(0) })
        image_button_swipe_right.setOnClickListener({ swipe_deck.swipeTopCardRight(0) })
    }

    override fun onAllQuestionsProcessed() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}
