package nl.fontys.smpt42_1.fontysswipe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.R;
import nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.QuestionViewHolder;
import nl.fontys.smpt42_1.fontysswipe.domain.Question;
import nl.fontys.smpt42_1.fontysswipe.util.locationUtil;

/**
 * @author SMPT42-1
 */
public class SwipeDeckAdapter extends BaseAdapter {

    private final List<Question> questions;

    public SwipeDeckAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.swipe_card, parent, false);

            QuestionViewHolder viewHolder = new QuestionViewHolder(view);

            view.setTag(viewHolder);
        }

        QuestionViewHolder viewHolder = (QuestionViewHolder) view.getTag();

        Question question = questions.get(position);
        viewHolder.set(question);

        return view;
    }

}
