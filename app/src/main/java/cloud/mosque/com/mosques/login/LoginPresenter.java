package cloud.mosque.com.mosques.login;

import cloud.mosque.com.mosques.service.MosqueService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

class LoginPresenter{

    private LoginView view;
    private MosqueService service;
    private CompositeSubscription subscription;

    LoginPresenter(LoginView view, MosqueService service){
        this.view = view;
        this.service = service;
        this.subscription = new CompositeSubscription();
    }

    void sendLogin(String username, String password){
        view.loading();

        subscription.add(service.sendLoginMosque(username, password)
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userSuccess -> {
                            view.onSuccess(userSuccess);
                        },
                        throwable -> {
                            view.onError(throwable.getMessage());
                        }
                )
        );

    }

}
