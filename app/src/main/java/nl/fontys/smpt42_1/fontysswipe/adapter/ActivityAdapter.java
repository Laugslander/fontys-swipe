package nl.fontys.smpt42_1.fontysswipe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.R;
import nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result.ActivityListViewHolder;
import nl.fontys.smpt42_1.fontysswipe.domain.Activity;

/**
 * @author SMPT42-1
 */
public class ActivityAdapter extends ArrayAdapter<Activity> {

    private static final int NUMBER_OF_ACTIVITIES_TO_SHOW = 3;

    private List<Activity> activities;

    public ActivityAdapter(Context context, List<Activity> activities) {
        super(context, R.layout.list_item_activity, activities);

        this.activities = activities;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.list_item_activity, parent, false);

            ActivityListViewHolder viewHolder = new ActivityListViewHolder(view);

            view.setTag(viewHolder);
        }

        ActivityListViewHolder viewHolder = (ActivityListViewHolder) view.getTag();

        Activity activity = activities.get(position);
        viewHolder.set(activity);

        return view;
    }

    @Override
    public int getCount() {
        return activities.size() < NUMBER_OF_ACTIVITIES_TO_SHOW ? activities.size() : NUMBER_OF_ACTIVITIES_TO_SHOW;
    }

}
