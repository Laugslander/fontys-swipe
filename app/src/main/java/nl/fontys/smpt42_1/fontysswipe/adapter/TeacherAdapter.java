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
import nl.fontys.smpt42_1.fontysswipe.adapter.viewholder.result.TeacherListViewHolder;
import nl.fontys.smpt42_1.fontysswipe.domain.Teacher;

/**
 * @author SMPT42-1
 */
public class TeacherAdapter extends ArrayAdapter<Teacher> {

    private static final int NUMBER_OF_TEACHERS_TO_SHOW = 3;

    private List<Teacher> teachers;

    public TeacherAdapter(Context context, List<Teacher> teachers) {
        super(context, R.layout.list_item_teacher, teachers);

        this.teachers = teachers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.list_item_teacher, parent, false);

            TeacherListViewHolder viewHolder = new TeacherListViewHolder(view);

            view.setTag(viewHolder);
        }

        TeacherListViewHolder viewHolder = (TeacherListViewHolder) view.getTag();

        Teacher teacher = teachers.get(position);
        viewHolder.set(teacher);

        return view;
    }

    @Override
    public int getCount() {
        return teachers.size() < NUMBER_OF_TEACHERS_TO_SHOW ? teachers.size() : NUMBER_OF_TEACHERS_TO_SHOW;
    }

}
