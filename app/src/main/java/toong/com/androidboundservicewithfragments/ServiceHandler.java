package toong.com.androidboundservicewithfragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class ServiceHandler {
    private BoundService mService;
    private Context mContext;
    private BoundServiceClient mClient;

    public ServiceHandler(Context context) {
//        mContext = context.getApplicationContext();
        mContext = context;
    }

    ServiceConnection weatherServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(BoundService.TAG, "onServiceConnected");
            BoundService.LocalBinder binder = (BoundService.LocalBinder) service;
            mService = binder.getService();
            mService.addBoundServiceClient(mClient);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(BoundService.TAG, "onServiceDisconnected");
        }
    };

    public void bindService(BoundServiceClient client) {
        Log.i(BoundService.TAG, "bindService");
        Intent intent = new Intent(mContext, BoundService.class);
        mContext.bindService(intent, weatherServiceConnection, Context.BIND_AUTO_CREATE);
        mClient = client;
    }

    public void unbind() {
        Log.i(BoundService.TAG, "unbind");
        if (mService != null) {
            mContext.unbindService(weatherServiceConnection);
            mService.removeBoundServiceClient(mClient);
        }
    }
}