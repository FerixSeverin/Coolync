package team8.coolync.Interface;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import team8.coolync.Model.FoodItem;

/**
 * Created by media on 2015-11-29.
 */
public interface FoodService {
    @GET("/data.json")
    void getFood(Callback<List<FoodItem>> callback);
}
