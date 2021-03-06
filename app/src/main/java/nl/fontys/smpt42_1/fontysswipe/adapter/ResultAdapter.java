package nl.fontys.smpt42_1.fontysswipe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.R;
import nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result.ActivityResultViewHolder;
import nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result.PrizeResultViewHolder;
import nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result.ResultViewHolder;
import nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result.StatisticResultViewHolder;
import nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result.TeacherResultViewHolder;
import nl.fontys.smpt42_1.fontysswipe.domain.result.ActivityResult;
import nl.fontys.smpt42_1.fontysswipe.domain.result.PrizeResult;
import nl.fontys.smpt42_1.fontysswipe.domain.result.Result;
import nl.fontys.smpt42_1.fontysswipe.domain.result.StatisticResult;
import nl.fontys.smpt42_1.fontysswipe.domain.result.TeacherResult;

import static nl.fontys.smpt42_1.fontysswipe.adapter.ViewTypes.ACTIVITY;
import static nl.fontys.smpt42_1.fontysswipe.adapter.ViewTypes.PRIZE;
import static nl.fontys.smpt42_1.fontysswipe.adapter.ViewTypes.STATISTIC;
import static nl.fontys.smpt42_1.fontysswipe.adapter.ViewTypes.TEACHER;

/**
 * @author SMPT42-1
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private final List<Result> results;

    public ResultAdapter(List<Result> results) {
        this.results = results;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ResultViewHolder viewHolder;
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case STATISTIC:
                view = inflater.inflate(R.layout.card_result_statistic, parent, false);
                viewHolder = new StatisticResultViewHolder(view);
                break;
            case TEACHER:
                view = inflater.inflate(R.layout.card_result_teacher, parent, false);
                viewHolder = new TeacherResultViewHolder(view);
                break;
            case ACTIVITY:
                view = inflater.inflate(R.layout.card_result_activity, parent, false);
                viewHolder = new ActivityResultViewHolder(view);
                break;

            case PRIZE:
                view = inflater.inflate(R.layout.card_result_prize, parent, false);
                viewHolder = new PrizeResultViewHolder(view);
                break;
            default:
                // Should never occur.
                viewHolder = null;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        Result result = results.get(position);

        switch (getItemViewType(position)) {
            case STATISTIC:
                StatisticResultViewHolder statisticResultViewHolder = (StatisticResultViewHolder) holder;
                statisticResultViewHolder.set((StatisticResult) result);
                break;
            case TEACHER:
                TeacherResultViewHolder teacherResultViewHolder = (TeacherResultViewHolder) holder;
                teacherResultViewHolder.set((TeacherResult) result);
                break;
            case ACTIVITY:
                ActivityResultViewHolder activityResultViewHolder = (ActivityResultViewHolder) holder;
                activityResultViewHolder.set((ActivityResult) result);
                break;
            case PRIZE:
                PrizeResultViewHolder prizeResultViewHolder = (PrizeResultViewHolder) holder;
                prizeResultViewHolder.set((PrizeResult) result);
                break;
            default:
                // Should never occur.
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;

        switch (position) {
            case 0:
                viewType = STATISTIC;
                break;
            case 1:
                viewType = TEACHER;
                break;
            case 2:
                viewType = ACTIVITY;
                break;
            case 3:
                viewType = PRIZE;
                break;
            default:
                viewType = 0;
                // Should never occur.
        }

        return viewType;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

}
