package cloud.mosque.com.mosques.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;
import javax.inject.Inject;
import cloud.mosque.com.mosques.R;
import cloud.mosque.com.mosques.databinding.MenuActivityBinding;
import cloud.mosque.com.mosques.di.ApplicationComponent;
import cloud.mosque.com.mosques.home.adapter.MenuAdapter;
import cloud.mosque.com.mosques.home.model.DataResponse;
import cloud.mosque.com.mosques.service.MosqueService;
import cloud.mosque.com.mosques.sharedpref.AppData;

public class MenuActivity extends AppCompatActivity implements MenuView{

    MenuActivityBinding binding;

    @Inject
    MosqueService service;

    @Inject
    MenuAdapter adapter;

    MenuPresenter presenter;

    @Inject
    AppData appData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent.provideApiComponent().inject(this);
        binding = DataBindingUtil.setContentView(MenuActivity.this, R.layout.menu_activity);
        presenter = new MenuPresenter(this, service);
        binding.setPresenter(presenter);
       // appData = new AppData(this);

        presenter.getMosques(appData.getToken());

    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, "error data...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(DataResponse response) {
        adapter.setMainMenu(response.data);
       // binding.recyclerView.setLayoutManager(new GridLayoutManager(MenuActivity.this, 2));
        binding.recyclerView.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerView.setAdapter(adapter);
    }
}
