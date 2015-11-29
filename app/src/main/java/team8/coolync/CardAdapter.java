package team8.coolync;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import team8.coolync.Model.FoodItem;

/**
 * Created by media on 2015-11-25.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    List<FoodItem> mItems;

    public CardAdapter(/*String[] name, int[] Type, int[] thumbnail*/) {
        super();
        mItems = new ArrayList<FoodItem>();
        FoodItem food = new FoodItem();
        List<FoodItem> foodItem;


        /*int i=0;
        while(name[i] != null){
            food = new FoodItem();
            food.setName(name[0]);
            food.setAmount(Type[0]);
            pickIcon(thumbnail[0], food);
            mItems.add(food);
            i++;
        }*/

        food = new FoodItem();
        food.setName("Cola");
        food.setAmount(1);
        food.setThumbnail(R.drawable.ic_menu_drink);
        mItems.add(food);

        food = new FoodItem();
        food.setName("Starbucks");
        food.setAmount(2);
        food.setThumbnail(R.drawable.ic_menu_coffee);
        mItems.add(food);

        food = new FoodItem();
        food.setName("Carlsberg");
        food.setAmount(1);
        food.setThumbnail(R.drawable.ic_menu_alcohol);
        mItems.add(food);

        food = new FoodItem();
        food.setName("Kebab Pizza");
        food.setAmount(3);
        food.setThumbnail(R.drawable.ic_menu_pizza);
        mItems.add(food);


        food = new FoodItem();
        food.setName("Köttfärs");
        food.setAmount(20);
        food.setThumbnail(R.drawable.ic_menu_food);
        mItems.add(food);

        /*food = new FoodItem();
        food.setName(name[0]);
        food.setAmount(Type[0]);
        pickIcon(thumbnail[0], food);
        mItems.add(food);*/
    }

    public void pickIcon(int icon, FoodItem food){
        if(icon == 0){
            food.setThumbnail(R.drawable.ic_menu_coffee);
        } else if(icon == 1){
            food.setThumbnail(R.drawable.ic_menu_pizza);
        } else if(icon == 2){
            food.setThumbnail(R.drawable.ic_menu_alcohol);
        } else if(icon == 3){
            food.setThumbnail(R.drawable.ic_menu_drink);
        } else {
            food.setThumbnail(R.drawable.ic_menu_food);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        FoodItem food = mItems.get(i);
        viewHolder.tvNature.setText(food.getName());
        viewHolder.tvAmountNature.setText(String.valueOf(food.getAmount()));
        viewHolder.imgThumbnail.setImageResource(food.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvNature;
        public TextView tvAmountNature;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvNature = (TextView)itemView.findViewById(R.id.item_name);
            tvAmountNature = (TextView)itemView.findViewById(R.id.item_amount);
        }
    }
}
