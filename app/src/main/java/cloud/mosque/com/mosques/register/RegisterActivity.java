package cloud.mosque.com.mosques.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import javax.inject.Inject;
import cloud.mosque.com.mosques.R;
import cloud.mosque.com.mosques.di.ApplicationComponent;
import cloud.mosque.com.mosques.home.MenuActivity;
import cloud.mosque.com.mosques.login.LoginActivity;
import cloud.mosque.com.mosques.register.model.Register;
import cloud.mosque.com.mosques.service.MosqueService;
import cloud.mosque.com.mosques.sharedpref.AppData;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    TextView txtusername, txtpassword, txtemail, txtfullName, txtlastName, txtlog;
    Button register;

    @Inject
    MosqueService service;

    @Inject
    AppData appData;

    RegisterPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        ApplicationComponent.provideApiComponent().inject(this);
        presenter = new RegisterPresenter(this, service);


        txtusername = (TextView)findViewById(R.id.txtusername);
        txtpassword = (TextView)findViewById(R.id.txtpassword);
        txtemail = (TextView)findViewById(R.id.txtemail);
        txtfullName = (TextView)findViewById(R.id.txtfullName);
        txtlastName = (TextView)findViewById(R.id.txtlastName);

        txtlog = (TextView)findViewById(R.id.txtlog);
        txtlog.setOnClickListener(this::sendLogin);

        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(this::sendReg);

    }

    private void sendReg(View view) {
        String username = txtusername.getText().toString().trim();
        String password = txtpassword.getText().toString().trim();
        String email = txtemail.getText().toString().trim();
        String fullName = txtfullName.getText().toString().trim();
        String lastName = txtlastName.getText().toString().trim();
        presenter.sendRegister(username, password, email, fullName, lastName);
    }

    private void sendLogin(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    @Override
    public void loading() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Register register) {
        appData.setToken(register.getAccess_token());
        startActivity(new Intent(RegisterActivity.this, MenuActivity.class));
        //Toast.makeText(this, "success..." + register.getAccess_token() , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show();
    }
}
