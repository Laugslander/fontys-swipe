package nl.fontys.smpt42_1.fontysswipe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.daprlabs.aaron.swipedeck.SwipeDeck
import kotlinx.android.synthetic.main.activity_main.*
import nl.fontys.smpt42_1.fontysswipe.adapter.SwipeDeckAdapter

/**
 * @author SMPT42-1
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipe_deck.setAdapter(SwipeDeckAdapter(TestData.questions))
        swipe_deck.setLeftImage(R.id.left_image)
        swipe_deck.setRightImage(R.id.right_image)
        swipe_deck.setCallback(swipeDeckCallBack)

        image_button_swipe_left.setOnClickListener({ swipe_deck.swipeTopCardLeft(0) })
        image_button_swipe_right.setOnClickListener({ swipe_deck.swipeTopCardRight(0) })
    }

    object swipeDeckCallBack : SwipeDeck.SwipeDeckCallback {
        override fun cardSwipedLeft(stableId: Long) {
            print("card swiped left")
        }

        override fun cardSwipedRight(stableId: Long) {
            print("card swiped right")
        }
    }

}
