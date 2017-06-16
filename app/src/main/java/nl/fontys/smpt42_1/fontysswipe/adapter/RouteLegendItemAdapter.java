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
import nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result.RouteLegendItemListViewHolder;
import nl.fontys.smpt42_1.fontysswipe.domain.Route;

/**
 * @author SMPT42-1
 */
public class RouteLegendItemAdapter extends ArrayAdapter<Route> {

    private static final boolean TIMETABLE_CLICKABLE = false;

    private List<Route> routeLegend;

    public RouteLegendItemAdapter(Context context, List<Route> routeLegend) {
        super(context, R.layout.list_item_timetable, routeLegend);

        this.routeLegend = routeLegend;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.list_item_route_legend, parent, false);

            RouteLegendItemListViewHolder viewHolder = new RouteLegendItemListViewHolder(view);

            view.setTag(viewHolder);
        }

        RouteLegendItemListViewHolder viewHolder = (RouteLegendItemListViewHolder) view.getTag();

        Route routLegendItem = routeLegend.get(position);
        viewHolder.set(routLegendItem);

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return TIMETABLE_CLICKABLE;
    }

}
