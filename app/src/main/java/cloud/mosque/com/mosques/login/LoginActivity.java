package cloud.mosque.com.mosques.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.jakewharton.rxbinding.widget.RxTextView;
import javax.inject.Inject;
import cloud.mosque.com.mosques.R;
import cloud.mosque.com.mosques.di.ApplicationComponent;
import cloud.mosque.com.mosques.home.MenuActivity;
import cloud.mosque.com.mosques.login.model.Login;
import cloud.mosque.com.mosques.register.RegisterActivity;
import cloud.mosque.com.mosques.service.MosqueService;
import cloud.mosque.com.mosques.sharedpref.AppData;

public class LoginActivity extends AppCompatActivity implements LoginView{

    @Inject
    MosqueService service;

    LoginPresenter presenter;

    TextView txtusername, txtpassword;
    TextView reg;
    Button btnlogin;

    @Inject
    AppData appData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ApplicationComponent.provideApiComponent().inject(this);
        presenter = new LoginPresenter(this, service);
        //appData = new AppData(this);

        txtusername = (TextView)findViewById(R.id.txtusername);
        txtpassword = (TextView)findViewById(R.id.txtpassword);
        reg = (TextView)findViewById(R.id.reg);

        btnlogin = (Button)findViewById(R.id.login);
        btnlogin.setOnClickListener(this::sendText);
        reg.setOnClickListener(this::sendRegister);
        rxText();

    }

    private void sendRegister(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void sendText(View view) {
        String username = txtusername.getText().toString().trim();
        String password = txtpassword.getText().toString().trim();
        presenter.sendLogin(username,password);
    }

    private void rxText(){
        RxTextView.textChangeEvents(txtusername)
                .subscribe(e -> e.text().toString());

        RxTextView.textChangeEvents(txtpassword)
                .map(e -> e.text())
                .map(t -> t.length() > 6)
                .map(b -> b ? Color.BLACK : Color.RED)
                .subscribe(color -> txtpassword.setTextColor(color));
    }



    @Override
    public void loading() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Login login) {
        appData.setToken(login.getAccess_token());
        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, "error...", Toast.LENGTH_SHORT).show();
    }

}
