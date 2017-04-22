package cloud.mosque.com.mosques.di.component;

import javax.inject.Singleton;
import cloud.mosque.com.mosques.di.module.ApiModule;
import cloud.mosque.com.mosques.home.MenuActivity;
import cloud.mosque.com.mosques.home.MenuPresenter;
import cloud.mosque.com.mosques.login.LoginActivity;
import cloud.mosque.com.mosques.register.RegisterActivity;
import cloud.mosque.com.mosques.service.MosqueService;
import cloud.mosque.com.mosques.testmodel.TestActivity;
import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(LoginActivity activity);
    void inject(MenuActivity activity);
    void inject(MenuPresenter menuPresenter);
    void inject(RegisterActivity activity);
    void inject(TestActivity activity);
 /*   MosqueService api();*/
}
