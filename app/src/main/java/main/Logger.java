package main;

import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * This class will manage the Log messages
 */
public class Logger {
    private static Logger __logger = null;
    protected static final String TAG = "[Logger]";
    private static TextView mTxtLogger;
    private static ScrollView mScrollLogger;

    /**
     * Ensure that we have only one Logger instance running
     * @param parent MainActivity
     * @return Logger
     */
    public static Logger getInstance(MainActivity parent) {
        return (__logger == null) ? new Logger(parent) : __logger;
    }

    public static Logger getInstance() {
        return (__logger == null) ? new Logger(null) : __logger;
    }

    private Logger(MainActivity parent) {
        mTxtLogger = (TextView)parent.findViewById(R.id.txtLogger);
        mScrollLogger = (ScrollView)parent.findViewById(R.id.scrLogger);
    }

    /**
     * Log the message, display it in the TextView area and in the logcat
     * @param txt
     */
    public static void Log(String txt) {
        Log.d(TAG, txt);
        mTxtLogger.append(txt + "\n");

        mScrollLogger.post(new Runnable() {
            @Override
            public void run() {
                mScrollLogger.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    /**
     * Log a error message, show it in the TextView, and in the logcat
     * @param txt
     */
    public static void LogError(String txt) {
        Log.e(TAG, txt);
        mTxtLogger.append("[ERROR] " + txt + "\n");

        mScrollLogger.post(new Runnable() {
            @Override
            public void run() {
                mScrollLogger.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}