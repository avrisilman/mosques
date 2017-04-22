package cloud.mosque.com.mosques.testmodel;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import cloud.mosque.com.mosques.R;
import cloud.mosque.com.mosques.di.ApplicationComponent;
import cloud.mosque.com.mosques.service.MosqueService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by domikado on 4/18/17.
 */

public class TestActivity extends AppCompatActivity{

    @Inject
    MosqueService service;

    CompositeSubscription subscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tes_activity);
        ApplicationComponent.provideApiComponent().inject(this);
        subscription = new CompositeSubscription();
        getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription != null){
            subscription.unsubscribe();
        }
    }

    void getData(){
        subscription.add(service.getDataDetail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Example>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(TestActivity.this, "ERRORR", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Example example) {
                        Toast.makeText(TestActivity.this, "next" + example.detail, Toast.LENGTH_LONG).show();
                        Log.d("TES ===" , String.valueOf(example.detail.get(0).outletName));
                    }
                })
        );
    }


}
