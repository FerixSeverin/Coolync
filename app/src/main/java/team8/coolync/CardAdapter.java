package team8.coolync;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by media on 2015-11-25.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    List<NatureItem> mItems;

    public CardAdapter(/*String[] name, int[] amount, int[] thumbnail*/) {
        super();
        mItems = new ArrayList<NatureItem>();
        NatureItem nature;

        /*int i=0;
        while(name[i] != null){
            nature = new NatureItem();
            nature.setName(name[0]);
            nature.setDes(amount[0]);
            pickIcon(thumbnail[0], nature);
            mItems.add(nature);
            i++;
        }*/

        nature = new NatureItem();
        nature.setName("Cola");
        nature.setDes("1");
        nature.setThumbnail(R.drawable.ic_menu_drink);
        mItems.add(nature);

        nature = new NatureItem();
        nature.setName("Starbucks");
        nature.setDes("2");
        nature.setThumbnail(R.drawable.ic_menu_coffee);
        mItems.add(nature);

        nature = new NatureItem();
        nature.setName("Carlsberg");
        nature.setDes("1");
        nature.setThumbnail(R.drawable.ic_menu_alcohol);
        mItems.add(nature);

        nature = new NatureItem();
        nature.setName("Kebab Pizza");
        nature.setDes("3");
        nature.setThumbnail(R.drawable.ic_menu_pizza);
        mItems.add(nature);


        nature = new NatureItem();
        nature.setName("Köttfärs");
        nature.setDes("20");
        nature.setThumbnail(R.drawable.ic_menu_food);
        mItems.add(nature);

        /*nature = new NatureItem();
        nature.setName(name[0]);
        nature.setDes(amount[0]);
        pickIcon(thumbnail[0], nature);
        mItems.add(nature);*/
    }

    public void pickIcon(int icon, NatureItem nature){
        if(icon == 0){
            nature.setThumbnail(R.drawable.ic_menu_coffee);
        } else if(icon == 1){
            nature.setThumbnail(R.drawable.ic_menu_pizza);
        } else if(icon == 2){
            nature.setThumbnail(R.drawable.ic_menu_alcohol);
        } else if(icon == 3){
            nature.setThumbnail(R.drawable.ic_menu_drink);
        } else {
            nature.setThumbnail(R.drawable.ic_menu_food);
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
        NatureItem nature = mItems.get(i);
        viewHolder.tvNature.setText(nature.getName());
        viewHolder.tvDesNature.setText(nature.getDes());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvNature;
        public TextView tvDesNature;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvNature = (TextView)itemView.findViewById(R.id.item_name);
            tvDesNature = (TextView)itemView.findViewById(R.id.item_amount);
        }
    }
}
