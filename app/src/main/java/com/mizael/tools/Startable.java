package com.mizael.tools;

import android.app.Activity;

public interface Startable {
   public void startOtherActivity(Activity activity, Class<?> cls);
}
