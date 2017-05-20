package nl.fontys.smpt42_1.fontysswipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.daprlabs.aaron.swipedeck.SwipeDeck;

import nl.fontys.smpt42_1.fontysswipe.adapter.SwipeDeckAdapter;

/**
 * @author SMPT42-1
 */
public class MainActivity extends AppCompatActivity {

    private static final int SWIPE_ANIMATION_DURATION = 200;

    private SwipeDeck swipeDeck;
    private ImageButton imageButtonSwipeLeft;
    private ImageButton imageButtonSwipeRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        loadActivity();
    }

    private void bindViews() {
        swipeDeck = (SwipeDeck) findViewById(R.id.swipe_deck);
        imageButtonSwipeLeft = (ImageButton) findViewById(R.id.image_button_swipe_left);
        imageButtonSwipeRight = (ImageButton) findViewById(R.id.image_button_swipe_right);
    }

    private void loadActivity() {
        SwipeDeckAdapter swipeDeckAdapter = new SwipeDeckAdapter(TestData.questions);
        swipeDeck.setAdapter(swipeDeckAdapter);
        swipeDeck.setLeftImage(R.id.left_image);
        swipeDeck.setRightImage(R.id.right_image);

        imageButtonSwipeLeft.setOnClickListener(imageButtonSwipeLeftOnClickListener);

        imageButtonSwipeRight.setOnClickListener(imageButtonSwipeRightOnClickListener);
    }

    private View.OnClickListener imageButtonSwipeLeftOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            swipeDeck.swipeTopCardLeft(SWIPE_ANIMATION_DURATION);
        }
    };

    private View.OnClickListener imageButtonSwipeRightOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            swipeDeck.swipeTopCardRight(SWIPE_ANIMATION_DURATION);
        }
    };

}
