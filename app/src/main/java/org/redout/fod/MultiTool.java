package org.redout.fod;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by tom.cooper on 5/6/2015.
 */
public class MultiTool {
    public static void setText(TextView tv, String value) {
        tv.setText(value);
    }
    public static void setText(EditText et, String value) {
        et.setText(value);
    }
}
