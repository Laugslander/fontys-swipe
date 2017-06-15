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
import nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result.TimetableItemListViewHolder;

/**
 * @author SMPT42-1
 */
public class TimetableItemAdapter extends ArrayAdapter<String> {

    private static final boolean TIMETABLE_CLICKABLE = false;

    private List<String> timetable;

    public TimetableItemAdapter(Context context, List<String> timetable) {
        super(context, R.layout.list_item_timetable, timetable);

        this.timetable = timetable;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.list_item_timetable, parent, false);

            TimetableItemListViewHolder viewHolder = new TimetableItemListViewHolder(view);

            view.setTag(viewHolder);
        }

        TimetableItemListViewHolder viewHolder = (TimetableItemListViewHolder) view.getTag();

        String timetableItem = timetable.get(position);
        viewHolder.set(timetableItem);

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return TIMETABLE_CLICKABLE;
    }

}
