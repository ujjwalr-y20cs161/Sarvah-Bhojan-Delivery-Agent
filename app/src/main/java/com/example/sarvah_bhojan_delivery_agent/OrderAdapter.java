package com.example.sarvah_bhojan_delivery_agent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private List<Order> data;

    public OrderAdapter(List<Order> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Order item = data.get(position);
        holder.bind(item,position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView orderId,pickAdd,dropAdd,customerName,Fare,Distance,Stat;
        private Button reject,accept;

        public MyViewHolder(View itemView) {
            super(itemView);
            Stat = itemView.findViewById(R.id.agentStat);
            orderId = itemView.findViewById(R.id.order_id);
            pickAdd = itemView.findViewById(R.id.order_pick_address);
            dropAdd = itemView.findViewById(R.id.order_drop_address);
            customerName = itemView.findViewById(R.id.order_cust_name);
            Fare = itemView.findViewById(R.id.order_fare);
            Distance = itemView.findViewById(R.id.order_distance);
            reject = itemView.findViewById(R.id.order_reject);
            accept  = itemView.findViewById(R.id.order_accept);
        }

        public void bind(Order item,int Position) {

            orderId.setText("Order Id : "+item.getId());
            pickAdd.setText("Pick Up : "+item.getPickUp());
            dropAdd.setText("Drop : "+item.getDropDown());
            customerName.setText("Customer : "+item.getCustomerName());
            Fare.setText("Rs."+Integer.toString(item.getFare()));
            Distance.setText(Integer.toString(item.getDistance())+"KMs");


            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.setStatus("Still Pending");
                    Stat.setText("You Rejected this order!");
                    accept.setVisibility(View.GONE);
                }
            });

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.setStatus("Agent Assigned");
                    item.setAgentId(UserSession.getInstance().getUser().getUid());
                    Stat.setText("You have Accepted the Order!");
                }
            });
        }
    }
}
