package cloud.mosque.com.mosques.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;

import cloud.mosque.com.mosques.home.adapter.MenuAdapter;
import cloud.mosque.com.mosques.service.MosqueService;
import cloud.mosque.com.mosques.sharedpref.AppData;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    Context context;

    public ApiModule(Context context){
        this.context = context;
    }

    @Singleton
    @Provides
    Context providesContext(){
        return context;
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
               // .baseUrl("http://find-mosque.herokuapp.com/")
                .baseUrl("http://paipaichang.com/api/shanghua/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor headerInterceptor = chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("APP-Version", "1.2")
                    .header("OS", "Android")
                    .header("OS-Version", "4.1")
                    .header("Device-Id", "adasd");

            Request request = requestBuilder.build();
            return chain.proceed(request);
        };

        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(headerInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public MosqueService provideApiService(Retrofit retrofit){
        return retrofit.create(MosqueService.class);
    }

    @Provides
    @Singleton
    public MenuAdapter providesMenuAdapter() {
        return new MenuAdapter();
    }

    @Provides
    @Singleton
    public AppData providesAppData(Context context){
        return new AppData(context);
    }

}
