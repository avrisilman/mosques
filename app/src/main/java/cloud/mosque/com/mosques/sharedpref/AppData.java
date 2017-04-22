package cloud.mosque.com.mosques.sharedpref;

import android.content.Context;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.Preference;
import com.f2prateek.rx.preferences2.RxSharedPreferences;

@SuppressWarnings({"ConstantConditions","unused"})
public class AppData {

    private RxSharedPreferences rxPreferences;
    private Preference<String> token;

    public AppData(Context context){
        rxPreferences = RxSharedPreferences.create(PreferenceManager.getDefaultSharedPreferences(context));
        saveString();
    }

    private void saveString(){
        token = rxPreferences.getString("token");
    }


    public void setToken(String value){
        token.set(value);
    }
    public String getToken(){
        return token.get();
    }

}

