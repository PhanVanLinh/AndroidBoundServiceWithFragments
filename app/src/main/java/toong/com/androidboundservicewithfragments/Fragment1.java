package toong.com.androidboundservicewithfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends BaseFragment {
    private ServiceHandler serviceHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceHandler = new ServiceHandler(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_1, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        serviceHandler.bindService(new BoundServiceClient() {
            @Override
            public void doSomething(int value) {

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        serviceHandler.unbind();
    }

}