package com.project.attendance.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.attendance.R;
import com.project.attendance.model.Allotment;
import com.project.attendance.model.RVClickListener;

import java.util.List;

/**
 * Created by Shashanth
 */

public class AllotmentsAdapter extends RecyclerView.Adapter<AllotmentsAdapter.AllotmentViewHolder> {

    private RVClickListener<Allotment> listener;
    private List<Allotment> allotmentList;

    public AllotmentsAdapter(List<Allotment> allotmentList, RVClickListener<Allotment> listener) {
        this.allotmentList = allotmentList;
        this.listener = listener;
    }

    @Override
    public AllotmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allotments_row, parent, false);
        return new AllotmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AllotmentViewHolder holder, int position) {
        bind(holder, allotmentList.get(position));
    }

    private void bind(final AllotmentViewHolder holder, final Allotment allotment) {
        holder.lblSubName.setText(allotment.getSubName());
        holder.lblSubDept.setText(allotment.getDepartment());
        holder.lblSubSem.setText(allotment.getSemText());
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null) {
                            listener.onClick(holder.getAdapterPosition(), allotment);
                        }
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return allotmentList == null ? 0 : allotmentList.size();
    }

    static class AllotmentViewHolder extends RecyclerView.ViewHolder {

        private TextView lblSubName, lblSubDept, lblSubSem;

        private AllotmentViewHolder(View itemView) {
            super(itemView);

            lblSubSem = (TextView) itemView.findViewById(R.id.lbl_sub_sem);
            lblSubName = (TextView) itemView.findViewById(R.id.lbl_sub_name);
            lblSubDept = (TextView) itemView.findViewById(R.id.lbl_sub_dept);
        }
    }
}
