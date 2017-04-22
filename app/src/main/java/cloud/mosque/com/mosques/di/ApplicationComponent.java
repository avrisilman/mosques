package cloud.mosque.com.mosques.di;

import android.app.Application;

import cloud.mosque.com.mosques.di.component.ApiComponent;
import cloud.mosque.com.mosques.di.component.DaggerApiComponent;
import cloud.mosque.com.mosques.di.module.ApiModule;

/**
 * Created by domikado on 4/1/17.
 */

public class ApplicationComponent extends Application {

    private static ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule(this))
                .build();

    }

    public static ApiComponent provideApiComponent(){
        return apiComponent;
    }

}
