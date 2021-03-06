/*******************************************************************************
 * Copyright 2014 Alexandros Schillings
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package uk.co.alt236.floatinginfo.service;

import uk.co.alt236.floatinginfo.provider.BaseProvider;
import uk.co.alt236.floatinginfo.provider.generalinfo.GeneralInfoProvider;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FloatingInfoService extends Service {

    private static boolean sIsRunning = false;
    private BaseProvider mMonitor;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMonitor = new GeneralInfoProvider(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sIsRunning = false;
        mMonitor.destroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sIsRunning = true;
        mMonitor.start();
        return Service.START_STICKY;
    }

    public static boolean isRunning() {
        return sIsRunning;
    }

}
