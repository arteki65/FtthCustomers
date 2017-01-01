package pl.aptewicz.ftthcustomers.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesUtils {

	public static String getServerHttpAddressWithContext(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		String serverAddress = sharedPreferences.getString("server_address", "default");
		return "http://" + serverAddress + "/PracaInzRest";
	}

	public static boolean isDummyLogin(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences
				(context);
		return sharedPreferences.getBoolean("dummyLogin", false);
	}

	public static boolean isDummyLocation(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences
				(context);
		return sharedPreferences.getBoolean("dummyLocation", false);
	}
}
