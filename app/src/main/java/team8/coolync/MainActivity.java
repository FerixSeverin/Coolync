package team8.coolync;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import java.util.ArrayList;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import team8.coolync.Model.FoodItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Declaration of a RequestQueue
    RequestQueue requestQueue;
    //Declaration of a string, made to contain the url that the app connects to
    String url;

    //Declaration of components for the list
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    //Declaration of an ArrayList that contains FoodItem
    //FoodItem is based on the class FoodItem.java, holds a string and an int
    ArrayList<FoodItem> foodItems = new ArrayList<>();

    //This executes when the application launches
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaration and assignment of a toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Assigment of components for the list
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CardAdapter(foodItems);
        mRecyclerView.setAdapter(mAdapter);

        //Assingment of what the url is
        url = "http://83.209.98.203:8081/get.php";
        //Assingment of the RequestQueue
        requestQueue = Volley.newRequestQueue(this);
        //Starts the JsonArrayRequest, this is the function that connects the app to the server and
        //fetches the data from a JSON, this will be executed when the app gets launched
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            //Checking if there is a response
            public void onResponse(JSONArray response) {
                try {
                    //Checks if the JSON is empty
                    if (response.length() > 0) {
                        //Clears the FoodItem ArrayList
                        foodItems.clear();
                        //For every product in the JSON this iteration will execute
                        for (int i = 0; i < response.length(); i++) {
                            //Gets the current JSONObject that should contain a string and an int
                            JSONObject jsonObject = response.getJSONObject(i);
                            //Declaration of a FoodItem
                            FoodItem food = new FoodItem();
                            //Checks if it is not true that the JSONObject does not contain "productName"
                            //If it contains "productName" the following code will execute
                            if (!jsonObject.isNull("productName")) {
                                //The value that "productName" has will be put as string in food
                                food.setName(jsonObject.getString("productName"));
                                //Prints to the log saying that a product name has been added
                                Log.v("Response: ", "productName");
                            }
                            //Checks if it is not true that the JSONObject does not contain "count"
                            //If it contains "count" the following code will execute
                            if (!jsonObject.isNull("count")) {
                                //The value that "count" has will be put as int in food
                                food.setAmount(jsonObject.getInt("count"));
                                //Prints to the log saying that an amount has been added
                                Log.v("Response: ", "count");
                            }
                            //Adds the food in the FoodItem ArrayList
                            foodItems.add(i, food);
                            //Prints the current FoodItem ArrayList to the log
                            Log.v("Response: ", foodItems.toString());
                        }
                        //Tells the list that the data has changed
                        mAdapter.notifyDataSetChanged();
                    }
                }
                //If the code in 'try' fails 'catch' will execute
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },
            //If there is no response (could be because of a bad internet connection or if the
            //server is down) this will execute
            new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Sends the error to the console log
                Log.v("The error is: ", String.valueOf(error));
                //This message pops up on the app
                Toast.makeText(MainActivity.this, "Could not connect!", Toast.LENGTH_SHORT).show();
            }
        });

        //Adds the function to the RequestQueue
        requestQueue.add(jsonArrayRequest);

        //Declaration and assingment of the floating action button, used to "refresh" the data
        //and fetch the data from the server again after the app has launched
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            //This data gets executed when the user clicks on the floating action button
            public void onClick(View view) {
                //Starts the JsonArrayRequest, this is the function that connects the app to the server and
                //fetches the data from a JSON
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    //Checking if there is a response
                    public void onResponse(JSONArray response) {
                        try {
                            //Checks if the JSON is empty
                            if (response.length() > 0) {
                                //Clears the FoodItem ArrayList
                                foodItems.clear();
                                //For every product in the JSON this iteration will execute
                                for(int i = 0;i < response.length();i++){
                                    //Gets the current JSONObject that should contain a string and an int
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    //Declaration of a FoodItem
                                    FoodItem food = new FoodItem();
                                    //Checks if it is not true that the JSONObject does not contain
                                    //"productName", if it contains "productName" the following code
                                    //will execute
                                    if(!jsonObject.isNull("productName")){
                                        //The value that "productName" has will be put as string in food
                                        food.setName(jsonObject.getString("productName"));
                                        //Prints to the log saying that a product name has been added
                                        Log.v("Response: ", "productName");
                                    }
                                    //Checks if it is not true that the JSONObject does not contain "count"
                                    //If it contains "count" the following code will execute
                                    if(!jsonObject.isNull("count")){
                                        //The value that "count" has will be put as int in food
                                        food.setAmount(jsonObject.getInt("count"));
                                        //Prints to the log saying that an amount has been added
                                        Log.v("Response: ", "count");
                                    }
                                    //Adds the food in the FoodItem ArrayList
                                    foodItems.add(i, food);
                                    //Prints the current FoodItem ArrayList to the log
                                    Log.v("Response: ", foodItems.toString());
                                }
                                //Tells the list that the data has changed
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                        //If the code in 'try' fails 'catch' will execute
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                    //If there is no response (could be because of a bad internet connection or if the
                    //server is down) this will execute
                    new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Sends the error to the console log
                        Log.v("The error is: ", String.valueOf(error));
                        //This message pops up on the app
                        Toast.makeText(MainActivity.this, "Could not connect!", Toast.LENGTH_SHORT).show();
                    }
                });
                //Adds the function to the RequestQueue
                requestQueue.add(jsonArrayRequest);
            }
        });

        //
        //The following code is not relevant to the purpose and function of the app
        //It just standard android stuff
        //

        //The drawer layouts
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //The navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //Back button from the navigation drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Creation of the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //The buttons in the menu
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

    //The buttons in the Navigation Drawer
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
