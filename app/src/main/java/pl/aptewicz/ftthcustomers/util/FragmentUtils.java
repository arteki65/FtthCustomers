package pl.aptewicz.ftthcustomers.util;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.io.Serializable;

public class FragmentUtils {

    public static void passSerializableArg(Fragment fragment, String key, Serializable arg) {
        Bundle args = new Bundle();
        args.putSerializable(key, arg);
        fragment.setArguments(args);
    }
}
