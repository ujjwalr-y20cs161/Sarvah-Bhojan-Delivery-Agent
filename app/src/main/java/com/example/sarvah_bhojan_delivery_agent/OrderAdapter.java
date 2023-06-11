package com.example.sarvah_bhojan_delivery_agent;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
//            views Init
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
//                    changing UI
                    item.setStatus("Still Pending");
                    Stat.setText("You rejected this order!");
                    Stat.setTextColor(ContextCompat.getColor(view.getContext(), R.color.denyred));
                    accept.setVisibility(View.INVISIBLE);
                    reject.setVisibility(View.INVISIBLE);
                }
            });

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(OrderSession.getInstance().getOrder() == null) {
//                    Changing state of the order object
                        item.setStatus("Agent Assigned");
                        item.setAgentId(UserSession.getInstance().getUser().getUid());
//                        Saving the order in the order session
//                        TODO : Should also store the order in sharedPreference, but should need the order coming from backend.
                        OrderSession.getInstance().setOrder(item);

//                        Changing UI
                        Stat.setText("You have accepted the Order!");
                        Stat.setTextColor(ContextCompat.getColor(view.getContext(),R.color.green));
                        reject.setVisibility(View.INVISIBLE);
                        accept.setText("Order Status");


//                        Change Drawables of Button
                        Drawable[] drawables = accept.getCompoundDrawablesRelative();
                        Drawable newDrawable = ContextCompat.getDrawable(view.getContext(), R.drawable.baseline_chevron_right_24);
                        accept.setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], newDrawable, drawables[3]);

//                    TODO : Should also start an Order Tracking Notification:

//                        Toasts
                        Toast.makeText(view.getContext(), "You have accepted order : "+item.getId(), Toast.LENGTH_SHORT).show();
                        //start the activity from the view/context
                        Intent intent = new Intent(view.getContext(), Tracker.class);
                        view.getContext().startActivity(intent);

                    }else if(OrderSession.getInstance().getOrder().getId() == item.getId()){

                        //                        Change Drawables of Button not often encountered case
                        Drawable[] drawables = accept.getCompoundDrawablesRelative();
                        Drawable newDrawable = ContextCompat.getDrawable(view.getContext(), R.drawable.baseline_chevron_right_24);
                        accept.setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], newDrawable, drawables[3]);

                        //start the activity from the view/context
                        Intent intent = new Intent(view.getContext(), Tracker.class);
                        view.getContext().startActivity(intent);
                    }else{
                        accept.setText("Cannot Accept!");
//                        Drawables Change
                        Drawable[] drawables = accept.getCompoundDrawablesRelative();
                        Drawable newDrawable = ContextCompat.getDrawable(view.getContext(), R.drawable.baseline_block_24);
                        accept.setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], newDrawable, drawables[3]);

                        Toast.makeText(view.getContext(), "You have already accepted order, with no: "+OrderSession.getInstance().getOrder().getId(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
