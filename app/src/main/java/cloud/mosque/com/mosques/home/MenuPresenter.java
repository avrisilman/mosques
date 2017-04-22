package cloud.mosque.com.mosques.home;

import javax.inject.Inject;

import cloud.mosque.com.mosques.di.ApplicationComponent;
import cloud.mosque.com.mosques.service.MosqueService;
import cloud.mosque.com.mosques.sharedpref.AppData;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MenuPresenter {

    private MenuView view;
    private MosqueService service;
    private CompositeSubscription subscription;

    MenuPresenter(MenuView view, MosqueService service){
        this.view = view;
        this.service = service;
        this.subscription = new CompositeSubscription();
        ApplicationComponent.provideApiComponent().inject(this);
    }

    void getMosques(String token){
        subscription.add(service.getDataMosque(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            view.onSuccess(response);
                        },
                        throwable -> {
                            view.onError(throwable.getMessage());
                        }
                )
        );

    }


}
