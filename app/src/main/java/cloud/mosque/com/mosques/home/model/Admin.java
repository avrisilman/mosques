package cloud.mosque.com.mosques.home.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by domikado on 4/5/17.
 */

public class Admin {

    @SerializedName("first_name")
    public String firstName;

    @SerializedName("last_name")
    public String lastName;

    @SerializedName("email")
    public Object email;

}
