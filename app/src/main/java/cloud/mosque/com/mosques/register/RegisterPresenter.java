package cloud.mosque.com.mosques.register;

import cloud.mosque.com.mosques.service.MosqueService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


class RegisterPresenter {

    private RegisterView view;
    private MosqueService service;
    private CompositeSubscription subscription;

    RegisterPresenter(RegisterView view, MosqueService service){
        this.view = view;
        this.service = service;
        this.subscription = new CompositeSubscription();
    }

    void sendRegister(String username, String password, String email, String fullName, String lastName){
        view.loading();

        subscription.add(service.sendRegisterMosque(username, password, email, fullName, lastName)
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
