package ru.techpark.districtfood;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.maps.model.LatLng;

import java.util.List;

import ru.techpark.districtfood.Bookmarks.BookmarksViewModel;
import ru.techpark.districtfood.CachingByRoom.Restaurant;
import ru.techpark.districtfood.CachingByRoom.RestaurantDao;
import ru.techpark.districtfood.CachingByRoom.RestaurantsDatabase;
import ru.techpark.districtfood.MainScreen.CardsPreview.Card;
import ru.techpark.districtfood.MainScreen.CardsPreview.CardsViewModel;
import ru.techpark.districtfood.Network.ApiRepo;

public class ApplicationModified extends Application {

    private ApiRepo mApiRepo;
    private RestaurantsDatabase mRestaurantDatabase;

    /**
     Static variables need for save state Filter and RecyclerView
     **/

    public static Bundle bundleForSaveStateRecyclerView;
    public static final Bundle bundleFilter = new Bundle();
    public static RecyclerView recyclerView;
    public static boolean filter_button_flag = false;
    public static String StringSearch;
    public static List<Restaurant> restaurantList;
    public static boolean enabled_recyclerView = true;
    public static List<Card> cardList;
    public static CardsViewModel cardsViewModel;
    public static BookmarksViewModel bookmarksViewModel;
    public static RestaurantDao restaurantDao;
    public static final Bundle bundle_for_maps = new Bundle();
    public static LatLng myLocation;

    public static Context context123;

    public static ApplicationModified Instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
        mApiRepo = new ApiRepo();
        mRestaurantDatabase = Room.databaseBuilder(Instance, RestaurantsDatabase.class, "tabs_of_restaurants")
                .build();
    }

    public RestaurantsDatabase getRestaurantDatabase() {
        return mRestaurantDatabase;
    }

    public ApiRepo getApis() {
        return mApiRepo;
    }

    public static ApplicationModified from(Context context) {
        return (ApplicationModified) context.getApplicationContext();
    }

    public static ApplicationModified getInstance(){
        return Instance;
    }
}
