package com.project.attendance.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.project.attendance.R;
import com.project.attendance.model.RVClickListener;
import com.project.attendance.model.Student;

import java.util.List;

/**
 * Created by Shashanth
 */

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    private List<Student> studentList;
    private RVClickListener<Student> listener;

    public StudentListAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    public StudentListAdapter(List<Student> studentList, RVClickListener<Student> listener) {
        this.studentList = studentList;
        this.listener = listener;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.students_row, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        bind(holder, studentList.get(position));
    }

    private void bind(final StudentViewHolder holder, final Student student) {
        holder.lblStudentName.setText(student.getStudentName());
        holder.lblRegNo.setText(student.getRegNo());
        holder.chkAttendance.setChecked(student.isSelected());
        holder.chkAttendance.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        student.setSelected(!student.isSelected());
                        holder.chkAttendance.setChecked(student.isSelected());
                    }
                }
        );

        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null) {
                            listener.onClick(holder.getAdapterPosition(), student);
                        }
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return studentList == null ? 0 : studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView lblStudentName, lblRegNo;
        private CheckBox chkAttendance;

        private StudentViewHolder(View itemView) {
            super(itemView);

            lblStudentName = (TextView) itemView.findViewById(R.id.lbl_stud_name);
            lblRegNo = (TextView) itemView.findViewById(R.id.lbl_reg_no);
            chkAttendance = (CheckBox) itemView.findViewById(R.id.chk_attendance);
        }
    }
}
