package team8.coolync;

import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import team8.coolync.Model.FoodItem;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    //Declaration of a FoodItem list
    List<FoodItem> mItems;

    public CardAdapter(ArrayList<FoodItem> mItems) {
        super();
        this.mItems = mItems;
    }

    //Declares how an entry in the list is going to look like
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //R.layout.recycler_view_card_item contains the design and look of the list
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        //Fetches the current position of the FoodItem list and puts it in food
        FoodItem food = mItems.get(i);
        //The string that contains the name of the product gets taken from food and gets inserted in
        //the viewHolder for the name
        viewHolder.tvNature.setText(food.getName());
        //The int that contains the amount of the product gets taken from food and gets inserted in
        //the viewHolder for the amount
        viewHolder.tvAmountNature.setText(String.valueOf(food.getAmount()));
    }

    //Returns how many unique products there are in the FoodItem list
    @Override
    public int getItemCount() {
        if(mItems != null){
            return mItems.size();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        //Declaration of TextViews
        public TextView tvNature;
        public TextView tvAmountNature;

        //Takes the value from the viewHolder and assings them to the TextViews in the xml to get
        //displayed
        public ViewHolder(View itemView) {
            super(itemView);
            tvNature = (TextView)itemView.findViewById(R.id.item_name);
            tvAmountNature = (TextView)itemView.findViewById(R.id.item_amount);
        }
    }
}
