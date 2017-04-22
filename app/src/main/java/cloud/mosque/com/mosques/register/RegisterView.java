package cloud.mosque.com.mosques.register;


import cloud.mosque.com.mosques.register.model.Register;

public interface RegisterView {
    void loading();
    void onSuccess(Register register);
    void onError(String message);
}
