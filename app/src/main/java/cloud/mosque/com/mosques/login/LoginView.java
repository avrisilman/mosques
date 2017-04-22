package cloud.mosque.com.mosques.login;

import cloud.mosque.com.mosques.login.model.Login;

interface LoginView {
    void loading();
    void onSuccess(Login login);
    void onError(String message);
}
