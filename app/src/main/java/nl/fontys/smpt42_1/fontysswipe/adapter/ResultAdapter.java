package nl.fontys.smpt42_1.fontysswipe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.R;
import nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result.ResultViewHolder;
import nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result.StatisticsResultViewHolder;
import nl.fontys.smpt42_1.fontysswipe.domain.result.Result;
import nl.fontys.smpt42_1.fontysswipe.domain.result.StatisticsResult;

import static nl.fontys.smpt42_1.fontysswipe.adapter.ViewTypes.STATISTICS;

/**
 * @author SMPT42-1
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private final List<Result> results;

    public ResultAdapter(Context context, List<Result> results) {
        this.results = results;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ResultViewHolder viewHolder;
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case STATISTICS:
                view = inflater.inflate(R.layout.card_statistics, parent, false);
                viewHolder = new StatisticsResultViewHolder(view);
                break;
            default:
                viewHolder = null; // Should never occur.
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        Result result = results.get(position);

        switch (position) {
            case STATISTICS:
                ((StatisticsResultViewHolder) holder).set((StatisticsResult) result);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

}
