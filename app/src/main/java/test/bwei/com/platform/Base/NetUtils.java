package test.bwei.com.platform.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/12/11
 * Description:
 */

public class NetUtils {

        public static boolean isConnected(Context context) {

            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (null != connectivity) {

                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (null != info && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * get wifi isOpen or not
         */
        public static boolean isWifi(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (cm == null)
                return false;
            return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

        }

        /**
         * To change NetState
         */
        public static void openSetting(Activity activity) {
//        Intent intent = new Intent("/");
//        ComponentName cm = new ComponentName("com.android.settings",
//                "com.android.settings.WirelessSettings");
//        intent.setComponent(cm);
//        intent.setAction("android.intent.action.VIEW");
//        activity.startActivityForResult(intent, 0);
            Intent intent = new Intent(Settings.ACTION_SETTINGS);//系统设置界面
            activity.startActivity(intent);


        }


    }

