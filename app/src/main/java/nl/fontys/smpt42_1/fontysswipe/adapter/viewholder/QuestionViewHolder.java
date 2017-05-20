package nl.fontys.smpt42_1.fontysswipe.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nl.fontys.smpt42_1.fontysswipe.R;
import nl.fontys.smpt42_1.fontysswipe.domain.Question;

/**
 * @author SMPT42-1
 */
public class QuestionViewHolder {

    private final Context context;
    private final View itemView;

    private ImageView cardImage;
    private TextView cardText;

    public QuestionViewHolder(Context context, View itemView) {
        this.context = context;
        this.itemView = itemView;

        bindViews();
    }

    private void bindViews() {
        cardImage = (ImageView) itemView.findViewById(R.id.card_image);
        cardText = (TextView) itemView.findViewById(R.id.card_text);
    }

    public void set(Question question) {
        Picasso.with(context).load(R.drawable.placeholder_image).fit().centerCrop().into(cardImage); // TODO replace placeholder image

        cardText.setText(question.getText());
    }

}
