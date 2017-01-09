package pl.aptewicz.ftthcustomers.util;

import android.os.Build;

public class ListViewUtils {

	public static int getListItemLayoutId() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
				android.R.layout.simple_list_item_activated_1 :
				android.R.layout.simple_list_item_1;
	}
}
