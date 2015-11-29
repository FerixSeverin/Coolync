package team8.coolync;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.RestAdapter;
import team8.coolync.Interface.FoodService;
import team8.coolync.Model.FoodItem;
/*import retrofit.client.Response;*/

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public Context context;
/*    private RequestQueue mRequestQueue;*/

    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    /*Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
    Network network = new BasicNetwork(new HurlStack());
    RequestQueue mRequestQueue = new RequestQueue(cache,network);*/

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    TextView txtItemName, txtItemAmount;
    ArrayList<FoodItem> foodItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CardAdapter(foodItems);
        mRecyclerView.setAdapter(mAdapter);

        txtItemName = (TextView) findViewById(R.id.item_name);
        txtItemAmount = (TextView) findViewById(R.id.item_amount);

        String url = "http://83.209.98.203:8081/get.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.length() > 0) {
                        foodItems.clear();
                        for(int i = 0;i < response.length();i++){
                            JSONObject jsonObject = response.getJSONObject(i);
                            FoodItem food = new FoodItem();
                            if(!jsonObject.isNull("productName")){
                                food.setName(jsonObject.getString("productName"));
                                Log.v("Response: ", "productName");
                            }
                            if(!jsonObject.isNull("count")){
                                food.setAmount(jsonObject.getInt("count"));
                                Log.v("Response: ", "count");
                            }
                            /*if(!jsonObject.isNull("type")){
                                food.setThumbnail(jsonObject.getInt("type"));
                                Log.v("Response: ", "type");
                            }*/
                            foodItems.add(i, food);
                            Log.v("Response: ", foodItems.toString());
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("The error is: ", String.valueOf(error));
                Log.v("Response: ", "catch");
            }
        });

        requestQueue.add(jsonArrayRequest);

        if(!connectInternet()){
            Toast.makeText(this, "GIIMME MANA", Toast.LENGTH_SHORT).show();
            return;
        }
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://83.209.98.203:8081/").build();
        FoodService service = restAdapter.create(FoodService.class);
        /*service.getFood(new Callback<List<FoodItem>>(){
            @Override
            public void success(List<FoodItem> food, Response response){
                for(int i = 0; i < food.size();i++){
                    FoodItem current = new FoodItem();

                    current.setName(food.get(i).getName());
                    current.setAmount(food.get(i).getAmount());

                    foodItems.add(current);
                }

                if(foodItems.size() > 0){
                    txtItemName.setText(String.valueOf(foodItems.get(0).getName()));
                    txtItemAmount.setText(foodItems.get(0).getAmount());
                }
            }

            @Override
            public void failure(RetrofitError retrofitError){
                Toast.makeText(getApplication(), retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });*/



        //Pings the server
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String url = "http://83.209.98.203:8081/data.json";
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                *//*Toast.makeText(MainActivity.this, "I try", Toast.LENGTH_SHORT).show();*//*
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if (response.length() > 0) {
                                foodItems.clear();
                                for(int i = 0;i < response.length();i++){
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    FoodItem food = new FoodItem();
                                    if(!jsonObject.isNull("name")){
                                        food.setName(jsonObject.getString("name"));
                                    }
                                    if(!jsonObject.isNull("amount")){
                                        food.setAmount(jsonObject.getInt("age"));
                                    }
                                }
                                mAdapter.notifyDataSetChanged();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Response: ", "catch");
                    }
                });*/


//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Toast.makeText(MainActivity.this, "ayy lmao", Toast.LENGTH_SHORT).show();
                /*fetchJsonResponse(view);*/
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public boolean connectInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager)this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }

 /*   public interface foodMethod {
        @GET("http://83.209.98.203:8081/data.json")
        List<FoodItem> getName();
    }*/

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/



    //Function that requests the server
    private void fetchJsonResponse(View view) {
        final View viw = view;
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.app_bar_main);
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.content_main);

        // Pass second argument as "null" for GET requests
        JsonObjectRequest req = new JsonObjectRequest("http://83.209.98.203:8081/get.php", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                          String result = "Your IP Address is " + response.getString("ip");
                            String[] tjo = {response.getString("item")};
                            int[] ayy = {response.getInt("amount")};
                            int[] lmao = {response.getInt("type")};
                            Log.v("jsonnnnnnnnnnnnnnnnnnn", tjo[0] + ayy[0] + lmao[0]);
                            Log.v("jsonnnnnnnnnnnnnnnnnnn", tjo[1] + ayy[1] + lmao[1]);
                            Log.v("jsonnnnnnnnnnnnnnnnnnn", tjo[2] + ayy[2] + lmao[2]);
                            Snackbar.make(viw, "ayylmao", Snackbar.LENGTH_SHORT)
                                    .setAction("RETRY", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    })
                                    .show();
                             Toast.makeText(MainActivity.this, tjo[0] + " " + ayy[0] + " " + lmao[0], Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

		/* Add your Requests to the RequestQueue to execute */
        /*requestQueue.add(req);*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
