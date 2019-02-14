package students.com.movierecommender.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Kamil Gonska on lut, 2019
 */
public class SharedPrefHelper extends Application {
    private static SharedPrefHelper instance;
    private static SharedPreferences sharedPreferences;
    private Context context;

    public static SharedPrefHelper getInstance() {
        if (instance == null) instance = new SharedPrefHelper();
        return instance;
    }

    public void Initialize(Context ctx) {
        context = ctx;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getToken() {
        return sharedPreferences.getString("token", "");
    }

    public static void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static void removeToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("token");
        editor.apply();
    }

    public static Integer getIdUser() {
        return sharedPreferences.getInt("idUser", 0);
    }

    public static void saveIdUser(Integer idUser) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("idUser", idUser);
        editor.apply();
    }
}
